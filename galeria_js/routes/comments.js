const express = require('express')
const router = express.Router();

var comment_controller = require('../controllers/commentController')
var { authenticate, loggedUser, authRole } = require('../middleware/authenticate')

router.get('/', authenticate, authRole("ADMIN"), comment_controller.index);

router.post('/add/:id', loggedUser, comment_controller.add);

router.post('/delete', authenticate, authRole("ADMIN"), comment_controller.delete_post);

module.exports = router;