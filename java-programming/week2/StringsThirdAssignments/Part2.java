
public class Part2 {
    public double cgRatio(String dna) {
		int cCount = 0;
		int gCount = 0;
		for (char c : dna.toCharArray()) {
			if (c == 'C' || c == 'c') cCount++;
			if (c == 'G' || c == 'g') gCount++;
		}
		return (double)(cCount + gCount) / dna.length();
	}

	public int countCTG(String dna) {
		int count = 0;
		int currIndex = 0;
		while (true) {
			currIndex = dna.indexOf("CTG", currIndex);
			if (currIndex == -1) break;
			count++;
			currIndex += 3;
		}
		return count;
	}

	public void testCGRatio() {
		System.out.println(cgRatio("ATGCCATAG"));
	}
}
