package orangepips.businesscardparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class PhoneNumberParser {
    private String phoneNumber = "";

    public void consider(String line) {
        if (phoneNumber.length() != 0) return;

        String digits = line.replaceAll("[^\\d]", "");
        if (digits.length() == 0 || (digits.length() != 10 && digits.length() != 11) || line.toLowerCase().indexOf("f") != -1) return;

        this.phoneNumber = digits;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
}
