var express = require('express');
var router = express.Router();
var Emd = require('../controllers/emd');

router.get('/emd', function(req, res) {
    if(req.query.res){
        if(req.query.res == "OK" ){
            Emd.resultadosTrue()
                .then(data => res.jsonp(data))
                .catch(erro => res.jsonp(erro))
            return;
        }
    } else if (req.query.modalidade) {
        const mod = req.query.modalidade;
        Emd.findByModalidade(mod)
            .then(data => res.jsonp(data))
            .catch(erro => res.jsonp(erro))
    } else {
        Emd.list()
            .then(data => res.jsonp(data))
            .catch(erro => res.jsonp(erro))
    }
});

router.get('/emd/:id', function(req, res) {
    Emd.findById(req.params.id)
        .then(data => res.jsonp(data))
        .catch(erro => res.jsonp(erro))
});

router.get('/modalidades', function(req, res) {
    Emd.modalidades()
        .then(data => res.jsonp(data))
        .catch(erro => res.jsonp(erro))
});

router.get('/atletas', function(req, res) {
    if(req.query.genero){
        Emd.findByGenero(req.query.genero)
            .then(data => res.jsonp(data))
            .catch(erro => res.jsonp(erro))
    } else if(req.query.clube){
        Emd.findByClube(req.query.clube)
            .then(data => res.jsonp(data))
            .catch(erro => res.jsonp(erro))
    }
});

module.exports = router;
