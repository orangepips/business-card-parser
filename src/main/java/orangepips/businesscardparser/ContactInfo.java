package orangepips.businesscardparser;

import com.google.common.base.Joiner;

/**
 *
 */
public class ContactInfo {
    private final String name;
    private final String phoneNumber;
    private final String emailAddress;

    ContactInfo(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    /**
     * returns the full name of the individual (eg. John Smith, Susan Malick)
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * returns the phone number formatted as a sequence of digits
     * @return
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * returns the email address
     * @return
     */
    public String getEmailAddress() {
        return this.emailAddress;
    }

    @Override
    public String toString() {
        return Joiner.on("\n").join(new String[] {"Name: " + name, "Phone: " + phoneNumber, "Email: " + emailAddress});
    }
}
