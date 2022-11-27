package application;

import java.util.Locale;
import java.util.Scanner;

import model.services.Sales;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the file .csv location: ");
		String path = sc.next();

		Sales sales = new Sales(path);

		// Gera o arquivo summary.csv
		sales.generateSummary();
		
		System.out.println(sales);
		
		sc.close();
	}
	
}
