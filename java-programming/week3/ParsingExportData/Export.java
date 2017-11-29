import edu.duke.*;
import org.apache.commons.csv.*;

public class Export {

	public String countryInfo(CSVParser parser, String country) {
		for (CSVRecord record : parser) {
			if (country.equals(record.get("Country"))) {
				return record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
			}
		}
		return "Not found";
	}

	public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
		for (CSVRecord record : parser) {
			String export = record.get("Exports");
			if (export.contains(exportItem1) && export.contains(exportItem2)) {
				System.out.println(record.get("Country"));
			}
		}
	}

	public int numberOfExporters(CSVParser parser, String exportItem) {
		int count = 0;
		for (CSVRecord record : parser) {
			String export = record.get("Exports");
			if (export.contains(exportItem)) {
				count++;
			}
		}
		return count;
	}

	public void bigExporters(CSVParser parser, String amount) {
		for (CSVRecord record : parser) {
			String value = record.get("Value (dollars)");
			if (value.length() > amount.length()) {
				System.out.println(record.get("Country") + " " + value);
			}
		}
	}

	public void tester() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		System.out.println(countryInfo(parser, "Nauru"));
	 	System.out.println(countryInfo(parser, "Germany"));
	 	System.out.println(countryInfo(parser, "Bangladesh"));
	 	System.out.println();

	 	parser = fr.getCSVParser();
	 	System.out.println("countries that export cotton and flowers: ");
	 	listExportersTwoProducts(parser, "cotton", "flowers");
	 	System.out.println();

	 	parser = fr.getCSVParser();
	 	System.out.println("Number of countries that export cocoa: " + numberOfExporters(parser, "cocoa"));
	 	System.out.println();

	 	parser = fr.getCSVParser();
	 	System.out.println("countries whose Value (dollars) string is longer than the amount string:");
	 	bigExporters(parser, "$999,999,999,999");
	}
}
