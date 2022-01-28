package com.zyro.driver;

import com.zyro.exceptions.TargetNotValidException;
import org.openqa.selenium.WebDriver;

import static com.zyro.config.ConfigurationManager.configuration;

public class TargetFactory {

    public WebDriver createInstance(String browser) {
        Target target = Target.valueOf(configuration().target().toUpperCase());
        WebDriver webdriver;

        if (target == Target.LOCAL) {
            webdriver = BrowserFactory.valueOf(browser.toUpperCase()).createDriver();
        } else {
            throw new TargetNotValidException(target.toString());
        }
        return webdriver;
    }

    enum Target {
        LOCAL
    }
}
