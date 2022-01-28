package com.zyro.constans;

import com.github.javafaker.Faker;
import lombok.Getter;

public class ContactPageConstants {

    public static final Faker FAKER = new Faker();
    public static final String SUCCESS_MESSAGE = "Thank You!";
    public static final String FIELD_IS_REQUIRED_ERROR = "This field is required";
    public static final String INVALID_EMAIL_ERROR = "Please enter a valid email address";

    @Getter
    public enum ContactInput {
        NAME("Name*", "Your name"),
        LAST_NAME("Last name", "Your last name"),
        EMAIL("Your email*", "Your email address"),
        MESSAGE("Message*", "Enter your message");

        private final String name;
        private final String placeholder;

        ContactInput(String name, String placeholder) {
            this.name = name;
            this.placeholder = placeholder;
        }
    }
}
