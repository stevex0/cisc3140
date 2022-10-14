import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.regex.Pattern;

public class LabDueDates {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); // Scanner object such that we can read input
		while (input.hasNext()) { // Loop until there is no more input to read
			printDaysDiff(input.next(), input.next());
		}
		input.close();
	}

	public static void printDaysDiff(String date1, String date2) {
		try {
			LocalDate localdate1 = getDate(date1);
			LocalDate localdate2 = getDate(date2);
			
			int difference = difference(localdate1, localdate2);
			if (difference < 0) { // Date1 is pass Date2, so it's difference is negative
				difference *= -1;
				System.out.printf("%s is %d days after %s\n", date1, difference, date2);
			} else { // Difference is positive, therefore Date2 is pass Date1
				System.out.printf("%s is %d days behind %s\n", date1, difference, date2);
			}
		} catch (DateTimeException e) { // Something went wrong tring to convert the string to a LocalDate object
			System.err.println(e.getMessage());
			return;
		}
	}

	// If the string given is a lab, quiz, or final, then it will return a predetermine date
	public static LocalDate getDate(String str) throws DateTimeException {
		switch (str.toLowerCase()) {
			case "today": return LocalDate.now();
			case "lab1": return LocalDate.of(2022, 9, 28);
			case "lab2": return LocalDate.of(2022, 10, 26);
			case "lab3": return LocalDate.of(2022, 11, 23);
			case "lab4": return LocalDate.of(2022, 12, 7);
			case "quiz1": return LocalDate.of(2022, 9, 14);
			case "quiz2": return LocalDate.of(2022, 9, 28);
			case "quiz4": return LocalDate.of(2022, 11, 16);
			case "final": return LocalDate.of(2022, 12, 9);
			default: return customDate(str);
		}
	}

	public static LocalDate customDate(String str) throws DateTimeException {
		try {
			String date = toMMDDYYYYFormat(str);
			return LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		} catch (DateTimeException e) {
			throw new DateTimeException(str + " could not be parse to a LocalDate");
		}
	}

	// Converts a custom format to an unified format (MM/dd/yyyy)
	public static String toMMDDYYYYFormat(String str) throws DateTimeException {
		final String MMDDYYYY = "(\\d{2})/(\\d{2})/(\\d{4})";
		final String MMDD = "(\\d{2})/(\\d{2})";
		if (Pattern.matches(MMDDYYYY, str)) {
			return str; // Already in MM/DD/YYYY format
		} else if (Pattern.matches(MMDD, str)) {
			// Get the next available date of month/day
			LocalDate now = LocalDate.now();
			int currentMonth = now.getMonthValue();
			int currentDay = now.getDayOfMonth();
			int year = now.getYear();

			// Since we know that str is in the MM/DD format,
			// we can split it into 2 parts (month, day)
			String[] split = str.split("/");
			int month = Integer.parseInt(split[0]);
			int day = Integer.parseInt(split[1]);

			// If the next available date is next year
			if (month == currentMonth && day < currentDay || month < currentMonth) {
				++year;
			}
			str += "/" + year; // MM/DD -> MM/DD/YYYY format
			return str;
		}
		throw new DateTimeException(str + " could not be parse to MM/DD/YYYY format"); // Unable to convert to MM/DD/YYYY format
	}

	// Returns the difference in days of two given LocalDate object
	// Equivalent to date1 - date2; value is negative if date1 is pass date2
	public static int difference(LocalDate date1, LocalDate date2) {
		return (int) date1.until(date2, ChronoUnit.DAYS);
	}
}
