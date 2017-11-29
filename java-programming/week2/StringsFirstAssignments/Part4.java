import edu.duke.*;

public class Part4 {
	public void printYoutubeURL() {
		URLResource res = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
		for (String word : res.words()) {
			int pos = word.toLowerCase().indexOf("youtube.com");
			if (pos != -1) {
				int startIndex = word.lastIndexOf("\"", pos - 1);
				int endIndex = word.indexOf("\"", pos + "youtube.com".length());
				if (startIndex >= 0 && endIndex >= 0)
					System.out.println(word.substring(startIndex + 1, endIndex));
			}
		}
	}
}
