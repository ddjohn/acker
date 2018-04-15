#!/bin/bash

meteor mongo <<- +++
show dbs
use meteor
show collections
db.items.find()
quit()
+++

# GET
#curl -X GET http://localhost:3000/api/items/
#curl -X GET http://localhost:3000/api/items/NYMP4rkvhzWiRyJbi

# POST
#curl --data name=pia -X POST http://localhost:3000/api/items/
#curl --data name=david -X POST http://localhost:3000/api/items/
#curl --data name=david --data test=true -X POST http://localhost:3000/api/items/
#$ curl -H "X-Auth-Token: 97f0ad9e24ca5e0408a269748d7fe0a0" http://localhost:3000/collectionapi/players

#DELETE
#curl -X DELETE http://localhost:3000/api/items/NYMP4rkvhzWiRyJbi
#curl -X PATCH http://localhost:3000/api/items/NYMP4rkvhzWiRyJbi
#curl --data kalle=polis -X PUT http://localhost:3000/api/items/NYMP4rkvhzWiRyJbi

curl --data name=demo --data value=${RANDOM} -X POST http://localhost:3000/api/items/

