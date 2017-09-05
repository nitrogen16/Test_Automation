import java.util.ArrayList;
import java.util.Collections;

public class App {

	public static void main(String[] args) {
		ArrayList<String> cars = new ArrayList<String>();
		
		cars.add("Opel");
		cars.add("BMW");
		cars.add("Mercedes-Benz");
		cars.add("Lada");
		cars.add("Toyota");
		cars.add("Ford");
		cars.add("Honda");
		System.out.println("Initial Array List: " + cars);
		System.out.println("--------------------------------------------------");
		
		for(String car : cars){
			System.out.println(cars.indexOf(car));
		}
		
		System.out.println("2nd element is " + cars.get(2));
		
		///// Sorting by string length
		System.out.println("This was sorted by string length:");
		Collections.sort(cars, new StringLengthComparator());
		for(String car : cars){
			System.out.println(car);
		}
		System.out.println("--------------------------------------------------");

		///// Reverting strings of array
		System.out.println("This was reverted:");
		Collections.sort(cars, new ReverseAlphabeticalComparator());
		for(String car : cars){
			System.out.println(car);
		}
		
		System.out.println("--------------------------------------------------");
		
		///// Change by places first and last letters in each second string of array
		System.out.println("First and last letters were changed in each second string of array: " + cars);
		for(int i = 1; i < cars.size(); i += 2){
			StringBuilder str = new StringBuilder (cars.get(i));
			char first = str.charAt(0);
			char last = str.charAt(str.length()-1);
			str.setCharAt((str.length()-1), first);
			str.setCharAt(0, last);
			System.out.println(str.toString());
	}
		}
	}
