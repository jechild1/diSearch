 package testCases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageFactories.DocumentsPageFactory;
import pageFactories.SearchPageFactory;
import testCases.ModularTests.LoginMod;
import utilities.AutomationHelper;

/**
 * Test to perform a a file upload and assert they're present.
 * 
 * @author Jesse Childress
 *
 */
public class UploadFiles extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void uploadFiles() {

		// Domain to add the files under
		String domain = "SQA Testing";
		String primaryColumnHeader = "File Name(s)";

		// Files to be used for testing | Uploading and Deleting
		String file1 = "SQA Test A - Software Testing Overview.pdf";
		String file2 = "SQA Test B - Selenium.pdf";
		String file3 = "SQA Test C - Jira.pdf";
		String file4 = "SQA Test D - Test Automation.pdf";
		String file5 = "SQA Test E - Java.pdf";

		// Declare these items in an array for processing later
		List<String> fileList = new ArrayList<String>();
		fileList.add(file1);
		fileList.add(file2);
		fileList.add(file3);
		fileList.add(file4);
		fileList.add(file5);

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
			for (String currentFile : fileList) {

				// If the file is in the table, go into the row and click delete.
				if (documentsPF.getDocumentsTable().isRowInTableByValue(primaryColumnHeader, currentFile)) {
					// Click delete on the row.
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

		searchPF.clickUploadFiles();

		// The modal is now displayed
		searchPF.getUploadFiles().clickSelectDomainButton();

		// Select the domain for the file to be attached to
		searchPF.getUploadFiles().selectDomain(domain);

		searchPF.getUploadFiles().uploadFile(file1 + "," + file2 + "," + file3 + "," + file4 + "," + file5);

		Assert.assertEquals(true, searchPF.getUploadFiles().isFileInUploadList(file1), "Assert File 1 present");
		Assert.assertEquals(true, searchPF.getUploadFiles().isFileInUploadList(file2), "Assert File 2 present");
		Assert.assertEquals(true, searchPF.getUploadFiles().isFileInUploadList(file3), "Assert File 3 present");
		Assert.assertEquals(true, searchPF.getUploadFiles().isFileInUploadList(file4), "Assert File 4 present");
		Assert.assertEquals(true, searchPF.getUploadFiles().isFileInUploadList(file5), "Assert File 5 present");

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

		// We are supposed to see ALL of the files, but there is a chance that the files
		// are taking some time to process. As a result, we must wait for them to
		// appear.

		int waitTimeForRecord = 0;
		int waitTimeMax = 90;
		boolean tablePresent;

		// First, lets wait for a table to be present as the files are processing.
		do {
			tablePresent = AutomationHelper.isWebElementPresent(By.xpath("//table"));
			waitTimeForRecord++;

			// If we didn't find a table, increment the count and refresh
			if (!tablePresent) {
				AutomationHelper.waitSeconds(1);
				documentsPF.getDomains().clickSearchDomains();
			}

		} while (!tablePresent && waitTimeForRecord < waitTimeMax);

		// If we never find a table, we will need to throw an exception
		if (!tablePresent) {
			throw new ElementNotVisibleException("There is no table present on the page.");
		}

		// After the table is present, we must loop through until we find each file. It
		// could be that it takes it some time to show up, so we must loop through for a
		// set amount of time.

		// Cycle through each file in the file list
		for (String currentFileName : fileList) {

			int currentFileWaitTime = 0;
			boolean rowFound;

			// First, lets try to find the row. We will do this up to 90 seconds for each
			// and every record
			do {
				rowFound = documentsPF.getDocumentsTable().isRowInTableByValue(primaryColumnHeader, currentFileName);

				currentFileWaitTime++;

				// If we didn't find the particular record, we must refresh and try again.
				if (!rowFound) {
					AutomationHelper.waitSeconds(1);
					documentsPF.getDomains().clickSearchDomains();
				}

			} while (!rowFound && currentFileWaitTime < waitTimeMax);

			// This is the purose of the test. Seeing if the files actually made it.
			Assert.assertEquals(
					documentsPF.getDocumentsTable().isRowInTableByValue(primaryColumnHeader, currentFileName), true,
					"Validating files present: File expected is: " + currentFileName);

		}

	}

}