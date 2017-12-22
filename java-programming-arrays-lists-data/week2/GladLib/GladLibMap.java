import edu.duke.*;
import java.util.*;

public class GladLibMap {
	HashMap<String, ArrayList<String>> myMap;
	private ArrayList<String> seenWordList;
	private ArrayList<String> usedCategoryList;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
		myMap = new HashMap<String, ArrayList<String>>();
		myRandom = new Random();
		seenWordList = new ArrayList<String>();
		usedCategoryList = new ArrayList<String>();
		initializeFromSource(dataSourceDirectory);
	}
	
	public GladLibMap(String source){
		myMap = new HashMap<String, ArrayList<String>>();
		myRandom = new Random();
		seenWordList = new ArrayList<String>();
		usedCategoryList = new ArrayList<String>();
		initializeFromSource(source);
	}
	
	private void initializeFromSource(String source) {
		String[] categories = {"adjective", "noun", "verb", "color", "country", "name", "animal", "timeframe", "fruit"};
		for (String category: categories) {
			String t = source + "/" + category + ".txt";
			myMap.put(category, readIt(t));
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("number")){
			return "" + myRandom.nextInt(50) + 5;
		}
		if (!usedCategoryList.contains(label))
			usedCategoryList.add(label);
		return randomFrom(myMap.get(label));
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String label = w.substring(first+1,last);
		String sub = getSubstitute(label);
		while (seenWordList.contains(sub)) {
			sub = getSubstitute(label);
		}
		seenWordList.add(sub);
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}

	public int totalWordsInMap() {
		int total = 0;
		for (String s: myMap.keySet()) {
			total += myMap.get(s).size();
		}
		return total;
	}

	public int totalWordsConsidered() {
		int total = 0;
		for (String s: usedCategoryList) {
			total += myMap.get(s).size();
		}
		return total;
	}
	
	public void makeStory(){
		seenWordList.clear();
		usedCategoryList.clear();
	    System.out.println("\n");
		String story = fromTemplate("datalong/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("\n\nNumber of words that were replaced: " + seenWordList.size());
		System.out.println("total number of words: " + totalWordsInMap());
		System.out.println("total number of words considered: " + totalWordsConsidered());
	}
}
