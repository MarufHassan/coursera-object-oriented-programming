public class Part1 {
	public String findSimpleGene(String dna) {
		String result = "";

		int startIndex = dna.indexOf("ATG");
		int stopIndex = dna.indexOf("TAA", startIndex + 3) + 3;

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
		// "AATGCTAGGGTAATATGGT" valid gene
		// "ATCCTATGTTCGGCTGCTCTAATATGGT" gene is not a multiple of 3
		String[] dnas = {"ATCGTTAACT", "ATATGCTAAGT", "TAGCCAGTACGTCAG", "AATGCGTAATATGGT", 
						"AATGCTAGGGTAATATGGT", "ATCCTATGTTCGGCTGCTCTAATATGGT"};


		for (String dna : dnas) {
			System.out.println("DNA stands is " + dna);
			String gene = findSimpleGene(dna);
			System.out.println("Gene is " + gene);
		}
	}
}
