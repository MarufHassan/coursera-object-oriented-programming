import edu.duke.*;

public class CaesarCipher {
	public String encrypt(String input, int key) {
		StringBuilder encrypted = new StringBuilder(input);
		String alphabetU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String alphabetL = "abcdefghijklmnopqrstuvwxyz";

		for (int i = 0; i < encrypted.length(); i++) {
			char currChar = encrypted.charAt(i);
			int idx = alphabetU.indexOf(Character.toUpperCase(currChar));
			if (idx != -1) {
				char newChar;
				if (Character.isUpperCase(currChar))
					newChar = alphabetU.charAt((idx + key) % alphabetU.length());
				else
					newChar = alphabetL.charAt((idx + key) % alphabetL.length());

				encrypted.setCharAt(i, newChar);
			}
		}
		return encrypted.toString();
	}

	public String encryptTwoKeys(String input, int key1, int key2) {
		StringBuilder encrypted = new StringBuilder(input);
		String alphabetU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String alphabetL = "abcdefghijklmnopqrstuvwxyz";

		for (int i = 0; i < encrypted.length(); i++) {
			char currChar = encrypted.charAt(i);
			int idx = alphabetU.indexOf(Character.toUpperCase(currChar));
			if (idx != -1) {
				char newChar;
				int key;
				if (i % 2 == 0)
					key = key1;
				else 
					key = key2;
				if (Character.isUpperCase(currChar))
					newChar = alphabetU.charAt((idx + key) % alphabetU.length());
				else
					newChar = alphabetL.charAt((idx + key) % alphabetL.length());

				encrypted.setCharAt(i, newChar);
			}
		}
		return encrypted.toString();
	}

	public void testCaesar() {
		int key = 15;
		FileResource fr = new FileResource();
		String message = fr.asString();
		String encrypted = encrypt(message, key);
		System.out.println(encrypted);
		String decrypted = encrypt(encrypted, 26 - key);
		System.out.println(decrypted);
		int key1 = 8;
		int key2 = 21;
		encrypted = encryptTwoKeys(message, key1, key2);
		System.out.println(encrypted);
		decrypted = encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
		System.out.println(decrypted);
	}
}
