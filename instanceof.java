/******************************************************************************

Author: Maria Viveros

*******************************************************************************/
interface Employee {
    String getName();
    String getEmployeeID();
    double getSalary();
}

class Person {
    private String name;
    
    Person(String name){
        this.name = name;
    }
    
    String getName(){
        return name;
    }
    
    void setName(String name){
        this.name = name;
    }
}

class Student extends Person {
    private double gpa;
    
    Student(String name, double gpa){
        super(name); //calling the constructor of Person
        this.gpa = gpa;
    }
    
    double getGpa(){
        return gpa;
    }
    
    void setGpa(double gpa){
        this.gpa = gpa;
    }
}

class Teacher extends Person implements Employee {
    private double salary;
    private String id;
    
    Teacher(String name, String id, double salary){
        super(name);
        this.id = id;
        this.salary = salary;
    }
    
    public String getEmployeeID(){
        return id;
    }
    
    public double getSalary(){
        return salary;
    }
    
    void setSalary(double salary){
        this.salary = salary;
    }
    
    public String getName(){
        return "Mr. " + super.getName();
    }
}

public class Main
{
	public static void main(String[] args) {
		Employee t = new Teacher("Joe", "E0001", 3000);
		Person p = new Teacher("Mike", "E0002", 4000);
		Person s = new Student("Bill", 3.5);
		
		if(t instanceof Person){
		    System.out.println(t.getName() + " is a person.");    
		}
		
		if (t instanceof Student){
		    System.out.println(t.getName() + "");
		}
		
		System.out.println(t.getName());
		//System.out.println(p.getSalary());
		
		Teacher t2 = (Teacher) p;
		System.out.println(t2.getSalary());
		
		if (s instanceof Teacher){
		    Teacher t3 = (Teacher) s;   
		}
	}
}
