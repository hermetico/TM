# rate, -codec:v especifica el codec de video acontinuacion
avconv -benchmark -r 25 -i ./cubo/Cubo%02d.png -codec:v mpeg1video ./cub_mpeg.avi
