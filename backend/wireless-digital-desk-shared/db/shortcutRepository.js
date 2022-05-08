const db = require('./configuration');

module.exports = {
    findAll: () => db.select('modifiers', 'shortcut_order', 'shortcut_key', 'shortcut_name', 'shortcut_user', 'application_name').from('shortcuts'),
    findByUser: (user) => db.select('modifiers', 'shortcut_order', 'shortcut_key', 'shortcut_name', 'application_name').from('shortcuts').where('shortcut_user', '=', user),
}