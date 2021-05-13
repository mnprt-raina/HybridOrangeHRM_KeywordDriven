package executionengine;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.poi.ss.usermodel.Sheet;
import basepage.BasePage;

public class ExecutionEngine 
{
	WebDriver driver;
	WebElement element;

	Properties prop;
	String locatorName = null;
	String locatorValue = null;

	public static Workbook workbook;
	public static Sheet sheet;

	public BasePage base;

	public final String SCENARIO_SHEET_PATH = "E:\\Z_WORK\\HybridOrangeHRM_KeywordDriven\\src\\main\\resources\\utility\\keywords.xlsx";
	

	public void startExecution(String sheetName)
	{
		FileInputStream file = null;

		try
		{
			file = new FileInputStream(SCENARIO_SHEET_PATH);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

		try 
		{
			workbook = WorkbookFactory.create(file);

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

		sheet = workbook.getSheet(sheetName);
		int k =0;
		for(int i =0; i<sheet.getLastRowNum(); i++)
		{
			String locatorColVal = sheet.getRow(i+1).getCell(k+1).toString().trim();
			if(!locatorColVal.equalsIgnoreCase("NA"))
			{
				locatorName = locatorColVal.split("=")[0].trim();
				locatorValue = locatorColVal.split("=")[1].trim();
			}
			String action = sheet.getRow(i+1).getCell(k+2).toString().trim();
			String value = sheet.getRow(i+1).getCell(k+3).toString().trim();

			//actions
			if (action.equals("open browser"))
			{

				base = new BasePage();
				base.init_Property();
				if(value.isEmpty() || value.equals("NA"))
				{
					driver = base.init_Driver(prop.getProperty("browser"));
				}
				else
				{
					driver = base.init_Driver(value);
				}
			}

			else if (action.equals("enter url"))
			{
				if(value.isEmpty() || value.equals("NA"))
				{
					driver.get(prop.getProperty("browser"));
				}
				else
				{
					driver.get(value);
				}

			}
			
			else if (action.equals("quit"))
			{
				//driver.quit();
			}

			//locator
			else if(locatorName.equals("id"))
			{
				element = driver.findElement(By.id(locatorValue));
			
				if(action.equalsIgnoreCase("sendkeys"))
				{
					element.sendKeys(value);
				}
				else if(action.equalsIgnoreCase("click"))
				{
					element.click();
				}
				
			}

		}

	} 
}
