
public class Part2 {
	public String findSimpleGene(String dna, String startCodon, String stopCodon) {
		String result = "";

		int startIndex = dna.toLowerCase().indexOf(startCodon.toLowerCase());
		int stopIndex = dna.toLowerCase().indexOf(stopCodon.toLowerCase(), startIndex + 3) + 3;

		if (startIndex >= 0 && stopIndex >= 0) {
			if ((stopIndex - startIndex) % 3 == 0) {
				result = dna.substring(startIndex, stopIndex);
			}
		}
		return result;
	}

	public void testSimpleGene() {
		// "ATCGTTAACT" no ATG
		// "ATATGCTAAGT" no TAA
		// "TAGCCAGTACGTCAG" no ATG, no TAA
		// "AATGCGTAATATGGT" gene is not a multiple of 3
		// "aatgctagggtaatatggt" valid gene
		// "ATCCTATGTTCGGCTGCTCTAATATGGT" gene is not a multiple of 3
		String[] dnas = {"ATCGTTAACT", "ATATGCTAAGT", "TAGCCAGTACGTCAG", "AATGCGTAATATGGT", 
						"aatgctagggtaatatggt", "ATCCTATGTTCGGCTGCTCTAATATGGT"};


		for (String dna : dnas) {
			System.out.println("DNA stands is " + dna);
			String gene = findSimpleGene(dna, "ATG", "TAA");
			System.out.println("Gene is " + gene);
		}
	}
}
