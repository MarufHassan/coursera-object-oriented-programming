import edu.duke.*;

public class WordLength {
	public void countWordLengths(FileResource resource, int[] counts) {
		for (String word : resource.words()) {
			int wordLength = word.length();
			if (!Character.isLetter(word.charAt(0)))
				wordLength--;
			if (!Character.isLetter(word.charAt(word.length() - 1)))
				wordLength--;
			if (wordLength >= counts.length)
				wordLength = counts.length - 1;
			if (wordLength > 0)
				counts[wordLength]++;
		}
	}

	public int indexOfMax(int[] counts) {
		int maxIndex = 0;
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > counts[maxIndex])
				maxIndex = i;
		}
		return maxIndex;
	}

	public void testCountWordLengths() {
		FileResource fr = new FileResource();
		int[] counts = new int[31];
		countWordLengths(fr, counts);
		System.out.println("Number of word with length ");
		for (int i = 1; i < counts.length; i++) {
			System.out.println(i + " is " + counts[i]);
		}
		System.out.println("largest word length: " + counts[indexOfMax(counts)]);
	}
}
