package orangepips.businesscardparser;

import com.google.common.base.Joiner;

/**
 * An immutable bean reperesenting the contact information from a business card.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactInfo that = (ContactInfo) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        return emailAddress != null ? emailAddress.equals(that.emailAddress) : that.emailAddress == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        return result;
    }
}
