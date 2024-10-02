#!/bin/bash

# URL to hit
URL="localhost:8083/api/geolocation/city/mumbai"
URL2="localhost:8083/api/geolocation/city/navimumbai"

# Loop to make 500 requests with 1-second interval
for i in {1..500}
do
   echo "Making request #$i"
   curl --location "$URL"
   curl --location "$URL2"
   
   # Wait for 1 second before the next request
   sleep 1
done
