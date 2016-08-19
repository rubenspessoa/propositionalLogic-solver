package Parser;

import java.util.LinkedList;

/**
 * Created by rubenspessoa on 19/08/16.
 */
public abstract class SequenceExpressionNode implements ExpressionNode {

    public class Term {

        public boolean positive;
        public ExpressionNode expression;

        public Term(boolean positive, ExpressionNode expression) {
            super();
            this.positive = positive;
            this.expression = expression;
        }

    }

    protected LinkedList<Term> terms;

    public SequenceExpressionNode() {
        this.terms = new LinkedList<Term>();
    }

    public SequenceExpressionNode(ExpressionNode a, boolean positive) {
        this.terms = new LinkedList<Term>();
        this.terms.add(new Term(positive, a));
    }

    public void add(ExpressionNode a, boolean positive) {
        this.terms.add(new Term(positive, a));
    }
}
