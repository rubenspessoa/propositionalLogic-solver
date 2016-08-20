package Parser;

/**
 * Created by rubenspessoa on 20/08/16.
 */

public interface ExpressionNodeVisitor {
    /** Visit a VariableExpressionNode */
    public void visit(VariableExpressionNode node);

    /**  Visit a ConstantExpressionNode */
    public void visit(ConstantExpressionNode node);

    /**  Visit a AdditionExpressionNode */
    public void visit(AdditionExpressionNode node);

    /**  Visit a MultiplicationExpressionNode */
    public void visit(MultiplicationExpressionNode node);

    /**  Visit a ExponentiationExpressionNode */
    public void visit(ExponentiationExpressionNode node);

    /**  Visit a FunctionExpressionNode */
    public void visit(FunctionExpressionNode node);
}
