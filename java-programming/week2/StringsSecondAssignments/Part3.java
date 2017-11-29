import edu.duke.*;
public class Part3 {
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

	public int countGenes(String dna) {
		int count = 0;
		int startIndex = 0;
		while (true) {
			String gene = findGene(dna, startIndex);
			if (gene.isEmpty()) break;
			count++;
			startIndex = dna.indexOf(gene, startIndex) + gene.length();
		}
		return count;
	}

	public void testCountGenes() {
		String dna = "ATAAGTTTTTATATAGAGAGA";
		System.out.println("Number of Gene in DNA strand " + dna + " is: " + countGenes(dna));

		//        v
		dna = "ATAATGTTTTATATAGAGAGA";
		System.out.println("Number of Gene in DNA strand " + dna + " is: " + countGenes(dna));

		//        v     v
		dna = "ATAATGTTTTAGTTAATAGAGAGA";
		System.out.println("Number of Gene in DNA strand " + dna + " is: " + countGenes(dna));

		//        v           v  v     v
		dna = "ATAATGTTTTGGAAGTAATAGAGATGA";
		System.out.println("Number of Gene in DNA strand " + dna + " is: " + countGenes(dna));

		//        v      v   v     v
		dna = "ATAATGTTTTTAAGTAGAGATGA"; // invalid
		System.out.println("Number of Gene in DNA strand " + dna + " is: " + countGenes(dna));

		//     v  v   v     v
		dna = "ATGTAAGATGCCCTAGT";
		System.out.println("Number of Gene in DNA strand " + dna + " is: " + countGenes(dna));
		
		FileResource fr = new FileResource();
		dna = fr.asString();
		System.out.println(countGenes(dna));
	}
}
