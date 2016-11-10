#!/bin/bash
DATAFILE=${1:-data.txt}

gnuplot << GPLOT
set key autotitle columnhead
set terminal pdf
set output 'plot.pdf'
set hidden3d
set pm3d
set dgrid3d
set view 65,230
set xlabel "Sliding"
set ylabel "Input"
set zlabel "Compression"
splot "$DATAFILE" u 1:2:5 with lines title "Compressed data"
GPLOT
