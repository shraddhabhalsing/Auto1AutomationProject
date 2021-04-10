package com.auto.qa.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.auto.qa.base.Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class AutoHero_MainPage extends Base {
			
	// Find elements at run time

	@FindBy(css="button[id='yearFilter']")
	WebElement yearFilterBtn;

	@FindBy(xpath="//div[@class='container___2SMPk menuContainer___22AAQ containerBottomEnd___1NYoL']//select[@id='rangeStart' and @class='select___2Azxe']")
	WebElement fromDrpdn;

	@FindBy(xpath="//div[@class='root___2wbAN']")
	WebElement containerBlock;

	@FindBy(xpath="//select[@id='sortBy']")
	WebElement sortByPricebtn;

	@FindBys(@FindBy(xpath="//div[@class='ReactVirtualized__Grid__innerScrollContainer']//ul//child :: li[1]"))
	List<WebElement> carFilteredbyRegYeartext;

	@FindBys(@FindBy(xpath="//div[@class='ReactVirtualized__Grid__innerScrollContainer']//div[@class='title___uRijL price___1A8DG']"))
	List <WebElement> ActualcarPriceByDesc;
	
   //Initialize WebElements mentioned in the current page
	public void initializeWebElement(){
		PageFactory.initElements(driver, this);
      }

	//Method to click filter for First registration from 2015 and apply filter
	
	public void clickYearAndSelectFromToYear(String fromYear){	
		try
		{
		yearFilterBtn.click();
		Select fromDrpdown=new Select(fromDrpdn);
		fromDrpdown.selectByVisibleText(fromYear);
		containerBlock.click();
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	//Method to sort price by descending order
	public void sortPriceByDescending(String sortPriceBy){
		try
		{
		sortByPricebtn.click();
		Select priceDrpdown=new Select(sortByPricebtn);
		priceDrpdown.selectByVisibleText(sortPriceBy);
		containerBlock.click();
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	//Method to verify cars are filtered by first registration
	public Boolean verifyCarsFiltered(){
		ArrayList<String>obtainEle=new ArrayList<String>();
		for (WebElement obYearList: carFilteredbyRegYeartext)
		{
			obtainEle.add(obYearList.getText());

		}
		ArrayList<String>sortEle=new ArrayList<String>();
		for (String actYearList: obtainEle){
			sortEle.add(actYearList);
		}
		Collections.reverseOrder();
		 if (sortEle.equals(obtainEle))
		 
			 return true;
		 else
			 return false;
			 
		
		
	}
	
	//Method to verify cars are sorted by price in descending order
	public Boolean verifyCarsSortedDescending(){
		
		ArrayList<String> obtainedList = new ArrayList<String>(); 
		for(WebElement we:ActualcarPriceByDesc){
		 obtainedList.add(we.getText());
		}
				
		ArrayList<String> sortedList = new ArrayList<String>();   
		for(String s:obtainedList){
			sortedList.add(s);
		}
		
		Collections.reverseOrder();
		
		 if (sortedList.equals(obtainedList))
			 
			 return true;
		 else
			 return false;
			 
		
	}


}
