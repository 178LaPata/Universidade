const aluno = require('../models/aluno')
const Aluno = require('../models/aluno')

module.exports.list = () => {
    return Aluno
        .find()
        .sort({nome: 1})
        .exec()
}

module.exports.findById = id => {
    return Aluno
        .findOne({_id: id})
        .exec()
}

module.exports.insert = aluno => {
    return Aluno.create(aluno)
}

module.exports.updateAluno = id, aluno => {
    return Aluno.updateOne({_id: id}, aluno)
}