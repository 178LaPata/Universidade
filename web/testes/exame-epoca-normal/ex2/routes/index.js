var express = require('express');
var router = express.Router();
var axios = require('axios')

/* GET home page. */
router.get('/', function(req, res, next) {
  axios.get('http://localhost:15030/plantas')
    .then(dados => {
      var d = new Date().toISOString().substring(0, 16)
      res.render('index', { title: 'Lista de Plantas', 'lista': dados.data, data: d})
    })
    .catch(erro => {
      res.render('error', {error: erro})
    })
});

router.get('/:id', function(req, res, next) {
  axios.get('http://localhost:15030/plantas/' + req.params.id)
    .then(dados => {
      var d = new Date().toISOString().substring(0, 16)
      res.render('planta', { title: 'Planta ' + req.params.id, 'planta': dados.data, data: d}) 
    })
    .catch(erro => {
      res.render('error', {error: erro})
    })
});

router.get('/especies/:idEspecie', function(req, res, next) {
  axios.get('http://localhost:15030/plantas?especies=' + req.params.idEspecie)
    .then(dados => {
      var d = new Date().toISOString().substring(0, 16)
      res.render('especie', { title: 'Especies', 'especies': dados.data, data: d})
    })
    .catch(erro => {
      res.render('error', {error: erro})
    })
});

module.exports = router;
