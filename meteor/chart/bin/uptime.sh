#!/bin/bash

fkn() {
	uptime | awk '{gsub(/,/,"."); print $NF * 100};'
}


while :
do
	./insertdb.sh uptime $(fkn)
	sleep 1
done
