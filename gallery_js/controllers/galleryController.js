var Gallery = require('../models/galeria')
var Obraz = require('../models/obraz')
const { body, validationResult } = require('express-validator');

exports.index = function (req, res) {
    Gallery.find().then(function (galleries) {
        res.render('galleries', { title: 'Galerie', items: galleries, user: req.user });
    });
}

exports.gallery_detail = function (req, res) {
    var id = req.params.galleryId
    Gallery.findOne({ _id: id }).then(function (gallery) {
        res.render('showGallery', { item: gallery, user: req.user });
    });
};

exports.gallery_list = function (req, res) {
    var id = req.params.galleryId
    Gallery.findOne({ _id: id }).then(function (gallery) {
        Obraz.find({ Galeria_ID: id }).then(function (pictures) {
            res.render('pictures', { title: gallery.Tytul, items: pictures, user: req.user });
        })
    })
};

exports.gallery_create_get = function (req, res) {
    res.render('createGallery', { user: req.user })
}
exports.gallery_create_post =
    [
        body('Tytul').trim().isLength({ min: 1 }).withMessage('Tytuł nie może być pusty!')
            .isAlpha().withMessage('Tytuł musi składać się tylko z liter!'),
        body('Opis').trim().isLength({ min: 1 }).withMessage('Opis nie może być pusty!')
            .isAlpha().withMessage('Opis musi składać się tylko z liter!'),
        body('Data').isISO8601().toDate().withMessage('Nieprawidłowa data!'),

        (req, res, next) => {
            const errors = validationResult(req);
            if (!errors.isEmpty()) {
                // There are errors. Render the form again with sanitized values/error messages.
                res.render('createGallery', { errors: errors.array(), user: req.user });
                return;
            }
            else {
                let widocznosc = req.body.Widocznosc ? "true" : "false"
                const gallery = new Gallery(
                    {
                        Tytul: req.body.Tytul,
                        Opis: req.body.Opis,
                        Data: req.body.Data,
                        Widocznosc: widocznosc,
                    })
                gallery.save().then(() => {
                    res.render('showGallery', { item: gallery, user: req.user })
                }).catch(err => {
                    console.log(err)
                })
            }
        }];
exports.gallery_update_get = function (req, res) {
    const id = req.params.galleryId
    const gallery = Gallery.findOne({ _id: id }).then(function (gallery) {
        res.render('editGallery', { item: gallery, id: id, data: gallery.Data.toISOString().split('T')[0], user: req.user })
    })
}
exports.gallery_update_post = function (req, res) {
    let widocznosc = req.body.Widocznosc ? "true" : "false"
    const gallery = Gallery.updateOne({ _id: req.params.galleryId },
        {
            $set: {
                Tytul: req.body.Tytul,
                Opis: req.body.Opis,
                Data: req.body.Data,
                Widocznosc: widocznosc,
            }
        }).then(() => {
            res.redirect('/galleries')
        }).catch(err => {
            console.log(err)
        })
};

exports.gallery_delete = function (req, res) {
    const id = req.body.id
    Gallery.findByIdAndDelete(id,
        function (err, docs) {
            if (err) {
                console.log(err)
            } else {
                res.redirect('/galleries')
            }
        })
};
