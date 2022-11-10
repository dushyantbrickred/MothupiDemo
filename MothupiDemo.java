import java.time.Duration;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class MothupiDemo {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Asus\\eclipse-workspace\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		String url="https://www.saucedemo.com";
		driver.get(url);
		String username="standard_user";
		String password="secret_sauce";
		
		//logging in
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
		
		//Select appropriate display order		
		Select selector= new Select(driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")));
		selector.selectByVisibleText("Price (low to high)");
		
		String priceLocator="//div[@class=\"inventory_item_price\"]";
		//get the length of prices
		List<WebElement>inventory= new LinkedList<>();
		inventory = driver.findElements(By.xpath(priceLocator));
		
		//get each price and verify that prices is sorted
		List<Double>price= new LinkedList<>();
		int length=inventory.size();
		for(int i=1;i<=length;i++) {
		price.add(Double.valueOf(driver.findElement(By.xpath("("+priceLocator+")["+(i)+"]")).getText().substring(1)));
		}
		System.out.println(price);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		//check if prices are sorted
		   LinkedList<Double> copy = new LinkedList<Double>(price);
		   Collections.sort(copy);
		   if(copy.equals(price)) {
			   System.out.println("list is sorted");
		}
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.close();
	}
}


