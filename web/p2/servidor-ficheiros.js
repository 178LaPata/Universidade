var http = require('http');
var fs = require('fs');
var url = require('url');

http.createServer(function (req, res) {
    var regex = /^\/[123]$/
    var q = url.parse(req.url, true)
    if(regex.test(q.pathname)) {
        fs.readFile('pag' + q.pathname.substring(1) + '.html', function(erro, data) {
            res.writeHead(200, {'Content-Type': 'text/html; charset=utf-8'})
            res.write(data)
            res.end()
        })
    }
    else if(q.pathname == '/w3.css'){
        fs.readFile('w3.css', function(erro, data) {
            res.writeHead(200, {'Content-Type': 'text/css'})
            res.write(data)
            res.end()
        })
    }
    else {
        res.writeHead(400, {'Content-Type': 'text/html; charset=utf-8'})
        res.write('<p>Pedido não suportado.</p>')
        res.write('<pre>' + q.pathname + '</pre>')
        res.end()
    }
    console.log(q.pathname)
}).listen(7777)