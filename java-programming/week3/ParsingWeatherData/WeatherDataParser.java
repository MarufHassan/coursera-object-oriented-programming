import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class WeatherDataParser {
	public CSVRecord coldestHourInFile(CSVParser parser) {
		CSVRecord coldestSoFar = null;
		for (CSVRecord record : parser) {
			double currentTemp = Double.parseDouble(record.get("TemperatureF"));
			if (currentTemp == -9999) continue;
			coldestSoFar = getColdestOfTwo(record, coldestSoFar);
		}
		return coldestSoFar;
	}

	public String fileWithColdestTemperature() {
		DirectoryResource dr = new DirectoryResource();
		CSVRecord coldestSoFar = null;
		File coldestFile = null;

		for (File f: dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord current = coldestHourInFile(fr.getCSVParser());
			if (coldestSoFar == null) {
				coldestSoFar = current;
				coldestFile = f;
			} else {
				double currentTemp = Double.parseDouble(current.get("TemperatureF"));
				double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
				if (currentTemp < coldestTemp) {
					coldestSoFar = current;
					coldestFile = f;
				}
			}
		}
		return coldestFile.getName();
	}

	public CSVRecord getColdestOfTwo(CSVRecord currentRow, CSVRecord coldestSoFar) {
		if (coldestSoFar == null) {
			coldestSoFar = currentRow;
		} else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
			if (currentTemp < coldestTemp) {
				coldestSoFar = currentRow;
			}
		}
		return coldestSoFar;
	}

	public CSVRecord getLowestHumidityOfTwo(CSVRecord currentRow, CSVRecord lowestHumidity) {
		if (lowestHumidity == null) {
			lowestHumidity = currentRow;
		} else {
			int currentH = Integer.parseInt(currentRow.get("Humidity"));
			int lowestH = Integer.parseInt(lowestHumidity.get("Humidity"));
			if (currentH < lowestH) {
				lowestHumidity = currentRow;
			}
		}
		return lowestHumidity;
	}

	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		CSVRecord lowestHumidity = null;
		for (CSVRecord currentRow : parser) {
			if (currentRow.get("Humidity").equals("N/A"))
				continue;
			lowestHumidity = getLowestHumidityOfTwo(currentRow, lowestHumidity);
		}
		return lowestHumidity;
	}

	public CSVRecord lowestHumidityInManyFiles() {
		DirectoryResource dr = new DirectoryResource();
		CSVRecord lowestHumidity = null;
		for (File f: dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentHumidity = lowestHumidityInFile(fr.getCSVParser());
			lowestHumidity = getLowestHumidityOfTwo(currentHumidity, lowestHumidity);
		}
		return lowestHumidity;
	}

	public double averageTemperatureInFile(CSVParser parser) {
		double sum = 0.0;
		int count = 0;
		for (CSVRecord record : parser) {
			sum += Double.parseDouble(record.get("TemperatureF"));
			count++;
		}
		return sum / count;
	}

	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
		double sum = 0.0;
		int count = 0;
		for (CSVRecord record : parser) {
			double humidity = Double.parseDouble(record.get("Humidity"));
			if (humidity >= value) {
				sum += Double.parseDouble(record.get("TemperatureF"));
				count++;
			}
		}
		return sum / count;
	}

	public void testColdestHourInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord coldest = coldestHourInFile(parser);
		System.out.println("Coldest temperature was: " + coldest.get("TemperatureF") + " at " + coldest.get("TimeEST"));
	}

	public void testFileWithColdestTemperature() {
		String filename = fileWithColdestTemperature();
		FileResource fr = new FileResource(filename);
		System.out.println("Coldest day was in file: " + filename);
		CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest temperature on that day was: " + coldestRecord.get("TemperatureF"));
		System.out.println("All the temperature on the coldest day were: ");
		for (CSVRecord record : fr.getCSVParser()) {
			System.out.println(record.get("DateUTC") + " " + record.get("TemperatureF"));
		}
	}

	public void testLowestHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord humidity = lowestHumidityInFile(parser);
		System.out.println("Lowest Humidity was: " + humidity.get("Humidity") + " at " + humidity.get("DateUTC"));
	}

	public void testLowestHumidityInManyFiles() {
		CSVRecord humidity = lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was: " + humidity.get("Humidity") + " at " + humidity.get("DateUTC"));
	}

	public void testAverageTemperatureInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		System.out.println("Average temperature in file is: " + averageTemperatureInFile(parser));
	}

	public void testAverageTemperatureWithHighHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		System.out.println("Average temp with high humidity is: " + averageTemperatureWithHighHumidityInFile(parser, 80));
	}
}
