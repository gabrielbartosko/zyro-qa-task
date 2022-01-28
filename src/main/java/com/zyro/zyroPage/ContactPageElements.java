package com.zyro.zyroPage;

public class ContactPageElements {

    public static String getInputByName = "//*[text()=' %s ']/../input";
    public static String getMessageInput = "//*[text()=' Message* ']/../textarea";
    public static String getErrorMessageByInputName = "//*[text()=' %s ']/..//*[contains(@class, 'error-message')]";
    public static String getAllInputErrors = "//*[@name='contactForm']//*[contains(@class, 'error-message')]";
    public static String getSendMessageButton = "//*[@name='submit' and text()='Send message']";
    public static String getSuccessMessage = "//*[contains(@class, 'success-message')]";
}
