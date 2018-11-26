/*
I certify that I wrote the code I am submitting. I did not copy whole or parts
of it from another student or have another person write the code for me.
Any code I am reusing in my program is clearly marked as such with its soruces clearly
identified in comments.
*/
/*
@author Alan Hancher (AJH0067)
@author Sean Silva (SCS0042)
*/

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.Random;
import java.lang.System;
import java.lang.Math;
import java.util.ArrayList;
import java.io.PrintWriter;

public class Algorithms {
   /*
   The main method reads from a file names "phw_input.txt" which containts 10 comma-delimeted
   integers in the first line, then creats an array containing these 10 integers, then run
   each algorithm and print out the answer produced by each.
   */
   public static void main(String[] args) throws Exception {
      //The first step is to read from the file
      //source: https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
      //We also used another soruce for how to read a file that is seperated by commas
      //Source 2: https://stackoverflow.com/questions/22936218/how-to-read-comma-separated-integer-inputs-in-java
       File file = new File("phw_input.txt");
       Scanner sc = new Scanner(file);
       int numArray[] = new int[10];
       String line = sc.nextLine();
       //This loop searches the file and uses substrings to insert the values into the array
       for(int i = 0; i < numArray.length; i++) {
         if (line.contains(",")) {
            String value = line.substring(0, line.indexOf(','));
            int number = Integer.parseInt(value);
            numArray[i] = number;
            line = line.substring(line.indexOf(',') + 1, line.length());
       
         }
         //the Else staement checks for the last value in the file and puts it in the array
         else {
            String value = line.substring(0, line.length());
            int number = Integer.parseInt(value);
            numArray[i] = number;
         }
      }
      
      //The next step is to run the 4 algorithms with the sample array
      //and print out the results
      Algorithms run = new Algorithms();
      System.out.println("Algorithm-1: " + run.algorithm1(numArray));
      System.out.println("Algorithm-2: " + run.algorithm2(numArray));
      System.out.println("Algorithm-3: " + run.maxSum(numArray, 0, 9));
      System.out.println("Algorithm-4: " + run.algorithm4(numArray));
      
      //Next, we have to create 19 integer sequences of length 10,15,20...90,95,100
      //Containing randomly generated negative, zero, and positive integers
      int[][] generatedArray = new int[19][];
      int generatedArrayIndex = 0;
      
      //Increment the size of each array added to the generatedArray by 5 up to 100
      
      for(int i = 10; i <= 100; i += 5){
         int[] newlyGeneratedArray = new int[i];
         
         //next we have to generated the random numbers 
         //Soruce for generating random numbers:
         //"https://stackoverflow.com/questions/5887709/getting-random-numbers-in-java"
         for(int j = 0; j < i; j++) {
            Random rand = new Random();
            
            //the line below will output a random number within the range of
            //-100 and 250. We used an example from the following source to generate the numbers.
            //Source: https://stackoverflow.com/questions/27976857/how-to-get-random-number-with-negative-number-in-range
            int random = rand.nextInt(250 + 1 + 100) - 100;
            newlyGeneratedArray[j] = random;
         
         }
         generatedArray[generatedArrayIndex] = newlyGeneratedArray;
         generatedArrayIndex++;
      }
      
      //Generating a 19x8 matrix
      //Source: https://stackoverflow.com/questions/36939957/creating-a-matrix-in-java
      long[][] matrix = new long[19][8];
      
      //Running of the first algoritm with all 19 arrays
      for(int i = 0; i < 19; i++) {
         //To measure time, we chose to use the System.nanoTime() functions
         //Included in the lang.system library. 
         //Source: https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#nanoTime--
         long t1 = System.nanoTime();
         long totalTime = 0;
         for(int j = 0; j < 5000; j++) {
            run.algorithm1(generatedArray[i]);
            long t2 = System.nanoTime();
            long timeElapsed = t2 - t1; 
            totalTime += timeElapsed;
         }
         long averageTime = 0;
         averageTime += totalTime/ 5000;
         matrix[i][0] = averageTime;
      }
      //Running of the second algorithm with all 19 arrays
      for(int i = 0; i < 19; i++) {
         //To measure the time for the second algorithm, we will use the
         //Same system that we used to measure the time for algorithm 1.
         long t1 = System.nanoTime();
         long totalTime = 0;
         for(int j = 0; j < 1000; j++) {
            run.algorithm2(generatedArray[i]);
            long t2 = System.nanoTime();
            long timeElapsed = t2 - t1; 
            totalTime += timeElapsed;
         }
         long averageTime = 0;
         averageTime += totalTime/1000;
         matrix[i][1] = averageTime;
      }
      
      //Running of the thrid algorithm with all 19 arrays
      for(int i = 0; i < 19; i ++) {
         //To measure the time for the third algorithm, we will use
         //a similar function to the functions above.
         long t1= System.nanoTime();
         long totalTime = 0;
         for(int j = 0; j < 1000; j++) {
            run.maxSum(generatedArray[i], 0, generatedArray[i].length -1);
            long t2 = System.nanoTime();
            long timeElapsed = t2 - t1; 
            totalTime += timeElapsed;
         }
         long averageTime = 0;
         averageTime += totalTime/1000;
         matrix[i][2] = averageTime;
      }
      
      //Running of the foruth algorithm with all 19 arrays
      for(int i = 0; i < 19; i++) {
         //To measure the time for the second algorithm, we will use the
         //Same system that we used to measure the time for algorithm 1.
         long t1 = System.nanoTime();
         long totalTime = 0;
         for(int j = 0; j < 1000; j++) {
            run.algorithm4(generatedArray[i]);
            long t2 = System.nanoTime();
            long timeElapsed = t2 - t1; 
            totalTime += timeElapsed;
         }
         long averageTime = 0;
         averageTime += totalTime/1000;
         matrix[i][3] = averageTime;
      }
      
      //The next step is to fill in the last four columns of the matrix
      //with the ceiling values. To find the ceiling, we used the function
      //Math.ceil() which is included in the lang.Math library.
      int index = 0;
      for(int i = 10; i <= 100; i += 5) {
         matrix[index][4] = (long) Math.ceil(Math.pow(i, 3) * 100000);
         matrix[index][5] = (long) Math.ceil(Math.pow(i, 2) * 100000);
         matrix[index][6] = (long) Math.ceil((i * (Math.log(i) / Math.log(2))) * 100000);
         matrix[index][7] = (long) Math.ceil(i * 100000);
         index++;
      }
      
      //The next step is to output one line of text of the algorithm and
      //the complexity order tiles seperated by commas.
      ArrayList<String> outputText = new ArrayList<String>();
      for(int i = 0; i < 19; i++) {
         String output = "";
         for(int j = 0; j <= 6; j++) {
            output += matrix[i][j] + ",";
         }
         output += matrix[i][7];
         outputText.add(output);
      }
      //Next we need to put the output into a file
      //In order to output text, we will use the PrintWriter function
      //included in the io.PrintWriter class. We used the following two 
      //sources for examples of how to implement the code.
      //source: https://stackoverflow.com/questions/1053467/how-do-i-save-a-string-to-a-text-file-using-java
      //Another source: https://www.youtube.com/watch?v=fgjIk7qQong
      try {
         PrintWriter pw = new PrintWriter("AJH0067_SCS0042_phw_output.txt");
         pw.println("algorithm-1,algorithm-2,algorithm-3,algorithm-4,T1(n),T2(n),T3(n),T4(n)");
         //Enhanced for loop for outputing the data into the file
         //https://www.geeksforgeeks.org/loops-in-java/
         for(String x : outputText){
            pw.println(x);
         }
         pw.close();
      }
      //Catch the error incase their is an issue writing to the file
      catch (IOException e){
         System.out.println("Error writing to this file");
      }
   }
      
      
      
