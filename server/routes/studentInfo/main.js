var express = require('express');
var router = express.Router();

var mysqlDB = require('../../config/mysql');


/* GET users listing. /main */
router.get('/', function(req, res, next) {
    res.render('main');
});

router.post('/subject, function(req, res, next) {
    var num = req.body.num.substring(0,4);
    var sql = 'select distinct required_credit_major from Graduation_requirement where major = ? And admission_num=?;';
    mysqlDB.query(sql, [req.body.major, num],function(error, result) {
        if(error == null) {
            console.log(result);
            res.json({
                "code" : 200,
                "result" : result
            });
        }
        else{
            console.log(error);
            res.json({
                "code" : 400,
                "result" : "failed"
            });
        }
    })
});

module.exports = router;
