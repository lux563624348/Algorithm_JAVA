public class Dog {
   String breed;
   int dog_age; // Instance variables
   String color;

public Dog(String name, int dog_age){
// This Constructor has one parameter, name
	System.out.println("The given name to your dog is:" + name); 
	System.out.println("The age of your dog is:" + dog_age);
}

public void changeAge(int age){
	dog_age=age;		
}

public int getAge(){
	System.out.println("Your dog age is:" + dog_age);
	return dog_age;
}

public static void main(String []args) {
//Create an object Dod
Dog myDog=new Dog("Erm", 5);
myDog.changeAge(10);
myDog.getAge();

System.out.println("Modifed age for your dog is:"+myDog.dog_age+myDog.breed);


}

   void barking() {
   }

   void hungry() {
   }

   void sleeping() {
   }
}
