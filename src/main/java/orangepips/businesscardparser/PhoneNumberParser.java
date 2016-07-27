package orangepips.businesscardparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Parse a US based phone number from the passed line to {@link #consider}.
 * Skip if it appears to be a fax number by looking for the letter "f" in the passed line. First in wins.
 */
public class PhoneNumberParser {
    private String phoneNumber = "";

    public void consider(String line) {
        if (phoneNumber.length() > 0) return;

        String digits = line.replaceAll("[^\\d]", "");
        if (digits.length() == 0 || (digits.length() != 10 && digits.length() != 11) || line.toLowerCase().indexOf("f") != -1) return;

        this.phoneNumber = digits;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
}
