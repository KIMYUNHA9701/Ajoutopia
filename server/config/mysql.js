var mysql = require('mysql');

var connection = mysql.createConnection({
    host : '172.31.43.67',
    port : 22,
    user : 'ubuntu',
    password : 'ajoutopia',
    database : 'ajoutopia',
    multipleStatements: true
})

module.exports = connection;