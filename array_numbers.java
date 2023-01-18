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
		
		n[0] = s.nextInt();
		n[1] = s.nextInt();
		n[2] = s.nextInt();
		n[3] = s.nextInt();
		n[4] = s.nextInt();
		
		int total = n[0]+n[1]+n[2]+n[3]+n[4];
		int avg = total/5;
		
		int min = min(n[0], min(n[1], min(n[2], min(n[3],n[4]))));
		int max = max(n[0], max(n[1], max(n[2], max(n[3],n[4]))));
		
		System.out.printf("You entered: %d, %d, %d, %d, and %d\n", n[0],n[1],n[2],n[3],n[4]);
		
		System.out.printf("Total is %d.\n", total);
		System.out.printf("The average is %d.\n", avg);
		System.out.printf("The min is %d.\n", min);
		System.out.printf("The max is %d.\n", max);
	}
}

