#!/bin/bash
ARG1=${1:-100}
java -jar target/avcont4.jar -i 00000000 -s 4 -w 2 -t $ARG1 > data.txt

