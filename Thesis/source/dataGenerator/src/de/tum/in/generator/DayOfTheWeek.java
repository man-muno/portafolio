package de.tum.in.generator;

public enum DayOfTheWeek {

	MONDAY("Monday", 1),
	TUESDAY("Tuesday", 2),
	WEDNESDAY("Wednesday", 3),
	THURSDAY("Thursday", 4),
	FRIDAY("Friday", 5),
	SATURDAY("Saturday", 6),
	SUNDAY(	"Sunday", 7);

	private final String name;
	private final int number;

	private DayOfTheWeek(String name, int number) {
		this.name = name;
		this.number = number;

	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}
}
