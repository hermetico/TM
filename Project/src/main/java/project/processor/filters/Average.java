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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ferran
 */
public class Average extends Filter{
    
    int value;
    public Average(int value){this.value = value;};

    @Override
    // average value computed by convolution with n x n array where n = value
    // all array elements has 1/(n*n) value. 
    // If n = 3 then array is {1/9, 1/9, 1/9, 1/9, 1/9, 1/9, 1/9, 1/9, 1/9}
    public void apply(BufferedImage image) {
        
        List<Float> dataList = new ArrayList<Float>();
        int n = value*value;
        for (int i=0;i < n; i++){
            dataList.add(1.0f/(float)n);
        }
        
        float[] data = new float[ dataList.size() ];
       int i = 0; 
       for (Float f : dataList){
            data[i] = f;
            i++;
       }
 
        Kernel avgKernel = new Kernel(value,value,data);
        
        BufferedImageOp average = new ConvolveOp(avgKernel);
        BufferedImage output = average.filter(image, null);
        
        image.setData(output.getRaster());
    }
    
}
