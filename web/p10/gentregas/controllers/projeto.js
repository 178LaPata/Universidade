const mongoose = require('mongoose')
var Projeto = require("../models/projeto")

module.exports.list = () => {
    return Projeto
        .find()
        .sort({designacao : 1})
        .exec()
}

module.exports.findById = id => {
    return Projeto
        .findOne({_id : id})
        .exec()
}

module.exports.insert = pj => {
    if((Projeto.find({_id : pj._id}).exec()).length != 1){
        var newProjeto = new Projeto(pj)
        return newProjeto.save()
    }
}

module.exports.update = (id, pj) => {
    return Projeto
        .findByIdAndUpdate(id, pj, {new : true})
        .exec()
}

module.exports.remove = id => {
    Projeto
        .find({_id : id})
        .deleteOne()
        .exec()
}

module.exports.findByUC = uc => {
    return Projeto
        .find({uc : uc})
        .sort({designacao : 1})
        .exec()
}