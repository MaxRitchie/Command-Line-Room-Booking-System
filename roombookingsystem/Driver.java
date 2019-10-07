package roombookingsystem;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Controls the initialisation and the running of the program
 * 
 * @author Max Ritchie 545737
 *
 */
public class Driver
{
    private static ReadInInput readInUserInput;
    private static DataManager dataManager;

    private static ArrayList<Room> roomsList;
    private static ArrayList<Room> suitableRoomsList;

    private static List<Room> avaliableRoomsList;

    private static final int MIN_NUMBER_OF_SEATS = 0;
    private static final int MAX_NUMBER_OF_SEATS = 20;

    private static final int MIN_DURATION = 1;
    private static final int MAX_DURATION = 1;

    private static final int MIN_MENU_SELECTION = 0;
    private static final int MAX_MENU_SELECTION = 10;

    /**
     * Calls a method to initialise and start the program
     * 
     * @param args part of String args for main
     */
    public static void main(String[] args)
    {
        initalizeRoomBooking();
    }

    /**
     * Initialises and starts the program and loads up all previous saves of
     * information is present
     */
    public static void initalizeRoomBooking()
    {
        dataManager = new DataManager();
        readInUserInput = new ReadInInput(DataManager.DATE_FORMAT);

        Scanner userInput = new Scanner(System.in);

        createSaveFile();

        populateRoomsList();

        getAllSavedInfo();

        processUserSelection(userInput);
    }

    /**
     * Gets and pulls in all files and information of previous saves required
     * for the system
     */
    public static void getAllSavedInfo()
    {
        dataManager.getAllBookings();
        dataManager.getAllCommunityMembers();

        dataManager.getCommunityMembersIDList();
        dataManager.getBookingsIDsList();
    }

    /**
     * Creates the required save file for the system if not already present
     */
    public static void createSaveFile()
    {
        new File("C:\\Room Booking Save Files").mkdir();
    }

    /**
     * Creates and adds all available rooms into the HashMap roomsList
     */
    public static void populateRoomsList()
    {
        roomsList = new ArrayList<>();

        Room room1 = new Room(0, 12, 6, false, false);
        roomsList.add(room1);

        Room room2 = new Room(10, 8, true, true);
        roomsList.add(room2);

        Room room3 = new Room(20, 0, 11, true, true);
        roomsList.add(room3);

        Room room4 = new Room(6, 0, 13, false, true);
        roomsList.add(room4);

        Room room5 = new Room(2, 14, true, true);
        roomsList.add(room5);

        Room room6 = new Room(10, 15, true, true);
        roomsList.add(room6);

        Room room7 = new Room(10, 17, true, true);
        roomsList.add(room7);

        Room room8 = new Room(0, 20, 108, true, false);
        roomsList.add(room8);

        Room room9 = new Room(0, 120, true, true);
        roomsList.add(room9);

        Room room10 = new Room(6, 301, true, true);
        roomsList.add(room10);
    }

    /**
     * Displays the option menu to the screen
     */
    public static void displayMenu()
    {
        System.out.println("Forth Valley College Room Booking System\n" + "-------------------------------------------"
                + "\nBooking Options" + "\n\n1.Book Room" + "\n2.Display All Bookings" + "\n3.Search for Booking"
                + "\n4.Remove Booking" + "\n-------------------------------------------" + "\nCommunity Member Options"
                + "\n\n5.Add Community Member" + "\n6.Display All Community Members" + "\n7.Search for Community Member"
                + "\n8.Display Bookings for Specific Community Member" + "\n9.Remove Community Member"
                + "\n-------------------------------------------" + "\nOther Options" + "\n\n10.Generate Report" + "\n"
                + "\n0.Exit" + "\n-------------------------------------------");
    }

    /**
     * Gets the user selection from the menu options
     * 
     * @param userInput used as a Scanner to take in users input
     * @return returns the integer value of the users selection
     */
    public static int getUserSelection(Scanner userInput)
    {
        int userSelection = 0;

        userSelection = readInUserInput.getInteger(userInput, "Please select an option: ", MIN_MENU_SELECTION,
                MAX_MENU_SELECTION);

        return userSelection;
    }

