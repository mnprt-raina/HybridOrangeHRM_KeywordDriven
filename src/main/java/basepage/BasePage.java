package basepage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage 
{

	WebDriver driver;
	Properties prop;
	InputStream is;

	public WebDriver init_Driver(String browserName)
	{
		if (browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\Users\\mnprt\\Downloads\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://s2.demo.opensourcecms.com/orangehrm/symfony/web/index.php/auth/login");
		}
		return driver;
	}

	public Properties init_Property()
	{
		try
		{
			is = new FileInputStream("user.dir"+"/src/main/resources/OR.properties");
			prop = new Properties();
			prop.load(is);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return prop;
	}

}
