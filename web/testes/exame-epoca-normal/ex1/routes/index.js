var express = require('express');
var router = express.Router();
var Planta = require('../controllers/planta');

router.get('/plantas', function(req, res, next) {
  if(req.query.especie){
    const esp = req.query.especie;
    Planta.findByEspecie(esp)
      .then(data => res.jsonp(data))
      .catch(erro => res.jsonp(erro))
    return;
  } else if (req.query.implant){
    const imp = req.query.implant;
    Planta.findByImplantacao(imp)
      .then(data => res.jsonp(data))
      .catch(erro => res.jsonp(erro))
    return;
  } else {
    Planta.list()
      .then(data => res.jsonp(data))
      .catch(erro => res.jsonp(erro))
  }
});

router.get('/plantas/freguesias', function(req, res, next) {
  Planta.freguesias()
    .then(data => res.jsonp(data))
    .catch(erro => res.jsonp(erro))
});

router.get('/plantas/especies', function(req, res, next) {
  Planta.especies()
    .then(data => res.jsonp(data))
    .catch(erro => res.jsonp(erro))
});

router.get('/plantas/:id', function(req, res, next) {
  Planta.findById(req.params.id)
    .then(data => res.jsonp(data))
    .catch(erro => res.jsonp(erro))
});

router.post('/plantas', function(req, res, next) {
  Planta.inserir(req.body)
    .then(data => res.jsonp(data))
    .catch(erro => res.jsonp(erro))
});

router.delete('/plantas/:id', function(req, res, next) {
  Planta.remove(req.params.id)
    .then(data => res.jsonp(data))
    .catch(erro => res.jsonp(erro))
});




module.exports = router;
