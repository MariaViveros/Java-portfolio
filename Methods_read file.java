/******************************************************************************

Problem description
    - action / verb = methods
    - nouns = variables, parameters
    
Write a program that asks the user for a file that contains the sales amounts
for 30 days. It calculates and displays the total sales and average daily sales.

*******************************************************************************/
import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws IOException {
		String filename = getFileName();
		double totalSales = getTotalSales(filename);
		double average = totalSales/30;
		displayResult(totalSales, average);
	}
	
	static void displayResult(double ts, double avg){
	    System.out.printf("Total sales is %.2f.", ts);
	    System.out.printf("Average daily sales is %.2f.", avg);
	}
	
	static double getTotalSales(String filename) throws IOException{
	    Scanner s = new Scanner (new File(filename));
	    double total = 0;
	    while(s.hasNextDouble()){
	        total += s.nextDouble();
	    }
	    s.close();
	    return total;
	}
	
	static String getFileName(){
	    System.out.print("Enter the name of the file: ");
	    Scanner s = new Scanner(System.in);
	    return s.nextLine();
	}
}
