package com.zyro.zyroPage;

import com.zyro.constans.ContactPageConstants.ContactInput;
import com.zyro.global.SeleniumMethods;
import io.qameta.allure.Step;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.zyro.constans.ContactPageConstants.FIELD_IS_REQUIRED_ERROR;
import static com.zyro.zyroPage.ContactPageElements.getAllInputErrors;
import static com.zyro.zyroPage.ContactPageElements.getErrorMessageByInputName;
import static com.zyro.zyroPage.ContactPageElements.getInputByName;
import static com.zyro.zyroPage.ContactPageElements.getMessageInput;
import static com.zyro.zyroPage.ContactPageElements.getSuccessMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class ContactPageAssertions extends SeleniumMethods {

    @Step("Verify Success message")
    public void verifyContactFormIsSubmitted(String successMessage) {
        assertThat(getText(getSuccessMessage)).isEqualTo(successMessage);
    }

    @Step("Verify Contact form {input} placeholder")
    public void verifyContactFormInputPlaceholder(ContactInput input) {
        if (input.equals(ContactInput.MESSAGE)) {
            assertThat(getAttributeValue(getMessageInput, "placeholder")).isEqualTo(input.getPlaceholder());
        } else {
            assertThat(getAttributeValue(getInputByName, "placeholder", input.getName())).isEqualTo(
                    input.getPlaceholder());
        }
    }

    @Step("Verify Contact form placeholders")
    public void verifyContactFormPlaceholders() {
        Arrays.stream(ContactInput.values()).forEach(this::verifyContactFormInputPlaceholder);
    }

    @Step("Verify Contact form {input} input's error")
    public void verifyContactFormInputError(ContactInput input, String error) {
        assertThat(getText(getErrorMessageByInputName, input.getName())).isEqualTo(error);
    }

    @Step("Verify input field is required errors")
    public void verifyInputFieldIsRequiredErrors() {
        Stream.of(ContactInput.NAME, ContactInput.EMAIL, ContactInput.MESSAGE).forEach(
                input -> verifyContactFormInputError(input, FIELD_IS_REQUIRED_ERROR));
    }

    @Step("Verify input errors are not visible")
    public void verifyInputErrorsAreNotVisible() {
        waitForElementToBeInvisible(getAllInputErrors);
    }
}
