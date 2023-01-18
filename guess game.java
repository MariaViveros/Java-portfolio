import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static void main (String[] args) {
       Random random = new Random();
       Scanner keyboard = new Scanner(System.in);
       
       int randomNum = random.nextInt(100) +1;
       
       System.out.println("I'm thinking of a number. What is it? ");
       int num = keyboard.nextInt();
       
       boolean end = false;
       int n = 0;
       
       while( !end ){
           if(randomNum == num){
               n++;
               System.out.printf("You got it in %d tries.", n);
               break;
           } else if(randomNum > num){
               System.out.println("Nope. It's higher. Try again: ");
               num = keyboard.nextInt();
               n++;
           } else if(randomNum < num){
               System.out.println("Nope. It's lower. Try again: ");
               num = keyboard.nextInt();
               n++;
           }
       }
    }
}


