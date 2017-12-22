import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i < key.length; i++) {
            CaesarCracker cc = new CaesarCracker(mostCommon);
            String sliced = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(sliced);
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> maplanguages = new HashMap<String, HashSet<String>>();
        for (String filename : languages) {
            fr = new FileResource("dictionaries/" + filename);
            System.out.println(filename + " data loaded..");
            HashSet<String> dictionary = readDictionary(fr);
            maplanguages.put(filename, dictionary);
        }

        String decrypted = breakForAllLangs(encrypted, maplanguages);
        System.out.println("Decrypted message: ");
        System.out.println(decrypted);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> words = new HashSet<String>();
        for (String word : fr.words()) {
            words.add(word.toLowerCase());
        }
        return words;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxCount = 0;
        String decrypted = null;
        int keyLength = 0;
        char mostCommon = mostCommonCharIn(dictionary);
        for (int i = 1; i <= 100; i++) {
            int[] key = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String testDecrypted = vc.decrypt(encrypted);
            int count = countWords(testDecrypted, dictionary);
            if (count > maxCount) {
                maxCount = count;
                decrypted = testDecrypted;
                keyLength = key.length;
            }
        }
        return decrypted;
    }

    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxCount = 0;
        String decrypted = null;
        String language = null;
        for (String lang : languages.keySet()) {
            HashSet<String> dictionary = languages.get(lang);
            String testDecrypted = breakForLanguage(encrypted, dictionary);
            int count = countWords(testDecrypted, dictionary);
            if (count > maxCount) {
                maxCount = count;
                decrypted = testDecrypted;
                language = lang;
            }
        }
        System.out.println("maxCount " + maxCount);
        System.out.println("languages " + language);
        return decrypted;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char c = Character.toLowerCase(word.charAt(i));
                if (map.containsKey(c)) {
                    int value = map.get(c);
                    map.put(c, value + 1);
                } else {
                    map.put(c, 1);
                }
            }
        }
        int max = 0;
        char mostCommon = 'e';
        for (char c : map.keySet()) {
            int value = map.get(c);
            if (max < value) {
                max = value;
                mostCommon = c;
            }
        }
        return mostCommon;
    }
}
