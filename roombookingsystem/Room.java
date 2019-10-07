package roombookingsystem;

import java.io.Serializable;

/**
 * 
 * @author Max Ritchie 545737
 *
 *         This class creates and returns a Room object
 * 
 */
@SuppressWarnings("serial")
public class Room implements Serializable
{
    private int numberOfWorkStations;
    private int numberOfBreakoutSeats;
    private int roomNumber;

    private boolean smartboard;
    private boolean printer;

    /**
     * 
     * The constructor of the object for Room. Initialises all required
     * variables
     * 
     * @param numberOfWorkStations holds the number of work stations within the
     *            rooms with 18 workstation
     * @param numberOfBreakoutSeats holds the number of break out seats within
     *            in the room
     * @param roomNumber holds the number of the room
     * @param smartboard holds a boolean value for the presence of a smart board
     *            within the room
     * @param printer holds a boolean value for the presence of a printer within
     *            the room
     */
    public Room(int numberOfBreakoutSeats, int roomNumber, boolean smartboard, boolean printer)
    {
        this(18, numberOfBreakoutSeats, roomNumber, smartboard, printer);
    }

    /**
     * The constructor of the object for Room. Initialises all required
     * variables
     * 
     * @param numberOfWorkStations holds the number of work stations within the
     *            room
     * @param numberOfBreakoutSeats holds the number of break out seats within
     *            in the room
     * @param roomNumber holds the number of the room
     * @param smartboard holds a boolean value for the presence of a smart board
     *            within the room
     * @param printer holds a boolean value for the presence of a printer within
     *            the room
     */
    public Room(int numberOfWorkStations, int numberOfBreakoutSeats, int roomNumber, boolean smartboard,
            boolean printer)
    {
        this.numberOfWorkStations = numberOfWorkStations;
        this.numberOfBreakoutSeats = numberOfBreakoutSeats;
        this.roomNumber = roomNumber;
        this.smartboard = smartboard;
        this.printer = printer;
    }

    /**
     * @return the numberOfWorkStations
     */
    public int getNumberOfWorkStations()
    {
        return numberOfWorkStations;
    }

    /**
     * @param numberOfWorkStations the numberOfWorkStations to set
     */
    public void setNumberOfWorkStations(int numberOfWorkStations)
    {
        this.numberOfWorkStations = numberOfWorkStations;
    }

    /**
     * @return the numberOfBreakoutSeats
     */
    public int getNumberOfBreakoutSeats()
    {
        return numberOfBreakoutSeats;
    }

    /**
     * @param numberOfBreakoutSeats the numberOfBreakoutSeats to set
     */
    public void setNumberOfBreakoutSeats(int numberOfBreakoutSeats)
    {
        this.numberOfBreakoutSeats = numberOfBreakoutSeats;
    }

    /**
     * @return the roomNumber
     */
    public int getRoomNumber()
    {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    /**
     * @return the smartboard
     */
    public boolean isSmartboard()
    {
        return smartboard;
    }

    /**
     * @param smartboard the smartboard to set
     */
    public void setSmartboard(boolean smartboard)
    {
        this.smartboard = smartboard;
    }

    /**
     * @return the printer
     */
    public boolean isPrinter()
    {
        return printer;
    }

    /**
     * @param printer the printer to set
     */
    public void setPrinter(boolean printer)
    {
        this.printer = printer;
    }

    /**
     * Creates a String object of the Room that can be displayed to the screen
     * with the created toString format
     */
    @Override
    public String toString()
    {
        StringBuilder roomInfo = new StringBuilder();

        roomInfo.append("\n-------------------------------------------");

        roomInfo.append("Room Number                    : ");
        roomInfo.append(roomNumber);

        roomInfo.append("Number of work stations        : ");
        roomInfo.append(numberOfWorkStations);

        roomInfo.append("Number of breakout seats       : ");
        roomInfo.append(numberOfBreakoutSeats);

        roomInfo.append("Does the room have printer     : ");
        roomInfo.append(printer);

        roomInfo.append("Does the room have a smartboard: ");
        roomInfo.append(smartboard);

        roomInfo.append("\n-------------------------------------------");

        return roomInfo.toString();
    }
}
