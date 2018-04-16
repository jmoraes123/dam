package dam.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignTest {

	// Instanciando a classe WebDrive
	static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		System.setProperty("webdriver.chrome.driver",
				"C:/Users/jefferson.moraes-pit.PITAGORAS/Documents/Automacao/WebDrive/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://testing.cms.aeducvirtual.com.br:8080/nuxeo");

	}

	@Test
	public void cadastroUsuárioCpfExistente() throws InterruptedException {
		WebElement linkCadastrar = driver.findElement(By.linkText("Cadastrar"));
		linkCadastrar.click();
		WebElement nome = driver.findElement(By.name("nome"));
		nome.sendKeys("Jose");
		WebElement sobrenome = driver.findElement(By.name("sobrenome"));
		sobrenome.sendKeys("Fulanodetal");
		WebElement email = driver.findElement(By.id("input-3"));
		email.sendKeys("jefferson.cflex+fulanodetal@gmail.com");
		WebElement confirmaEmail = driver.findElement(By.id("input-4"));
		confirmaEmail.sendKeys("jefferson.cflex+fulanodetal@gmail.com");
		WebElement senha = driver.findElement(By.id("input-5"));
		senha.sendKeys("Abcd1234@");
		WebElement confirmaSenha = driver.findElement(By.id("input-6"));
		confirmaSenha.sendKeys("Abcd1234@");
		WebElement cpf = driver.findElement(By.name("cpf"));
		cpf.sendKeys("14326125659");
		WebElement dataNascimento = driver.findElement(By.name("dataNascimento"));
		dataNascimento.sendKeys("01011991");
		// WebElement genero = driver.findElement(By.xpath("//*[@id=\"input-9\"]"));
		// genero.click();
		WebElement genero = driver.findElement(By.cssSelector("paper-dropdown-menu"));
		genero.click();
		WebElement generoMasculino = driver.findElement(By.cssSelector("paper-listbox > paper-item:nth-child(2)"));
		Boolean resultUntil = new WebDriverWait(driver, 100).until(ExpectedConditions
				.textToBePresentInElement(By.cssSelector("paper-listbox > paper-item:nth-child(2)"), "Masculino"));
		if (resultUntil) {
			generoMasculino.click();
		}
		WebElement cadastrar = driver.findElement(By.id("createButton"));
		cadastrar.click();

		Thread.sleep(1000);
		WebElement messageError = driver.findElement(By.cssSelector("#formUsuario > p"));
		String result = messageError.getText().replace("Erro(s):", "");
		Assert.assertTrue(result.equals("\nCampo CPF (digite somente números) inválido: Já existe um usuário cadastrado com este CPF."));

		driver.quit();

	}

	@Test
	public void confirmarUsuario() {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// driver.quit();
	}

}
