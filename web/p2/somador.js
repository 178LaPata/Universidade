var http = require('http')
var url = require('url')

http.createServer(function (req, res) {
    res.writeHead(200, {'Content-Type': 'text/html'})
    var q = url.parse(req.url, true).query
    var r = parseInt(q.a, 10) + parseInt(q.b, 10)
    var txt = q.a + ' + ' + q.b + ' = ' + r
    console.log(txt)
    res.end(txt)
}).listen(7777);

// http://localhost:7777/?a=12&b=47