ciekawe
# WebDev21_22
Projekt Galeria

install mongoDB

sudo systemctl start mongod
sudo systemctl status mongod
mongosh
use galleryDB

By zainstalować w folderze projektu wykonaj:

npm install .
npm start

Stwórz bazę danych lub zmień parametry w pliku db/mongoose.js

const url = 'mongodb://localhost:27017/';
const dbName = 'galleryDB';

