#!/bin/bash
# -*- coding: UTF-8 -*-

# hamlet compression
gnuplot << GPLOT
set terminal postscript eps enhanced color
set output 'hamlet-compression.eps'
set xlabel "Input Window"
set logscale x 2
set key right bottom box
set grid
set xrange [4:8192]
set ylabel "Compression Rate"
plot 	"hamlet_data.txt" u 1:2 with linespoints linetype 31 title "SLID = 8", \
 	"hamlet_data.txt" u 4:5 with linespoints title "SLID = 16", \
	"hamlet_data.txt" u 7:8 with linespoints title "SLID = 32", \
	"hamlet_data.txt" u 10:11 with linespoints title "SLID = 64", \
	"hamlet_data.txt" u 13:14 with linespoints title "SLID = 128", \
	"hamlet_data.txt" u 16:17 with linespoints title "SLID = 256", \
	"hamlet_data.txt" u 19:20 with linespoints title "SLID = 512", \
	"hamlet_data.txt" u 22:23 with linespoints title "SLID = 1024", \
	"hamlet_data.txt" u 25:26 with linespoints title "SLID = 2048", \
	"hamlet_data.txt" u 28:29 with linespoints title "SLID = 4096"
GPLOT

gnuplot << GPLOT
set terminal postscript eps enhanced color
set encoding utf8
set output 'hamlet-time.eps'
set xlabel "Input Window"
set logscale x 2
set grid
set key box
set xrange [4:8192]
set ylabel "Time {/Symbol m}s"
plot 	"hamlet_data.txt" u 1:3 with linespoints linetype 31 title "SLID = 8", \
 	"hamlet_data.txt" u 4:6 with linespoints title "SLID = 16", \
	"hamlet_data.txt" u 7:9 with linespoints title "SLID = 32", \
	"hamlet_data.txt" u 10:12 with linespoints title "SLID = 64", \
	"hamlet_data.txt" u 13:15 with linespoints title "SLID = 128", \
	"hamlet_data.txt" u 16:18 with linespoints title "SLID = 256", \
	"hamlet_data.txt" u 19:21 with linespoints title "SLID = 512", \
	"hamlet_data.txt" u 22:24 with linespoints title "SLID = 1024", \
	"hamlet_data.txt" u 25:27 with linespoints title "SLID = 2048", \
	"hamlet_data.txt" u 28:30 with linespoints title "SLID = 4096"
GPLOT

# quijote compression
gnuplot << GPLOT
set terminal postscript eps enhanced color
set output 'quijote-compression.eps'
set xlabel "Input Window"
set logscale x 2
set key right bottom box
set grid
set xrange [4:8192]
set ylabel "Compression Rate"
plot 	"quijote_data.txt" u 1:2 with linespoints linetype 31 title "SLID = 8", \
 	"quijote_data.txt" u 4:5 with linespoints title "SLID = 16", \
	"quijote_data.txt" u 7:8 with linespoints title "SLID = 32", \
	"quijote_data.txt" u 10:11 with linespoints title "SLID = 64", \
	"quijote_data.txt" u 13:14 with linespoints title "SLID = 128", \
	"quijote_data.txt" u 16:17 with linespoints title "SLID = 256", \
	"quijote_data.txt" u 19:20 with linespoints title "SLID = 512", \
	"quijote_data.txt" u 22:23 with linespoints title "SLID = 1024", \
	"quijote_data.txt" u 25:26 with linespoints title "SLID = 2048", \
	"quijote_data.txt" u 28:29 with linespoints title "SLID = 4096"
GPLOT

gnuplot << GPLOT
set encoding utf8
set terminal postscript eps enhanced color
set output 'quijote-time.eps'
set xlabel "Input Window"
set logscale x 2
set grid
set key box
set xrange [4:8192]
set ylabel "Time {/Symbol m}s"
plot 	"quijote_data.txt" u 1:3 with linespoints linetype 31 title "SLID = 8", \
 	"quijote_data.txt" u 4:6 with linespoints title "SLID = 16", \
	"quijote_data.txt" u 7:9 with linespoints title "SLID = 32", \
	"quijote_data.txt" u 10:12 with linespoints title "SLID = 64", \
	"quijote_data.txt" u 13:15 with linespoints title "SLID = 128", \
	"quijote_data.txt" u 16:18 with linespoints title "SLID = 256", \
	"quijote_data.txt" u 19:21 with linespoints title "SLID = 512", \
	"quijote_data.txt" u 22:24 with linespoints title "SLID = 1024", \
	"quijote_data.txt" u 25:27 with linespoints title "SLID = 2048", \
	"quijote_data.txt" u 28:30 with linespoints title "SLID = 4096"
GPLOT
