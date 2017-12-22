import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log");
        la.printAll();
    }

    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        System.out.println("Number of unique ip address: " + la.countUniqueIPs());
    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog1_log");
        la.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        String someday = "Sep 27";
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay(someday);
        System.out.println("Unique IPs in " + someday + " are:\n" + uniqueIPs);
        System.out.println("Total IPs: " + uniqueIPs.size());

        someday = "Sep 30";
        uniqueIPs = la.uniqueIPVisitsOnDay(someday);
        System.out.println("Unique IPs in " + someday + " are:\n" + uniqueIPs);
    }

    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        int low = 200; int high = 299;
        System.out.println("Number of Unique IPs that have a status code from " + low + " to " + high + ": \n" + la.countUniqueIPsInRange(low, high));

        low = 300; high = 399;
        System.out.println("Number of Unique IPs that have a status code from " + low + " to " + high + ": \n" + la.countUniqueIPsInRange(low, high));
    }

    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        for (String ip : counts.keySet()) {
            System.out.println(ip + " " + counts.get(ip));
        }
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        int max = la.mostNumberVisitsByIP(la.countVisitsPerIP());
        System.out.println("Maximum number of visits to this website by a single IP address: " + max);
    }

    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println("Maximum number of visits IPs: ");
        System.out.println(la.iPsMostVisits(counts));
    }

    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog3-short_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        for (String day : map.keySet()) {
            System.out.println(day + " " + map.get(day));
        }
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        System.out.println("Day with most ip visits: " + la.dayWithMostIPVisits(map));
    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        String day = "Sep 29";
        System.out.println("IP addresses that had the most accesses on day " + day);
        System.out.println(la.iPsWithMostVisitsOnDay(map, day));
    }
}
