const mongoose = require('mongoose');

const plantaSchema = new mongoose.Schema({
    _id : Number,
    Nr_Registo : Number,
    Cod_Rua : Number,
    Rua : String,
    Local : String,
    Freguesia : String,
    Especie : String,
    Nome_Cientifico : String,
    Origem : String,
    Data_Plantacao : String,
    Estado : String,
    Caldeira : String,
    Tutor : String,
    Implantacao : String,
    Gestor : String,
    Data_Atualizacao : String,
    Nr_Intervencoes : String,
}, { versionKey: false });

module.exports = mongoose.model('plantas', plantaSchema);


