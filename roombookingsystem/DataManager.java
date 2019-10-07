package roombookingsystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * 
 * @author Max Ritchie 545737
 * 
 *         This class controls what happens to the process of all data in the
 *         system
 *
 */
public class DataManager
{
    private static int communityMemberIDs = 0;
    private static int bookingIds = 0;

    private static HashSet<CommunityMember> communityMembersList;
    private static HashSet<Booking> bookingsList;

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public final int MIN_COMMUNITY_MEMBERS_ID = 001;
    public final int MAX_COMMUNITY_MEMBERS_ID = 999;

    public final int MIN_BOOKING_ID = 001;
    public final int MAX_BOOKING_ID = 999;

    /**
     * The constructor for the class where both required hash sets are
     * initialised
     */
    public DataManager()
    {
        communityMembersList = new HashSet<>();
        bookingsList = new HashSet<Booking>();
    }

    /**
     * Adds a community member id into the communityMemberIDs list and returns
     * the created communityMember object. Increments the total
     * communityMembersIDs to prevent duplication of community member ids. Saves
     * the community member into an external file in the C drive
     * 
     * @param givenName holds the community members given name
     * @param lastName holds the community members last name
     * @param emailAddress holds the community members email address
     * @param phoneNumber holds the community members phone number
     * @return returns an object of a new created community member
     */
    public CommunityMember addCommunityMember(String givenName, String lastName, String emailAddress,
            String phoneNumber)
    {
        CommunityMember communityMember = null;

        FileOutputStream outputtedFile = null;
        ObjectOutputStream outPuttedObject = null;

        communityMemberIDs++;

        try {
            //Creates new file if not found in the required location
            outputtedFile = new FileOutputStream("C:\\Room Booking Save Files/Community Members IDs.ser");
            outPuttedObject = new ObjectOutputStream(outputtedFile);

            outPuttedObject.writeInt(communityMemberIDs);

            outPuttedObject.close();
            outputtedFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: No file found for Community Members IDs");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Error: File could not be created");
            e.printStackTrace();
        }

        if (communityMemberIDs > MAX_COMMUNITY_MEMBERS_ID) {
            System.out.println("Error: Maximum number of community members reached");
        }

        communityMember = new CommunityMember(communityMemberIDs, givenName, lastName, emailAddress, phoneNumber);

        communityMembersList.add(communityMember);

