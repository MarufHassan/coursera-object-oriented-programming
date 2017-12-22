import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;

    public WordsInFiles() {
        map = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String filename = f.getName();
        for (String word : fr.words()) {
            if (map.containsKey(word)) {
                ArrayList<String> t1 = map.get(word);
                if (!t1.contains(filename)) {
                    t1.add(filename);
                }
                map.put(word, t1);
            } else {
                ArrayList<String> t2 = new ArrayList<String>();
                t2.add(filename);
                map.put(word, t2);
            }
        }
    }

    private void buildWordFileMap() {
        map.clear();
        DirectoryResource dir = new DirectoryResource();
        for (File f : dir.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    private int maxNumber() {
        int max = Integer.MIN_VALUE;
        for (String s: map.keySet()) {
            ArrayList<String> t = map.get(s);
            if (t.size() > max)
                max = t.size();
        }
        return max;
    }

    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordList = new ArrayList<String>();
        for (String s: map.keySet()) {
            ArrayList<String> t = map.get(s);
            if (t.size() == number)
                wordList.add(s);
        }
        return wordList;
    }

    private void printFilesIn(String word) {
        if (map.containsKey(word)) {
            ArrayList<String> t = map.get(word);
            System.out.println("\"" + word + "\" appears in the files:");
            for (String filename : t) {
                System.out.println(filename);
            }
        }
    }

    public void tester() {
        buildWordFileMap();
        int number = 4;
        ArrayList<String> t = wordsInNumFiles(number);
        System.out.println("greatest number of files a word appears in is: " + number);
        System.out.println("words that appear in exactly " + number + " files: " + wordsInNumFiles(number));
        System.out.println("How many words are there that occur in " + number + " files?: " + t.size());
        printFilesIn("tree");
    }
}