    /**
     * A switch statement is used process the suer users selection and call the
     * next procedure that has been selected
     * 
     * @param userInput used as a Scanner to take in users input
     */
    private static void processUserSelection(Scanner userInput)
    {
        int userSelection = 0;

        boolean run = true;

        while (run) {

            displayMenu();
            userSelection = getUserSelection(userInput);

            switch (userSelection) {
            case 1:
                addBooking(userInput);
                break;
            case 2:
                listAllBookings();
                break;
            case 3:
                searchForBooking(userInput);
                break;
            case 4:
                removeBooking(userInput);
                break;
            case 5:
                addCommunityMember(userInput);
                break;
            case 6:
                listAllCommunityMembers();
                break;
            case 7:
                searchForCommunityMember(userInput);
                break;
            case 8:
                displayBookingsForSpecficCommunityMembers(userInput);
                break;
            case 9:
                removeCommunityMember(userInput);
                break;
            case 10:
                generateReport();
                break;
            case 0:
                run = false;
                exitSystem();
                break;
            }
        }
    }

    /**
     * This is the whole process of getting the required information for the
     * booking and finding the best room to fir from the entered room
     * requirements
     * 
     * @param userInput used as a Scanner to take in users input
     * @return returns a created booking object
     */
    @SuppressWarnings("unused")
    public static Booking getBookingDetails(Scanner userInput)
    {
        int numberOfWorkStations = 0;
        int numberOfBreakoutSeats = 0;
        int durationOfBooking = 0;
        int communityMemberID = 0;

        boolean isPrinterRequired = false;
        boolean isSmartBoardRequired = false;
        boolean roomsAvailable = false;
        boolean generateBooking = true;

        LocalTime timeOfBooking = null;
        LocalDate dateOfBooking = null;

        Booking booking = null;

        CommunityMember communityMemberBooking = null;

        Room bestFitRoom = null;

        suitableRoomsList = new ArrayList<>();
        avaliableRoomsList = new ArrayList<>();

        //Displaying, saving and prompting the user to enter in the required information for the booking process
        numberOfWorkStations = readInUserInput.getInteger(userInput,
                "-------------------------------------------\nEnter the amount of work stations you require: ",
                MIN_NUMBER_OF_SEATS, MAX_NUMBER_OF_SEATS);
        numberOfBreakoutSeats = readInUserInput.getInteger(userInput,
                "\nEnter the amount of breakout seats you require: ", MIN_NUMBER_OF_SEATS, MAX_NUMBER_OF_SEATS);

        isPrinterRequired = readInUserInput.getBoolean(userInput, "\nDo you require a printer? (yes(1)/no(2)): ");
        isSmartBoardRequired = readInUserInput.getBoolean(userInput, "\nDo you require a smartboard? (yes(1)/no(2)): ");

        timeOfBooking = readInUserInput.getTime(userInput, "\nEnter time required time (HH:MM): ");
        dateOfBooking = readInUserInput.getDate(userInput, "\nEnter the date you require (dd/mm/yyyy): ");

        durationOfBooking = readInUserInput.getInteger(userInput, "\nEnter the duration of the booking: ", MIN_DURATION,
                MAX_DURATION);

        System.out.println("-------------------------------------------");

        bestFitRoom = findBestRoomToFitProcess(numberOfWorkStations, dateOfBooking, timeOfBooking, durationOfBooking,
                roomsAvailable, bestFitRoom);

        if (bestFitRoom == null) {
            return null;
        }
        else {
            getCommunityMemberForBooking(userInput, generateBooking, communityMemberID, communityMemberBooking, booking,
                    dateOfBooking, timeOfBooking, durationOfBooking, bestFitRoom);
        }
        return null;
    }

