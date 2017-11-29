public class Part2 {
	public int howMany(String stringa, String stringb) {
		int count = 0;
		int currIndex = 0;
		while (true) {
			currIndex = stringb.indexOf(stringa, currIndex);
			if (currIndex == -1) break;
			count++;
			currIndex += stringa.length();
		}
		return count;
	}

	public void testHowMany() {
		System.out.println("\"GAA\" apprear in \"ATGAACGAATTGAATC\" " + howMany("GAA", "ATGAACGAATTGAATC") + " time(s)");
		System.out.println("\"GAA\" apprear in \"ATGAACGAATTGAA\" " + howMany("GAA", "ATGAACGAATTGAA") + " time(s)");
		System.out.println("\"AA\" apprear in \"ATAAAA\" " + howMany("AA", "ATAAAA") + " time(s)");
		System.out.println("\"AA\" apprear in \"A\" " + howMany("AA", "A") + " time(s)");
		System.out.println("\"AA\" apprear in \"\" " + howMany("AA", "") + " time(s)");
	}
}
