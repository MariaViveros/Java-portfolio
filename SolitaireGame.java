import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
/**
   A solitaire matching game in which you have a list of random
   integer values between 10 and 99. You remove from the list any
   pair of adjacent integers whose first or second digits match.
   If all values are removed, you win.

   @author Charles Hoot 
   @version 5.0
   Modified by: Maria Viveros. 
 */
public class SolitaireGame
{
	private static List<Integer> theNumbers;
        
	/** Initializes the list with 40 random 2 digit numbers. */
	public static void initializeList(List<Integer> theList)
	{
            // Create the random variable
            Random random = new Random();
            
            // Make a for loop to create the list for the game
            for(int index = 0; index < 40; index++)
            {
                int number = random.nextInt(89) + 10;
                theList.add(number);
            }
	} // end initializeList

	/** Sees whether two numbers are removable.
		 @param x  The first 2 digit integer value.
		 @param y  The second 2 digit integer value.
 		 @return  True if x and y match in the first or second digit. */
	public static boolean removable(Integer x, Integer y)
	{
            // Create a boolean variable to check if the numbers are removable
            boolean isRemovable = false;
            
            // if both numbers are the same, the pair is removable
            if(x == y)
                isRemovable = true;
            
            // if the first digits of both numbers are the same, they are removable
            else if(x/10 == y/10)
                isRemovable = true;
            
            // of the second digits of both numbers are the same, they are removable
            else if(x%10 == y%10)
                isRemovable = true;
            
            return isRemovable;
	} // end removable

	/** Scans over the list and removes any pairs of values that are removable.
		 @param theList  The list of 2 digit integers to scan over.
		 @return  True if any pair of integers was removed. */
	public static boolean scanAndRemovePairs(List<Integer> theList)
	{
            // Create a boolean variable to remove pairs
            boolean remove = false;
            
            // Create a list iterator
            ListIterator<Integer> scanList = theList.listIterator();
            
            // Create two variables to store the two numbers to compare
            Integer first = null;
            Integer second = null;
            
            // if there is another number in the array, make it the 
            // first number to compare
            if(scanList.hasNext())
                first = scanList.next();
            
            // if there is another number in the array, make it the 
            // second number to compare
            if(scanList.hasNext())
                second = scanList.next();
            
            // while there are more numbers in the array, keep scanning them
            while(first != null && second != null)
            {
                // if both numbers are removable, remove them, and print them.
                // Then, check if there are other two numbers to compare
                if(removable(first, second))
                {
                    scanList.previous();
                    scanList.remove();
                    scanList.previous();
                    scanList.remove();
                    remove = true;
                    System.out.println("\nRemoved: " + first + " and " + second);
                    
                    first = null;
                    second = null;
                    
                    if(scanList.hasNext())
                        first = scanList.next();

                    if(scanList.hasNext())
                        second = scanList.next();
                }
                // if both numbers are not removable, go to the next one,
                // and compare them. If there are no more numbers, make 
                // second one null.
                else
                {
                    first = second; // Go to the next number
                    if(scanList.hasNext())
                        second = scanList.next();
                    else
                        second = null;
                }
            }
            
            return remove;
	} // end scanAndRemovePairs

	public static void main(String args[])
	{
		theNumbers = new ArrayList<>();
		initializeList(theNumbers);
		System.out.println("The list is originally: " + theNumbers);

		while (scanAndRemovePairs(theNumbers))
			System.out.println("The list is now: " + theNumbers);
		System.out.println("No more pairs to remove.");
	} // end main
} // end SolitaireGame

/*
The list is originally: [81, 50, 11, 61, 42, 74, 16, 65, 49, 49, 11, 19, 67, 79, 33, 95, 85, 52, 59, 67, 46, 81, 62, 30, 60, 66, 80, 96, 30, 81, 37, 30, 34, 30, 15, 80, 11, 61, 55, 46]
   Removed: 11  61
   Removed: 49  49
   Removed: 11  19
   Removed: 95  85
   Removed: 52  59
   Removed: 30  60
   Removed: 37  30
   Removed: 34  30
   Removed: 11  61
The list is now: [81, 50, 42, 74, 16, 65, 67, 79, 33, 67, 46, 81, 62, 66, 80, 96, 30, 81, 15, 80, 55, 46]
   Removed: 65  67
   Removed: 62  66
The list is now: [81, 50, 42, 74, 16, 79, 33, 67, 46, 81, 80, 96, 30, 81, 15, 80, 55, 46]
   Removed: 81  80
The list is now: [81, 50, 42, 74, 16, 79, 33, 67, 46, 96, 30, 81, 15, 80, 55, 46]
   Removed: 46  96
The list is now: [81, 50, 42, 74, 16, 79, 33, 67, 30, 81, 15, 80, 55, 46]
No more pairs to remove.

 */

