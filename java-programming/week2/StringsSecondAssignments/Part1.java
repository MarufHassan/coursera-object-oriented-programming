import edu.duke.*;
public class Part1 {
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

	public void testFindStopCodon() {
		//			  01234567890123456789012345
		String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
		int dex = findStopCodon(dna, 0, "TAA");
		if (dex != 9) System.out.println("error on 9");

		dex = findStopCodon(dna, 9, "TAA");
		if (dex != 21) System.out.println("error on 21");

		dex = findStopCodon(dna, 1, "TAA");
		if (dex != -1) System.out.println("error on 26");

		dex = findStopCodon(dna, 0, "TAG");
		if (dex != -1) System.out.println("error on 26 TAG");

		System.out.println("tests finished");
	}

	public void testFindGene() {
		String dna = "ATAAGTTTTTATATAGAGAGA";
		System.out.println("Gene of DNA strand " + dna + " is: " + findGene(dna, 0));

		//        v
		dna = "ATAATGTTTTATATAGAGAGA";
		System.out.println("Gene of DNA strand " + dna + " is: " + findGene(dna, 0));

		//        v     v
		dna = "ATAATGTTTTAGTTAATAGAGAGA";
		System.out.println("Gene of DNA strand " + dna + " is: " + findGene(dna, 0));

		//        v           v  v     v
		dna = "ATAATGTTTTGGAAGTAATAGAGATGA";
		System.out.println("Gene of DNA strand " + dna + " is: " + findGene(dna, 0));

		//        v      v   v     v
		dna = "ATAATGTTTTTAAGTAGAGATGA"; // invalid
		System.out.println("Gene of DNA strand " + dna + " is: " + findGene(dna, 0));
	}

	public void printAllGenes(String dna) {
		int startIndex = 0;
		while (true) {
			String currentGene = findGene(dna, startIndex);
			if (currentGene.isEmpty()) {
				break;
			}
			System.out.println(currentGene);
			startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
		}
	}

	public void testOn(String dna) {
		System.out.println("Testing printAllGenes on " + dna);
		printAllGenes(dna);
	}

	public void test() {
		//      ATGv  TAAv  ATG   v  v  TGA
		testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
		testOn("");
		//      ATG   v  v  v  TAAv  v  v  ATGTAA
		testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
	}
}
