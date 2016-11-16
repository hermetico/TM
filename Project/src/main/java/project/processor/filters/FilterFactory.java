/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.processor.filters;

import project.misc.Tracer;
import project.settings.Setup;

public class FilterFactory {
    Tracer tr = Tracer.getInstance();
    public Filter createFilter(Setup setup){
        Filter fl;
        switch(setup.getFilter()){
            case NEGATIVE:
                fl = new Negative();
                break;
            case AVERAGE:
                fl = new Average(setup.getAveragingValue());
                break;
            case BINARIZATION:
                fl = new Binarize(setup.getBinarizationValue());
                break;
            case CONVOLUTIONAL:
                fl = new Conv(setup.getConvolutionalFilterType());
                break;
                
            case NONE:
            default:
                fl = null;
                break;
        }
        tr.trace(setup.getFilter().toString() + " filter loaded");
        return fl;
    }
}
