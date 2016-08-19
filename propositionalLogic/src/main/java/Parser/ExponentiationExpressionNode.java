package Parser;

/**
 * Created by rubenspessoa on 19/08/16.
 */
public class ExponentiationExpressionNode
        implements ExpressionNode {

    private ExpressionNode base;
    private ExpressionNode exponent;

    public ExponentiationExpressionNode(ExpressionNode base, 
                                        ExpressionNode exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    public int getType() {
        return ExpressionNode.EXPONENTIATION_NODE;
    }

    public double getValue() throws Exception {
        return Math.pow(base.getValue(), exponent.getValue());
    }
}