    /**
     * The whole process of finding and assigning the best room to fit with the
     * given room requirements
     * 
     * @param numberOfWorkStations holds the number of workstations that the
     *            user has requested
     * @param dateOfBooking holds the requested date of the booking
     * @param timeOfBooking holds the requested time of the booking
     * @param durationOfBooking holds the requested duration of the booking
     * @param roomsAvailable holds all the available rooms that have been found
     *            that match the given room requirements
     * @param bestFitRoom holds the room that has the best fit for the given
     *            room requirements
     * @return returns a new created best room to fit room object
     */
    private static Room findBestRoomToFitProcess(int numberOfWorkStations, LocalDate dateOfBooking,
            LocalTime timeOfBooking, int durationOfBooking, boolean roomsAvailable, Room bestFitRoom)
    {
        //Finds all rooms that meet the requested requirements and adds them into the suitableRoomsList
        for (Room rooms : roomsList) {
            if (rooms.getNumberOfWorkStations() >= numberOfWorkStations) {
                suitableRoomsList.add(rooms);
            }
        }

        //Finds all rooms that are currently available and removes nay rooms that are currently booked with the matching requirements
        for (int i = 0; i < suitableRoomsList.size(); i++) {
            Room room = suitableRoomsList.get(i);

            roomsAvailable = dataManager.isRoomAvaliable(room, dateOfBooking, timeOfBooking, durationOfBooking);

            if (roomsAvailable) {
                avaliableRoomsList.add(room);
            }
        }

        //Displays message to the screen if they're no rooms currently available
        if (avaliableRoomsList.size() == 0) {
            System.out.println("\nUnable to made a booking, no rooms currently avaliable for room requirements\n");

            return null;
        }

        //Assigns the found rooms with the requirements into the bestfitRoom
        bestFitRoom = avaliableRoomsList.get(0);

        //Checks all number of workstations between all rooms found to determine the best room to fit and assigns to bestFitRoom
        for (Room isRoomBestFit : avaliableRoomsList) {
            if (bestFitRoom.getNumberOfWorkStations() > isRoomBestFit.getNumberOfWorkStations()) {
                bestFitRoom = isRoomBestFit;
            }
        }
        return bestFitRoom;
    }

    /**
     * Asks the user to enter their community member id to link with the booking
     * and then returns a new created Booking object
     * 
     * @param userInput used as a Scanner to take in users input
     * @param generateBooking holds the generated booking
     * @param communityMemberID holds the community members id
     * @param communityMemberBooking holds all the information of the community
     *            members booking
     * @param booking holds all the bookings information
     * @param dateOfBooking holds the requested date of the booking
     * @param timeOfBooking holds the requested time of the booking
     * @param durationOfBooking holds the requested duration of the booking
     * @param bestFitRoom holds the found best room to fit when found
     * @return returns a new created booking object
     */
    private static Booking getCommunityMemberForBooking(Scanner userInput, boolean generateBooking,
            int communityMemberID, CommunityMember communityMemberBooking, Booking booking, LocalDate dateOfBooking,
            LocalTime timeOfBooking, int durationOfBooking, Room bestFitRoom)
    {
        if (generateBooking == true) {
            communityMemberID = readInUserInput.getInteger(userInput,
                    "Enter the id of the community member requesting the booking: ",
                    dataManager.MIN_COMMUNITY_MEMBERS_ID, dataManager.MAX_BOOKING_ID);

            communityMemberBooking = dataManager.getCommunityMemberForBooking(communityMemberID);

            booking = addBookingDetails(communityMemberBooking, dateOfBooking, timeOfBooking, durationOfBooking,
                    bestFitRoom);

            System.out.println("\nBooking successfully added\n");

            return booking;
        }
        return null;
    }

