package orangepips.businesscardparser;

import com.google.common.base.Joiner;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find the most likely proper name in lines passed to {@link #consider(String)}. Uses Apache OpenNLP to evaluate
 * and determine a probability. Returns the highest probability value from {@link #getName()}. Note, that the evaluation
 * may only consider part of the line to be a proper name, but the entire line is returned under the assumption the
 * data passed will have a separate line with the proper name.
 */
class NameParser {
    private static Tokenizer tokenizer = null;
    private static NameFinderME nameFinder = null;

    static {
        try {
            tokenizer = new TokenizerME(new TokenizerModel(ClassLoader.getSystemResourceAsStream("en-token.bin")));
            nameFinder = new NameFinderME(new TokenNameFinderModel(ClassLoader.getSystemResourceAsStream("en-ner-person.bin")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // taken and modified from https://gist.github.com/johnmiedema/312819de5bd80ca3438a
    private List<Double> nameProbabilities = new ArrayList<Double>();
    private List<String> names = new ArrayList<String>();

    public void consider(String line) {
        String tokens[] = tokenizer.tokenize(line);
        Span nameSpans[] = nameFinder.find(tokens);

        if (nameSpans.length == 0) return;
        // debug(tokens, nameSpans);

        nameProbabilities.add(nameFinder.probs(nameSpans)[0]);
        names.add(line);
    }

    public String getName() {
        if (names.size() == 1) {
            return names.get(0);
        } else if (names.size() > 1) {
            // return the highest probability name
            double maxNameProbability = Double.MIN_VALUE;
            int maxNameProbabilityPosition = -1;
            for (int i = 0; i < nameProbabilities.size(); i++) {
                if (nameProbabilities.get(i) > maxNameProbability) {
                    maxNameProbability = nameProbabilities.get(i);
                    maxNameProbabilityPosition = i;
                }
            }
            return names.get(maxNameProbabilityPosition);
        }
        return "";
    }

    /**
     * Uncomment the reference to this in {@link #consider(String)} to see how OpenNLP evaluated the given line.
     * @param tokens
     * @param nameSpans
     */
    private void debug(String[] tokens, Span[] nameSpans) {
        if (nameSpans.length == 0) return;
        Span nameSpan = nameSpans[0];
        System.out.println("Span: " + nameSpan.toString());
        System.out.println("Covered text is: " + Joiner.on(" ").join(Arrays.asList(tokens).subList(nameSpans[0].getStart(), nameSpans[0].getEnd())));
        System.out.println("Probability is: " + nameFinder.probs(nameSpans)[0]);
    }
}
