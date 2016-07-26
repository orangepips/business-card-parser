package orangepips.businesscardparser;

/**
 * http://asymmetrik.com/programming-challenges/
 */
public class BusinessCardParser {
    public static ContactInfo getContactInfo(String document) {
        String[] lines = document.split("\\n");

        // assume first line is name until proven otherwise
        NameParser nameParser = new NameParser();
        PhoneNumberParser phoneNumberParser = new PhoneNumberParser();
        EmailAddressParser emailAddressParser = new EmailAddressParser();

        for (String line: lines) {
            nameParser.consider(line);
            phoneNumberParser.consider(line);
            emailAddressParser.consider(line);
        }

        return new ContactInfo(nameParser.getName(), phoneNumberParser.getPhoneNumber(), emailAddressParser.getEmailAddress());
    }

}
