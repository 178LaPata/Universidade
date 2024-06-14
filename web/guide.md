docker cp emd.json mongoEW:/tmp

docker exec mongoEW mongoimport -d emd -c exames /tmp/emd.json --jsonArray

docker exec -it mongoEW mongosh

show dbs

use emd

show collections

express --view=pug testeModelo

cd testeModelo

npm i

npm i mongoose --save

ver cheatsheet