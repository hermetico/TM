#!/bin/bash
DATAFILE=${1:-out.dat}

gnuplot << GPLOT
set key autotitle columnhead
set terminal pdf
set output 'plot.pdf'
set xlabel "Number"
set ylabel "Compressed length"
plot "$DATAFILE" u 1:2 with lines title "length"
GPLOT
