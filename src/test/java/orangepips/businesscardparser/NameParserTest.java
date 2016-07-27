package orangepips.businesscardparser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link orangepips.businesscardparser.NameParser}
 */
public class NameParserTest {
    @Test
    public void getName() throws Exception {
        String expected = "Matthew Lesko";
        String lines[] = new String[] {expected, "Rocky Road"};
        doTest(expected, lines);
    }

    @Test
    public void getNameWhenNERPartialMatch() throws Exception {
        String expected = "Ray 'Boom Boom' Mancini";
        String lines[] = new String[] {"Rocky Road", expected};
        doTest(expected, lines);
    }

    private void doTest(String expected, String[] lines) {
        NameParser nameParser = new NameParser();
        for (String line: lines) {
            nameParser.consider(line);
        }
        assertEquals(expected, nameParser.getName());
    }
}