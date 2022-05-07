var data = [];

module.exports = {
    insertAll: (shortcuts) => data = shortcuts,
    findById: (id) => data[id]
}