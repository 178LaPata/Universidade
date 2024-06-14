var express = require('express');
var router = express.Router();
var Aluno = require('../controllers/aluno')

router.get('/alunos', function(req, res) {
  Aluno.list()
    .then(dados => res.status(200).jsonp(dados))
    .catch(erro => res.status(521).jsonp(erro))
});

router.get('/alunos/:id', function(req, res) {
  Aluno.findById(req.params.id)
    .then(dados => res.status(200).jsonp(dados))
    .catch(erro => res.status(522).jsonp(erro))
});

router.post('/alunos', function(req, res) {
  Aluno.insert(req.body)
    .then(dados => res.status(201).jsonp(dados))
    .catch(erro => res.status(523).jsonp(erro))
});

router.put('/alunos/:id', function(req, res) {
  Aluno.updateAluno(req.params.id, req.body)
    .then(dados => res.status(200).jsonp(dados))
    .catch(erro => res.status(524).jsonp(erro))
});

module.exports = router;
