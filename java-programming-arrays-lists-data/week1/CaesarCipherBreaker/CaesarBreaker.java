import edu.duke.*;

public class CaesarBreaker {
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

	public int maxIndex(int[] vals) {
		int maxDex = 0;
		for (int i = 0; i < vals.length; i++) {
			if (vals[i] > vals[maxDex])
				maxDex = i;
		}
		return maxDex;
	}

	public String halfOfString(String message, int start) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < message.length(); i += 2) {
			sb.append(message.charAt(i));
		}
		return sb.toString();
	}

	public int getKey(String s) {
		int[] counts = countLetters(s);
		int maxDex = maxIndex(counts);
		int dkey = maxDex - 4; // assume 'e' is the most frequent english letter
		if (maxDex < 4)
			dkey = 26 - (4 - maxDex);
		return dkey;
	}

	public String decryptTwoKeys(String encrypted) {
		int key1 = getKey(halfOfString(encrypted, 0));
		int key2 = getKey(halfOfString(encrypted, 1));
		System.out.println("key1: " + key1 + " key2: " + key2);
		CaesarCipher cc = new CaesarCipher();
		return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
	}

	public String decrypt(String encrypted) {
		CaesarCipher cc = new CaesarCipher();
		int[] freqs = countLetters(encrypted);
		int maxDex = maxIndex(freqs);
		int dkey = maxDex - 4; // assume 'e' is the most frequent english letter
		if (maxDex < 4)
			dkey = 26 - (maxDex - 4);
		return cc.encrypt(encrypted, 26 - dkey);
	}

	public void testDecrypt() {
		FileResource fr = new FileResource();
		String message = fr.asString();
		CaesarCipher cc = new CaesarCipher();
		String encrypted = cc.encrypt(message, 23);
		String decrypted = decrypt(encrypted);
		System.out.println("encrypted message: " + encrypted);
		System.out.println("decrypted message: " + decrypted);
	}

	public void testDecryptTwoKeys() {
		FileResource fr = new FileResource();
		String encrypted = fr.asString();
		CaesarCipher cc = new CaesarCipher();
		String decrypted = decryptTwoKeys(encrypted);
		System.out.println("decrypted message: " + decrypted);	
	}
}
