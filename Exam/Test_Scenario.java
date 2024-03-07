package Exam;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Driver;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import practice_session.keyboard;

public class Test_Scenario {

	public static WebDriver driver;
	public static String URL = "https://www.flipkart.com/";
	public static Actions action;
	public static Robot robot; 
	public static String new_window_url;
	public static ArrayList<String> window_Tabs;
	public static String product_name;
	public static String username = "8247613936";
	
	Test_Scenario() throws AWTException{
		driver = new ChromeDriver();
		action = new Actions(driver);
		robot = new Robot();
	}
	
	public static void main(String[] args) throws InterruptedException, AWTException {
		
		Test_Scenario obj = new Test_Scenario();
		
		obj.opening_the_URL(URL);
		obj.verifying_HomePage();
		obj.search_Item("Laptop dell");
		obj.click_on_one_of_the_searchResults();
		obj.add_item_to_cart();
		obj.viewing_cart();
		obj.verify_item_in_cart();
		obj.click_on_the_proceed_button();
		
//		obj.entering_credentials(); // Here OTP is not generated for me in this page Thats's why i'm skipping this login
		// because of this above problem all the below methods are not starting 
		// But i wrote the code for those methods also.
//		obj.verify_the_user_logged_in();
//		obj.entering_shipping_info();
//		obj.click_on_order_continue();
//		obj.selecting_the_payment_method();
		
		obj.exit_Out();
		
	}
	
	// Open the ShopNow website
	void opening_the_URL(String URL) throws InterruptedException {
		
		driver.get(URL);
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
	
	// Verify that the homepage loads successfully.
	void verifying_HomePage() throws InterruptedException {
		
		String expected_title = "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
		String title = driver.getTitle();
		
		if(title.equals(expected_title)) {
			System.out.println("Title matched, Homepage load successfully.");
		}
		else {
			System.out.println("Title not matched, Homepage is different.");
		}
		Thread.sleep(3000);	
	}

	// In the search bar, type "laptop" and press Enter
	void search_Item(String item) throws InterruptedException {
		
		WebElement search_bar = driver.findElement(By.xpath("//input[@title=\"Search for Products, Brands and More\"]"));
		search_bar.sendKeys(item); 
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
	}
	
	// Click on one of the search results to view the product details
	void click_on_one_of_the_searchResults() throws InterruptedException {
		WebElement click_on_item = driver.findElement(By.xpath("//div[@class='_2kHMtA']"));
		click_on_item.click();
		Thread.sleep(2000);
		window_Tabs = new ArrayList<>(driver.getWindowHandles());
	}
	
	// Add the selected laptop to the shopping cart.
	void add_item_to_cart() throws InterruptedException {	//div[@class='_1YokD2 _3Mn1Gg col-5-12 _78xt5Y']//button[@class='_2KpZ6l _2U9uOA _3v1-ww']
		driver.switchTo().window(window_Tabs.get(1));
		product_name = driver.getTitle();
		System.out.println(product_name);
		WebElement add_to_cart_button = driver.findElement(By.xpath("//li[@class='col col-6-12']//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")); 
		action.click(add_to_cart_button).build().perform();
		Thread.sleep(3000);
	}
	
	// Navigate to the shopping cart.
	void viewing_cart() throws InterruptedException {
		WebElement view_cart = driver.findElement(By.xpath("//a[@class='_3SkBxJ']"));
		view_cart.click();
		Thread.sleep(3000);
	}
	
	//Verify that the correct item is in the cart.
	void verify_item_in_cart() {
		WebElement verify_item= driver.findElement(By.xpath("//div[@class='_2-uG6-']"));
		String item_details = verify_item.getText();
		System.out.println(item_details);
		
		if(product_name.contains(item_details)) {
			System.out.println("Product verification is done, Matched...");
		}
		else {
			System.out.println("Product verification is done, Not-Matched...");
		}
	}
	
	//Click on the "Proceed to Checkout" button
	void click_on_the_proceed_button() throws InterruptedException {
		WebElement proceed_button = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2ObVJD _3AWRsL']"));
		proceed_button.click();
		Thread.sleep(3000);
	}
	
	// On the login page, enter valid credentials (username and password) to log in.
	void entering_credentials() throws InterruptedException {
		WebElement username_field = driver.findElement(By.xpath("//input[@class='_2IX_2- _17N0em']"));
		username_field.sendKeys(username);
		Thread.sleep(2000);
		
		// click on continue button
		WebElement continue_button = driver.findElement(By.xpath("//button[@class='_2KpZ6l _20xBvF _3AWRsL']"));
		continue_button.click();
		Thread.sleep(15000);
		
		// It is asking OTP not password so Entering OTP manually, That's why i increased time(15 secs)
		// After that click on login button
		// Here OTP is not generated for me in this page Thats's why i'm skipping this login
		WebElement login_button = driver.findElement(By.xpath("//button[@class='_2KpZ6l _20xBvF _3AWRsL']"));
		login_button.click();
		Thread.sleep(3000);
	}
	
	// Verify that the user is successfully logged in.
	void verify_the_user_logged_in() throws InterruptedException {
		WebElement verify_login = driver.findElement(By.xpath("//div[@class='_1aULyb']"));
		String login_text = verify_login.getText();
		String expected_login_text = "Login";
		
		if(login_text.equals(expected_login_text)) {
			System.out.println("User Logged in successfully.");
		}
		else {
			System.out.println("User not Logged in.");
		}
		Thread.sleep(3000);
	}
	
	// Enter valid shipping information( Iam using the existing address)
	void entering_shipping_info() throws InterruptedException {
		WebElement click_on_Add_address = driver.findElement(By.xpath("(//button[normalize-space()='Deliver Here'])[1]"));
		click_on_Add_address.click();
		Thread.sleep(3000);
	}
	
	// click on continue for order payment confimation
	void click_on_order_continue() throws InterruptedException {
		WebElement order_continue = driver.findElement(By.xpath("(//button[normalize-space()='CONTINUE'])[1]"));
		order_continue.click();
		Thread.sleep(3000);
		// handling the popup here
		Alert at = driver.switchTo().alert();
		at.accept();
		Thread.sleep(3000);
	}
	
	// Selecting the payment method
	void selecting_the_payment_method() throws InterruptedException {
		WebElement select_payment = driver.findElement(By.xpath("//div[@class='_2ECO5T']"));
		select_payment.click();
		Thread.sleep(3000);
	}
	

	//Exiting out of the browser
	void exit_Out() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
