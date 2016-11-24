
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import static java.lang.Math.abs;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Main {
    
    ArgParser args;
    public static void main(String[] args) {
        
ArgParser parser = new ArgParser();
        JCommander jcomm;
        try{
            jcomm = new JCommander(parser, args);
            
            }catch(ParameterException e){
            System.out.println(e.getMessage());
            System.err.println(" Try --help or -h for help");
            System.exit(1);
        }       
    }
    
    public void run(){
        String n0 = args.n.get(0);
        String n1 = args.n.get(0);
        int m = args.m;
        int n = 17;
        int out;
        out = getNumber(n,m); 
    };
    
    public boolean IsPowerOfTwo(int x){
         return (x != 0) && ((x & (x - 1)) == 0);
     }
    
    public String getQ(int n, int m){
        int counter;
        StringBuilder output = new StringBuilder();
        counter = abs(n)/m;
        for (int i = 0; i< counter;i++){
            output.append("1");
        }
        output.append("0");
        
        return output.toString();
    }
    public String getR(int n, int m){
        int out; 
        out = abs(n)%m;
        return Integer.toBinaryString(out);
    }
    public String getSign(int n){
        Integer.signum(n);
        int s = 0;
        if (n>=0){ s ++; }
        return Integer.toString(s);
    }
    public int getNumber(int n, int m){
        String str = getSign(n) + getQ(n, m) + getR(n, m);
        return Integer.parseInt(str);
    }
    
}
