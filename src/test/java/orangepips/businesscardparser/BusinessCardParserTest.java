package orangepips.businesscardparser;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link orangepips.businesscardparser.BusinessCardParser}
 */
public class BusinessCardParserTest {
//    @Rule
//    public TemporaryFolder temporaryFolder = new TemporaryFolder();
//
//    @org.junit.Test
//    public void testMainParseFile() throws Exception {
//        URL url = Resources.getResource("example-arthur-wilson.txt");
//        String content = Resources.toString(url, Charsets.UTF_8);
//        File temporaryFile = temporaryFolder.newFile("example-arthur-wilson.txt");
//        Files.write(content, temporaryFile, Charsets.UTF_8);
//
//        BusinessCardParser.main("-f " + temporaryFile.getAbsolutePath());
//    }

    @org.junit.Test
    public void testMainShowHelp() throws Exception {
        BusinessCardParser.main("-h");
    }

    @org.junit.Test
    public void getContactInfoErinWright() throws Exception {
        doTest("example-erin-wright.txt", "Erin Wright", "12025550152", "erin@nac.com");
    }

    @org.junit.Test
    public void getContactInfoJamesMahon() throws Exception {
        doTest("example-james-aw-mahon.txt", "James A.W. Mahon, L.L.B.", "8678734969", "jmahon@marshall.yk.com");
    }

    @org.junit.Test
    public void getContactInfoMikeSmith() throws Exception {
        doTest("example-mike-smith.txt", "Mike Smith", "4105551234", "msmith@asymmetrik.com");
    }

    @org.junit.Test
    public void getContactInfoLisaHuang() throws Exception {
        doTest("example-lisa-huang.txt", "Lisa Haung", "4105551234", "lisa.haung@foobartech.com");
    }

    @org.junit.Test
    public void getContactInfoArthurWilson() throws Exception {
        doTest("example-arthur-wilson.txt", "Arthur Wilson", "17035551259",  "awilson@abctech.com");
    }

    private void doTest(String resource, String name, String phoneNumber, String emailAddress) throws IOException {
        URL url = Resources.getResource(resource);
        String example = Resources.toString(url, Charsets.UTF_8);
        String expected = Joiner.on("\n").join(new String[] {"Name: " + name, "Phone: " + phoneNumber, "Email: " + emailAddress});

        assertEquals(expected, BusinessCardParser.getContactInfo(example).toString());
    }

}