const mongoose = require('mongoose')
const AdministratorSchema = new mongoose.Schema({
    Prawa: {
        type: Boolean,
    }
})

const Administrator = mongoose.model('Administrator', AdministratorSchema)

module.exports = Administrator