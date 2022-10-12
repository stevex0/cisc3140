import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LabDueDates {
	public static void main(String[] args) {
		printDaysDiff("Lab1", "Today");
		printDaysDiff("Lab2", "Today");
		printDaysDiff("Lab3", "Today");
		printDaysDiff("Lab4", "Today");
	}

	public static void printDaysDiff(String date1, String date2) {
		LocalDate localdate1 = getDate(date1);
		LocalDate localdate2 = getDate(date2);
		if (localdate1 == null || localdate2 == null) {
			return;
		}

		int difference = difference(localdate1, localdate2);
		String localdate1f = formatDate(localdate1);
		String localdate2f = formatDate(localdate2);
		if (difference < 0) { // Date1 is pass Date2, so it's difference is negative
			difference *= -1;
			System.out.printf("%s(%s) is %d days between %s(%s)\n",
				date1, localdate1f, difference, date2, localdate2f
			);
		} else { // Difference is positive, therefore Date2 is pass Date1
			System.out.printf("%s(%s) is %d days pass %s(%s)\n",
				date1, localdate1f, difference, date2, localdate2f
			);
		}
	}

	// If the string given is a lab, quiz, or final, then it will return a predetermine date
	public static LocalDate getDate(String str) {
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
			default:
				return null;
		}
	}

	// Returns the difference in dayd of two given LocalDate object
	public static int difference(LocalDate date1, LocalDate date2) {
		return (int) date1.until(date2, ChronoUnit.DAYS);
	}

	// Given a LocalDate object, returns a string in MM/dd/yyyy format
	public static String formatDate(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	}
}
