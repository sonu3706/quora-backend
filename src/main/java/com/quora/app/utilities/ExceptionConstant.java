package com.quora.app.utilities;

public interface ExceptionConstant {
    String USER_DOES_NOT_EXISTS_EXCEPTION = "User does not exists in our record with emailId:- ";
    String UNABLE_TO_GENERATE_TOKEN = "Unable to generate unique Id, please try to login again";
    String USER_ALREADY_EXISTS = "User Already exists, Please try with new emailId or login with existing one";
    String USERID_PASSWORD_MISMATCH = "UserId & password did not match";
    String PASSWORD_CHARACTER_SAME = "Password character cannot be same, please provide different once.";

    /* Questions Exceptions*/
    String QUESTION_CONTENT_NULL = "Question content cannot be empty";
}
