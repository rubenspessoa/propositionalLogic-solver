package Parser;

import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Created by rubenspessoa on 06/08/16.
 */

public class Tokenizer {

    private class TokenInfo {

        public final Pattern regex;
        public final int token;

        public TokenInfo(Pattern regex, int token) {

            super();
            this.regex = regex;
            this.token = token;

        }
    }

    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;

    public Tokenizer() {
        this.tokenInfos = new LinkedList<TokenInfo>();
        this.tokens = new LinkedList<Token>();
    }

    public void add(String regex, int token) {
        this.tokenInfos.add(
                new TokenInfo(
                        Pattern.compile("^("+regex+")"), token));
    }


    public void tokenize(String str) {

        String s = str.trim();
        this.tokens.clear();

        while (!s.equals("")) {

            // The match variable indicates if any of the tokens provided a match with the beginning of the input string.

            boolean match = false;

            for (TokenInfo info : tokenInfos) {

                Matcher m = info.regex.matcher(s);

                if (m.find()) {

                    match = true;
                    String tok = m.group().trim();
                    s = m.replaceFirst("").trim();
                    tokens.add(new Token(info.token, tok));
                    break;

                }
            }

            if (!match)
                throw new ParserException("Unexpected character in input: " + s);
        }
    }

    public LinkedList<Token> getTokens()
    {
        return tokens;
    }
}
