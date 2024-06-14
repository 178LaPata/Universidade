var http = require('http');

http.createServer(function (req, res) {
    res.writeHead(200, {'Content-Type': 'text/html; charset=utf-8'})
    res.write('<h1>Hello Wordl!</h1>');
    res.write('<p>Este é um servidor web em Node.js</p>');
    res.end();
}).listen(7777);

