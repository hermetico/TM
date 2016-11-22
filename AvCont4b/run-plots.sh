#!/bin/bash

# hamlet compression
gnuplot << GPLOT
set terminal postscript eps enhanced color
set output 'hamlet-compression.eps'
set xlabel "Input Window"
set xrange [8:4096]
set ylabel "Compression Rate"
plot "hamlet_data.txt" u 1:3 with linespoints title "SLID = 4", \
	"hamlet_data.txt" u 1:6 with linespoints title "SLID = 8", \
	"hamlet_data.txt" u 1:9 with linespoints title "SLID = 16"
GPLOT
