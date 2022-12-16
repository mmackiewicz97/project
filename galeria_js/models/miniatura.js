const mongoose = require('mongoose')
const MiniaturaSchema = new mongoose.Schema({
    Typ: {
        type: String,
    },
})

const Miniatura = mongoose.model('Miniatura', MiniaturaSchema)

module.exports = Miniatura