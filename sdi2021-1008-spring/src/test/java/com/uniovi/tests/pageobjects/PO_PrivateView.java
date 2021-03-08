package com.uniovi.tests.pageobjects;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;


public class PO_PrivateView extends PO_NavView {
	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		// Seleccionamos el alumnos userOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		// Rellenemos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	static public void countElements(WebDriver driver, int number) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == number);
	}
	
	static public void markDetails(WebDriver driver, String nota) {
		SeleniumUtils.esperarSegundos(driver, 1);
		By enlace = By.xpath("//td[contains(text(), '" + nota + "')]/followingsibling::*[2]");
		driver.findElement(enlace).click();
		SeleniumUtils.esperarSegundos(driver, 1);
		// Esperamos por la ventana de detalle
		PO_View.checkElement(driver, "text", "Detalles de la nota");
		SeleniumUtils.esperarSegundos(driver, 1);
	}
	
	static public void selectMarkAndCheck(WebDriver driver, int userOrder, String descriptionp, String scorep) {
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'marks-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de añadir nota: //a[contains(@href,
		// 'mark/add')]
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'mark/add')]");
		// Pinchamos en agregar Nota.
		elementos.get(0).click();
		// Ahora vamos a rellenar la nota. //option[contains(@value, '4')]
		fillFormAddMark(driver, userOrder, descriptionp, scorep);
		// Esperamos a que se muestren los enlaces de paginación la lista de notas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		// Nos vamos a la última página
		elementos.get(3).click();
		// Comprobamos que aparece la nota en la pagina
		elementos = PO_View.checkElement(driver, "text", descriptionp);
	}
	
	static public void deleteMark(WebDriver driver, List<WebElement> elementos, String descriptionp) {
		elementos = PO_View.checkElement(driver, "free", "//td[contains(text(), '"+descriptionp+"')]/following-sibling::*/a[contains(@href, 'mark/delete')]");
		elementos.get(0).click();
		//Volvemos a la última pagina
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'pagelink')]");
		elementos.get(3).click();
		//Y esperamos a que NO aparezca la ultima "Nueva Nota 1"
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, descriptionp, PO_View.getTimeout() );
	}
}
