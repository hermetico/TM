/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.processor.filters;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 *
 * @author ferran
 */
public class Conv extends Filter{
    
    // type filter in string format
    String type;
    // filter array to be convolved with input image
    float[] data;
    // type of filter
    private enum FilterType {PREWITT_X, PREWITT_Y, SOBEL_X, SOBEL_Y, LAPLACIAN, UNSHARP, SHARPEN, BLUR, EMBOSS}
    private FilterType filterType;
    
    // Constructor
    public Conv(String type){
        this.type = type;
        setFilterType(type);
        setFilterData(filterType);
        
    };
    
    private void setFilterType(String type)
    {
        filterType = filterType.valueOf(type.toUpperCase());
    }
    // Set the rigth filter array to be convolved with input image
    private void setFilterData(FilterType filter) {
        switch(filter){
            case PREWITT_X:
                data = new float[] { -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f };
                break;
            case PREWITT_Y:
                break;
            case SOBEL_X:
                data = new float[] { 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, -1.0f, -1.0f, -1.0f };
                break;
            case SOBEL_Y:
                data = new float[] { 1.0f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, -1.0f, -2.0f, -1.0f };
                break;
            case LAPLACIAN:
                data = new float[] { 0.0f, 1.0f, 0.0f, 1.0f, -4.0f, 1.0f, 0.0f, 1.0f, 0.0f };
                break;
            case UNSHARP:
                data = new float[] { 0.0f, -1.0f, 0.0f, -1.0f, 5.0f, -1.0f, 0.0f, -1.0f, 0.0f };
                break;
            case SHARPEN:
                data = new float[] { -1.0f, -1.0f, -1.0f, -1.0f, 8.0f, -1.0f, -1.0f, -1.0f, -1.0f };
                break;
            case BLUR:
                data = new float[] { 0.0f, 0.2f, 0.0f, 0.2f, 0.2f, 0.2f, 0.0f, 0.2f, 0.0f };
                break;
            case EMBOSS:
                data = new float[] { -1.0f, -1.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f };
                break;
            default:
                // identity
                data = new float[] { 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f };
                break;
        }
  
    }
    @Override
    public void apply(BufferedImage image) {
        
        Kernel avgKernel = new Kernel(3,3,data);
        BufferedImageOp average = new ConvolveOp(avgKernel);
        BufferedImage output = average.filter(image, null);
        image.setData(output.getRaster());
    } 
}
   

