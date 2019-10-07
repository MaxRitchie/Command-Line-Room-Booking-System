package roombookingsystem;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Max Ritchie 5454737
 * 
 *         This class takes in all user input for the driver
 *
 */
public class ReadInInput
{
    private DateTimeFormatter formatterForDate;
    private DateTimeFormatter formatterForTime;

    /**
     * Sets the time and date formats to be used later in the program
     * 
     * @param dateFormat used for the set format for date
     */
    public ReadInInput(String dateFormat)
    {
        formatterForDate = DateTimeFormatter.ofPattern(dateFormat);
        formatterForTime = DateTimeFormatter.ofPattern("HH:00");
    }

    /**
     * Takes in an boolean value from the user and processes it
     * 
     * @param userInput used as a Scanner to take in users input
     * @param message gets taken from the driver and displayed
     * @return returns boolean value of either true or false
     */
    public boolean getBoolean(Scanner userInput, String message)
    {
        int value = 0;

        String input = "";

        System.out.print(message);

        do {

            input = userInput.next();

            try {
                value = Integer.parseInt(input);
            }
            catch (NullPointerException e) {
                System.out.print(
                        "\nError: You must input a non null value" + "\nError: Please enter either yes(1) or no(2): ");
                continue;
            }
            catch (InputMismatchException e) {
                System.out.print(
                        "\nError: You must input a valid data type" + "\nError: Please enter either yes(1) or no(2): ");
                continue;
            }
            catch (NumberFormatException e) {
                System.out.print(
                        "\nError: You must input a valid data type" + "\nError: Please enter either yes(1) or no(2): ");
                continue;
            }

            if (value == 1) {
                return true;
            }
            else if (value == 2) {
                return false;
            }
            else {
                System.out.print(
                        "\nError: You must input a valid data type" + "\nError: Please enter either yes(1) or no(2): ");
                value = 0;
                continue;
            }
        }
        while (value == 0);

        return false;
    }

    /**
     * Take in an integer value from the suer and processes it
     * 
     * @param userInput used as a Scanner to take in users input
     * @param message gets taken from the driver and displayed
     * @param lowerSelection gets taken from the driver and used as the lowest
     *            value in the number range
     * @param higherSelection gets taken from the driver and used as the highest
     *            value in the number range
     * @return returns an integer value
     */
    public int getInteger(Scanner userInput, String message, int lowerSelection, int higherSelection)
    {
        int value = 0;

        String input = "";

        System.out.print(message);

        while (true) {

            input = userInput.next();

            try {
                value = Integer.parseInt(input);
            }
            catch (NullPointerException e) {
                System.out.print("\nError: You must input a non null value " + "\nPlease re-enter a value: ");
                continue;
            }
            catch (InputMismatchException e) {
                System.out.print("\nError: You must input a valid data type" + "\nPlease re-enter a valid value: ");
                continue;
            }
            catch (NumberFormatException e) {
                System.out.print("\nError: You must input a valid data type" + "\nPlease re-enter a valid value: ");
                continue;
            }

            if (value >= lowerSelection && value <= higherSelection) {
                break;
            }

            System.out.printf(
                    "\nError: You must enter a value in the range %d-%d" + "\nPlease re-enter a value within range: ",
                    lowerSelection, higherSelection);
        }
        return value;
    }

    /**
     * Takes in a string value from the user and processes it
     * 
     * @param userInput used as a Scanner to take in users input
     * @param message gets taken from the driver and displayed
     * @return returns a string value
     */
    public String getString(Scanner userInput, String message)
    {
        String inputtedString = "";

        System.out.printf(message);

        do {
            try {
                inputtedString = userInput.next();
            }
            catch (NullPointerException e) {
                System.out.print("\nError: You must input a non null value " + "\nPlease re-enter a value: ");
                continue;
            }

        }
        while (inputtedString == "");

        return inputtedString;
    }

    /**
     * Takes in a phone number value from the user and processes it
     * 
     * @param userInput used as a Scanner to take in users input
     * @param message gets taken from the driver and displayed
     * @return returns an integer value as the phone number
     */
    public String getPhoneNumber(Scanner userInput, String message)
    {
        String phoneNumber = "";

        String phoneNumberValidation = "[0-9]{11}";

        System.out.print(message);

        do {
            try {
                phoneNumber = userInput.next();
                phoneNumber.trim();
            }
            catch (NullPointerException e) {
                System.out.print("\nError: You must input a non null value " + "\nPlease re-enter a value: ");
                continue;
            }
            catch (NumberFormatException e) {
                System.out.print("\nError: You must input a valid data type" + "\nPlease re-enter a valid value: ");
                continue;
            }

            if (phoneNumber.length() != 11) {
                System.out.print("\nError: Phone number must be 11 digits" + "\nPlease re-enter a valid value: ");
                phoneNumber = "";
            }
            else if (!phoneNumber.matches(phoneNumberValidation)) {
                System.out.print("\nError: You must input a valid data type" + "\nPlease re-enter a valid value: ");
                phoneNumber = "";
            }
        }
        while (phoneNumber == "");

        return phoneNumber;

    }

    /**
     * Takes in a local time value from the user and processes it
     * 
     * @param userInput used as a Scanner to take in users input
     * @param message gets taken from the driver and displayed
     * @return returns a local time value
     */
    public LocalTime getTime(Scanner userInput, String message)
    {
        LocalTime time = null;

        String temp = "";

        System.out.print(message);

        while (time == null) {
            temp = userInput.next();
            try {
                time = LocalTime.parse(temp, formatterForTime);
            }
            catch (DateTimeException e) {
                System.out.print("\nError: You must enter the time in the format HH:MM"
                        + "\nPlease re-enter a valid time value: ");
            }
            catch (InputMismatchException e) {
                System.out.print("\nError: You must enter the time in the format HH:MM"
                        + "\nPlease re-enter a valid time value: ");
            }
        }
        return time;
    }

    /**
     * Takes in a local date value from the user and processes it
     * 
     * @param userInput used as a Scanner to take in users input
     * @param message gets taken from the driver and displayed
     * @return returns a local date value
     */
    public LocalDate getDate(Scanner userInput, String message)
    {
        String inputtedDate = "";

        LocalDate date = null;

        System.out.printf(message);

        do {
            inputtedDate = userInput.next();
            try {
                date = LocalDate.parse(inputtedDate, formatterForDate);

                if (!Booking.isBookingDateValid(date)) {
                    System.out.print("\nError: Date cannot be in the past" + "\nPlease re-enter valid date format: ");
                    date = null;
                }
            }
            catch (DateTimeException e) {
                System.out.print(
                        "\nError: You must input the valid date format" + "\nPlease re-enter valid date format: ");
                continue;
            }
            catch (InputMismatchException e) {
                System.out.println(
                        "\nError: You must input the valid date format" + "\nPlease re-enter valid date format: ");
                continue;
            }
            catch (NullPointerException e) {
                System.out.print(
                        "\nError: You must input the valid date format" + "\nPlease re-enter valid date format: ");
                continue;
            }
        }
        while (date == null);
        return date;
    }

}