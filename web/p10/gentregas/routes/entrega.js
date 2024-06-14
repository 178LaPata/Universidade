/* Operações CRUD sobre Entrega 
    2024-04-21 @jcr
    ----------------------- */
var express = require('express');
var router = express.Router();
var Entrega = require('../controllers/entrega')
var Projeto = require('../controllers/projeto')
var multer = require('multer')
var fs = require('fs')
var jszip = require('jszip')
var xml2js = require('xml2js');
const { promiseHooks } = require('v8');

var upload = multer({dest: 'uploads'})

/* Listar as Entrega (R) */
router.get('/', function(req, res) {
  if(req.query.projeto) {
    Entrega.findByProjeto(req.query.projeto)
        .then(data => res.jsonp(data))
        .catch(erro => res.jsonp(erro))
  } else { 
    Entrega.list()
        .then(data => res.jsonp(data))
        .catch(erro => res.jsonp(erro))
  }
});

/* Consultar uma Entrega (R) */
router.get('/:id', function(req, res) {
    Entrega.findById(req.params.id)
      .then(data => res.jsonp(data))
      .catch(erro => res.jsonp(erro))
  });

/* Criar uma Entrega (C) */
router.post('/', upload.single("submissao"), function(req, res) {
  oldPath = __dirname + '/../' + req.file.path
  zipData = fs.readFileSync(oldPath)
  jszip.loadAsync(zipData)
    .then(zip => {
      zip.file("PR.xml").async("string")
        .then(xmlContent => {
          parser = new xml2js.Parser()
          parser.parseString(xmlContent, (err, result) => {
            if(err) {
              res.jsonp(err)
            } else {
              var idEquipa = "" // TODO

              fs.
              Projeto.findById(result.PR.meta.key)
                .then(project => {
                  var entrega = {
                    //_id : String,
                    creationDate : new Date(),
                    uc : project.uc,
                    idProjeto : result.PR.meta.key,
                    designacao : result.PR.meta.title,
                    idEquipa : String,
                    desigEquipa : String,
                    ficheiro : String,
                    obs : String
                }
              })
              .catch(erro => res.jsonp(erro))
            }
          })
        })
    })
    .catch(erro => res.jsonp(erro))
});

/* Alterar uma Entrega (U) */
router.put('/:id', function(req, res) {
    Entrega.update(req.params.id, req.body)
      .then(data => res.jsonp(data))
      .catch(erro => res.jsonp(erro))
  });

/* Remover uma Entrega (D ) */
router.delete('/:id', function(req, res) {
    Entrega.remove(req.params.id)
      .then(console.log("Deleted " + req.params.id))
      .catch(erro => res.jsonp(erro))
  });

module.exports = router;
