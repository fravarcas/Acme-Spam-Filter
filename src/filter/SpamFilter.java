
package filter;

import java.util.Arrays;
import java.util.List;

public class SpamFilter {

	public static boolean antiSpamFilter(final String text, final Double threshold) {

		final String[] forbiddenWords = {
			"Sex", "Sexo", "Viagra", "Cialis", "OneMillion", "UnMillon", "YouveWon", "HasGanado", "Nigeria"
		};

		final List<String> forbiddenList = Arrays.asList(forbiddenWords);

		Double totalWordsCount;
		double forbiddenWordsCount = 0.0;
		Double percentageOfForbiddenWords;
		boolean res = false;
		String newString = "";

		final String textWithoutExtraCharacters = text.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
		final String textWithoutExtraSpaces = textWithoutExtraCharacters.replaceAll("\\s+", " ");
		final String[] words = textWithoutExtraSpaces.split(" ");
		for (int i = 0; i < words.length; i++) {

			final String palabra = words[i];
			final String firstLetter = palabra.substring(0, 1);
			final String restWord = palabra.substring(1);
			final String firstLetterUppercase = firstLetter.toUpperCase();
			final String wordUppercase = firstLetterUppercase + restWord;
			newString += wordUppercase + " ";

		}

		totalWordsCount = (double) words.length;

		final String analiticText = newString.toString().replaceAll(" ", "").concat("A");

		for (final String forbiddenWord : forbiddenList) {

			int position = analiticText.indexOf(forbiddenWord);

			while (position != -1) {
				if (Character.isUpperCase(analiticText.charAt(position + forbiddenWord.length())))
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
