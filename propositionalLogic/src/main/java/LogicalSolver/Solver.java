package LogicalSolver;

import Parser.AbstractSyntaxTree.BiconsequenceExpressionNode;
import Parser.AbstractSyntaxTree.ExpressionNode;
import Parser.Token;

/**
 * Created by rubenspessoa on 30/08/16.
 */
public class Solver {

    private ExpressionNode expression;

    public Solver(ExpressionNode expression) {
        this.expression = expression;
    }

    /*
    This method search for logical equivalences in order to solve the expression.
    */
    public boolean solve(ExpressionNode expression) {
        //TODO private methods
        return false;
    }

    private ExpressionNode searchForLeafs(ExpressionNode expr) {
        if (expr.getType() == Token.BICONSEQ) {
            BiconsequenceExpressionNode expr_aux = (BiconsequenceExpressionNode) expr;
            searchForLeafs(expr_aux.getLeftTerm());
            searchForLeafs(expr_aux.getRightTerm());
        }
    }
}
