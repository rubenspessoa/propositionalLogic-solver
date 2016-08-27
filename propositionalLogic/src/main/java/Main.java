import Parser.*;

/**
 * Created by rubenspessoa on 06/08/16.
 */
public class Main {

    public static void main(String[] args) {

        Tokenizer tokenizer = Tokenizer.getExpressionTokenizer();

        String inputTest = "(A voi) v C -> ( D <-> B ) ";

        try {
            tokenizer.tokenize(inputTest);

            for (Token tok : tokenizer.getTokens()) {
                System.out.println("" + tok.token + " " + tok.sequence);
            }
        }
        catch (ParserException e) {
            System.out.println(e.getMessage());
        }

        Parser parser = new Parser();

        try {
            parser.parse(inputTest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
