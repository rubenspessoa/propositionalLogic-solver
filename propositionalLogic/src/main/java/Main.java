import Parser.*;

/**
 * Created by rubenspessoa on 06/08/16.
 */
public class Main {

    public static void main(String[] args)
    {

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
    }
}
