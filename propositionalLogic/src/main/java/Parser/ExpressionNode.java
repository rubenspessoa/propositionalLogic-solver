package Parser;

/**
 * Created by rubenspessoa on 18/08/16.
 */
public interface ExpressionNode {

    public static final int VARIABLE_NODE = 1;
    public static final int CONSTANT_NODE = 2;
    public static final int BICONSEQ_NODE = 3;
    public static final int CONSEQ_NODE = 4;
    public static final int DISJ_NODE = 5;
    public static final int CONJ_NODE = 6;

    public int getType();
    public double getValue() throws Exception;
    //public void accept(ExpressionNodeVisitor visitor);

}
