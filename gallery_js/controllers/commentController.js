var Comment = require('../models/komentarz')

exports.index = function (req, res) {
    Comment.find().then(function (comments) {
        res.render('comments', { title: 'Komentarze', items: comments, user: req.user });
    });
}

exports.add = function (req, res) {
    const comment = new Comment(
        {
            Tresc: req.body.Tresc,
            Data: new Date(),
            User: req.body.User,
            Zdjecie_ID: req.body.Zdjecie_ID,
            rating: req.body.rating,
            stars: "☆".repeat(req.body.rating)
        })
    comment.save().then(() => {
        console.log(comment.rating)
        res.redirect('/pictures/id/' + comment.Zdjecie_ID)
    }).catch(err => {
        console.log(err)
    })
}
exports.delete_post = function (req, res) {
    const id = req.body.id
    Comment.findByIdAndDelete(id,
        function (err, docs) {
            if (err) {
                console.log(err)
            } else {
                res.redirect('/comments')
            }
        })
};