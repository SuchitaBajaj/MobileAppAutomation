package StepDefinitions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.en.*;
import utility.Helper;
import utility.ObjectPropertyFile;

public class awesomeAppAwt {

	AppiumDriver<MobileElement> driver;

	@Given("Launch the app")
	public void launch_the_app() throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung Galaxy S9");
		cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"/App/AwesomeApp.apk");
		cap.setCapability(MobileCapabilityType.UDID, "2ccaa5d0340b7ece");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.awesomeapp");
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.awesomeapp.MainActivity");
		cap.setCapability(MobileCapabilityType.NO_RESET, true);

		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		String ActualTitle = driver.findElement(By.xpath(ObjectPropertyFile.Instructions)).getText(); 
		String ExpectedTitle = "Testing instructions";
		Assert.assertEquals(ActualTitle, ExpectedTitle, "Appium invocation error");
	}

		@When("user user clicks on dashboard and settings tab")
		public void user_user_clicks_on_dashboard_and_settings_tab() {
			driver.findElement(By.xpath(ObjectPropertyFile.dashboard)).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
			String dashboardTitle = "//android.widget.TextView[@content-desc=\"dashboard-title\"]";
			String dashboardActualTitle = driver.findElement(By.xpath(dashboardTitle)).getText(); 
			String dashboardExpectedTitle = "100 coins";
			Assert.assertEquals(dashboardActualTitle, dashboardExpectedTitle, "Unable to navigate on Dashboard page");
	
			//String settingsTab = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.View/android.view.View[3]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView";
			driver.findElement(By.xpath(ObjectPropertyFile.settingsTab)).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 	
			String settingsTabTitle = "//android.widget.TextView[@content-desc=\"settings-title\"]";
			String settingActualtitle = driver.findElement(By.xpath(settingsTabTitle)).getText(); 
			String settingExpectedTitle = "Application Settings";
			Assert.assertEquals(settingActualtitle, settingExpectedTitle, "Unable to navigate on Settings page");
		}
			
		@And("user menu slides out with drag from left edge of screen")
		public void user_menu_slides_out_with_drag_from_left_edge_of_screen() throws InterruptedException {
			driver.findElement(By.xpath(ObjectPropertyFile.InstructionTab)).click();
			Thread.sleep(3000);
			try {
				Helper.scroll(driver, 0.1, 0.1, 0.7, 0.1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@And("user press the button")
		public void user_press_the_button() throws InterruptedException {
			String sliderbutton = "//android.widget.ScrollView[@content-desc=\"instructions\"]/android.view.ViewGroup/android.widget.TextView[46]";
			driver.findElement(By.xpath(sliderbutton)).click();
			Thread.sleep(3000); 
		}

		@Then("validate alert is displayed")
		public void validate_alert_is_displayed() throws InterruptedException {
			Alert alert = driver.switchTo().alert();
			String text = alert.getText();
			if (text.equalsIgnoreCase("42")) {
				alert.accept();
			}
		}
		
}



