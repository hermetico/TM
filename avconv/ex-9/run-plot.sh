#!/bin/bash
# -*- coding: UTF-8 -*-

gnuplot << GPLOT
set encoding utf8
set terminal postscript eps enhanced color
set output "plot.eps"
set grid
set yrange [1.0:3]
set y2range [400:500]
unset log y2
set ytics nomirror
set y2tics
set ylabel "segundos"
set y2label "KB"
set xlabel "rango de busqueda"
set title "Tiempo de ejecucion y tamaño de archivo en funcion del rango de busqueda de desplazamiento"
plot "data.txt" using (\$1) axes x1y1 with lines title "Tiempo ejecucion", "data.txt" using (\$2) axes x1y2 with lines title "Tamaño archivo"
GPLOT
