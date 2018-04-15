#!/bin/bash

avg() {
	uptime | awk '{gsub(/,/,"."); print $NF * 100};'
}


while :
do
	./insertdb.sh demo $(avg)
	sleep 1
done
