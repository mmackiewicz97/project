const mongoose = require('mongoose')
const KomentarzSchema = new mongoose.Schema({
    Tresc: {
        type: String,
    },
    Data: {
        type: Date,
    },
    User: {
        type: String,
    },
    Zdjecie_ID: {
        type: String,
    },
    rating: {
        type: String,
    },
    stars: {
        type: String,
    }
})

const Komentarz = mongoose.model('Komentarz', KomentarzSchema)

module.exports = Komentarz