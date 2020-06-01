var mysql = require('mysql');

var connection = mysql.createConnection({
      host : '127.0.0.1',
      port : 3306,
      user : 'root',
      password : 'ajoutopia',
      database : 'Ajoutopia',
      multipleStatements: true
  })

module.exports = connection;