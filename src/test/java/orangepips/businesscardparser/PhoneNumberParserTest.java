package orangepips.businesscardparser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link PhoneNumberParser}
 */
public class PhoneNumberParserTest {
    @Test
    public void getPhoneNumber() throws Exception {
        String expected = "4108001234";
        String lines[] = new String[] {"A Guy", "F: 8882221111", "P: " + expected};
        doTest(expected, lines);
    }

    public void getPhoneNumber11Digits() throws Exception {
        String expected = "14108001234";
        String lines[] = new String[] {"A Guy", "F: 8882221111", "P: " + expected};
        doTest(expected, lines);
    }

    private void doTest(String expected, String[] lines) {
        PhoneNumberParser phoneNumberParser = new PhoneNumberParser();
        for (String line: lines) {
            phoneNumberParser.consider(line);
        }
        assertEquals(expected, phoneNumberParser.getPhoneNumber());
    }
}