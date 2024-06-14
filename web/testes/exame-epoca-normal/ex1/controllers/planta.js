const Planta = require('../models/planta');

module.exports.list = async () => {
    return Planta
        .find()
        .exec();
}

module.exports.findById = async id => {
    return Planta
        .findOne({ _id: id })
        .exec();
}

module.exports.findByEspecie = async especie => {
    return Planta
        .find({ Especie: especie })
        .exec();
}   

module.exports.findByImplantacao = async implantacao => {
    return Planta
        .find({ Implantacao: implantacao })
        .exec();
}

module.exports.freguesias = async () => {
    return Planta
        .distinct("Freguesia")
        .sort()
        .exec();
}

module.exports.especies = async () => {
    return Planta
        .distinct("Especie")
        .sort()
        .exec();
}

module.exports.inserir = async planta => {
    if((Planta.find({_id : planta._id}).exec()).length != 1){
        var newPlanta = new Planta(planta)
        return newPlanta.save()
    }
}

module.exports.remove = async id => {
    Planta
        .find({_id : id})
        .deleteOne()
        .exec()
}