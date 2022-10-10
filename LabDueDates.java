import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LabDueDates {
	public static void main(String[] args) {
		printLab("Lab 1", 9, 28); 	// 09/02/2022
		printLab("Lab 2", 10, 26);	// 10/26/2022
		printLab("Lab 3", 11, 23); 	// 11/23/2022
		printLab("Lab 4", 12, 7); 	// 12/07/2022
	}

	public static void printLab(String title, int month, int day) {
		final LocalDateTime dueDate = LocalDateTime.of(2022, month, day, 15, 30); // month/day/2022 at 3:30pm
		final LocalDateTime now = LocalDateTime.now();
		final String date = dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' h:mma"));
		if (now.isAfter(dueDate)) {
			System.out.printf("%s (%s): Pass Due\n", title, date);
		} else {
			long seconds = now.until(dueDate, ChronoUnit.SECONDS) % 60;
			long minutes = now.until(dueDate, ChronoUnit.MINUTES) % 60;
			long hours = now.until(dueDate, ChronoUnit.HOURS) % 24;
			long days = now.until(dueDate, ChronoUnit.DAYS);
			System.out.printf("%s (%s): %d Days %d Hours %d Minutes %d Seconds Remaining\n", title, date, days, hours, minutes, seconds);
		}
	}
}
