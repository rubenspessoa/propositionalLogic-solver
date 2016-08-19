package Parser;

import com.sun.tools.corba.se.idl.constExpr.EvaluationException;

/**
 * Created by rubenspessoa on 18/08/16.
 */
public class VariableExpressionNode implements ExpressionNode {

    private String name;
    private double value;
    private boolean valueSet;

    public VariableExpressionNode(String name) {
        this.name = name;
        this.valueSet = false;
    }

    public int getType() {
        return ExpressionNode.VARIABLE_NODE;
    }

    public void setValue(double value) {
        this.value = value;
        this.valueSet = true;
    }

    @Override
    public double getValue() throws EvaluationException {
        if (valueSet) {
            return this.value;
        } else {
            throw new EvaluationException("Variable '" + this.name + "' was not initialized.");
        }
    }


}
