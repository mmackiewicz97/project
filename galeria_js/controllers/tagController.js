var Tag = require('../models/tag')
const { body, validationResult } = require('express-validator');

exports.index = function (req, res) {
    Tag.find().then(function (tags) {
        res.render('tags', { title: 'Tagi', items: tags, user: req.user });
    });
}

exports.tag_detail = function (req, res) {
    var id = req.params.tagId
    Tag.findOne({ _id: id }).then(function (tag) {
        res.render('showTag', { item: tag, user: req.user });
    });
};

exports.tag_list = function (req, res) {
    let tagsList

    const getTags = async () => {
        try {
            tags = await Tag.find()
            console.log(tags)
            res.send(tags);
        } catch (err) {
            console.log(err);
        }
    }
    getTags();
};

exports.tag_create_get = function (req, res) {
    res.render('createTag', { user: req.user })
}
exports.tag_create_post =
    [
        body('Tekst').trim().isLength({ min: 1 }).withMessage('Pole tekst nie może być puste!')
            .isAlpha().withMessage('Tekst musi składać się tylko z liter!'),

        (req, res, next) => {
            const errors = validationResult(req);
            if (!errors.isEmpty()) {
                // There are errors. Render the form again with sanitized values/error messages.
                res.render('createTag', { errors: errors.array(), user: req.user });
                return;
            }
            else {
                const tag = new Tag(
                    { Tekst: req.body.Tekst })
                tag.save().then(() => {
                    res.render('showTag', { item: tag, user: req.user })
                }).catch(err => {
                    console.log(err)
                })
            }
        }];
exports.tag_update_get = function (req, res) {
    const id = req.params.tagId
    const tag = Tag.findOne({ _id: id }).then(function (tag) {
        res.render('editTag', { item: tag, id: id, user: req.user })
    })
};
exports.tag_update_post = function (req, res) {
    const id = req.params.tagId
    const tag = Tag.updateOne({ _id: id },
        {
            $set: {
                Tekst: req.body.Tekst,
            }
        }).then(() => {
            res.redirect('/tags')
        }).catch(err => {
            console.log(err)
        })
};

exports.tag_delete = function (req, res) {
    const id = req.body.id
    Tag.findByIdAndDelete(id,
        function (err, docs) {
            if (err) {
                console.log(err)
            } else {
                res.redirect('/tags')
            }
        })
};
