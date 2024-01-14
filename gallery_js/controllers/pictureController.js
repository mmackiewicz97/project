var Obraz = require('../models/obraz')
var Komentarz = require('../models/komentarz')
var Tag = require('../models/tag')
var Galeria = require('../models/galeria')
const { body, validationResult } = require('express-validator');

exports.index = function (req, res) {

    Obraz.find().then(function (pictures) {
        for (let i = 0; i < pictures.length; i++) {
            Komentarz.find({ Zdjecie_ID: pictures[i].id }).then(function (comments) {
                let rating = 0
                for (let j = 0; j < comments.length; j++) {
                    rating += parseInt(comments[j].rating)
                }
                pictures[i].numOfComment = comments.length
                if (rating != 0) {
                    pictures[i].rating = rating / comments.length
                } else {
                    pictures[i].rating = 0
                }
            })
        }
        res.render('pictures', { title: 'Zdjęcia', items: pictures, user: req.user });
    });
}

exports.picture_detail = function (req, res) {
    var id = req.params.pictureId
    Obraz.findOne({ _id: id }).then(function (picture) {
        Komentarz.find({ Zdjecie_ID: id }).then(function (comments) {
            Galeria.findOne({ _id: picture.Galeria_ID }).then(function (gallery) {
                res.render('showPic', { item: picture, comments: comments, gallery: gallery, user: req.user });
            })
        })
    })
};

exports.picture_create_get = function (req, res) {
    Tag.find().then(function (tags) {
        Galeria.find().then(function (galleries) {
            res.render('createPic', { tags: tags, galleries: galleries, user: req.user })
        })
    })
}
exports.picture_create_post = [
    body('Tytul').trim().isLength({ min: 1 }).withMessage('Tytuł nie może być pusty!')
        .isAlpha().withMessage('Tytuł musi składać się tylko z liter!'),
    body('Opis').trim().isLength({ min: 1 }).withMessage('Opis nie może być pusty!')
        .isAlpha().withMessage('Opis musi składać się tylko z liter!'),
    body('Data').isISO8601().toDate().withMessage('Nieprawidłowa data!'),
    body('NazwaPliku').trim().isLength({ min: 1 }).withMessage('Nazwa pliku nie może być pusta!'),
    body('Sciezka').trim().isLength({ min: 1 }).withMessage('Ścieżka nie może być pusta!'),
    body('Rozmiar').isFloat({ min: 1 }).withMessage('Rozmiar musi być większy od 0!'),

    (req, res, next) => {
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            // There are errors. Render the form again with sanitized values/error messages.
            Tag.find().then(function (tags) {
                Galeria.find().then(function (galleries) {
                    res.render('createPic', { tags: tags, galleries: galleries, user: req.user, errors: errors.array() });
                    return;
                })
            })
        }
        else {
            let widocznosc = req.body.Widocznosc ? "true" : "false"
            var obraz = new Obraz({
                Tytul: req.body.Tytul,
                Opis: req.body.Opis,
                Data: req.body.Data,
                NazwaPliku: req.body.NazwaPliku,
                Sciezka: req.body.Sciezka,
                Rozmiar: req.body.Rozmiar,
                Widocznosc: widocznosc,
                Galeria_ID: req.body.Galeria_ID,
                Tagi: req.body.Tagi
            });
            obraz.save().then(function (obraz) {
                Galeria.findOne({ _id: obraz.Galeria_ID }).then(function (gallery) {

                    res.render('showPic', { item: obraz, gallery: gallery, user: req.user })
                })
            }).catch(err => {
                console.log(err)
            })
        }
    }];
exports.picture_update_get = function (req, res) {
    const id = req.params.pictureId
    const picture = Obraz.findOne({ _id: id }).then(function (picture) {
        Tag.find().then(function (tags) {
            Galeria.find().then(function (galleries) {
                res.render('editPic', { item: picture, id: id, data: picture.Data.toISOString().split('T')[0], tags: tags, galleries: galleries, user: req.user })
            })
        })
    })
}
exports.picture_update_post = function (req, res) {
    let widocznosc = req.body.Widocznosc ? "true" : "false"
    const picture = Obraz.updateOne({ _id: req.params.pictureId },
        {
            $set: {
                Tytul: req.body.Tytul,
                Opis: req.body.Opis,
                Data: req.body.Data,
                NazwaPliku: req.body.NazwaPliku,
                Sciezka: req.body.Sciezka,
                Rozmiar: req.body.Rozmiar,
                Widocznosc: widocznosc,
                Galeria_ID: req.body.Galeria_ID,
                Tagi: req.body.Tagi
            }
        }).then(() => {
            res.redirect('/pictures/')
        }).catch(err => {
            console.log(err)
        })
};

exports.picture_delete_post = function (req, res) {
    const id = req.body.id
    Obraz.findByIdAndDelete(id,
        function (err, docs) {
            if (err) {
                console.log(err)
            } else {
                res.redirect('/pictures')
            }
        })
};
