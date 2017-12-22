import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
     
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }
        
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String s : fr.lines()) {
            records.add(WebLogParser.parseEntry(s));
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {
        System.out.println("Log Entrys that have a status code greater than " + num);
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            if (date.indexOf(someday) != -1) {
                String ipAddr = le.getIpAddress();
                if (!uniqueIPs.contains(ipAddr)) {
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode >= low && statusCode <= high) {
                String ipAddr = le.getIpAddress();
                if (!uniqueIPs.contains(ipAddr)) {
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
    	HashMap<String, Integer> counts = new HashMap<String, Integer>();
    	for (LogEntry le : records) {
    		String ipAddr = le.getIpAddress();
    		if (counts.containsKey(ipAddr)) {
    			int value = counts.get(ipAddr);
    			counts.put(ipAddr, value + 1);
    		} else {
    			counts.put(ipAddr, 1);
    		}
    	}
    	return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
    	int max = 0;
    	for (String ipAddr : counts.keySet()) {
    		int value = counts.get(ipAddr);
    		if (value > max)
    			max = value;
    	}
    	return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
    	ArrayList<String> ips = new ArrayList<String>();
    	int max = mostNumberVisitsByIP(counts);
    	for (String ipAddr : counts.keySet()) {
    		int value = counts.get(ipAddr);
    		if (value == max)
    			ips.add(ipAddr);
    	}
    	return ips;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
    	HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    	for (LogEntry le : records) {
    		String day = le.getAccessTime().toString().substring(4, 10);;
    		ArrayList<String> temp;
    		if (map.containsKey(day)) {
    			temp = map.get(day);
    		} else {
    			temp = new ArrayList<String>();
    		}
    		temp.add(le.getIpAddress());
    		map.put(day, temp);
    	}
    	return map;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
    	int max = 0;
    	String mostVisitedDay = null;
    	for (String day : map.keySet()) {
    		ArrayList<String> temp = map.get(day);
    		if (temp.size() > max) {
    			max = temp.size();
    			mostVisitedDay = day;
    		}
    	}
    	return mostVisitedDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String someday) {
    	ArrayList<String> ips = map.get(someday);

    	HashMap<String, Integer> counts = new HashMap<String, Integer>();
    	for (String ip : ips) {
    		if (counts.containsKey(ip)) {
    			int value = counts.get(ip);
    			counts.put(ip, value + 1);
    		} else {
    			counts.put(ip, 1);
    		}
    	}
    	return iPsMostVisits(counts);
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
}
