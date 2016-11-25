package AvCont5;
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
            parser.checkRange();
            if (parser.help){ 
                jcomm.usage();
            }
            
            }catch(ParameterException e){
           
            System.err.println(" Try --help or -h for help");
            System.out.println(e.getMessage());
            System.exit(1);
        }
         Main main = new Main(parser);
    }
    
    public Main(ArgParser args) {
        this.args = args;
        run();
    }
    
    public void run(){
        int n0 = args.n.get(0);
        int n1 = args.n.get(1);
        int m = args.m;
        
        String out;
        System.out.print("Number"+ "\t");
        System.out.println("Coded length");
        for (int number = n0; number <= n1;  number++){ 
            out = getNumber(number,m);
            
            System.out.print(number + "\t");
            System.out.println(out.length());
        }
        
    };
    
    
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
    public String getNumber(int n, int m){
        String str = getSign(n) + getQ(n, m) + getR(n, m);
        return str;
    }
    
}
