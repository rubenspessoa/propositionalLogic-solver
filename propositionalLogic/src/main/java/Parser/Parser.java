package Parser;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by rubenspessoa on 17/08/16.
 */
public class Parser {

    private LinkedList<Token> tokens;
    private Token lookahead;

    public void parse(List<Token> tokens) {
        this.tokens = (LinkedList<Token>) tokens.clone();
        lookahead = this.tokens.getFirst();

        expression();

        if (lookahead.token != Token.EPSILON)
            throw new ParserException("Unexpected symbol %s found", lookahead);

    }

    private void nextToken() {
        this.tokens.pop();
        // at the end of input we return an epsilon token
        if (this.tokens.isEmpty())
            lookahead = new Token(Token.EPSILON, "");
        else
            lookahead = this.tokens.getFirst();
    }

    private void expression() {
        // expression -> signed_term sum_op
        signedTerm();
        sumOp();
    }

    private void sumOp() {
        if (lookahead.token == Token.PLUSMINUS) {
            // sum_op -> PLUSMINUS term sum_op
            nextToken();
            term();
            sumOp();
        } else {
            // sum_op -> EPSILON
        }
    }

    private void signedTerm() {
        if (lookahead.token == Token.PLUSMINUS) {
            // signed_term -> PLUSMINUS term
            nextToken();
            term();
        } else {
            // signed_term -> term
            term();
        }
    }

    private void term() {
        // term -> factor term_op
        factor();
        termOp();
    }

    private void termOp() {
        if (lookahead.token == Token.MULTDIV) {
            // term_op -> MULTDIV factor term_op
            nextToken();
            signedFactor();
            termOp();
        } else {
            // term_op -> EPSILON
        }
    }

    private void signedFactor() {
        if (lookahead.token == Token.PLUSMINUS) {
            // signed_factor -> PLUSMINUS factor
            nextToken();
            factor();
        } else {
            // signed_factor -> factor
            factor();
        }
    }

    private void factor() {
        // factor -> argument factor_op
        argument();
        factorOp();
    }

    private void factorOp() {
        if (lookahead.token == Token.RAISED) {
            // factor_op -> RAISED expression
            nextToken();
            signedFactor();
        } else {
            // factor_op -> EPSILON
        }
    }

    private void argument() {
        if (lookahead.token == Token.FUNCTION) {
            // argument -> FUNCTION argument
            nextToken();
            argument();
        } else if (lookahead.token == Token.OPEN_BRACKET) {
            // argument -> OPEN_BRACKET sum CLOSE_BRACKET
            nextToken();
            expression();

            if (lookahead.token != Token.CLOSE_BRACKET)
                throw new ParserException("Closing brackets expected and "
                        + lookahead.sequence + " found instead");

            nextToken();
        } else {
            // argument -> value
            value();
        }
    }

    private void value() {
        if (lookahead.token == Token.NUMBER) {
            // argument -> NUMBER
            nextToken();
        } else if (lookahead.token == Token.VARIABLE) {
            // argument -> VARIABLE
            nextToken();
        } else {
            throw new ParserException("Unexpected symbol " + lookahead.sequence + " found");
        }
    }
    
}
