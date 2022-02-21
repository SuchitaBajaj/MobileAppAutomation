package utility;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Helper {
	
	public static int start_x;
	public static int start_y;
	public static int end_x;
	public static int end_y;
	public static Dimension dimension;
 
	public static void scroll(AppiumDriver<MobileElement> driver, double start_xd, double start_yd, double end_xd, double end_yd) throws InterruptedException{

		 dimension = driver.manage().window().getSize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 start_x = (int) (dimension.width * start_xd);
		 start_y = (int) (dimension.height * start_yd);
		 end_x = (int) (dimension.width * end_xd);
		 end_y = (int) (dimension.height * end_yd);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		TouchAction touch = new TouchAction(driver);
		touch.press(PointOption.point(start_x, start_y)).waitAction(WaitOptions
		.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(end_x, end_y)).release().perform();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}
