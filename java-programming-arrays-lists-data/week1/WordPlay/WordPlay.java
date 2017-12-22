
public class WordPlay {
	public boolean isVowel(char ch) {
		return "AEIOUaeiou".indexOf(ch) != -1;
	}

	public String replaceVowels(String phrase, char ch) {
		StringBuilder outPhrase = new StringBuilder(phrase);
		for (int i = 0; i < outPhrase.length(); i++) {
			if (isVowel(outPhrase.charAt(i))) {
				outPhrase.setCharAt(i, ch);
			}
		}
		return outPhrase.toString();
	}

	public void testReplaceVowels() {
		String original = "Hello World";
		String replaced = replaceVowels(original, '*');
		System.out.println("Original phrase: " + original);
		System.out.println("Replaced vowel phrase: " + replaced);
	}

	public String emphasize(String phrase, char ch) {
		StringBuilder outPhrase = new StringBuilder(phrase);
		for (int i = 0; i < outPhrase.length(); i++) {
			char currChar = outPhrase.charAt(i);
			if (Character.toLowerCase(currChar) == Character.toLowerCase(ch)) {
				if (i % 2 == 0) {
					outPhrase.setCharAt(i, '*');
				} else {
					outPhrase.setCharAt(i, '+');
				}
			}
		}
		return outPhrase.toString();
	}

	public void testEmphasize() {
		System.out.println(emphasize("dna ctgaaactga", 'a'));
		System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
	}
}
