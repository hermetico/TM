#!/bin/bash

DATAFILE="out-d.dat"

gnuplot << GPLOT
	set grid xtics mxtics ytics
	set mxtics 2
	set grid	
	set key autotitle columnhead
	set terminal postscript enhanced color eps
	set output 'plot-d.eps'
	set xlabel "Number"
	set ylabel "Compressed length"
	set xrange [-1250:1250]
	plot "$DATAFILE" u 1:2 with lines title "m = 32", \
	"$DATAFILE" u 1:3 with lines title "m = 64", \
	"$DATAFILE" u 1:4 with lines title "m = 128", \
	"$DATAFILE" u (\$1):(11) with lines title "Binary" lt -1, \
#	"$DATAFILE" u 1:5 with lines title "m = 256", \
#	"$DATAFILE" u 1:6 with lines title "m = 512", \

GPLOT
DATAFILE="out-e.dat"

gnuplot << GPLOT
	set grid xtics mxtics ytics mytics
	set mxtics 2
	set mytics 2
	set grid	
	set key autotitle columnhead
	set terminal postscript enhanced color eps
	set output 'plot-e.eps'
	set xlabel "Number"
	set ylabel "Compressed length"
	set xrange [-50:50]
	set yrange [0:15]
	plot "$DATAFILE" u 1:2 with lines title "m = 4", \
	"$DATAFILE" u 1:3 with lines title "m = 8", \
	"$DATAFILE" u 1:4 with lines title "m = 16", \
	"$DATAFILE" u 1:5 with lines title "m = 32", \
	"$DATAFILE" u 1:6 with lines title "m = 64", \
	"$DATAFILE" u (\$1):(11) with lines title "Binary" lt -1, \

GPLOT
