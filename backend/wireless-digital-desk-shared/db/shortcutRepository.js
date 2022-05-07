const knex = require('knex')({
    client: 'pg',
    debug: false,
    connection: {
        connectionString: process.env.DATABASE_URL,
        ssl: { rejectUnauthorized: false },
    }
});

module.exports = {
    findAll: () => knex.select('modifiers', 'shortcut_order', 'shortcut_key', 'shortcut_name', 'shortcut_user', 'application_name').from('shortcuts'),
    findByUser: (user) => knex.select('modifiers', 'shortcut_order', 'shortcut_key', 'shortcut_name', 'application_name').from('shortcuts').where('shortcut_user', '=', user),
}