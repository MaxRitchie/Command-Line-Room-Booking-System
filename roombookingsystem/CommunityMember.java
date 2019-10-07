package roombookingsystem;

import java.io.Serializable;

/**
 * 
 * @author Max Ritchie 545737
 *
 *         This class creates and returns a CommunityMember object
 */
@SuppressWarnings("serial")
public class CommunityMember implements Serializable
{
    String givenName;
    String lastName;
    String emailAddress;
    String phoneNumber;

    int communityMemberID;

    /**
     * The constructor of the object for CommunityMember. Initialises all
     * required variables
     * 
     * @param communityMemberID holds the id of the community member
     * @param givenName holds the given name of the community member
     * @param lastName holds the last name of the community member
     * @param emailAddress holds the email address of the community member
     * @param phoneNumber holds the phone number of the community member
     */
    public CommunityMember(int communityMemberID, String givenName, String lastName, String emailAddress,
            String phoneNumber)
    {
        this.communityMemberID = communityMemberID;
        this.givenName = givenName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * @return the givenName
     */
    public String getGivenName()
    {
        return givenName;
    }

    /**
     * @param givenName the givenName to set
     */
    public void setGivenName(String givenName)
    {
        this.givenName = givenName;
    }

    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress()
    {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the communityMemberID
     */
    public int getCommunityMemberID()
    {
        return communityMemberID;
    }

    /**
     * @param communityMemberID the communityMemberID to set
     */
    public void setCommunityMemberID(int communityMemberID)
    {
        this.communityMemberID = communityMemberID;
    }

    /**
     * @return the phoneNumner
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * @param phoneNumner the phoneNumner to set
     */
    public void setPhoneNumner(String phoneNumner)
    {
        this.phoneNumber = phoneNumner;
    }

    /**
     * Creates a String object of the CommunityMember that can be displayed to
     * the screen with the created toString format
     */
    @Override
    public String toString()
    {
        StringBuilder communityMemberInfo = new StringBuilder();

        communityMemberInfo.append("-------------------------------------------");

        communityMemberInfo.append("\nID           : ");
        communityMemberInfo.append(communityMemberID);

        communityMemberInfo.append("\nGiven Name   : ");
        communityMemberInfo.append(givenName);

        communityMemberInfo.append("\nLast Name    : ");
        communityMemberInfo.append(lastName);

        communityMemberInfo.append("\nEmail Address: ");
        communityMemberInfo.append(emailAddress);

        communityMemberInfo.append("\nPhone Number : ");
        communityMemberInfo.append(phoneNumber);

        communityMemberInfo.append("\n-------------------------------------------");

        return communityMemberInfo.toString();
    }

}
