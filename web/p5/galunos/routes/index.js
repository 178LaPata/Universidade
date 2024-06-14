var express = require('express');
var router = express.Router();
var axios = require('axios');

/* GET home page. */
router.get('/', function(req, res) {
  var d = new Date().toISOString().substring(0, 16)
  res.render('index', { title: 'Gestão de alunos', data : d});
});

router.get('/alunos', function(req, res) {
  var d = new Date().toISOString().substring(0, 16)
  axios.get('http://localhost:3000/alunos')
  .then(resposta => {
    res.render('listaAlunos', { lista: resposta.data, data: d, title: 'Lista de alunos'});
  })
  .catch(erro => {
    res.render('error', { error: erro, message : 'Erro ao recuperar os alunos.' });
  })
});

router.get('/alunos/registo', function(req, res) {
  var d = new Date().toISOString().substring(0, 16)
  res.render('registoAluno', { title: 'Registo de aluno', data: d});
});

router.get('/alunos/:id', function(req, res) {
  var d = new Date().toISOString().substring(0, 16)
  axios.get('http://localhost:3000/alunos/' + req.params.id)
  .then(resposta => {
    res.render('aluno', { aluno: resposta.data, data: d, title: 'Consulta de aluno'});
  })
  .catch(erro => {
    res.render('error', { error: erro, message : 'Erro ao recuperar o aluno.' });
  })
});

router.post('/alunos/registo', function(req, res) {
  var d = new Date().toISOString().substring(0, 16)
  console.log(JSON.stringify(req.body))
  axios.post('http://localhost:3000/alunos', req.body)
  .then(resposta => {
    res.render('confirmRegisto', { info: req.body, title: 'Registo de aluno com sucesso', data: d});
  })
  .catch(erro => {
    res.render('error', { error: erro, message : 'Erro ao gravar um aluno novo.' });
  })
});



module.exports = router;
