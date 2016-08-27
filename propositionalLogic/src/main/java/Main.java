import Parser.*;
import Parser.AbstractSyntaxTree.*;

/**
 * Created by rubenspessoa on 06/08/16.
 */
public class Main {

    public static void main(String[] args) {

        /* Testing the Tokenizer */

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

        /* Testing the Parser */

        Parser parser = new Parser();

        try {
            parser.parse(inputTest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* Testing the Abstract Syntax Tree */

        ConstantExpressionNode trueBool = new ConstantExpressionNode(true);
        ConstantExpressionNode falseBool = new ConstantExpressionNode(false);

        ConjunctionExpressionNode innerConj = new ConjunctionExpressionNode();
        innerConj.add(trueBool);
        innerConj.add(falseBool);
        innerConj.add(falseBool);

        DisjuctionExpressionNode innerDisj = new DisjuctionExpressionNode();
        innerDisj.add(trueBool);
        innerDisj.add(falseBool);
        innerDisj.add(trueBool);
        innerDisj.add(trueBool);

        ConjunctionExpressionNode innerConj1 = new ConjunctionExpressionNode();
        innerConj1.add(innerConj);
        innerConj1.add(innerDisj);

        ConsequenceExpressionNode innerCons = new ConsequenceExpressionNode(
                innerConj1,
                falseBool);

        BiconsequenceExpressionNode biconsequence = new BiconsequenceExpressionNode(innerCons, trueBool);

        try {
            System.out.println("The result is: " + biconsequence.getValue());
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

    }
}
