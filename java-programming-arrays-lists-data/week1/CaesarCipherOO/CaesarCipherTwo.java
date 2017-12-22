
public class CaesarCipherTwo {
	private String alphabetU;
	private String alphabetL;
	private int mainKey1;
	private int mainKey2;

	public CaesarCipherTwo(int key1, int key2) {
		alphabetU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		alphabetL = "abcdefghijklmnopqrstuvwxyz";
		mainKey1 = key1;
		mainKey2 = key2;
	}

	public String encrypt(String input) {
		StringBuilder encrypted = new StringBuilder(input);

		for (int i = 0; i < encrypted.length(); i++) {
			char currChar = encrypted.charAt(i);
			int idx = alphabetU.indexOf(Character.toUpperCase(currChar));
			if (idx != -1) {
				char newChar;
				int key;
				if (i % 2 == 0)
					key = mainKey1;
				else 
					key = mainKey2;
				if (Character.isUpperCase(currChar))
					newChar = alphabetU.charAt((idx + key) % alphabetU.length());
				else
					newChar = alphabetL.charAt((idx + key) % alphabetL.length());

				encrypted.setCharAt(i, newChar);
			}
		}
		return encrypted.toString();
	}

	public String decrypt(String input) {
		CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
		return cc.encrypt(input);
	}
}
