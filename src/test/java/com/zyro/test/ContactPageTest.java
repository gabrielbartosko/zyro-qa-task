package com.zyro.test;

import com.zyro.TestBase;
import com.zyro.constans.ContactPageConstants.ContactInput;
import com.zyro.zyroPage.ContactPageActions;
import com.zyro.zyroPage.ContactPageAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.zyro.constans.ContactPageConstants.FAKER;
import static com.zyro.constans.ContactPageConstants.INVALID_EMAIL_ERROR;
import static com.zyro.constans.ContactPageConstants.SUCCESS_MESSAGE;

public class ContactPageTest extends TestBase {

    private final ContactPageActions contactPageActions = new ContactPageActions();
    private final ContactPageAssertions contactPageAssertions = new ContactPageAssertions();

    @BeforeMethod(alwaysRun = true)
    public void refresh() {
        contactPageActions.navigateToPage();
    }

    @Test(description = "Fill in Contact form and verify it is submitted with lastname")
    void verifySubmitContactFormWithLastName() {
        contactPageAssertions.verifyContactFormPlaceholders();
        contactPageActions.fillContactForm(
                FAKER.name().firstName(), FAKER.name().lastName(), FAKER.internet().emailAddress(),
                FAKER.yoda().quote()
        );
        contactPageActions.clickSendMessageButton();
        contactPageAssertions.verifyContactFormIsSubmitted(SUCCESS_MESSAGE);
    }

    @Test(description = "Fill in Contact form and verify it is submitted without lastname")
    void verifySubmitContactFormWithoutLastName() {
        contactPageAssertions.verifyContactFormPlaceholders();
        contactPageActions.fillContactForm(
                FAKER.name().firstName(), FAKER.internet().emailAddress(), FAKER.yoda().quote());
        contactPageActions.clickSendMessageButton();
        contactPageAssertions.verifyContactFormIsSubmitted(SUCCESS_MESSAGE);
    }

    @Test(description = "Verify placeholders and input errors")
    void verifyContractFormPlaceholdersAndInputErrors() {
        contactPageAssertions.verifyContactFormPlaceholders();
        contactPageActions.clickSendMessageButton();
        contactPageAssertions.verifyInputFieldIsRequiredErrors();
        contactPageActions.fillContactFormInput(ContactInput.EMAIL, FAKER.name().firstName());
        contactPageAssertions.verifyContactFormInputError(ContactInput.EMAIL, INVALID_EMAIL_ERROR);
        contactPageActions.fillContactForm(
                FAKER.name().firstName(), FAKER.internet().emailAddress(), FAKER.yoda().quote());
        contactPageAssertions.verifyInputErrorsAreNotVisible();
    }
}
