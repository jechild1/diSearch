package testCases;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pageFactories.DocumentDetailsPageFactory;
import pageFactories.DocumentsPageFactory;
import pageFactories.SearchPageFactory;
import testCases.ModularTests.LoginMod;

/**
 * @author Jesse Childress
 *
 */
public class DocumentDetailsValidation extends SearchBaseTestScriptConfig {
	
	@Test
	public void validateDocumentDetailsPageStructure() {
		
		//Log in
		LoginMod login = new LoginMod();
		
		login.login(USER_NAME, PASSWORD);
		
		//Navigate to a known file in SQA Testing domain
		SearchPageFactory searchPF = new SearchPageFactory();
		searchPF.clickDocumentsSlideMenuLink();
		
		//Open the file
		String fileName = "Aretec - Test A.pdf";
		
		DocumentsPageFactory documentsPF = new DocumentsPageFactory();
		
		documentsPF.getDocumentsTable().clickLinkInRow("File Name(s)", fileName);
		
		DocumentDetailsPageFactory documentDetailsPF = new DocumentDetailsPageFactory();
		
		assertTrue(documentDetailsPF.isOverviewLinkPresent(), "Document Details - Overview Link Present");
		assertTrue(documentDetailsPF.isPoliciesLinkPresent(), "Document Details - Policies Link Present");
		assertTrue(documentDetailsPF.isPeopleLinkPresent(), "Document Details - People Link Present");
		assertTrue(documentDetailsPF.isTechnologyLinkPresent(), "Document Details - Technology Link Present");
		assertTrue(documentDetailsPF.isProcessesLinkPresent(), "Document Details - Processes Link Present");
		assertTrue(documentDetailsPF.isOtherLinkPresent(), "Document Details - Other Link Present");
		assertTrue(documentDetailsPF.isImagesLinkPresent(), "Document Details - Images Link Present");
		assertTrue(documentDetailsPF.isTablesLinkPresent(), "Document Details - Tables Link Present");

		
//		System.out.println("Test" + documentDetailsPF.isImagesLinkPresent());
		
		//Validate all of the links
		
		
	}

}
