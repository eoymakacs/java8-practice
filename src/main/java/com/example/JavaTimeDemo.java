package com.example;

import java.time.*;
import java.time.format.DateTimeFormatter;

/* 
What this file demonstrates:
    LocalDate – dates without time, date arithmetic with plusDays, plusYears, etc.
    LocalTime – times without date, arithmetic with plusHours, minusMinutes.
    LocalDateTime – combined date and time.
    Duration – measures time-based differences (hours, minutes, seconds).
    Period – measures date-based differences (years, months, days).
    Formatting with DateTimeFormatter.

Key points:
    LocalDate, LocalTime, LocalDateTime are immutable and thread-safe.
    Duration is best for time differences, Period for date differences.
    Useful for age calculation, countdowns, and scheduling tasks. 
*/

public class JavaTimeDemo {

    public static void main(String[] args) {

        // --- LocalDate ---
        System.out.println("=== LocalDate ===");
        LocalDate today = LocalDate.now();
        System.out.println("Today: " + today); // e.g., 2025-11-10

        LocalDate birthday = LocalDate.of(1990, Month.MARCH, 15);
        System.out.println("Birthday: " + birthday);

        LocalDate nextYearBirthday = birthday.plusYears(1);
        System.out.println("Next Year Birthday: " + nextYearBirthday);

        // --- LocalTime ---
        System.out.println("\n=== LocalTime ===");
        LocalTime now = LocalTime.now();
        System.out.println("Current Time: " + now);

        LocalTime meetingTime = LocalTime.of(14, 30); // 2:30 PM
        System.out.println("Meeting Time: " + meetingTime);

        LocalTime plusTwoHours = meetingTime.plusHours(2);
        System.out.println("Meeting + 2 hours: " + plusTwoHours);

        // --- LocalDateTime ---
        System.out.println("\n=== LocalDateTime ===");
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current DateTime: " + currentDateTime);

        LocalDateTime appointment = LocalDateTime.of(2025, Month.DECEMBER, 1, 10, 0);
        System.out.println("Appointment: " + appointment);

        // --- Duration (time-based difference) ---
        System.out.println("\n=== Duration ===");
        Duration duration = Duration.between(meetingTime, plusTwoHours);
        System.out.println("Duration between meetingTime and plusTwoHours: " + duration.toHours() + " hours");

        Duration durationBetweenDateTime = Duration.between(currentDateTime, appointment);
        System.out.println("Duration until appointment: " + durationBetweenDateTime.toDays() + " days");

        // --- Period (date-based difference) ---
        System.out.println("\n=== Period ===");
        Period age = Period.between(birthday, today);
        System.out.println("Age: " + age.getYears() + " years, " + age.getMonths() + " months, " + age.getDays() + " days");

        Period untilAppointment = Period.between(today, appointment.toLocalDate());
        System.out.println("Time until appointment: " + untilAppointment.getMonths() + " months, " + untilAppointment.getDays() + " days");

        // --- Formatting ---
        System.out.println("\n=== Formatting ===");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Formatted currentDateTime: " + currentDateTime.format(formatter));
    }
}
