
package filter;

import java.util.Arrays;
import java.util.List;

public class SpamFilter {

	public static boolean antiSpamFilter(final String text, final Double threshold) {

		final String[] forbiddenWords = {
			"sex", "sexo", "viagra", "cialis", "onemillion", "you'vewon", "hasganado", "nigeria"
		};

		final List<String> forbiddenList = Arrays.asList(forbiddenWords);

		Double totalWordsCount;
		double forbiddenWordsCount = 0.0;
		Double percentageOfForbiddenWords;
		boolean res = false;

		final String textWithoutExtraCharacters = text.replaceAll("[^a-zA-Z0-9 ]", "");
		final String[] palabras = textWithoutExtraCharacters.split(" ");
		totalWordsCount = (double) palabras.length;

		final String analiticText = text.toLowerCase().replaceAll(" ", "");

		for (final String forbiddenWord : forbiddenList) {

			int position = analiticText.indexOf(forbiddenWord);

			while (position != -1) {

				forbiddenWordsCount++;
				position = text.indexOf(forbiddenWord, position + 1);
			}
		}

		percentageOfForbiddenWords = forbiddenWordsCount / totalWordsCount * 100;

		if (percentageOfForbiddenWords >= threshold)
			res = true;

		return res;
	}

}
