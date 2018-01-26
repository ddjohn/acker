#!/bin/bash

while :
do
 	curl http://www-avelon.rhcloud.com/kpi/uptime/home/$(awk '{print $3}' /proc/loadavg)/success
	sleep 40
done

