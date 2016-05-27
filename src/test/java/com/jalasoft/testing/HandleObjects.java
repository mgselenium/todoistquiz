package com.jalasoft.testing;

import com.jalasoft.testing.pages.Footer;
import com.jalasoft.testing.pages.Privacy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jalasoft.testing.pages.CampaignDetail;
import com.jalasoft.testing.pages.CampaignHome;
import com.jalasoft.testing.pages.Login;
import com.jalasoft.testing.pages.MainContainer;
import com.jalasoft.testing.pages.NewCampaignForm;
import com.jalasoft.testing.pages.TabBar;

import static org.testng.Assert.assertEquals;

public class HandleObjects {

    private MainContainer mainContainer;

    private TabBar tabBar;

    @BeforeClass
    public void setUp() {
        Login login = new Login();
        mainContainer = login.loginAs("carledriss@freeorg02.com", "P@ssw0rd");
    }

    @BeforeMethod
    public void beforeMethod() {
        tabBar = mainContainer.goToTabBar();
    }

    @Test
    public void testCreateCampaign() {
        String expectedCampaignName = "New Campaign 00001";

        CampaignHome campaignHome = tabBar.clickCampaignsTab();
        NewCampaignForm newCampaignForm = campaignHome.clickNewButton();
        newCampaignForm.setCampaignNameTextField(expectedCampaignName);
        CampaignDetail campaignDetail = newCampaignForm.clickSaveButton();

        assertEquals(campaignDetail.getCampaignName() + " [View Hierarchy]",
                expectedCampaignName);
    }

    @Test
    public void testPrivacyLink() {
        Footer footer = mainContainer.gotToFooter();
        Privacy privacy =  footer.clickPrivacyLink();

        assertEquals(privacy.getTitleText(), "Privacy Statements");

        privacy.switchMainWindow();
    }

//    @Test
//    public void testCreateLead() {
//        String expectedLeadName = "New Lead 00001";
//
//        LeadHome leadHome = tabBar.clickLeadsTab();
//        NewLeadForm newLeadForm = leadHome.clickNewButton();
//        newLeadForm.setLeadNameTextField(expectedLeadName);
//        LeadDetail leadDetail = newLeadForm.clickSaveButton();
//
//        assertEquals(leadDetail.getLeadName() + " [View Hierarchy]",
//                expectedLeadName);
//    }
}