    /**
     * Adds all required information for the booking
     * 
     * @param communityMemberBooking holds all information about the community
     *            member who is assigned to the booking
     * @param dateOfBooking holds the requested date of the booking
     * @param timeOfBooking holds the requested time of the booking
     * @param durationOfBooking holds the requested duration of the booking
     * @param bestFitRoom holds the best room to fit that has been found and
     *            assigned
     * @return calls a method in the data manager to add the booking details
     */
    private static Booking addBookingDetails(CommunityMember communityMemberBooking, LocalDate dateOfBooking,
            LocalTime timeOfBooking, int durationOfBooking, Room bestFitRoom)
    {
        return dataManager.addBooking(communityMemberBooking, dateOfBooking, timeOfBooking, durationOfBooking,
                bestFitRoom);
    }

    /**
     * Saves the booking into the hash set inside the DataManager
     * 
     * @param userInput used as a Scanner to take in users input
     */
    private static void addBooking(Scanner userInput)
    {
        boolean isPresent;

        //Checks to see if the community members list is empty before proceeding
        isPresent = dataManager.isCommunityMembersListEmpty();

        if (isPresent == false) {
            getBookingDetails(userInput);

            saveAllInfo();
        }
        else if (isPresent == true) {
            System.out.println("\nYou must create a community member before making a booking\n");
        }
    }

    /**
     * Calls a method form the data manager that lists and displays all bookings
     * to the screen
     */
    public static void listAllBookings()
    {
        dataManager.listBookings();
    }

    /**
     * Searches for and displays the requested community member with the use of
     * a community member id
     * 
     * @param userInput used as a Scanner to take in users input
     */
    public static void searchForBooking(Scanner userInput)
    {
        int bookingID = 0;

        boolean isEmpty;

        //Checks to see if the bookings list is empty before proceeding
        isEmpty = dataManager.isBookingsListEmpty();

        if (isEmpty == false) {
            bookingID = readInUserInput.getInteger(userInput, "\nEnter booking id: ", dataManager.MIN_BOOKING_ID,
                    dataManager.MAX_BOOKING_ID);

            dataManager.displaySearchedBooking(bookingID);
        }
        else if (isEmpty == true) {
            System.out.println("\nNo bookings currently saved\n");
        }
    }

    /**
     * Removes the requested booking from the system with the use of a booking
     * id
     * 
     * @param userInput used as a Scanner to take in users input
     */
    public static void removeBooking(Scanner userInput)
    {
        Booking booking;

        int bookingID;

        bookingID = readInUserInput.getInteger(userInput,
                "\nPlease enter the ID of the booking that you want to delete: ", dataManager.MIN_BOOKING_ID,
                dataManager.MAX_BOOKING_ID);

        booking = dataManager.removeBooking(bookingID);

        if (booking != null) {
            System.out.printf("\nThe Booking:\n%s \nHas successfully been removed\n\n", booking);
        }
        else {
            System.out.println("\nBooking could not be found\n");
        }

        dataManager.saveAllBookings();
    }

    /**
     * This is the process of promoting the user for their required information,
     * storing it and creating a new Community Member Object
     * 
     * @param userInput used as a Scanner to take in users input
     * @return returns a new created Community Member object
     */
    public static CommunityMember getCommunityMemberDetails(Scanner userInput)
    {
        String givenName = "";
        String lastName = "";
        String emailAddress = "";
        String phoneNumber = "";

        givenName = readInUserInput.getString(userInput,
                "-------------------------------------------\nEnter your given name: ");
        lastName = readInUserInput.getString(userInput, "\nEnter your last name: ");
        emailAddress = readInUserInput.getString(userInput, "\nEnter your email address: ");

        phoneNumber = readInUserInput.getPhoneNumber(userInput, "\nEnter your phone number: ");

        System.out.println("\n-------------------------------------------");

        CommunityMember communityMember = null;

        communityMember = addCommunityMemberDetails(givenName, lastName, emailAddress, phoneNumber);

        return communityMember;
    }

    /**
     * Adds all required information for the community member
     * 
     * @param givenName holds the given given name that has been entered by the
     *            user
     * @param lastName holds the given last name that has been entered by the
     *            user
     * @param emailAddress holds the email address that has been entered by the
     *            user
     * @param phoneNumber holds the phone number that has been entered by the
     *            user
     * @return calls a method in the data manager to add the community members
     *         details
     */
    private static CommunityMember addCommunityMemberDetails(String givenName, String lastName, String emailAddress,
            String phoneNumber)
    {
        return dataManager.addCommunityMember(givenName, lastName, emailAddress, phoneNumber);
    }

