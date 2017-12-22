import edu.duke.*;

public class TestCaesarCipher {
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

	public void breakCaesarCipher(String input) {
		int[] freqs = countLetters(input);
		int maxDex = maxIndex(freqs);
		int dkey = maxDex - 4; // assume 'e' is the most frequent english letter
		if (maxDex < 4)
			dkey = 26 - (4 - maxDex);
		CaesarCipher cc = new CaesarCipher(dkey);
		System.out.println("automatic determined key: " + dkey);
		System.out.println("decrypted message with automated key: " + cc.decrypt(input));
	}

	public void simpleTests() {
		FileResource fr = new FileResource();
		String message = fr.asString();
		CaesarCipher cc = new CaesarCipher(15);
		String encrypted = cc.encrypt(message);
		System.out.println("encrypted message: " + encrypted);
		System.out.println("decrypted message: " + cc.decrypt(encrypted));
		breakCaesarCipher(encrypted);
	}
}
