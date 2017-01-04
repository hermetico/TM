package project.processor;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.zip.ZipEntry;
import project.counters.Counters;
import project.input.Unzip;
import project.misc.Tracer;
import project.player.Player;
import project.settings.Configuration;

public class Encoder {
    protected Configuration cf;
    protected Tracer tr;
    protected Unzip zp;

    protected Buffer<BufferedImage> buffer;
    protected Counters counters;
    protected Player player;

    
}
