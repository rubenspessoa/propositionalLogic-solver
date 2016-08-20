package Parser;

/**
 * Created by rubenspessoa on 19/08/16.
 */
public class AdditionExpressionNode
        extends SequenceExpressionNode {

    public AdditionExpressionNode() {
        super();
    }

    public AdditionExpressionNode(ExpressionNode a,
                                  boolean positive) {
        super(a, positive);
    }

    public int getType() {
        return ExpressionNode.ADDITION_NODE;
    }

    public double getValue() throws Exception {
        double sum = 0.0;
        for (Term t : terms) {
            if (t.positive)
                sum += t.expression.getValue();
            else
                sum -= t.expression.getValue();
        }
        return sum;
    }

    public void accept(ExpressionNodeVisitor visitor)
    {
        visitor.visit(this);
        for (Term t : terms)
            t.expression.accept(visitor);
    }
}

