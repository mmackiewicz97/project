const express = require('express')
const router = express.Router();
var { authenticate, loggedUser, authRole } = require('../middleware/authenticate')
var tag_controller = require('../controllers/tagController')

router.get('/', loggedUser, tag_controller.index);

router.get('/id/:tagId', loggedUser, tag_controller.tag_detail);

router.get('/update/:tagId', authenticate, tag_controller.tag_update_get);

router.post('/update/:tagId', authenticate, tag_controller.tag_update_post);

router.get('/list', loggedUser, tag_controller.tag_list);

router.get('/create', authenticate, tag_controller.tag_create_get);

router.post('/create', authenticate, tag_controller.tag_create_post);

router.post('/delete', authenticate, tag_controller.tag_delete);

module.exports = router;