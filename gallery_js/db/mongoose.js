const mongoose = require('mongoose');
// Connection URL
const url = 'mongodb://localhost:27017/';
const dbName = 'galleryDB';

mongoose.connect(url + dbName, err => {
    if (err) throw err;
    console.log('Connected to database')
});