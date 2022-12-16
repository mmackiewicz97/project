const express = require('express')
const router = express.Router();

var picture_controller = require('../controllers/pictureController')

var { authenticate, loggedUser, authRole } = require('../middleware/authenticate')

router.get('/', loggedUser, picture_controller.index);

router.get('/id/:pictureId', loggedUser, picture_controller.picture_detail);

router.get('/update/:pictureId', authenticate, picture_controller.picture_update_get);

router.post('/update/:pictureId', authenticate, picture_controller.picture_update_post);

router.get('/create', authenticate, picture_controller.picture_create_get);

router.post('/create', authenticate, picture_controller.picture_create_post);

router.post('/delete', authenticate, picture_controller.picture_delete_post);

module.exports = router;