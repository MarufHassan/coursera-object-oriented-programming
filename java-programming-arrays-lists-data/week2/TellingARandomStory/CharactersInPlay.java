import java.util.ArrayList;
import edu.duke.FileResource;

public class CharactersInPlay {
	private ArrayList<String> names;
	private ArrayList<Integer> counts;

	public CharactersInPlay() {
		names = new ArrayList<String>();
		counts = new ArrayList<Integer>();
	}

	public void update(String person) {
		int index = names.indexOf(person);
		if (index == -1) {
			names.add(person);
			counts.add(1);
		} else {
			int value = counts.get(index);
			counts.set(index, value + 1);
		}
	}

	public void findAllCharacters() {
		FileResource fr = new FileResource();
		names.clear();
		counts.clear();
		for (String line : fr.lines()) {
			int index = line.indexOf(".");
			if (index != -1) {
				update(line.substring(0, index).trim());
			}
		}
	}

	public int findIndexOfMax() {
		int max = 0;
		for (int i = 0; i < counts.size(); i++) {
			int value = counts.get(i);
			if (value > counts.get(max))
				max = i;
		}
		return max;
	}

	public void charactersWithNumParts(int num1, int num2) {
		for (int i = 0; i < names.size(); i++) {
			int number = counts.get(i);
			if (number >= num1 && number <= num2)
				System.out.println(names.get(i) + " " + counts.get(i));
		}
	}

	public void tester() {
		findAllCharacters();
		int index = findIndexOfMax();
		System.out.println("character with the most speaking parts: " + names.get(index) + " " + counts.get(index));
		charactersWithNumParts(50, 1500);
	}
}
