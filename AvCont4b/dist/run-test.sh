#!/bin/bash
java -jar avcont4.jar -f hamlet_short.txt -s 16 -w 8 --mode c --test > hamlet_raw_data.txt
java -jar avcont4.jar -f quijote_short.txt -s 16 -w 8 --mode c --test > quijote_raw_data.txt

