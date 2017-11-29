import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyBirth {
	public void totalBirths(FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;

		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += 1;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
			} else {
				totalGirls += numBorn;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("total girls = " + totalGirls);
		System.out.println("total boys = " + totalBoys);
	}

	public void testTotalBirths() {
		 FileResource fr = new FileResource("data/yob1900.csv");
		 totalBirths(fr);
	}

	public int getRank(int year, String name, String gender) {
		String fname = "data/yob" + year + "short.csv";
		FileResource fr = new FileResource(fname);
		int rank = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				rank++;
				if (rec.get(0).equals(name)) {
					return rank;
				}
			}
		}
		return -1;
	}

	public void testGetRank() {
		int year = 1971;
		String name = "Frank";
		String gender = "M";
		System.out.println("rank of Name: \"" + name + "\", gender: " + gender + " in year " + year + " is " + getRank(year, name, gender));
		gender = "F";
		System.out.println("rank of Name: \"" + name + "\", gender: " + gender + " in year " + year + " is " + getRank(year, name, gender));
	}

	public String getName(int year, int rank, String gender) {
		String fname = "data/yob" + year + "short.csv";
		FileResource fr = new FileResource(fname);
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) 
				rank--;
			if (rank == 0)	
				return rec.get(0);
		}
		return "NO NAME";
	}

	public void testGetName() {
		int year = 1982;
		int rank = 450;
		String gender = "M";
		System.out.println("Name of rank " + rank + " in year " + year + " is: " + getName(year, rank, gender));
		rank = 6;
		System.out.println("Name of rank " + rank + " in year " + year + " is: " + getName(year, rank, gender));
	}

	public void whatIsNameInYear(String name, int year, int newYear, String gender) {
		int rank = getRank(year, name, gender);
		String newName = getName(newYear, rank, gender);
		System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
	}

	public void testWhatIsNameInYear() {
		String name = "Owen";
		int year = 1974;
		int newYear = 2014;
		String gender = "M";
		whatIsNameInYear(name, year, newYear, gender);
	}

	public int yearOfHighestRank(String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		int highestRank = Integer.MAX_VALUE;
		int highestRankYear = -1;

		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			int year = Integer.parseInt(f.getName().substring(3, 7));
			int currRank = getRank(year, name, gender);
			if (currRank != -1 && currRank < highestRank) {
				highestRank = currRank;
				highestRankYear = year;
			}
		}
		return highestRankYear;
	}

	public void testYearOfHighestRank() {
		String name = "Genevieve";
		String gender = "F";
		int year = yearOfHighestRank(name, gender);
		System.out.println("Name: " + name + " ranked " + getRank(year, name, gender) + " in year " + year);
	}

	public double getAverageRank(String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		int sum = 0;
		int count = 0;
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			int year = Integer.parseInt(f.getName().substring(3, 7));
			int rank = getRank(year, name, gender);
			if (rank > 0) sum += rank;
			count += 1;
		}
		if (sum > 0) return (double) sum / count;
		else return -1.0;
	}

	public void testGetAverageRank() {
		String name = "Susan";
		String gender = "F";
		System.out.println("average rank of Name " + name + " is: " + getAverageRank(name, gender));
		name = "Robert";
		gender = "M";
		System.out.println("average rank of Name " + name + " is: " + getAverageRank(name, gender));
	}

	public int getTotalBirthsRankedHigher(int year, String name, String gender) {
		String fname = "data/yob" + year + "short.csv";
		FileResource fr = new FileResource(fname);
		int totalBirths = 0;
		int rank = getRank(year, name, gender);
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				rank--;
				if (rank > 0) 
					totalBirths += Integer.parseInt(rec.get(2));
			}
		}
		return totalBirths;
	}

	public void testGetTotalBirthsRankedHigher() {
		String name = "Drew";
		String gender = "M";
		int year = 1990;
		System.out.println("Total Births that are higher than ranked is: " + getTotalBirthsRankedHigher(year, name, gender));
	}
}
