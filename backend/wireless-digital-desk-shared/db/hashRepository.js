const knex = require('knex')({
    client: 'pg',
    debug: false,
    connection: {
        connectionString: process.env.DATABASE_URL,
        ssl: { rejectUnauthorized: false },
    }
});

module.exports = {
    findAll: () => knex.select('*').from('hashes'),
    findByHash: (hash) => knex.select('hash_user').from('hashes').where('unique_hash', '=', hash),
}