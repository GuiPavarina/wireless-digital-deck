const db = require('./configuration');

module.exports = {
    findAll: () => db.select('*').from('hashes'),
    findByHash: (hash) => db.select('hash_user').from('hashes').where('unique_hash', '=', hash),
}