/*
 * This software and all files contained in it are distrubted under the MIT license.
 *
 * Copyright (c) 2013 Cogito Learning Ltd
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/*
 * Modified by rubenspessoa on 06/08/16.
 */

package Parser;

import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * A class for reading an input string and separating it into tokens that can be
 * fed into Parser.
 *
 * The user can add regular expressions that will be matched against the front
 * of the string. Regular expressions should not contain beginning-of-string or
 * end-of-string anchors or any capturing groups as these will be added by the
 * tokenizer itslef.
 */
public class Tokenizer {

    /**
     * Internal class holding the information about a token type.
     */

    private class TokenInfo {

        public final Pattern regex;
        public final int token;

        /**
         * Construct TokenInfo with its values
         */
        public TokenInfo(Pattern regex, int token) {
            super();
            this.regex = regex;
            this.token = token;
        }
    }

    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;
    private static Tokenizer expressionTokenizer = null;

    /**
     * Default constructor
     */
    public Tokenizer() {
        super();
        tokenInfos = new LinkedList<TokenInfo>();
        tokens = new LinkedList<Token>();
    }

    /**
     * A static method that returns a tokenizer for logical expressions
     * @return a tokenizer that can handle logical expressions
     */
    public static Tokenizer getExpressionTokenizer() {
        if (expressionTokenizer == null)
            expressionTokenizer = createExpressionTokenizer();
        return expressionTokenizer;
    }

    /**
     * A static method that actually creates a tokenizer for logical expressions
     * @return a tokenizer that can handle mathematical expressions
     */
    private static Tokenizer createExpressionTokenizer() {

        Tokenizer tokenizer = new Tokenizer();

        tokenizer.add("ˆ", Token.CONJ); //1
        tokenizer.add("v", Token.DISJ); //2
        tokenizer.add("->", Token.CONSEQ); //3
        tokenizer.add("<->", Token.BICONSEQ); //4
        tokenizer.add("¬", Token.NEG); //9
        tokenizer.add("\\(", Token.OPEN_BRACKET); //5
        tokenizer.add("\\)", Token.CLOSE_BRACKET); //6
        tokenizer.add("(\\btrue\\b|\\bfalse\\b)", Token.BOOL); //7
        tokenizer.add("[a-zA-Z]\\w*", Token.VARIABLE); //8

        return tokenizer;
    }

    /**
     * Add a regular expression and a token id to the internal list of recognized tokens
     * @param regex the regular expression to match against
     * @param token the token id that the regular expression is linked to
     */
    public void add(String regex, int token) {
        tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex+")"), token));
    }

    /**
     * Tokenize an input string.
     *
     * The reult of tokenizing can be accessed via getTokens
     *
     * @param str the string to tokenize
     */
    public void tokenize(String str) {

        String s = str.trim();
        int totalLength = s.length();
        tokens.clear();

        while (!s.equals("")) {

            int remaining = s.length();
            boolean match = false;

            for (TokenInfo info : tokenInfos) {

                Matcher m = info.regex.matcher(s);

                if (m.find()) {
                    match = true;
                    String tok = m.group().trim();
                    /* Debugging
                    System.out.println("Success matching " + s + " against " +
                    info.regex.pattern() + " : " + tok);
                    */
                    s = m.replaceFirst("").trim();
                    tokens.add(new Token(info.token, tok, totalLength - remaining));
                    break;
                }
            }

            if (!match) throw new ParserException("Unexpected character in input: " + s);
        }
    }

    /**
     * Get the tokens generated in the last call to tokenize.
     * @return a list of tokens to be fed to Parser
     */

    public LinkedList<Token> getTokens() {
        return tokens;
    }
}
