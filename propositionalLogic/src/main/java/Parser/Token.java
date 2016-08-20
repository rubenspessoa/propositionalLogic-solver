package Parser;

/**
 * Created by rubenspessoa on 06/08/16.
 */
public class Token {

    public static final int EPSILON = 0;
    public static final int PLUSMINUS = 1;
    public static final int MULTDIV = 2;
    public static final int RAISED = 3;
    public static final int FUNCTION = 4;
    public static final int OPEN_BRACKET = 5;
    public static final int CLOSE_BRACKET = 6;
    public static final int NUMBER = 7;
    public static final int VARIABLE = 8;

    /** the token identifier */
    public final int token;
    /** the string that the token was created from */
    public final String sequence;
    /** the position of the token in the input string */
    public final int pos;

    /**
     * Construct the token with its values
     * @param token the token identifier
     * @param sequence the string that the token was created from
     * @param pos the position of the token in the input string
     */
    public Token(int token, String sequence, int pos)
    {
        super();
        this.token = token;
        this.sequence = sequence;
        this.pos = pos;
    }




}
