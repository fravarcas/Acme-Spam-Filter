
package filter;

import java.util.Arrays;
import java.util.List;

public class SpamFilter {

	public static boolean antiSpamFilter(final String text, final Double threshold) {

		final String[] forbiddenWords = {
			"Sex", "Sexo", "Viagra", "Cialis", "OneMillion", "UnMillon", "You'veWon", "HasGanado", "Nigeria"
		};

		final List<String> forbiddenList = Arrays.asList(forbiddenWords);

		Double totalWordsCount;
		double forbiddenWordsCount = 0.0;
		Double percentageOfForbiddenWords;
		boolean res = false;
		String nuevaCadena = "";

		final String textWithoutExtraCharacters = text.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
		final String[] palabras = textWithoutExtraCharacters.split(" ");
		final List<String> wordList = Arrays.asList(palabras);
		for (int i = 0; i < palabras.length; i++) {

			final String palabra = palabras[i];
			final String primeraLetra = palabra.substring(0, 1);
			final String restoPalabra = palabra.substring(1);
			final String primeraLetraMayuscula = primeraLetra.toUpperCase();
			final String palabraMayuscula = primeraLetraMayuscula + restoPalabra;
			nuevaCadena += palabraMayuscula + " ";

		}

		totalWordsCount = (double) palabras.length;

		final String analiticText = nuevaCadena.toString().replaceAll(" ", "").concat("a");

		for (final String forbiddenWord : forbiddenList) {

			int position = analiticText.indexOf(forbiddenWord);

			while (position != -1) {
				if (!Character.isUpperCase(analiticText.charAt(position + forbiddenWord.length())))
					forbiddenWordsCount++;
				position = analiticText.indexOf(forbiddenWord, position + 1);
			}
		}

		percentageOfForbiddenWords = forbiddenWordsCount / totalWordsCount * 100;

		if (percentageOfForbiddenWords >= threshold)
			res = true;

		return res;
	}

}
