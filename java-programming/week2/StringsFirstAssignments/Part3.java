
public class Part3 {
	public boolean twoOccurrences(String stringa, String stringb) {
		int count = 0;
		int lastIndex = 0;
		while (lastIndex != -1) {
			lastIndex = stringb.indexOf(stringa, lastIndex);
			if (lastIndex != -1) {
				count++;
				lastIndex += stringa.length();
			}
		}
		return count >= 2;
	}

	public String lastPart(String stringa, String stringb) {
		int index = stringb.indexOf(stringa);
		if (index == -1) 
			index = 0;
		else
			index += stringa.length();
		return stringb.substring(index);
	}

	public void testing() {
		System.out.println("\"by\" appears at least twice in \"A story by Abby Long\": " + twoOccurrences("by", "A story by Abby Long"));
		System.out.println("\"a\" appears at least twice in \"banana\": " + twoOccurrences("a", "banana"));
		System.out.println("\"atg\" appears at least twice in \"ctgtatgta\": " + twoOccurrences("atg", "ctgtatgta"));

		System.out.println("The part of the string after \"an\" in \"banana\" is " + lastPart("an", "banana"));
		System.out.println("The part of the string after \"zoo\" in \"forest\" is " + lastPart("zoo", "forest"));
	}
}
