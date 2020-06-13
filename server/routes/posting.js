var express = require('express');
var router = express.Router();

var mysqlDB = require('../config/mysql');

// /users

/* GET users listing. /posting */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});

/* 내 qna 게시물  */
router.post('/mine/qna', function(req, res, next) {
  var sql = 'select title from qna, study where qna id = ?';

    mysqlDB.query(sql, [req.body.id], function(error, major) {
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

/* 내 study 게시물  */
router.post('/mine/study', function(req, res, next) {
  var sql = 'select title from study where id = ?';

    mysqlDB.query(sql, [req.body.id], function(error, major) {
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

/* 내 qna 게시물 보기 */
 router.post('/mine/qna/posting', function(req, res, next) {
   var sql = 'select nickname, title, posting, metoobtn from qna where id = ? and title = ?';

     mysqlDB.query(sql, [req.body.id, req.body.title], function(error, major) {
         if(error == null) {
             console.log(major);
             res.json({
                 "code" : 200,
                 "result" : study
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

/* 내 study 게시물 보기 */
  router.post('/mine/study/posting', function(req, res, next) {
    var sql = 'select nickname, title, posting, likebtn, scrabbtn from study where id = ? and title = ?';

      mysqlDB.query(sql, [req.body.id, req.body.title], function(error, major) {
          if(error == null) {
              console.log(major);
              res.json({
                  "code" : 200,
                  "result" : study
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

/* qna 게시물 */
router.post('/qna', function(req, res, next) {
  var sql = 'select title from qna';

    mysqlDB.query(sql, [], function(error, major) {
        if(error == null) {
            console.log(major);
            res.json({
                "code" : 200,
                "result" : qna
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

/* qna 게시물 보기 */
 router.post('/qna/posting', function(req, res, next) {
   var sql = 'select nickname, title, posting, metoobtn from qna where title = ?';

     mysqlDB.query(sql, [req.body.title], function(error, major) {
         if(error == null) {
             console.log(major);
             res.json({
                 "code" : 200,
                 "result" : study
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

/* study 게시물 */
router.post('/study', function(req, res, next) {
     var sql = 'select title from study';

       mysqlDB.query(sql, [], function(error, major) {
           if(error == null) {
               console.log(major);
               res.json({
                   "code" : 200,
                   "result" : study
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

/* study 게시물보기 */
 router.post('/study/posting', function(req, res, next) {
   var sql = 'select nickname, title, posting, likebtn, scrabbtn from study where title = ?';

     mysqlDB.query(sql, [req.body.title], function(error, major) {
         if(error == null) {
             console.log(major);
             res.json({
                 "code" : 200,
                 "result" : study
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
