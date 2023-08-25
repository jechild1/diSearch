 package testCases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pageFactories.DocumentsPageFactory;
import pageFactories.SearchPageFactory;
import testCases.ModularTests.LoginMod;
import utilities.AutomationHelper;

/**
 *Deleting 100 files...
 * 
 * @author Jesse Childress
 *
 */
public class DeleteCertainFiles_TEMP extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void uploadFiles() {

		// Domain to add the files under
		String domain = "SQA File Testing";
		String primaryColumnHeader = "File Name(s)";



		// Declare these items in an array for processing later
		List<String> fileList = new ArrayList<String>();
		
		   for (int i = 1; i <= 100; i++) {
	            String fileName = "pdf" + "_" + i + ".pdf";
	            fileList.add(fileName);
	
	        }
		


		// First, login
		// Modular Script for Login
		LoginMod login = new LoginMod();
		login.login(USER_NAME, PASSWORD);

		/*
		 * Begin the delete process, if necessary
		 */

		// Delete the files if they exist already
		// Search Page
		SearchPageFactory searchPF = new SearchPageFactory();

		searchPF.clickDocumentsSlideMenuLink();

		// Documents page
		DocumentsPageFactory documentsPF = new DocumentsPageFactory();

		// Select the domain from the left hand side
		documentsPF.getDomains().selectDomain(domain);

		// Click Search in Domains
		documentsPF.getDomains().clickSearchDomains();

		// Check for the files one by one, and then delete the ones that exist that are
		// a part of the array above.
		
		List<WebElement> trashCans = driver.findElements(By.xpath("//span[@class='anticon anticon-delete']"));
		
		do {
			trashCans.get(0).click();
			waitForPageToLoad();
			trashCans= driver.findElements(By.xpath("//span[@class='anticon anticon-delete']"));
			AutomationHelper.waitSeconds(3);
		}while(trashCans.size() > 0);

//		// First, check to see if a table exists. If not, skip over this.
//		if (AutomationHelper.isWebElementPresent(By.xpath("//table"))) {
//			for (String currentFile : fileList) {
//
//				// If the file is in the table, go into the row and click delete.
//				if (documentsPF.getDocumentsTable().isRowInTableByValue(primaryColumnHeader, currentFile)) {
//					// Click delete on the row.
//					documentsPF.getDocumentsTable().clickDeleteInRow(currentFile);
//				}
//			}
//
//		} else {
//			Reporter.log("There is no table object on the page.", true);
//		}

	

	}

}