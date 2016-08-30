import Parser.*;
import Parser.AbstractSyntaxTree.*;

/**
 * Created by rubenspessoa on 06/08/16.
 */
public class Main {

    public static void main(String[] args) {

        String inputTest = "(A voi) v C -> ( D <-> B ) ";

        /* Testing the Parser */

        Parser parser = new Parser();
        ExpressionNode expression;

        try {
            expression = parser.parse(inputTest);
            expression.accept(new SetVariable("A", false));
            expression.accept(new SetVariable("oi", false));
            expression.accept(new SetVariable("C", false));
            expression.accept(new SetVariable("D", true));
            expression.accept(new SetVariable("B", true));
            System.out.println(expression.getValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
