import edu.duke.*;

public class CaesarCipher {
	private String alphabetU;
	private String alphabetL;
	private int mainKey;

	public CaesarCipher(int key) {
		mainKey = key;
		alphabetU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		alphabetL = "abcdefghijklmnopqrstuvwxyz";
	}

	public String encrypt(String input) {
		StringBuilder encrypted = new StringBuilder(input);
		for (int i = 0; i < encrypted.length(); i++) {
			char currChar = encrypted.charAt(i);
			int idx = alphabetU.indexOf(Character.toUpperCase(currChar));
			if (idx != -1) {
				char newChar;
				if (Character.isUpperCase(currChar))
					newChar = alphabetU.charAt((idx + mainKey) % alphabetU.length());
				else
					newChar = alphabetL.charAt((idx + mainKey) % alphabetL.length());

				encrypted.setCharAt(i, newChar);
			}
		}
		return encrypted.toString();
	}

	public String decrypt(String input) {
		CaesarCipher cc = new CaesarCipher(26 - mainKey);
		return cc.encrypt(input);
	}
}
