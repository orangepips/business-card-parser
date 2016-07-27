package orangepips.businesscardparser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link orangepips.businesscardparser.EmailAddressParser}
 */
public class EmailAddressParserTest {
    @Test
    public void getEmailAddress() throws Exception {
        String expected = "orangepeeps@place.com";
        String lines[] = new String[] {expected, "someguy@domain.net"};
        doTest(expected, lines);
    }

    public void getEmailAddressAtSymbol() throws Exception {
        String expected = "orangepeeps@place.com";
        String lines[] = new String[] {"someguy@", expected};
        doTest(expected, lines);
    }

    public void getEmailAddressEmailWithStuff() throws Exception {
        String expected = "\"let me in\"+nospam@gmail.com";
        String lines[] = new String[] {"someguy@", expected};
        doTest(expected, lines);
    }

    private void doTest(String expected, String[] lines) {
        EmailAddressParser emailAddressParser = new EmailAddressParser();
        for (String line: lines) {
            emailAddressParser.consider(line);
        }
        assertEquals(expected, emailAddressParser.getEmailAddress());
    }
}