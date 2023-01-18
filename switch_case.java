/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.*;

public class Main
{
	public static void main(String[] args) {
	    
	    Scanner s = new Scanner(System.in);
	    
		System.out.println("1) make circle");
        System.out.println("2) make rectangle");
        System.out.println("3) Exit: ");
        
        int choice = s.nextInt();
        
            switch(choice){
                case 1: System.out.println("Enter radius: ");
                        double radius = s.nextDouble();
                        System.out.println(radius);
                        break;
                        
                case 2: System.out.println("Enter height: "); 
                        double height = s.nextDouble();
                        System.out.println("Enter width: "); 
                        double width = s.nextDouble();
                        System.out.println(height);
                        System.out.println(width);
                        break;
                        
                case 3: break;    
            }   
	}
}
