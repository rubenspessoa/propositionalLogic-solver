import Parser.*;

/**
 * Created by rubenspessoa on 06/08/16.
 */
public class Main {

    public static void main(String[] args) {

        Tokenizer tokenizer = new Tokenizer();

        tokenizer.add("ˆ", Token.CONJ); //1
        tokenizer.add("v", Token.DISJ); //2
        tokenizer.add("->", Token.CONSEQ); //3
        tokenizer.add("<->", Token.BICONSEQ); //4
        tokenizer.add("¬", Token.NEG); //9
        tokenizer.add("\\(", Token.OPEN_BRACKET); //5
        tokenizer.add("\\)", Token.CLOSE_BRACKET); //6
        tokenizer.add("(\\btrue\\b|\\bfalse\\b)", Token.BOOL); //7
        tokenizer.add("[a-zA-Z]\\w*", Token.VARIABLE); //8

        try {
            tokenizer.tokenize("(true voi) v C -> ( D <-> B ) ");

            for (Token tok : tokenizer.getTokens()) {
                System.out.println("" + tok.token + " " + tok.sequence);
            }
        }
        catch (ParserException e) {
            System.out.println(e.getMessage());
        }



        /*
        String exprstr = "2*(1+sin(pi/2))^2";
        if (args.length>0) exprstr = args[0];

        Parser parser = new Parser();

        try
        {
            ExpressionNode expr = parser.parse(exprstr);
            expr.accept(new SetVariable("pi", Math.PI));
            System.out.println("The value of the expression is "+expr.getValue());

        }
        catch (ParserException e)
        {
            System.out.println(e.getMessage());
        }
        catch (EvaluationException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}
