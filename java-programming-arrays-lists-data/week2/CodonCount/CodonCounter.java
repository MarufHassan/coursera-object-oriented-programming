import edu.duke.*;
import java.util.*;

public class CodonCounter {
	private HashMap<String, Integer> map;

	public CodonCounter() {
		map = new HashMap<String, Integer>();
	}

	private void buildCodonMap(int start, String dna) {
		map.clear();
		dna = dna.toUpperCase();
		for (int i = start; i < dna.length() - 3; i += 3) {
			String sub = dna.substring(i, i + 3);
			if (map.containsKey(sub)) {
				int value = map.get(sub);
				map.put(sub, value + 1);
			} else {
				map.put(sub, 1);
			}
		}
	}

	private String getMostCommonCodon() {
		int max = 0;
		String maxCodons = null;
		for (String key : map.keySet()) {
			int value = map.get(key);
			if (value > max) {
				max = value;
				maxCodons = key;
			}
		}
		return maxCodons;
	}

	private void printCodonCounts(int start, int end) {
		System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
		for (String codon : map.keySet()) {
			int value = map.get(codon);
			if (value >= start && value <= end) {
				System.out.println(codon + " " + value);
			}
		}
	}

	public void tester() {
		FileResource fr = new FileResource();
		String dna = fr.asString().trim();
		for (int i = 0; i < 3; i++) {
			buildCodonMap(i, dna);
			System.out.println("Reading frame starting with " + i + " results in " + map.size() + " unique codons");
			String common = getMostCommonCodon();
			System.out.println("and most common codon is " + common + " with count " + map.get(common));
			printCodonCounts(1, 7);	
		}
	}
}
