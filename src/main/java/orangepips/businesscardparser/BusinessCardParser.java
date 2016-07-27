package orangepips.businesscardparser;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.io.IOException;

/**
 * Solution for "Business Card OCR" from http://asymmetrik.com/programming-challenges/
 */
public class BusinessCardParser {
    private static Options options = new Options();
    static {
        options.addOption("f", "file", true, "File to parse");
        options.addOption("h", "help", false, "Show help");
    }

    public static void main(String... args) throws Exception {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("f")) {
            String document = Files.toString(new File(cmd.getOptionValue("f")), Charsets.UTF_8);
            System.out.println(BusinessCardParser.getContactInfo(document));
        } else {
            showHelp();
        }
    }

    /**
     * Parses the passed string into a Contact Info object. Expects line item per thing to be ID'd e.g.
     * <pre>
     *   Arthur Wilson
     *   Software Engineer
     *   Decision & Security Technologies
     *   ABC Technologies
     *   123 North 11th Street
     *   Suite 229
     *   Arlington, VA 22209
     *   Tel: +1 (703) 555-1259
     *   Fax: +1 (703) 555-1200
     *   awilson@abctech.com
     * </pre>
     *
     * @param document string to parse with data from an OCR'd business card. Expects newline separated data therein.
     * @return object representing the contact information from the passed document param
     */
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

    private static void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "business-card-parser.jar", options );
    }
}
