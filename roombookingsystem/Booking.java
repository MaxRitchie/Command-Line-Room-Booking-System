package roombookingsystem;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 
 * @author Max Ritchie 545737
 * 
 *         This class creates and returns a Booking object
 */
@SuppressWarnings("serial")
public class Booking implements Serializable
{
    private CommunityMember communityMemberBooking;

    private int bookingID;
    private int bookingDuration;

    private LocalTime timeBooked;
    private LocalDate dateBooked;

    private Room room;

    /**
     * The constructor of the object for Booking. Initialises all required
     * variables
     * 
     * @param bookingID holds the id of the booking, this will never be
     *            duplicated and will always be unique
     * @param communityMemberBooking holds all information about the community
     *            member who has requested the booking and entered in their
     *            details
     * @param dateBooked holds the requested date of the booking that the
     *            community member has asked
     * @param timeBooked holds the requested time that of booking that the
     *            community member has asked
     * @param bookingDuration holds the requested duration of the booking that
     *            the community member has asked
     * @param room holds all information about the room that has been assigned
     *            and booked by the system
     */
    public Booking(int bookingID, CommunityMember communityMemberBooking, LocalDate dateBooked, LocalTime timeBooked,
            int bookingDuration, Room room)
    {
        this.bookingID = bookingID;
        this.communityMemberBooking = communityMemberBooking;
        this.bookingDuration = bookingDuration;
        this.timeBooked = timeBooked;
        this.dateBooked = dateBooked;
        this.room = room;
    }

    /**
     * @return the communityMemberBooking
     */
    public CommunityMember getCommunityMemberBooking()
    {
        return communityMemberBooking;
    }

    /**
     * @param communityMemberBooking the communityMemberBooking to set
     */
    public void setCommunityMemberBooking(CommunityMember communityMemberBooking)
    {
        this.communityMemberBooking = communityMemberBooking;
    }

    /**
     * @return the bookingID
     */
    public int getBookingID()
    {
        return bookingID;
    }

    /**
     * @param bookingID the bookingID to set
     */
    public void setBookingID(int bookingID)
    {
        this.bookingID = bookingID;
    }

    /**
     * @return the bookingDuration
     */
    public int getBookingDuration()
    {
        return bookingDuration;
    }

    /**
     * @param bookingDuration the bookingDuration to set
     */
    public void setBookingDuration(int bookingDuration)
    {
        this.bookingDuration = bookingDuration;
    }

    /**
     * @return the timeBooked
     */
    public LocalTime getTimeBooked()
    {
        return timeBooked;
    }

    /**
     * @param timeBooked the timeBooked to set
     */
    public void setTimeBooked(LocalTime timeBooked)
    {
        this.timeBooked = timeBooked;
    }

    /**
     * @return the dateBooked
     */
    public LocalDate getDateBooked()
    {
        return dateBooked;
    }

    /**
     * @param dateBooked holds the requested date that the community member has
     *            entered
     * @return true or false returned for validation of the requested booking
     *         date
     */
    public static boolean isBookingDateValid(LocalDate dateBooked)
    {
        if (dateBooked == null) {
            return false;
        }
        return dateBooked.isAfter(LocalDate.now());
    }

    /**
     * @param dateBooked the dateBooked to set
     */
    public void setDateBooked(LocalDate dateBooked)
    {
        this.dateBooked = dateBooked;
    }

    /**
     * @return the room
     */
    public Room getRoom()
    {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(Room room)
    {
        this.room = room;
    }

    /**
     * Creates a String object of the booking that can be displayed to the
     * screen with the created toString format
     */
    @Override
    public String toString()
    {
        StringBuilder bookingInfo = new StringBuilder();

        bookingInfo.append("-------------------------------------------");

        bookingInfo.append("\nID                      : ");
        bookingInfo.append(bookingID);

        bookingInfo.append("\nDate                    : ");
        bookingInfo.append(dateBooked);

        bookingInfo.append("\nTime                    : ");
        bookingInfo.append(timeBooked);

        bookingInfo.append("\nDuration                : ");
        bookingInfo.append(bookingDuration + " hour(s)");

        bookingInfo.append("\nRoom Number             : ");
        bookingInfo.append(getRoom().getRoomNumber());

        bookingInfo.append("\n\nCommunity member id     : ");
        bookingInfo.append(getCommunityMemberBooking().getCommunityMemberID());

        bookingInfo.append("\nCommunity member name   : ");
        bookingInfo
                .append(getCommunityMemberBooking().getGivenName() + " " + getCommunityMemberBooking().getLastName());

        bookingInfo.append("\n-------------------------------------------");

        return bookingInfo.toString();
    }
}
