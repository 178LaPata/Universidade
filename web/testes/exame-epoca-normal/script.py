import json

nome_arquivo = "plantas.json"

mapa_chaves = {
    "Id": "_id",
    "Número de Registo": "Nr_Registo",
    "Código de rua": "Cod_Rua",
    "Espécie": "Especie",
    "Nome Científico": "Nome_Cientifico",
    "Data de Plantação": "Data_Plantacao",
    "Implantação": "Implantacao",
    "Data de actualização": "Data_Atualizacao",
    "Número de intervenções": "Nr_Intervencoes"
}

with open(nome_arquivo, "r") as file:
    data = json.load(file)

for item in data:
    for chave_antiga, chave_nova in mapa_chaves.items():
        if chave_antiga in item:
            item[chave_nova] = item.pop(chave_antiga)

json_string = json.dumps(data, indent=2, ensure_ascii=False)  
with open(nome_arquivo, "w", encoding='utf-8') as file: 
    file.write(json_string)