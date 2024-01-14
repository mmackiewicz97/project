var User = require('../models/uzytkownik')
const bcrypt = require('bcryptjs')
const jwt = require('jsonwebtoken')
const alert = require('alert')
const { body, validationResult } = require('express-validator');

exports.index = function (req, res) {
    User.find().then(function (users) {
        res.render('users', { users: users, user: req.user });
    })
}
exports.login_post = (req, res, next) => {
    let login = req.body.Login
    let haslo = req.body.Haslo
    User.findOne({
        Login: { $eq: login }
    })
        .then(user => {
            if (user) {
                bcrypt.compare(haslo, user.Haslo, function (err, result) {
                    if (result) {
                        let token = jwt.sign({ login: user.Login, role: user.Role }, 'kodSzyfrujacy', { expiresIn: '1h' })
                        if (typeof localStorage === "undefined" || localStorage === null) {
                            var LocalStorage = require('node-localstorage').LocalStorage;
                            localStorage = new LocalStorage('./scratch');
                        }
                        localStorage.setItem('token', token);
                        res.redirect("/")
                    } else {
                        alert("Podano złe hasło!")
                    }
                })
            } else {
                alert("Brak użytkownika w bazie!")
            }
        })
}

exports.user_register_get = function (req, res) {
    res.render('createUser')
}
exports.user_register_post = [
    body('Imie').trim().isLength({ min: 1 }).withMessage('Imię nie może być puste!')
        .isAlpha().withMessage('Imię musi składać się tylko z liter!'),
    body('Nazwisko').trim().isLength({ min: 1 }).withMessage('Nazwisko nie może być puste!')
        .isAlpha().withMessage('Nazwisko musi składać się tylko z liter!'),
    body('Login').trim().isLength({ min: 1 }).withMessage('Login nie może być pusty!'),
    body('Haslo').trim().isLength({ min: 8 }).withMessage('Hasło jest za krótkie! min. 8 znaków!'),
    body('Email').trim().isLength({ min: 1 }).withMessage('Email nie może być pusty!'),

    (req, res, next) => {
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            // There are errors. Render the form again with sanitized values/error messages.
            res.render('createUser', { errors: errors.array() });
            return;
        }
        else {


            bcrypt.hash(req.body.Haslo, 10, function (err, PasswordHash) {
                if (err) {
                    alert('Błąd funkcji haszującej')
                }
                let user = new User({
                    Imie: req.body.Imie,
                    Nazwisko: req.body.Nazwisko,
                    Login: req.body.Login,
                    Haslo: PasswordHash,
                    Email: req.body.Email,
                    Role: "USER"
                })
                user.save().then(() => {
                    res.render("showUser", { item: user, user: req.user })
                }).catch(() => {
                    alert("Błąd dodawnia użytkownika!")
                })
            })
        }
    }];
exports.user_update = function (req, res) {
    const user = User.updateOne({ _id: req.params.userId },
        {
            $set: {
                Role: req.body.Role
            }
        }).then(() => {
            res.redirect('/users')

        }).catch(err => {
            console.log(err)
        })
};
exports.user_logout = function (req, res) {
    var LocalStorage = require('node-localstorage').LocalStorage;
    localStorage = new LocalStorage('./scratch');
    localStorage.setItem('token', "");
    res.redirect('/')
}
exports.user_delete = function (req, res) {
    const id = req.body.id
    User.findByIdAndDelete(id,
        function (err, docs) {
            if (err) {
                console.log(err)
            } else {
                res.redirect('/users')
            }
        })
};

