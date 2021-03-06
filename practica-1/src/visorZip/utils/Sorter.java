/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visorZip.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;

/**
 *
 * @author hermetico
 */
public class Sorter {
    
    /**
     * Returns the list in ascending order based on the number contained
     * within the name
     * @param list
     * @return
     */
    public static List<ZipEntry> sortEntriesByName(List<ZipEntry> list){
        
        Collections.sort(list, new Comparator<ZipEntry>() {
            @Override
            public int compare(ZipEntry a, ZipEntry b) {
                return extractInt(a.getName()) - extractInt(b.getName());
            }

            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                // return 0 if no digits found
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        });
        

        return list;
    }

    
}

