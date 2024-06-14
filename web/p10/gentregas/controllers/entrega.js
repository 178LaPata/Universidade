const mongoose = require('mongoose')
var Entrega = require("../models/entrega")

module.exports.list = () => {
    return Entrega
        .find()
        .sort({designacao : 1})
        .exec()
}

module.exports.findById = id => {
    return Entrega
        .findOne({_id : id})
        .exec()
}

module.exports.insert = et => {
    if((Entrega.find({_id : et._id}).exec()).length != 1){
        var newEntrega = new Entrega(et)
        return newEntrega.save()
    }
}

module.exports.update = (id, et) => {
    return Entrega
        .findByIdAndUpdate(id, et, {new : true})
        .exec()
}

module.exports.remove = id => {
    Entrega
        .find({_id : id})
        .deleteOne()
        .exec()
}

module.exports.findByProjeto = idProjeto => {
    return Entrega
        .find({idProjeto : idProjeto})
        .sort({designacao : 1})
        .exec()
}

module.exports.findByUC = ucID => {
    return Entrega
        .find({uc : ucID})
        .sort({designacao : 1})
        .exec()
}

module.findByEquipa = idEquipa => {
    return Entrega
        .find({idEquipa : idEquipa})
        .sort({designacao : 1})
        .exec()
}

module.exports.countProjetos = idProjeto => {
    return Entrega
        .countDocuments({idProjeto : idProjeto})
        .exec()
}