package com.quora.app.exceptions;

public class QuestionException {
    public static class QuestionContentNull extends RuntimeException {
        public QuestionContentNull(String message) {
            super(message);
        }
    }
}