        return communityMember;
    }

    /**
     * Removes a community member from the system. Removes the community member
     * from the community members list
     * 
     * @param communityMemberID holds the requested community member id that is
     *            to be removed from the system
     * @return returns the community member that has been requested to be
     *         removed from the system
     */
    public CommunityMember removeCommunityMember(int communityMemberID)
    {
        CommunityMember communityMemberToBeRemoved = null;

        int idOfCommunityMember = 0;

        for (CommunityMember communityMember : communityMembersList) {
            idOfCommunityMember = ((CommunityMember) communityMember).getCommunityMemberID();

            if (idOfCommunityMember == communityMemberID) {
                communityMemberToBeRemoved = communityMember;
                break;
            }
        }
        if (communityMemberToBeRemoved != null) {
            communityMembersList.remove(communityMemberToBeRemoved);
        }

        return communityMemberToBeRemoved;
    }

    /**
     * Gets the community member when making a booking to store the community
     * members information along with the booking that they have just made
     * 
     * @param communityMemberID holds the community members id that been entered
     *            while in the process of making a booking
     * @return returns the required community member that has been requested
     */
    public CommunityMember getCommunityMemberForBooking(int communityMemberID)
    {
        int tempID = 0;

        CommunityMember communityMember = null;

        for (CommunityMember tempCommunityMember : communityMembersList) {
            tempID = ((CommunityMember) tempCommunityMember).getCommunityMemberID();

            if (tempID == communityMemberID) {
                communityMember = tempCommunityMember;
                break;
            }
        }
        return communityMember;
    }

    /**
     * Gets the total amount of community members that are currently stored in
     * the communityMembersList
     * 
     * @return integer value returned of the size/amount of community members
     *         that are currently being stored in the list
     */
    public static int getTotalNumberOfCommunityMember()
    {
        return communityMembersList.size();
    }

    /**
     * Checks to see if the communityMembersList is empty
     * 
     * @return true or false is returned depending on if the list is empty or
     *         not
     */
    public boolean isCommunityMembersListEmpty()
    {
        if (communityMembersList.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Gets the communityMembersIDList that has been previously saved
     */
    public void getCommunityMembersIDList()
    {
        FileInputStream inPuttedFile = null;
        ObjectInputStream inPuttedObject = null;

        try {
            //Creates new file if not found in the required location
            inPuttedFile = new FileInputStream("C:\\Room Booking Save Files/Community Members IDs.ser");
            inPuttedObject = new ObjectInputStream(inPuttedFile);

            communityMemberIDs = (int) inPuttedObject.readInt();

            inPuttedFile.close();
            inPuttedObject.close();
        }
        catch (IOException e) {
            System.out.println("Error: Unable to find file for: Community Members IDs\n");
        }
    }

    /**
     * Lists of currently stored community members in the communityMembersList
     * and also checks if the list is empty or not
     */
    public void listCommunityMembers()
    {
        if (communityMembersList.isEmpty()) {
            System.out.println("\nNo community members to display\n");
        }
        else {
            System.out.println("\nAll community members\n");

            for (CommunityMember communityMember : communityMembersList) {
                System.out.println(communityMember + "\n");
            }
        }
    }

    /**
     * Searches for the community member using a community member id that the
     * user has entered and displays the searched community member to the screen
     * 
     * @param communityMemberID holds the community members id which is in
     *            passed to be compared with all community members currently
     *            stored in the communityMembersList
     */
    public void displaySearchedCommunityMemeber(int communityMemberID)
    {
        for (CommunityMember communityMember : communityMembersList) {
            if (communityMember.getCommunityMemberID() == communityMemberID) {
                System.out.println(communityMember);
            }
        }
    }

    /**
     * Gets all community members that have been previously saved saved
     */
    @SuppressWarnings("unchecked")
    public void getAllCommunityMembers()
    {
        FileInputStream inPuttedFile = null;
        ObjectInputStream inPuttedObject = null;

        try {
            //Creates new file if not found in the required location
            inPuttedFile = new FileInputStream("C:\\Room Booking Save Files/All Community Members.ser");
            inPuttedObject = new ObjectInputStream(inPuttedFile);

            communityMembersList = (HashSet<CommunityMember>) inPuttedObject.readObject();

            inPuttedFile.close();
            inPuttedObject.close();
        }
        catch (IOException e) {
            System.out.println("Error: Unable to create new file for: All Community Members\n");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error: Unable to find required file for: All Community Members\n");
        }
    }

    /**
     * Saves all community members that are currently being held in the
     * communityMembersList into an external file in the C drive
     */
    public void saveAllCommunityMembers()
    {
        FileOutputStream outPuttedFile = null;
        ObjectOutputStream outPuttedObject = null;

        try {
            //Creates new file if not found in the required location
            outPuttedFile = new FileOutputStream("C:\\Room Booking Save Files/All Community Members.ser");
            outPuttedObject = new ObjectOutputStream(outPuttedFile);

            outPuttedObject.writeObject(communityMembersList);

            outPuttedObject.close();
            outPuttedFile.close();
        }
        catch (IOException e) {
            System.out.printf("Error: Unable to create new file for: All Community Members\n");
        }
    }

    /**
     * Adds a booking id into the bookingIds list and returns the created
     * booking object. Increments the total communityMembersIDs to prevent
     * duplication of booking ids. Saves the booking into an external file in
     * the C drive
     * 
     * @param communityMemberBooking holds the community member object
     * @param dateOfBooking holds the requested booking date
     * @param timeOfBooking holds the requested booking time
     * @param durationOfBooking holds the required booking duration
     * @param bestFitRoom holds the found best room to fit
     * @return returns the created Booking object
     */
    public Booking addBooking(CommunityMember communityMemberBooking, LocalDate dateOfBooking, LocalTime timeOfBooking,
            int durationOfBooking, Room bestFitRoom)
    {
        Booking booking = null;

        FileOutputStream outPuttedFile = null;
        ObjectOutputStream outPuttedObject = null;

        bookingIds++;

        try {
            //Creates new file if not found in the required location
            outPuttedFile = new FileOutputStream("C:\\Room Booking Save Files/Booking IDs.ser");
            outPuttedObject = new ObjectOutputStream(outPuttedFile);

            outPuttedObject.writeInt(bookingIds);

            outPuttedObject.close();
            outPuttedFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: Unable to find required file for: Booking IDs\n");
        }
        catch (IOException e) {
            System.out.println("Error: Unable to create new file for: Booking IDs\n");
        }

        booking = new Booking(bookingIds, communityMemberBooking, dateOfBooking, timeOfBooking, durationOfBooking,
                bestFitRoom);

        bookingsList.add(booking);

        return booking;
    }

    /**
     * Removes booking from the system. Removes the booking from the bookings
     * list
     * 
     * @param bookingID holds the booking id that has been requested to be
     *            deleted
     * @return returns the booking that is to be removed from the system
     */
    public Booking removeBooking(int bookingID)
    {
        Booking bookingToBeRemoved = null;

        int idOfBooking = 0;

        for (Booking booking : bookingsList) {
            idOfBooking = ((Booking) booking).getBookingID();

            if (idOfBooking == bookingID) {
                bookingToBeRemoved = booking;
                break;
            }
        }
        if (bookingToBeRemoved != null) {
            bookingsList.remove(bookingToBeRemoved);
        }

        return bookingToBeRemoved;
    }

    /**
     * Gets the total amount of bookings that are currently stored in the
     * bookingsList
     * 
     * @return integer value returned of the size/amount of bookings that are
     *         currently being stored in the list
     */
    public static int getTotalNumberOfBookings()
    {
        return bookingsList.size();
    }

    /**
     * Checks to see if there are any rooms currently meet the entered room
     * requirements
     * 
     * @param room holds the room object
     * @param dateOfBooking holds the requested booking date
     * @param timeOfBooking holds the requested booking time
     * @param durationOfBooking holds the requested booking duration
     * @return true of false is returned depending if they're any available
     *         rooms
     */
    public boolean isRoomAvaliable(Room room, LocalDate dateOfBooking, LocalTime timeOfBooking,
            Integer durationOfBooking)
    {
        for (Booking isBooked : bookingsList) {
            if (dateOfBooking.equals(isBooked.getDateBooked())
                    && durationOfBooking.equals(isBooked.getBookingDuration())
                    && timeOfBooking.equals(isBooked.getTimeBooked())
                    && room.getRoomNumber() == isBooked.getRoom().getRoomNumber()) {

                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if the bookingsList is empty
     * 
     * @return true or false is returned depending on if the list is empty or
     *         not
     */
    public boolean isBookingsListEmpty()
    {
        if (bookingsList.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Gets the bookingIds list that has been previously saved
     */
    public void getBookingsIDsList()
    {
        FileInputStream inPuttedFile = null;
        ObjectInputStream inPuttedObject = null;

        try {
            //Creates new file if not found in the required location
            inPuttedFile = new FileInputStream("C:\\Room Booking Save Files/Booking IDs.ser");
            inPuttedObject = new ObjectInputStream(inPuttedFile);

            bookingIds = (int) inPuttedObject.readInt();

            inPuttedObject.close();
            inPuttedFile.close();
        }
        catch (IOException e) {
            System.out.println("Error: Unable to find file for: Booking IDs List\n");
        }
    }

    /**
     * Gets all bookings that have been previously saved
     */
    @SuppressWarnings("unchecked")
    public void getAllBookings()
    {
        FileInputStream inPuttedFile = null;
        ObjectInputStream inPuttedObject = null;

        try {
            //Creates new file if not found in the required location
            inPuttedFile = new FileInputStream("C:\\Room Booking Save Files/All Bookings.ser");
            inPuttedObject = new ObjectInputStream(inPuttedFile);

            bookingsList = (HashSet<Booking>) inPuttedObject.readObject();

            inPuttedFile.close();
            inPuttedObject.close();
        }
        catch (IOException e) {
            System.out.println("Error: Unable to create new file for: All Bookings\n");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error: Unable to find required file for: All Bookings\n");
        }
    }

    /**
     * Saves all bookings that are currently being held in the bookingsList into
     * an external file in the C drive
     */
    public void saveAllBookings()
    {
        FileOutputStream outPuttedFile = null;
        ObjectOutputStream outPuttedObject = null;

        try {
            //Creates new file if not found in the required location
            outPuttedFile = new FileOutputStream("C:\\Room Booking Save Files/All Bookings.ser");
            outPuttedObject = new ObjectOutputStream(outPuttedFile);

            outPuttedObject.writeObject(bookingsList);

            outPuttedObject.close();
            outPuttedFile.close();
        }
        catch (IOException e) {
            System.out.print("Error: Unable to create new file for: All Bookings\n");
        }
    }

    /**
     * Searches for the booking using a booking id that the user has entered and
     * displays the searched booking to the screen
     * 
     * @param bookingID holds the bookings id which is passed in to be compared
     *            with all bookings currently stored in the bookingsList
     */
    public void displaySearchedBooking(int bookingID)
    {
        for (Booking booking : bookingsList) {
            if (booking.getBookingID() == bookingID) {
                System.out.println(booking);
            }
        }
    }

    /**
     * Lists of currently stored bookings in the bookingsList and also checks if
     * the list is empty or not
     */
    public void listBookings()
    {
        if (bookingsList.isEmpty()) {
            System.out.println("\nNo bookings to display\n");
        }
        else {
            System.out.println("\nAll current bookings\n");

            for (Booking booking : bookingsList) {
                System.out.println(booking + "\n");
            }
        }
    }

    /**
     * Gets and displays all bookings that have been made by the community
     * member that has been entered using their id
     * 
     * @param communityMemberID holds the id of the community member that has
     *            been entered to displays all of their bookings
     */
    public void getBookingsForSpecficCommunityMembers(int communityMemberID)
    {
        ArrayList<Booking> communityMembersBookings;

        communityMembersBookings = new ArrayList<>();

        System.out.print("\nAll bookings for selected community member\n");

        for (Booking booking : bookingsList) {
            if (booking.getCommunityMemberBooking().getCommunityMemberID() == communityMemberID) {
                communityMembersBookings.add(booking);
            }
            System.out.println(booking);
        }
    }
}