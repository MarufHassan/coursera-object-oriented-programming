import edu.duke.*;

public class TestCaesarCipherTwo {
	public int[] countLetters(String message) {
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		int[] counts = new int[26];
		for (int i = 0; i < message.length(); i++) {
			char ch = Character.toLowerCase(message.charAt(i));
			int dex = alpha.indexOf(ch);
			if (dex != -1)
				counts[dex] += 1;
		}
		return counts;
	}

	private int maxIndex(int[] vals) {
		int maxDex = 0;
		for (int i = 0; i < vals.length; i++) {
			if (vals[i] > vals[maxDex])
				maxDex = i;
		}
		return maxDex;
	}

	private String halfOfString(String message, int start) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < message.length(); i += 2) {
			sb.append(message.charAt(i));
		}
		return sb.toString();
	}

	private int getKey(String s) {
		int[] counts = countLetters(s);
		int maxDex = maxIndex(counts);
		int dkey = maxDex - 4; // assume 'e' is the most frequent english letter
		if (maxDex < 4)
			dkey = 26 - (4 - maxDex);
		return dkey;
	}

	public void breakCaesarCipher(String input) {
		int key1 = getKey(halfOfString(input, 0));
		int key2 = getKey(halfOfString(input, 1));
		CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
		System.out.println("automatic determined key1: " + key1 + " key2: " + key2);
		System.out.println("decrypted message with automated key: " + cc.decrypt(input));
	}

	public void simpleTests() {
		FileResource fr = new FileResource();
		String message = fr.asString();
		CaesarCipherTwo cc = new CaesarCipherTwo(21, 8);
		String encrypted = cc.encrypt(message);
		System.out.println("encrypted message: " + encrypted);
		System.out.println("decrypted message: " + cc.decrypt(encrypted));
		breakCaesarCipher(encrypted);
	}
}
