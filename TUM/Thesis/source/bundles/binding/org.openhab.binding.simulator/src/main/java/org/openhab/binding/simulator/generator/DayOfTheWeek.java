package org.openhab.binding.simulator.generator;

import java.util.Calendar;
import java.util.Collections;

public enum DayOfTheWeek {
	
	SUNDAY(	"Sunday", Calendar.SUNDAY),
	MONDAY("Monday", Calendar.MONDAY),
	TUESDAY("Tuesday", Calendar.TUESDAY),
	WEDNESDAY("Wednesday", Calendar.WEDNESDAY),
	THURSDAY("Thursday", Calendar.THURSDAY),
	FRIDAY("Friday", Calendar.FRIDAY),
	SATURDAY("Saturday", Calendar.SATURDAY);
	

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
	
	public static DayOfTheWeek[] getDays(int firstDay){
		
		DayOfTheWeek[] actualWeek = DayOfTheWeek.values();
		DayOfTheWeek[] values = new DayOfTheWeek[7];
		
		int j = 0;
		for (int i =firstDay-1; i < actualWeek.length; i++) {
			values[j]= actualWeek[i];
			j++;
		}
		for (int i=0; i < firstDay-1; i++) {
			values[j]= actualWeek[i];
			j++;
		}
		return values;
	}
	
	
}
