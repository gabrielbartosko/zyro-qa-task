package com.zyro.zyroPage;

import com.zyro.constans.ContactPageConstants.ContactInput;
import com.zyro.global.SeleniumMethods;
import io.qameta.allure.Step;

import static com.zyro.config.ConfigurationManager.configuration;
import static com.zyro.zyroPage.ContactPageElements.getInputByName;
import static com.zyro.zyroPage.ContactPageElements.getMessageInput;
import static com.zyro.zyroPage.ContactPageElements.getSendMessageButton;

public class ContactPageActions extends SeleniumMethods {

    @Step("Navigate to page")
    public void navigateToPage() {
        navigateToURL(configuration().url(), "/contact");
        waitForPageToLoad();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Fill Contact form {input} input")
    public void fillContactFormInput(ContactInput input, String text) {
        if (input.equals(ContactInput.MESSAGE)) {
            enterText(getMessageInput, text);
        } else {
            click(getInputByName, input.getName());
            enterText(getInputByName, text, input.getName());
        }
    }

    @Step("Fill Contact form")
    public void fillContactForm(String firstName, String lastName, String email, String message) {
        fillContactFormInput(ContactInput.NAME, firstName);
        fillContactFormInput(ContactInput.LAST_NAME, lastName);
        fillContactFormInput(ContactInput.EMAIL, email);
        fillContactFormInput(ContactInput.MESSAGE, message);
    }

    public void fillContactForm(String firstName, String email, String message) {
        fillContactForm(firstName, "", email, message);
    }

    @Step("Click Send message button")
    public void clickSendMessageButton() {
        click(getSendMessageButton);
    }
}
