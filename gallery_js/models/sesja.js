const mongoose = require('mongoose')
const SesjaSchema = new mongoose.Schema({
    Token: {
        type: String,
        required: true,
    },
    Waznosc: {
        type: Date,
        required: true,
    }
})

const Sesja = mongoose.model('Sesja', SesjaSchema)

module.exports = Sesja