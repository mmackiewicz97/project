const mongoose = require('mongoose')
const TagSchema = new mongoose.Schema({
    Tekst: {
        type: String,
    },
})

const Tag = mongoose.model('Tag', TagSchema)

module.exports = Tag