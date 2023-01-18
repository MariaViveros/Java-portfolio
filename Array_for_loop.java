/*
Read 5 integers:
Compute the total, average, min, and the max

Array
    - Made of many slots, each slot can store a single value
    - Using only a single variable name
    - Use position or index to write to or read from Array
    
    numbers [5][4][1][3][8]
             0  1  2  3  4
*/

import java.util.*;

public class Main
{
    static int min(int a, int b){
        return a < b ? a : b;
    }
    
    static int max(int a, int b){
        return a > b ? a : b;
    }
    
	public static void main(String[] args) {
	    Scanner s = new Scanner(System.in);
		System.out.print("Enter 5 numbers: ");
		int [] n = new int[5];
		
		for(int i=0; i<n.length; i++)
		    n[i] = s.nextInt();
		
		int total = 0;
		int min = n[0]; //assume current min value is n[0](the first in the array)
		int max = n[0];
		
		for(int i=0; i<n.length; i++){
		    total += n[i];
		}
		
		for(int i=0; i<n.length; i++){
		    if(min > n[i])
		        min = min > n[i] ? n[i] : min;
		        max = max < n[i] ? n[i] : max;
		}
		
		System.out.printf("You entered: ");
		for(int i=0; i<n.length; i++)
		    System.out.print(n[i] + "");
		
		System.out.printf("Total is %d.\n", total);
		System.out.printf("The average is %d.\n", total/5);
		System.out.printf("The min is %d.\n", min);
		System.out.printf("The max is %d.\n", max);
	}
}

