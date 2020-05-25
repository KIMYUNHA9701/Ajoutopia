var mysql = require('mysql');

var connection = mysql.createConnection({
    host : '13.125.251.197',
    port : 22,
    user : 'root',
    password : 'mysql0113!',
    database : 'ajoutopia',
    multipleStatements: true
})

module.exports = connection;