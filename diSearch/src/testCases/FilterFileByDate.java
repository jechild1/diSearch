 package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageFactories.DocumentsPageFactory;
import pageFactories.SearchPageFactory;
import testCases.ModularTests.LoginMod;
import utilities.AutomationCalendar;
import utilities.AutomationHelper;

/**
 * Test to perform a filter by selecting files that have been uploaded on a specific date.
 * 
 * @author Jesse Childress
 *
 */
public class FilterFileByDate extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void FileFilter() {

		// Domain to add the files under
		String domain = "Automation Testing";
		String primaryColumnHeader = "File Name(s)";

		// Files to be used for testing | Uploading and Deleting - This is for Filtering
		String file1 = "Filtering a Java Collection by a List _ Baeldung.pdf";
		String file2 = "How to filter a list in Java.pdf";


		// Declare these items in an array for processing later
		List<String> fileList = new ArrayList<String>();
		fileList.add(file1);
		fileList.add(file2);


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

		// First, check to see if a table exists. If not, skip over this.
		if (AutomationHelper.isWebElementPresent(By.xpath("//table"))) {
			 
			Reporter.log("Array list for attempted delete: " + Arrays.toString(fileList.toArray()), true);
			
			for (String currentFile : fileList) {

				// If the file is in the table, go into the row and click delete.
				if (documentsPF.getDocumentsTable().isRowInTableByValue(primaryColumnHeader, currentFile)) {
					// Click delete on the row.
					Reporter.log("Currently attempting to delete: " + currentFile, true);
					documentsPF.getDocumentsTable().clickDeleteInRow(currentFile);
				}
			}

		} else {
			Reporter.log("There is no table object on the page.", true);
		}

		// Navigate back to the main search page.
		documentsPF.clickHomeSlideMenuLink();

		// Obtain a new reference to the search page
		searchPF = new SearchPageFactory();

		/*
		 * Begin the document upload process
		 */

		searchPF.clickIngestFiles();

		// The modal is now displayed
		searchPF.getUploadFiles().clickSelectDomainButton();

		// Select the domain for the file to be attached to
		searchPF.getUploadFiles().selectDomain(domain);

		searchPF.getUploadFiles().uploadFile(file1 + "," + file2);

		Assert.assertEquals(true, searchPF.getUploadFiles().isFileInUploadList(file1), "Assert File 1 present");
		Assert.assertEquals(true, searchPF.getUploadFiles().isFileInUploadList(file2), "Assert File 2 present");


		searchPF.getUploadFiles().clickUpload();

		/*
		 * Begin check for recently uploaded files
		 */

		// Navigate back to the documents section
		searchPF.clickDocumentsSlideMenuLink();

		// Obtain a reference to the Documents page, again
		documentsPF = new DocumentsPageFactory();

		// Scope down the search domain to SQA
		documentsPF.getDomains().selectDomain(domain);

		// Click Search in Domains
		documentsPF.getDomains().clickSearchDomains();
		
		//Further narrow down by selecting date
		AutomationCalendar cal = new AutomationCalendar();
		documentsPF.getDate().setFromDate(cal.getTodaysDate());
		documentsPF.getDate().setToDate(cal.getTodaysDate());
		
		
		//Validate that the newly uploaded files are in the list.
		Assert.assertEquals(documentsPF.getDocumentsTable().isRowInTableByValue("File Name(s)", file1), true, "File 1 exist: ");
		Assert.assertEquals(documentsPF.getDocumentsTable().isRowInTableByValue("File Name(s)", file2), true, "File 2 exist: ");
		
		//Tear down the test and clean up.
		documentsPF.getDocumentsTable().clickDeleteInRow(file1);
		documentsPF.getDocumentsTable().clickDeleteInRow(file2);

		documentsPF.clickLogout();
		
		

	}

}