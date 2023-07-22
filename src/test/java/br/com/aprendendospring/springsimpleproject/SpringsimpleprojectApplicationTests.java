package br.com.aprendendospring.springsimpleproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runners.MethodSorters;

@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SpringsimpleprojectApplicationTests {

	@Test
	//teste para salvar usuario
	void teste1() {
		System.setProperty("webdriver.edge.driver", "drivers\\msedgedriver.exe");
        WebDriver browser = new EdgeDriver();
        browser.navigate().to("http://localhost:8080/springsimpleproject/");
        browser.findElement(By.id("primeironome")).sendKeys("Testenome");
        browser.findElement(By.id("segundonome")).sendKeys("Testesobrenome");
        browser.findElement(By.id("idade")).sendKeys("07/09/2003");
        browser.findElement(By.id("submitbutton")).click();
		WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.alertIsPresent());
		String alertText = browser.switchTo().alert().getText();
		assertEquals("Salvo", alertText);
        browser.quit();
	}

	@Test
	//teste para deletar usuario
	void teste2() {
		System.setProperty("webdriver.edge.driver", "drivers\\msedgedriver.exe");
        WebDriver browser = new EdgeDriver();
        browser.navigate().to("http://localhost:8080/springsimpleproject/lista.html");
        browser.findElement(By.className("btn-danger")).click();
		WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Simbutton")));
        browser.findElement(By.id("Simbutton")).click();
		/* org.openqa.selenium.TimeoutException */
		assertThrows(org.openqa.selenium.TimeoutException.class,() -> wait.until(ExpectedConditions.alertIsPresent()));
        browser.quit();
	}

}