      //Algorithm 1
      public int algorithm1(int[] x) {
         int maxSoFar = 0;
         int p = 0;
         int q = x.length;
         
         for(int L = p; L <= q; L++) {
            for(int U = L; U <= q; U++) {
               int sum = 0;
               for(int I = L; I < U; I++) {
                  sum = sum + x[I];
                  //The sum now contains the sum of X[L...U]
               }
            maxSoFar = max(maxSoFar, sum);
            }
         }
         return maxSoFar;
      }
      
      
      //Algorithm 2
      public int algorithm2(int[] x){
         int maxSoFar = 0;
         int p = 0;
         int q = x.length;
         
         for(int L = p; L < q; L++) {
            int sum = 0;
            for(int U = L; U < q; U++) {
               sum = sum + x[U];  
               //sum now contains the sum of X[L...U]
               maxSoFar = max(maxSoFar, sum);
            }
         }
         return maxSoFar;
      }
      
      //Algortihm 3
      public int maxSum(int[] x, int L, int U) {
         if(L > U) {
            //This returns the zero element vector
            return 0;
          }
          if(L == U) {
            //This returns the one element vector
            return max(0, x[L]);
          }
          int M = (L + U)/2; //A is X[L..M], B is X[M+1 .. U}
          
          //Find max crossing to the left
          int sum = 0;
          int maxToLeft = 0;
          for(int I = M; I >= L; I--) {
            sum = sum + x[I];
            maxToLeft = max(maxToLeft, sum);
          }
          //Find max crossing to the right
          sum = 0;
          int maxToRight = 0;
          for(int I = M + 1; I <= U; I++) {
            sum = sum + x[I];
            maxToRight = max(maxToRight, sum);
          } 
         int maxCrossing = maxToLeft + maxToRight;
         
         int maxInA = maxSum(x, L, M);
         int maxInB = maxSum(x, M + 1, U);
         
         return max(maxCrossing, maxInA, maxInB);
      }
      
      //Algortithm 4
      public int algorithm4(int[] x) {
         int maxSoFar = 0;
         int maxEndingHere = 0;
         int p = 0;
         int q = x.length;
         for(int I = p; I < q; I++) {
            maxEndingHere = max(0, maxEndingHere + x[I]);
            maxSoFar = max(maxSoFar, maxEndingHere);
         }
         return maxSoFar;
      }
      
      //Max Algorithm
      //This algorithm takes in 2 parameters and outputs the max value between the two
      //The run time complexity is constant cost of 4.
      
      public int max(int x, int y){
         if(x >= y) {
            return x;
         }
         else {
            return y;
         }
      }
      
      
      //Max algorithm 
      //This algorithm takes in 3 parameters and returns the max value between the three values
      //The run time complexity is constant `13.
      
      public int max(int x, int y, int z) {
         if (x >= y) {
            if(x >= z) {
               return x;
            }
            else {
               return z;
            }
         }
         else if (y >= x) {
            if (y >= z) {
               return y;
            }
            else {
               return z;
            }
          }
          else {
            return z;
            }
      
      }
      
}