package Crypto;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.NullType;

public class Elgamal{
    public static void main(String[] args) {
        System.gc(); 
        // int n = 8;
        // String text = "0010100101001100011100110100001011001000101000101000101000110110110010011110000010111110100001010011010011111001100100100110111001011010011000110011101000001010101101001001000111010100001110110100000100100100101011101101011010000101000110001101001110001011"; 
        // int i = 0;

        // BigInteger  x = new BigInteger("129380827387187283717281");
        // BigInteger  y = new BigInteger("23128731787111");
        // ExtendedGCD g =  new ExtendedGCD(x,y);
        // System.out.println(g.getGCD());
        // System.out.println(g.getinvert());
        BigInteger  z = new BigInteger("13");
        setOfNumber set = new setOfNumber(z);
        set.findSetNumber_Invert();
        // set.findSetNumber_Mutiply();
        // List<BigInteger> set_Zn = set.getSet();
        // for (BigInteger number : set_Zn) {
        //     System.out.print(number+" ");
        // }
        

    }
    
    
    
}
class Prime{
    private BigInteger number;
    Prime(BigInteger number){
        this.number = number;
    }
    public boolean findPrime(){
        return true;
    }
}
class setOfNumber{
    private List<BigInteger> set = new ArrayList<BigInteger>();
    private BigInteger number;
    setOfNumber(BigInteger number){
        this.number =number;
    }
    public void findSetNumber_Mutiply(){
        
        for(BigInteger n = BigInteger.valueOf(0);n.compareTo(number) < 0;n = n.add(BigInteger.ONE)){
            for(BigInteger i = BigInteger.valueOf(0);i.compareTo(number) < 0;i = i.add(BigInteger.ONE)){
                
                BigInteger result = (n.multiply(i)).mod(number);
                if(result.compareTo(BigInteger.valueOf(1)) == 0) {
                    System.out.print(n+"*"+i+" "+result);
                    set.add(n); 
                }
                i.add(BigInteger.ONE);
            }
            n.add(BigInteger.ONE);
            System.out.println();
        }
    }

    public void findSetNumber_Invert(){
        BigInteger[] set_invert = new BigInteger[number.intValue()];
        for(BigInteger n = BigInteger.valueOf(0);n.compareTo(number) < 0;n = n.add(BigInteger.ONE)){
            if(set_invert[n.intValue()] != null ){
                ExtendedGCD gcd = new ExtendedGCD(n, number);
                gcd.findGCD();
                if(gcd.getGCD().compareTo(BigInteger.ONE) == 1){

                }else{
                    
                }
            }
            
        }


    }

    public List<BigInteger> getSet(){
        return set;
    }
}
class ExtendedGCD{
    private BigInteger table[] = new BigInteger [8];
    private BigInteger g = new BigInteger("0"),a = new BigInteger("0"),b = new BigInteger("0");;
    private BigInteger n2_init;
    private BigInteger n1_init;
    ExtendedGCD(BigInteger n1,BigInteger n2){
        this.n1_init = n1;
        this.n2_init = n2;
        table[0] = n1;
        table[1] = n2;
        table[2] = n1.gcd(n2);
        table[3] = (BigInteger) n1.divide(n2); 
        table[4] = new BigInteger("1");
        table[5] = new BigInteger("0");
        table[6] = new BigInteger("0");
        table[7] = new BigInteger("1");
        printTable();
        findGCD();
        
    }
    public void printTable(){
        for(int i = 0;i<8;i++){
            System.out.print(table[i]+" ");
        }
        System.out.println();
    }
    public void findGCD(){
        
        BigInteger x = new BigInteger("0");
        if(table[2].compareTo(x) == 0){
            this.g = table[1];
            this.a = table[6];
            this.b = table[7];
            System.out.println("g = "+g+" , "+"a = "+a+" , "+"b = "+b);
        }else if(table[2].compareTo(x) == 1){
            
            BigInteger t = table[6];
            table[6] = table[4].subtract(table[3].multiply(table[6]));
            table[4] = t;
            t = table[7];
            table[7] = table[5].subtract(table[3].multiply(table[7]));
            table[5] = t;
            table[0] = table[1];
            table[1] = table[2];
            table[2] = table[0].mod(table[1]);
            table[3] = (BigInteger) (table[0].divide(table[1])); 
            printTable();
            findGCD();
        }
    }
    public BigInteger getGCD(){
        return this.g;
    }
    public BigInteger getinvert(){
        return b.mod(this.n2_init);
        // if(b.compareTo(x) == -1){
        //     b = n2_init.add(b); 
        //     return b.mod(this.n2_init);
        // }else{
        //     return b.mod(this.n2_init);
        // }
    }
}

