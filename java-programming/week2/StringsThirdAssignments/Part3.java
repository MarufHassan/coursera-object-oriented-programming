import edu.duke.*;

public class Part3 {
	public StorageResource getAllGenes(String dna) {
		StorageResource res = new StorageResource();
		int startIndex = 0;
		while (true) {
			String currentGene = findGene(dna, startIndex);
			if (currentGene.isEmpty()) {
				break;
			}
			res.add(currentGene);
			startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
		}
		return res;
	}

	public int findStopCodon(String dna, int startIndex, String stopCodon) {
		int currIndex = dna.indexOf(stopCodon, startIndex + 3);
		while (currIndex != -1) {
			int diff = currIndex - startIndex;
			if (diff % 3 == 0) {
				return currIndex;
			} else {
				currIndex = dna.indexOf(stopCodon, currIndex + 3);
			}
		}
		return -1;
	}

	public String findGene(String dna, int where) {
		int startIndex = dna.indexOf("ATG", where);
		if (startIndex == -1) {
			return "";
		}

		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");

		int minIndex = 0;
		if (taaIndex == -1 || (tagIndex != -1 && tagIndex < taaIndex)) {
			minIndex = tagIndex;
		} else {
			minIndex = taaIndex;
		}

		if (minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex)) {
			minIndex = tgaIndex;
		}

		if (minIndex == -1) {
			return "";
		} else {
			return dna.substring(startIndex, minIndex + 3);
		}

	}

	public void processGenes(StorageResource sr) {
		int count = 0;

		System.out.println("all the Strings in sr that are longer than 60 characters: ");
		for (String s : sr.data()) {
			if (s.length() > 60) {
				System.out.println(s);
				count++;
			}
		}
		System.out.println("the number of Strings in sr that are longer than 60 characters: " + count);

		count = 0;
		System.out.println("all the Strings in sr that are longer than 9 characters: ");
		for (String s : sr.data()) {
			if (s.length() > 9) {
				System.out.println(s);
				count++;
			}
		}
		System.out.println("the number of Strings in sr that are longer than 9 characters: " + count);

		System.out.println("the Strings in sr whose C-G-ratio is higher than 0.35: ");
		count = 0;
		for (String s : sr.data()) {
			double cgRatio = cgRatio(s);
			if (cgRatio > 0.35) {
				System.out.println(s);
				count++;
			}
		}
		System.out.println("the number of strings in sr whose C-G-ratio is higher than 0.35: " + count);

		int max = Integer.MIN_VALUE;
		for (String s : sr.data()) {
			if (s.length() > max)
				max = s.length();
		}
		System.out.println("the length of the longest gene in sr: " + max);
		
	}

	public double cgRatio(String dna) {
		int cCount = 0;
		int gCount = 0;
		for (char c : dna.toCharArray()) {
			if (c == 'C' || c == 'c') cCount++;
			if (c == 'G' || c == 'g') gCount++;
		}
		return (float)(cCount + gCount) / dna.length();
	}

	public void testProcessGenes() {
		StorageResource sr = new StorageResource();
		sr.add("ATGGGGTTTCCCTAAGCC");
		sr.add("ATGTAA");
		sr.add("ATGCCCGGGTAA");
		sr.add("ATGTTTAAACTATGA");
		processGenes(sr);

		System.out.println();
		System.out.println();

		FileResource fr = new FileResource("GRch38dnapart.fa.txt");
		String dna = fr.asString();
		sr = getAllGenes(dna.toUpperCase());
		processGenes(sr);
	}
}
