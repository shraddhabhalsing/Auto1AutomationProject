package com.auto.qa.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.auto.qa.base.Base;
import com.auto.qa.page.AutoHero_MainPage;
import com.auto.qa.util.CommonUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class AutoHero_MainPageTest extends Base {

	AutoHero_MainPage mainPageObj=new AutoHero_MainPage();

	public static ExtentHtmlReporter pathHtml;
	public static ExtentReports exReport;
	public static ExtentTest exLog,exLog1,exLog2,exLog3,exLog4;

	AutoHero_MainPageTest(){
		super();
	}

	@BeforeTest
	public void basicSetUp()
	{
		DriversetUp();
	    pathHtml=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\src\\main\\java\\com\\auto\\qa\\reporting\\ExtentReportForAuto1.html");
		exReport=new ExtentReports();
		exReport.attachReporter(pathHtml);
		
	}
	
	// Launch URL

	@Test(priority=1)
	public void launchURL(){
		String pageTitle=LaunchBrowser();
		mainPageObj.initializeWebElement();
		exLog=exReport.createTest("Open URL", "Search functionality Automation");
		if (pageTitle.equals("Jetzt Top-Gebrauchtwagen online kaufen | Autohero.com"))
		{
			exLog.log(Status.PASS,"URL is launched successfully") ;
		}

		else

		{
			exLog.log(Status.FAIL,"Failed to launch URL") ;
		}
	}

	@DataProvider
	public Object[][] passSheet()
	{
		Object[][] val=CommonUtil.readDataFromExcel("InputData");
		return val;
	}


	//Filter for First registration (Erstzulassung).Filter for FROM 2015 and apply filter

	@Test(priority=2,dataProvider="passSheet")
	public void filterForFirstRegistration(String RegfromYear){
		mainPageObj.clickYearAndSelectFromToYear(RegfromYear);
		exLog1=exReport.createTest("Filter for First registration", "Search functionality Automation");
		if (RegfromYear.equals("2015"))
		{
			exLog1.log(Status.PASS,"First Registration Year selected Successfully ") ;
		}
		else

		{
			exLog1.log(Status.FAIL,"Failed to select First Registration Year ") ;

		}
	}

	//Sort cars by Price Descending (Höchster Preis)

	@Test(priority=3)
	@Parameters({"SortByPrice"})
	public void sortCarsByPriceDesc(String sortCarsByPric){
		mainPageObj.sortPriceByDescending(sortCarsByPric);
		exLog2=exReport.createTest("Sort cars by Price Descending ", "Search functionality Automation");
		if (sortCarsByPric.equals("Höchster Preis"))
		{
			exLog2.log(Status.PASS,"Price Descending selected successfully") ;
		}
		else

		{
			exLog2.log(Status.FAIL,"Failed to select Price Descending ") ;
		}

	}

	//Verify all cars are filtered by first registration (2015+)

	@Test(priority=4)
	public void verifyCarsFilteredByFirstReg(){
		Boolean verCarFilterstatus=mainPageObj.verifyCarsFiltered();
		exLog3=exReport.createTest("Verify all cars are filtered by first registration", "Search functionality Automation");
		if (verCarFilterstatus)
		{
			exLog3.log(Status.PASS,"All cars are filtered by first registration (2015+) successfully ") ;
		}
		else

		{
			exLog3.log(Status.FAIL," Failed to filter all cars by first registration (2015+)") ;
		}
	}

	//Verify all cars are sorted by price descending

	@Test(priority=5)
	public void verifyCarsSortedByPriceDesc(){
		Boolean verCarSortedStatus=mainPageObj.verifyCarsSortedDescending();
		exLog4=exReport.createTest("Verify all cars are sorted by price descending", "Search functionality Automation");
		if (verCarSortedStatus)
		{
			exLog4.log(Status.PASS,"All cars are sorted by price descending order successfully ") ;
		}
		else

		{
			exLog4.log(Status.FAIL," Failed to filter all cars by price desccending order") ;
		}

	}

	//Close browser

	@AfterTest
	public void closeBrowser() {
		exReport.flush();
		driver.close();
		
	}
}
