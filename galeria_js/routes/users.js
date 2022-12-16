const express = require('express')
var { authenticate, loggedUser, authRole } = require('../middleware/authenticate')

const router = express.Router();
var token = function() {
    return Math.random().toString(36).substr(2);
};

var user_controller = require('../controllers/userController')

router.get('/', authenticate, authRole("ADMIN"), user_controller.index);

router.post('/login', user_controller.login_post);

router.get('/register', user_controller.user_register_get);

router.post('/register', user_controller.user_register_post);

router.post('/update/:userId', authenticate, authRole("ADMIN"), user_controller.user_update);

router.get('/logout', loggedUser, user_controller.user_logout);

router.post('/delete', authenticate, authRole("ADMIN"), user_controller.user_delete);

module.exports = router;