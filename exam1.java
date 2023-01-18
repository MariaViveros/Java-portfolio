
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner (System.in);
		
		System.out.print("How many exam scores? ");
		int count = s.nextInt();
		double total = 0;
		
		for(int i=1; i<=count; i++){
		    System.out.printf("Enter score for exam %d: ", i);
		    int score = s.nextInt();
		    total +=score;
		}
		
		double avg = total/count;
		
		char grade = 'F';
		
		if (avg >= 90) grade = 'A';
		else if (avg >= 80) grade = 'B';
		else if (avg >= 70) grade = 'C';
		else if (avg >= 60) grade = 'D';
		
		System.out.printf("Your letter grade is %s.", grade);
		
		//switch cannot use ranges, that is why we use the letters
		switch(grade){
		    case 'A':
		    case 'B': System.out.println("Good job!"); break;
		    case 'C': System.out.println("So.so."); break;
		    case 'D':
		    case 'F': System.out.println("Study harder"); break;      
		}
	}
}
