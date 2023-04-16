
package filter;

import java.util.Scanner;

public class Main {

	public static void main(final String[] args) {

		final Scanner scanner = new Scanner(System.in);
		final Double threshold = 10.0;
		System.out.println("Introduce texto: ");
		final String text = scanner.nextLine();
		final boolean res = SpamFilter.antiSpamFilter(text, threshold);
		System.out.println("el texto es: " + res);

	}

}