    /**
     * Saves the community member into the hash set inside the DataManager
     * 
     * @param userInput used as a Scanner to take in users input
     */
    private static void addCommunityMember(Scanner userInput)
    {
        getCommunityMemberDetails(userInput);

        saveAllInfo();

        System.out.println("Community member successfully added\n");
    }

    /**
     * Calls a method form the data manager that lists and displays all
     * community members to the screen
     */
    public static void listAllCommunityMembers()
    {
        dataManager.listCommunityMembers();
    }

    /**
     * Searches for and displays the requested community member with the use of
     * a community member id
     * 
     * @param userInput used as a Scanner to take in users input
     */
    public static void searchForCommunityMember(Scanner userInput)
    {
        int communityMemberID = 0;

        boolean isEmpty;

        isEmpty = dataManager.isCommunityMembersListEmpty();

        if (isEmpty == false) {
            communityMemberID = readInUserInput.getInteger(userInput, "\nEnter community member id: ",
                    dataManager.MIN_COMMUNITY_MEMBERS_ID, dataManager.MAX_COMMUNITY_MEMBERS_ID);

            dataManager.displaySearchedCommunityMemeber(communityMemberID);
        }
        else {
            System.out.println("\nNo community members currently saved\n");
        }
    }

    /**
     * When a community members id is entered, all booking that are associated
     * with the id are displayed to the screen
     * 
     * @param userInput used as a Scanner to take in users input
     */
    public static void displayBookingsForSpecficCommunityMembers(Scanner userInput)
    {
        int communityMemberID;

        communityMemberID = readInUserInput.getInteger(userInput, "\nEnter community member id: ",
                dataManager.MIN_COMMUNITY_MEMBERS_ID, dataManager.MAX_COMMUNITY_MEMBERS_ID);

        dataManager.getBookingsForSpecficCommunityMembers(communityMemberID);
    }

    /**
     * Removes the requested community member from the system with the use of a
     * community member id
     * 
     * @param userInput
     */
    public static void removeCommunityMember(Scanner userInput)
    {
        CommunityMember communityMember;

        int communityMemberID;

        communityMemberID = readInUserInput.getInteger(userInput,
                "\nPlease enter the ID of the community member that you want to delete: ",
                dataManager.MIN_COMMUNITY_MEMBERS_ID, dataManager.MAX_COMMUNITY_MEMBERS_ID);

        communityMember = dataManager.removeCommunityMember(communityMemberID);

        if (communityMember != null) {
            System.out.printf("\nThe community member:\n%s \nHas successfully been removed\n\n", communityMember);
        }
        else {
            System.out.println("\nCommunity member could not be found\n");
        }

        dataManager.saveAllCommunityMembers();
    }

    /**
     * Generates a small report displaying the total amount of booking and
     * community members that a currently stored within the system
     */
    @SuppressWarnings("static-access")
    public static void generateReport()
    {
        int totalNumberOfBooking = 0;
        int totalNumberofCommunityMembers = 0;

        totalNumberOfBooking = dataManager.getTotalNumberOfBookings();
        totalNumberofCommunityMembers = dataManager.getTotalNumberOfCommunityMember();

        System.out.printf("\nNumber of current bookings: %s", totalNumberOfBooking);
        System.out.printf("\nNumber of current community members: %s\n\n", totalNumberofCommunityMembers);
    }

    /**
     * Saves all information for bookings and community members
     */
    public static void saveAllInfo()
    {
        dataManager.saveAllBookings();
        dataManager.saveAllCommunityMembers();
    }

    /**
     * All information for booking and community members are saved before the
     * system is exited and closed
     */
    public static void exitSystem()
    {
        saveAllInfo();

        System.out.println("\nGoodbye");

        System.exit(0);
    }
}
