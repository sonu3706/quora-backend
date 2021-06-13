package com.quora.app.exceptions;

/*@Chandan Mishra
*  This class is responsible in managing all exception related to token
* */
public class TokenException {
    /* Unable to generate token*/
    public static class UnableToGenerateToken extends RuntimeException {
        public UnableToGenerateToken(String message) {
            super(message);
        }
    }

    /* Token expired */
    public static class TokenExpired extends RuntimeException {
        public TokenExpired(String message) {
            super(message);
        }
    }
}
