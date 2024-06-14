const Emd = require('../models/emd');

module.exports.list = async () => {
    const res = Emd.find({}, { _id: 1, dataEMD : 1, nome : 1, resultado : 1 }).exec();
    return res;
}

module.exports.findById = async (id) => {
    const res = Emd.findOne({ _id: id }).exec();
    return res;
}

module.exports.modalidades = async () => {
    const res = Emd.distinct("modalidade").exec();
    return res;
}

module.exports.resultadosTrue = async () => {
    const res = Emd.find({"resultado": true }).exec();
    return res;
}

module.exports.findByModalidade = async (modalidade) => {
    const res = Emd.find({ "modalidade": modalidade }).exec();
    return res;
}

module.exports.findByGenero = genero => {
    const res = Emd.find({ "género": genero }, {nome: 1}).sort({nome: 1}).exec();
    return res;
}

module.exports.findByClube = clube => {
    const res = Emd.find({ "clube": clube }, {nome: 1}).sort({nome: 1}).exec();
    return res;
}