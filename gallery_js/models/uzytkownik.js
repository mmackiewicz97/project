const mongoose = require('mongoose')
const UzytkownikSchema = new mongoose.Schema({
    Imie: {
        type: String,
    },
    Nazwisko: {
        type: String,
    },
    Login: {
        type: String,
    },
    Haslo: {
        type: String,
    },
    Email: {
        type: String,
    },
    Role: {
        type: String,
    }
})

const Uzytkownik = mongoose.model('Uzytkownik', UzytkownikSchema)

module.exports = Uzytkownik