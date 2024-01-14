const jwt = require('jsonwebtoken')
const User = require('../models/uzytkownik')
const alert = require('alert')
const authenticate = (req, res, next) => {
    try {
        if (typeof localStorage === "undefined" || localStorage === null) {
            var LocalStorage = require('node-localstorage').LocalStorage;
            localStorage = new LocalStorage('./scratch');
        }
        const token = localStorage.getItem('token')
        const decode = jwt.verify(token, 'kodSzyfrujacy')
        req.user = decode
        next()
    } catch (err) {
        alert("Brak dostępu, zaloguj się!")
    }
}
const loggedUser = (req, res, next) => {
    try {
        if (typeof localStorage === "undefined" || localStorage === null) {
            var LocalStorage = require('node-localstorage').LocalStorage;
            localStorage = new LocalStorage('./scratch');
        }
        const token = localStorage.getItem('token')
        const decode = jwt.verify(token, 'kodSzyfrujacy')
        req.user = decode
        next()
    } catch (err) {
        next()
    }
}
const authRole = (role) => {
    return (req, res, next) => {
        User.findOne({ Login: { $eq: req.user.login } }).then(user => {
            if (role !== user.Role) {
                return alert("Brak wymaganych uprawnień!")
            }
            next()
        })


    }
}
module.exports = { authenticate, loggedUser, authRole }