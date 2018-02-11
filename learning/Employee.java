import java.io.*; // ask the compiler to load all the classes available in directory java_installation/java/io −

public class Employee {
	String Name;
	int Age;
	String Title;
	double Salary;

public Employee(String name){
	this.Name=name;
 }

public void Set_Salary(double salary){
	Salary=salary;
}
public void Set_Age(int age){
	Age=age;
}
public void Set_Title(String title){
	Title=title;
}


public void Print_Info(){
	System.out.println("Name:"+Name);
	System.out.println("Age:"+Age);
	System.out.println("Title:"+Title);
	System.out.println("Salary:"+Salary);
}

}
