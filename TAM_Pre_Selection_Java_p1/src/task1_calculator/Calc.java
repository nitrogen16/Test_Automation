package task1_calculator;

import java.util.Scanner;

public class Calc{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter 1st number:");
		int num1 = sc.nextInt();
		System.out.println("Enter operation. + - / * are available:");
		String oper = sc.next();
		System.out.println("Enter 2nd number:");
		int num2 = sc.nextInt();
		sc.close();
		System.out.println("Result is: " + CalcLogic.logicmethod(num1, oper, num2));
	}
}