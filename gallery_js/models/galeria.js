const mongoose = require('mongoose')
const GaleriaSchema = new mongoose.Schema({
    Tytul: {
        type: String,
    },
    Opis: {
        type: String,
    },
    Data: {
        type: Date,
        required: true,
    },
    Widocznosc: {
        type: Boolean,
    }
})

const Galeria = mongoose.model('Galeria', GaleriaSchema)

module.exports = Galeria