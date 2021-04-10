package com.hippo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.hippo.base.TestUtils;

public class ProspectsHomePage extends TestUtils {

	@FindBy(how = How.ID, using = "addManually")
	private WebElement wAddProspectBtn;

	@FindBy(how = How.CLASS_NAME, using = "importIcon import")
	private WebElement wImportsPlusIcon;

	@FindBy(how = How.ID, using = "contactEmail")
	private WebElement wProspectEmailField;

	@FindBy(how = How.ID, using = "contactFirstName")
	private WebElement wProspectFirstNametField;

	@FindBy(how = How.ID, using = "contactLastName")
	private WebElement wProspectLastNameField;

	@FindBy(how = How.ID, using = "contactCompanyName")
	private WebElement wProspectCompanyNameField;

	@FindBy(how = How.ID, using = "createContactBtn")
	private WebElement wProspectCreateBtn;

	@FindAll(@FindBy(how = How.XPATH, using = "//tbody/tr/td[2]"))
	private List<WebElement> wUserEmailIDsFromProspectTable;
	
	@FindBy(how = How.ID, using = "deleteContacts")
	private WebElement wProspectDeleteOption;
	
	@FindBy(how = How.ID, using = "contactDeleteYesBtn")
	private WebElement wContactDeleteYesBtn;
	

	public void addNewProspect(String[] userDetails) throws Exception {
		/***
		 * Enter all the fields of Add prospect popup & add a new prospect entry.
		 * @param List of new user details
		 * @return None.
		 */
		clickOn(wAddProspectBtn, "Add Prospect button");
		typeIn(wProspectEmailField, userDetails[0], "Prospect's email field");
		typeIn(wProspectFirstNametField, userDetails[1], "Prospect's first name field");
		typeIn(wProspectLastNameField, userDetails[2], "Prospect's last name field");
		typeIn(wProspectCompanyNameField, userDetails[3], "Prospect's company field");
		clickOn(wProspectCreateBtn, "Create Prospect button");

	}

	public Boolean isAddNewProspectsWorking(String[] userDetails) {
		/***
		 * Verify whether the user is able to add new prospects.
		 * 
		 * @param List of new user details
		 * @return True if the new prospect is added without any exception, False
		 *         otherwise.
		 */
		try {
			addNewProspect(userDetails);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void removeProspectEntry(String prospectEmail) throws Exception {
		/***
		 * Remove the user entry from the prospect table.
		 * @param prospectEmail: String denotes the email of a prospect
		 * @return None.
		 */
		String xpath = "//td[contains(text(),'" + prospectEmail
				+ "')]//parent::tr/td//label[@class='name-checkbox cursor-pointer']";
		WebElement prospectCheckBox = driver.findElement(By.xpath(xpath));
		clickOn(prospectCheckBox, "Prospect's checkbox of entry " + prospectEmail);
		clickOn(wProspectDeleteOption, "Delete option");
		clickOn(wContactDeleteYesBtn, "Delete yes button");
	}
	
	public Boolean isRemoveProspectsWorking(String prospectEmail) {
		/***
		 * Verify whether the user is able to add remove prospect entry from the table.
		 * @param prospectEmail: String denotes the email of a prospect
		 * @return True if the new prospect is removed without any exception, False otherwise.
		 */
		try {
			removeProspectEntry(prospectEmail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Boolean isRequiredEmailAvailableInProspectsTable(String prospectEmail) {
		/***
		 * Verify whether the required user entry is available on the prospect table.
		 * @param prospectEmail: String denotes the email of a prospect
		 * @return True if the required prospect entry is available, False otherwise.
		 */
		try {
			if (getListText(wUserEmailIDsFromProspectTable, "Email column of Prospects table").contains(prospectEmail))
				return true;
			else
				return false;
		} catch (Exception e) {
			return null;
		}
	}

}
