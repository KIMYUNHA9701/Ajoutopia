const http = require('http')
  const app = require('./app')

  http.createServer(app).listen(22,() => {
      console.log(`Backend Server is running on 22..`)
  })