var data = [];

module.exports = {
    insertAll: (shortcuts) => data = shortcuts,
    findById: (id) => data.find(d => d['id'] == id)
}