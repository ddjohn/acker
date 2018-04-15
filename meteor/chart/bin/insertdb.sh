#!/bin/bash

NAME=$1
VALUE=$2

echo curl --data name=${NAME} --data value=${VALUE} -X POST http://localhost:3000/api/items/
curl --data name=${NAME} --data value=${VALUE} -X POST http://localhost:3000/api/items/

