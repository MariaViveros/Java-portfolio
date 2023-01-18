/******************************************************************************

unchecked exception - not require to catch and handle
    should extend RuntimeException

checked exception - must catch and handle or throw it
    should extends Exception class

*******************************************************************************/

class MyUncheckedException extends RuntimeException {
    MyUncheckedException(String msg){
        super(msg);
    }
}

class MyCheckedException extends Exception {
    MyCheckedException(String msg){
        super(msg);
    }
}

public class Main
{
    static void method1(){
        throw new MyUncheckedException("E 1");
    } 
    
    static void method2() throws MyCheckedException {
        throw new MyCheckedException("E 2");
    } 
    
	public static void main(String[] args) {
	    try{
	       method1(); 
	    } catch(Exception e){
	        System.out.println(e.getMessage());
	    }
	    
	    
	    try{
	       method2(); 
	    } catch(Exception e){
	        System.out.println(e.getMessage());
	    }
	    
	}
}
