const mongoose = require('mongoose')
const Galeria = require('./galeria')

const ObrazSchema = new mongoose.Schema({
    Tytul: {
        type: String,
    },
    Opis: {
        type: String,
    },
    Data: {
        type: Date,
    },
    NazwaPliku: {
        type: String,
    },
    Sciezka: {
        type: String,
        default: './images'
    },
    Rozmiar: {
        type: Number,
        max:10000
    },
    Widocznosc: {
        type: Boolean,
    },
    Galeria_ID: {
        type: mongoose.Schema.Types.ObjectID,
        ref: Galeria
    },
    Tagi: []
})

const Obraz = mongoose.model('Obraz', ObrazSchema)

module.exports = Obraz