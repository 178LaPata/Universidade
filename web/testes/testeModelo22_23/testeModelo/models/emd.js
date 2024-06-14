const mongoose = require('mongoose');

const emdSchema = new mongoose.Schema({ 
    _id : String,
    index : Number,
    dataEMD : String,
    nome : {
        primeiro : String,
        ultimo : String
    },
    idade : Number,
    género : String,
    morada : String,
    modalidade : String,
    clube : String,
    email : String,
    federado : Boolean,
    resultado : Boolean
}, { versionKey: false });

module.exports = mongoose.model('exames', emdSchema);