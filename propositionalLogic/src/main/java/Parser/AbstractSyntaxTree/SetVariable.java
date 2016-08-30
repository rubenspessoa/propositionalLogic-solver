package Parser.AbstractSyntaxTree;

/**
 * Created by rubenspessoa on 30/08/16.
 */
public class SetVariable implements ExpressionNodeVisitor {

    private String name;
    private boolean value;

    public SetVariable(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void visit(VariableExpressionNode node) {
        if (node.getName().equals(this.name)) {
            node.setValue(this.value);
        }
    }

    @Override
    public void visit(ConstantExpressionNode node) {
        //DO nothing
    }

    @Override
    public void visit(ConjunctionExpressionNode node) {
        //DO nothing
    }

    @Override
    public void visit(DisjuctionExpressionNode node) {
        //DO nothing
    }

    @Override
    public void visit(ConsequenceExpressionNode node) {
        //DO nothing
    }

    @Override
    public void visit(BiconsequenceExpressionNode node) {
        //DO nothing
    }

    @Override
    public void visit(NegationExpressionNode node) {
        //DO nothing
    }
}
