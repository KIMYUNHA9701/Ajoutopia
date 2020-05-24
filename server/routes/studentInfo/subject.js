var express = require('express');
var router = express.Router();

var mysqlDB = require('../../config/mysql-db');


// /subject

// GET studentInfo listing./subject
router.get('/', function(req, res, next) {
    res.render('subject');
});

// /studentInfo/subject/majorlist
/* 전공 클릭시 학과 리스트 가져오기 */
router.get('/majorlist', function(req, res, next) {
    var sql = 'select distinct major from Graduation_requirement;';

    mysqlDB.query(sql, [], function(error, major) {
        if(error == null) {
            console.log(major);
            res.json({
                "code" : 200,
                "result" : major
            });
        }
        else{
            console.log(error);
            res.json({
                "code" : 400,
                "result" : "failed"
            });
        }
    });

});


module.exports = router;
