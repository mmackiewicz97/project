const express = require('express')
const router = express.Router();
var gallery_controller = require('../controllers/galleryController')
var { authenticate, loggedUser, authRole } = require('../middleware/authenticate')

router.get('/', loggedUser, gallery_controller.index);

router.get('/id/:galleryId', loggedUser, gallery_controller.gallery_detail);

router.get('/update/:galleryId', authenticate, gallery_controller.gallery_update_get);

router.post('/update/:galleryId', authenticate, gallery_controller.gallery_update_post);

router.get('/list/:galleryId', loggedUser, gallery_controller.gallery_list);

router.get('/create', authenticate, gallery_controller.gallery_create_get);

router.post('/create', authenticate, gallery_controller.gallery_create_post);

router.post('/delete', authenticate, gallery_controller.gallery_delete);

module.exports = router;