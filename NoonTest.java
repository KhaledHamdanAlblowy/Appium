import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class NoonTest {

	public static AndroidDriver<MobileElement> driver;

	@BeforeTest
	public void startTest() throws MalformedURLException, InterruptedException {
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "(Galaxy Nexus) API 28");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
		cap.setCapability(MobileCapabilityType.APP, "noonshopping.apk");
 
		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(url, cap);	
	}

	@AfterTest
	public void closeOrQuit() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	@BeforeMethod
	public void sleepBeforeMethod() throws InterruptedException {
		Thread.sleep(4000);

	}

	@Test (priority = 0)
	public void startUpTOMainPage() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		MobileElement saudiArabiaElement = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Saudi Arabia\"]"));
		saudiArabiaElement.click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		MobileElement denyLocElement = driver.findElement(By.id("com.android.packageinstaller:id/permission_deny_button")); 
		denyLocElement.click();
		Thread.sleep(4000);
		WebElement confirElement = driver.findElement(By.xpath("//android.widget.TextView[@text='CONFIRM LOCATION']"));
		confirElement.click();		
		Thread.sleep(4000);
		TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
		touchAction.tap(PointOption.point(100, 50))
		.perform();


	}

	@Test (priority = 1)
	public void proceedToLogin() throws MalformedURLException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS);
		TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
		MobileElement myAccountTabElement = driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"My Account, tab, 4 out of 5\"]"));
		Point locPoint =  myAccountTabElement.getLocation();
		int x = locPoint.getX();
		int y = locPoint.getY();
		touchAction.tap(PointOption.point(x, y))
		.perform();
		MobileElement signInElement = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]"));
		signInElement.click();
		Thread.sleep(3000);

	}
	
	@Test (priority = 2)
	public void loginToAccount () {
		//get email and Password
		AppDriver datAppDriver = new AppDriver();
		//make it private
		MobileElement nameElement = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.EditText"));
		nameElement.sendKeys(datAppDriver.email());
		MobileElement passElement = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.EditText"));
		passElement.sendKeys(datAppDriver.password());
		MobileElement signClickElement = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
		signClickElement.click();
	}


	@Test (priority = 3)
	public void addItemOne() throws InterruptedException {

		Thread.sleep(3000);
		MobileElement categoriesTab = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Categories\"]"));
		categoriesTab.click();
		driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS);
		//clicking mens fashion
		MobileElement mensFashionElement = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.TextView"));
		mensFashionElement.click();
		driver.findElementByXPath("//android.widget.TextView[@text='New Arrivals']").click();
		
		String text = "adidas Duramo SL Running Shoes White";
		MobileElement item = driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ text + "\").instance(0))");
		item.click();
		
		//Adding to cart then continue shopping
		driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS) ;
		MobileElement addToCartElement = driver.findElement(By.xpath("//android.widget.TextView[@text='ADD TO CART']"));
		addToCartElement.click();
		driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS) ;
		MobileElement continuElement = driver.findElement(By.xpath("//android.widget.TextView[@text='CONTINUE SHOPPING']"));
		continuElement.click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
				
		//click back to the main category page
		driver.findElement(By.xpath("//android.widget.ImageButton")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		driver.findElement(By.xpath("//android.widget.ImageButton")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		
	}

	@Test (priority = 4)
	public void addItemTwo() {
		driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[6]/android.view.ViewGroup")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

		driver.findElementByXPath("//android.widget.TextView[@text=\"Laptops\"]").click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

		MobileElement itemLaptop = driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ "Apple Macbook Air With 13.3-Inch Retina Display, Core i3 Processor/8GB RAM/256GB SSD/Intel Iris Plus Graphics With English Keyboard Space Gray"+
				"\").instance(0))");

		itemLaptop.click();
		//contunue shooping to the third elements
		driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS) ;
		MobileElement addToCartElement1 = driver.findElement(By.xpath("//android.widget.TextView[@text='ADD TO CART']"));
		addToCartElement1.click();


		driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS) ;
		MobileElement continuElement1 = driver.findElement(By.xpath("//android.widget.TextView[@text='CONTINUE SHOPPING']"));
		continuElement1.click();

		driver.findElement(By.xpath("//android.widget.ImageButton")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

		driver.findElement(By.xpath("//android.widget.ImageButton")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		
	}
	
	@Test (priority = 5)
	public void addItemThree() {
		driver.findElementByXPath("//android.widget.TextView[@text=\"Electronics\"]").click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		driver.findElementByXPath("//android.widget.TextView[@text=\"Headphones & Speakers\"]").click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		scrollDown();
		driver.findElementByXPath("//android.widget.TextView[@text=\"Wireless Earphones\"]").click();
		driver.findElementByXPath("//android.widget.TextView[@text=\"Apple AirPods Pro Wireless Earphones White \"]").click();
		driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS) ;
		MobileElement addToCartElement2 = driver.findElement(By.xpath("//android.widget.TextView[@text='ADD TO CART']"));
		addToCartElement2.click();
		driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS) ;
		MobileElement continuElement2 = driver.findElement(By.xpath("//android.widget.TextView[@text='CHECKOUT']"));
		continuElement2.click();
		
	}
	@Test (priority = 6)
	public void chooseWayOfPay() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS) ;
		MobileElement continuElement2 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView"));
		continuElement2.click();
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Pay With Card\"]")).click();
		Thread.sleep(5000);
		driver.findElementByXPath("//android.widget.TextView[@text=\"Add a New card\"]").click();
		Thread.sleep(5000);
	}

	@Test (priority = 7)
	public void enterCardDetails(){
		System.out.println(driver.findElementByXPath("//android.widget.TextView[@text=\"Card Number\"]").getText());

		driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS) ;
		MobileElement accountnuMobileElement = driver.findElementByXPath("//android.widget.EditText[@text=\"Enter your card number\"]");
		accountnuMobileElement.click();
		accountnuMobileElement.sendKeys("5217295288465118");
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		String realString = driver.findElementByXPath("//android.widget.TextView[@text=\"Oops! This card number is invalid.\"]").getText();
		String expectedString = "Oops! This card number is invalid.";
		assertEquals(realString, expectedString);

	}


	public void scrollDown() {
		Dimension d = driver.manage().window().getSize();
		int start_x = (int)(d.width*0.5);
		int start_y = (int)(d.height*0.5);
		int end_x = (int)(d.width*0.2);
		int end_y = (int)(d.height*0.1);
		TouchAction touch = new TouchAction(driver);
		touch .press(PointOption.point(start_x,start_y))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(end_x, end_y)).release().perform();
	}

}
