package com.school;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class Factorial
{

    public void calcFactorial(String s)
 {
     int num;
     try {
         num = Integer.parseInt(s);
     }
     catch (NumberFormatException e)
     {
         num = 0;
     }
     System.out.println("Factorial for "+num +" = " +factorial(num));
 }

    static long factorial(int n){
        if (n == 0)
            return 1;
        else
            return(n * factorial(n-1));
    }

}

public class Main {

    public static void main(String[] args)
    {
        System.out.println("Main thread started...");

        List<String> myList=readFromFile();

        for (String s:myList) {
            new Thread(new Runnable() {
                private String myParam;

                public Runnable init(String myParam) {
                    this.myParam = myParam;
                    return this;
                }

                @Override
                public void run() {
                    System.out.println("Solving factorial for " + myParam);
                    Factorial f =new Factorial();
                    f.calcFactorial(myParam);
                }
            }.init(s)).start();
        }

        System.out.println("Main thread finished...");

    }

    public static List<String> readFromFile()
    {
        String fileName = "src/main/resources/res.txt";
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}

