package Week1;

import java.util.Scanner;
import java.math.BigInteger;

public class Karatsuba {

    private BigInteger multiply(String x, String y) {

        //Base case: if one of the arguments is 1 digits long multiply easy way
        if(x.length() == 1 || y.length() == 1) {
            BigInteger a = new BigInteger(x);
            BigInteger b = new BigInteger(y);
            return a.multiply(b);
        }
        //Pad with zeros shorter string
        int len = Math.abs(x.length()- y.length());
        if(x.length() > y.length()) {
            y = y + "0".repeat(len);
        } else {
            x = x + "0".repeat(len);
        }
        //Save number of zeros added
        BigInteger repeat = new BigInteger(Long.toString((long)Math.pow(10,len)));

        int length = x.length();

        //Create divisions of the arguments
        String a = x.substring(0,length/2);
        String b = x.substring(length/2);

        String c = y.substring(0,length/2);
        String d = y.substring(length/2);

        //Calculates ac a.k.a first step of algorithm
        BigInteger first = multiply(a,c);

        //Calculates bd a.k.a second step of algorithm
        BigInteger second = multiply(b, d);

        //Calcualtes (a+b) and (c+d) for third step
        String addedX = ((new BigInteger(a).add(new BigInteger(b))).toString());
        String addedY = ((new BigInteger(c).add(new BigInteger(d))).toString());

        //Calculates (ad + bc) a.k.a third step of the algorithm
        BigInteger third = multiply(addedX,addedY).subtract(first).subtract(second);

        //If length of the strings was even add one to length for zeros
        length = length + length %2;

        //Multiplyes first with 10 to the power of length and third with 10 to the power of half length
        first = new BigInteger(first.toString() + "0".repeat(length));
        third = new BigInteger(third.toString() + "0".repeat(length/2));

        //Calcualtes result (divides it on repeat variable as we padded one argument with that many zeros)
        BigInteger result = first.add(second).add(third).divide(repeat);

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Karatsuba karatsuba = new Karatsuba();


        System.out.println("Please enter two numbers");
        String x = scanner.nextLine();
        String y = scanner.nextLine();

        while(!x.equals("-1")) {
            long start =System.currentTimeMillis();
            BigInteger result = karatsuba.multiply(x,y);
            System.out.println("Result is " + result);
            System.out.println("Time needed was " + (System.currentTimeMillis() - start));
            System.out.println("Please enter two numbers");
            x = scanner.nextLine();
            y = scanner.nextLine();

        }


    }
}


