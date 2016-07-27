package orangepips.businesscardparser;

import java.util.regex.Pattern;

/**
 * Parses an email address out of the passed data to {@link #consider(String)}
 */
public class EmailAddressParser {
    /**
     * RegEx taken from http://emailregex.com/ - there's also an extensive discussion in the oomments regarding why
     * /.+@.+/ as a RegEx is a better solution. Suffice to say only surefire way to validate a given value is an
     * email address is actually attempt to email it and check the response (e.g. bounceback)
     */
    private static final Pattern EMAIL_ADDDRESS_PATTERN = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    private String emailAddress = "";

    /**
     * Evaluates passed line to search for a string that appears to be an email address.
     * If value has already been found that matches ignores all other values passed in (i.e. first in wins)
     * @param line value to search for an email address
     */
    public void consider(String line) {
        if (this.emailAddress.length() > 0) return;
        if (EMAIL_ADDDRESS_PATTERN.matcher(line).matches()) {
            this.emailAddress = line;
        }
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }
}
