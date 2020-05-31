var mysql = require('mysql');

var connection = mysql.createConnection({
    host : '172.31.44.244',
    port : 3306,
    user : 'root',
    password : 'mysql0113!',
    database : 'ajoutopia',
    multipleStatements: true
})

module.exports = connection;