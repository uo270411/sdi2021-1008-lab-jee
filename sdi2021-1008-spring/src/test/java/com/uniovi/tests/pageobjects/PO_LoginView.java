package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.*;
import com.uniovi.tests.util.SeleniumUtils;

public class PO_LoginView extends PO_NavView {
	static public void fillForm(WebDriver driver, String dnip, String passwordp) {
		WebElement dni = driver.findElement(By.name("dni"));
		dni.click();
		dni.clear();
		dni.sendKeys(dnip);
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
//Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}
