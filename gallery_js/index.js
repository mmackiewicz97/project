const express = require('express')
const bodyParser = require('body-parser')
const app = express()
const port = 8000

const mongoose = require('./db/mongoose')

const usersRoutes = require('./routes/users.js');
const picturesRoutes = require('./routes/picture.js')
const galleryRoutes = require('./routes/gallery.js')
const tagRoutes = require('./routes/tag.js')
const commentRoutes = require('./routes/comments.js')

const Obraz = require('./models/obraz')
const Sesja = require('./models/sesja')
const Uzytkownik = require('./models/uzytkownik')
const Administrator = require('./models/administrator')
const Galeria = require('./models/galeria')
const Tag = require('./models/tag')
const Miniatura = require('./models/miniatura')
const Komentarz = require('./models/komentarz')

const { authenticate, authRole, loggedUser } = require('./middleware/authenticate')

const hbs = require('express-handlebars')
var path = require('path')
const alert = require('alert')

app.use(express.static(path.join(__dirname, 'public')))

//app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}))
app.use('/users', usersRoutes);
app.use('/pictures', picturesRoutes);
app.use('/galleries', galleryRoutes);
app.use('/tags', tagRoutes);
app.use('/comments', commentRoutes)

app.engine('hbs', hbs.engine({
    extname: 'hbs', defaultLayout: 'layout', layoutsDir: __dirname + '/views/layouts/',
    runtimeOptions: {
        allowProtoPropertiesByDefault: true,
        allowProtoMethodsByDefault: true
    }
}));
app.set('view engine', 'hbs');

var helper = hbs.create({})
helper.handlebars.registerHelper({
    eq: (v1, v2) => v1 === v2,
    ne: (v1, v2) => v1 !== v2,
    lt: (v1, v2) => v1 < v2,
    gt: (v1, v2) => v1 > v2,
    lte: (v1, v2) => v1 <= v2,
    gte: (v1, v2) => v1 >= v2,
    and() {
        return Array.prototype.every.call(arguments, Boolean);
    },
    or() {
        return Array.prototype.slice.call(arguments, 0, -1).some(Boolean);
    }
});

app.get('/', loggedUser, (req, res) => {
    Obraz.find().then(function (pictures) {
        Uzytkownik.find().then(function (users) {
            Komentarz.find().then(function (comments) {
                Galeria.find().then(function (gallery) {
                    res.render('index', {
                        title: 'Galeria', user: req.user, items: [{ nazwa: "Ilość galerii", liczba: gallery.length }, { nazwa: "Ilość zdjęć", liczba: pictures.length },
                        { nazwa: "Ilość komentarzy", liczba: comments.length }, { nazwa: "Liczba użytkowników", liczba: users.length }]
                    })

                })
            })
        })
    })
})

app.listen(port)