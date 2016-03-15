package com.selvenium.web;

import java.awt.AWTException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;


public class TheInternetSolved {

	 private WebDriver driver;

	    @BeforeTest
	    public void setupSelenium(){
	        driver = new FirefoxDriver();
	    }
	    
	    @AfterTest
	    public void closeSelenium(){
	      //  driver.quit();
	    }

	   
	    
		/*
		 * Example: A/B Testing
		 */
		
		@Test(enabled=false)
		public void ABTesting(){
			//TODO: not sure what do to.
		}

	    

		/*
		 * Example: Basic Auth
		 */
		    
	    @Test(enabled=false)
	    public void BasicAuth(){
	    	/*
	    	//@Beta
	        driver.get("http://the-internet.herokuapp.com/basic_auth");
	        WebDriverWait wait = new WebDriverWait(driver, 10);      
	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());     
	        alert.authenticateUsing(new UserAndPassword("admin", "admin"));*/
	        
	    	//It works
	    	 driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
	    	
	        Assert.assertTrue(driver.getPageSource().contains("Congratulations! You must have the proper credentials."));
			
	    }
    
	    
	    /*
		 * Example: Broken Images
		 */
		    
	    @Test(enabled=false)
	    public void BrokenImages(){
	    	 driver.get("http://the-internet.herokuapp.com/broken_images");
	    	 
	    	 List<WebElement> imagesList = driver.findElements(By.tagName("img"));
	    	 
	    	 System.out.println("Total no. of images are " + imagesList.size());
	    	 
	    	 for (WebElement myimageelementselefjd : imagesList) {
					//if (imgElement != null) {
						verifyimageActive(myimageelementselefjd);
					//}
				}
			
	    	 System.out.println("Total no. of invalid images are "	+ TotalInvalidImages);
				
	    }
	   
	    private int TotalInvalidImages;
	    
	    public void verifyimageActive(WebElement imgElement) {
			try {
				HttpClient client = HttpClientBuilder.create().build();
				
				HttpGet request = new HttpGet(imgElement.getAttribute("src"));
				HttpResponse response = client.execute(request);
				// verifying response code he HttpStatus should be 200 if not,
				// increment as invalid images count
				if (response.getStatusLine().getStatusCode() != 200)
					TotalInvalidImages++;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    
	    
	    /*
		 * Example:Challenging DOM
		 * Yet to do
		 */

	    @Test(enabled=false)
	    public void ChallengingDOM(){
	    	
	    	
	    }
	    
	    
	    /*
		 * Example: Checkboxes
		 * 10 minutes
		 */

	    @Test(enabled=false)
	    public void Checkboxes(){
	    	 driver.get("http://the-internet.herokuapp.com/checkboxes");
	    	
	    	 List<WebElement> checkBoxList=driver.findElements(By.cssSelector("input[type='checkbox']"));
	    	 
	    	 for(WebElement checkBox:checkBoxList)
	    	    {
	    	    	System.out.println(checkBox.getText());
	    	    	if ( !checkBox.isSelected() )
	    	    	{
	    	    		checkBox.click();  
	    	    	    // driver.findElement(By.id("idOfTheElement")).click();
	    	    	}
	    	    }
	    	    
	    	 // Assert if any checkbox left checked
	    	    List<WebElement> allCheckedBoxList=driver.findElements(By.xpath("//input[@type='checkbox' and @checked='checked']"));
	    	        if(!allCheckedBoxList.isEmpty()) {
	    	            Assert.fail();
	    	        }
	    	      
	    }
	    
	    
	    /*
		 * Example: Context Menu
		 * 10 minutes
		 */
	    
	    @Test(enabled=false)
	    public void ContextMenu(){
	    	 driver.get("http://the-internet.herokuapp.com/context_menu");
	    	 
	    	 Actions action = new Actions(driver);

	    	// To click on the element
	    	action.moveToElement(driver.findElement(By.id("hot-spot")))
	    		.contextClick()
	    		.sendKeys(Keys.DOWN)
	    		.sendKeys(Keys.DOWN)
	    		.sendKeys(Keys.DOWN)
	    		.sendKeys(Keys.DOWN)
	    		.sendKeys(Keys.DOWN)
	    		.sendKeys(Keys.ENTER)
	    		//.keyDown(Keys.DOWN)
	    		.build()
	    		.perform();

	    	WebDriverWait wait = new WebDriverWait(driver, 2);
	    	wait.until(ExpectedConditions.alertIsPresent());

	    	Alert alert = driver.switchTo().alert();
	    	String AlertTitle = alert.getText();
	    	alert.dismiss();
	    	
	        Assert.assertEquals(AlertTitle, "You selected a context menu");
	    	
	    }

	    /*
		 * Example: Disappearing Elements
		 * 10 minutes
		 */

	    @Test(enabled=false)
	    public void DisappearingElements(){
	    	driver.get("http://the-internet.herokuapp.com/disappearing_elements");
	    	if(driver.findElement(By.linkText("Gallery")).isDisplayed()){
	    		driver.findElement(By.linkText("Gallery")).click();
	    	}
	    }
	    
	    /*
		 * Example: Drag and Drop
		 * Not working
		 */
	    

	    @Test(enabled=false)
	    public void DragandDrop(){
	    	driver.get("http://the-internet.herokuapp.com/drag_and_drop");
	    
	    	WebElement source = driver.findElement(By.id("column-a"));
	    	WebElement target = driver.findElement(By.id("column-a"));
	    	
	    	Actions builder = new Actions(driver);

	    	Action dragAndDrop = builder.clickAndHold(source)
	    	   .moveToElement(target)
	    	   .release(source)
	    	   .build();

	    	dragAndDrop.perform();
	    	
	    	//(new Actions(driver)).dragAndDrop(source, target).perform();
	    	/*
	    	Actions action = new Actions(driver);
	    	
	    	Action dragAndDrop = action.clickAndHold(source)
	    	.moveToElement(target)
	    	.release(target)
	    	.build();
	    	dragAndDrop.perform();
	    	*/
	    }
	    
	    
	    /*
		 * Example: Disappearing Elements
		 * 10 minutes
		 */
	    
	    @Test(enabled=false)
	    public void Dropdown(){
	    	driver.get("http://the-internet.herokuapp.com/dropdown");
	    	Select dropdown = new Select(driver.findElement(By.id("dropdown")));
	    	dropdown.selectByVisibleText("Option 2");
	    	
	    }
	    
	    
	    /*
		 * Example: Dynamic Content
		 * 10 minutes
		 */
	    
	    @Test(enabled=false)
	    public void DynamicContent(){
	    	
	    	driver.get("http://the-internet.herokuapp.com/dynamic_content");
	    	
	    	List<WebElement> imagesList = driver.findElements(By.tagName("img"));
	    	 
	    	boolean isImagefound = false;
	    	 
	    	 for (WebElement myimageelement : imagesList) {
	    		 System.out.println( myimageelement.getAttribute("src"));
	    		if( myimageelement.getAttribute("src").contains("/img/avatars/Original-Facebook-Geek-Profile-Avatar-3.jpg"))
	    		{
	    			isImagefound = true;
				}
	    	 }
	    	 
	    	 Assert.assertTrue(isImagefound);
	    	
	    	
	    }
	    

	    /*
		 * Example: Dynamic Controls
		 * 10 minutes
		 */
	    
	    @Test(enabled=false)
	    public void DynamicControls(){
	    	driver.get("http://the-internet.herokuapp.com/dynamic_controls");
	    	
	    	driver.findElement(By.id("checkbox")).click();
	    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    	
	    	driver.findElement(By.id("btn")).click();
	    	
	    	 WebDriverWait wait = new WebDriverWait(driver, 10);
	    	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
	    	 
	    	 driver.findElement(By.id("btn")).click();
	    	 
	    	 driver.findElement(By.id("checkbox")).click();
	    	 
	    	 /*
	    	boolean present = wait
	                .ignoring(StaleElementReferenceException.class)
	                .ignoring(NoSuchElementException.class)
	              //  .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("loading"))));
*/
	    	
	    	 //WebDriverWait wait = new WebDriverWait(driver, 10);
	    	// wait.until(ExpectedConditions.invisibilityOfElementLocated(driver.findElement(By.id("loading"))));
	    	 
	    	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //time in second
	    	//WebElement we = driver.findElement(By.id("loading"));  
	    	// System.out.println(we.getCssValue("display"));
	    	//Assert.assertEquals("none", we.getCssValue("display"));
	    	
	    }
	    
	    /*
		 * Example: Dynamic Loading
		 * 10 minutes
		 */
	    
	    @Test(enabled=false)
	    public void DynamicallyLoadedPageElements1(){
	    	
	    	driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
	    	                                                    
	    	driver.findElement(By.xpath("//div[@id='start']/button")).click();
	    	
	    	WebDriverWait wait = new WebDriverWait(driver, 30);
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/H4"))); 
	    	WebElement e = driver.findElement(By.xpath("//div[@id='finish']/H4"));

	    	Assert.assertEquals(e.getText(), "Hello World!");
	    	                                                   
	    }                                         
	    
	    @Test(enabled=false)
	    public void DynamicallyLoadedPageElements2(){
	    	                                         
	    	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2 ");
	    	
	    	driver.findElement(By.xpath("//div[@id='start']/button")).click();
	    	
	    	WebDriverWait wait = new WebDriverWait(driver, 30);      
	    	                            
	    	//presenceOfElementLocated - Verify presence of element in the DOM.    
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='finish']/H4"))); 
	    	 
	    	WebElement e = driver.findElement(By.xpath("//div[@id='finish']/H4"));

	    	Assert.assertEquals(e.getText(), "Hello World! ");
	    	 
	    }
	    
	    
	    @Test(enabled=false)
	    public void ExitIntent_HandleModalWindow() throws AWTException, InterruptedException {
			driver.get("http://the-internet.herokuapp.com/exit_intent");
			
			 driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			 
			 //move the cursor out of viewport
			 Actions actions = new Actions(driver);
			 actions.moveToElement(driver.findElement(By.tagName("html")), -100, -100).perform();
			 
			 Thread.sleep(4000);
			 
			 //driver.switchTo().frame("This is a modal window");
			 
			System.out.println(driver.switchTo().activeElement().getText());
			
			 Thread.sleep(2000);
			
			driver.switchTo().activeElement().findElement(By.xpath("//div[@class='modal-footer']/p")).click();
			 
			 /*

			 Set<String> handle= driver.getWindowHandles();//Return a set of window handle
			
			 String firstWinHandle = driver.getWindowHandle(); 
			 handle.remove(firstWinHandle);
			 
			 String winHandle=handle.iterator().next();
			 
			 if (winHandle!=firstWinHandle){
			 
			 //To retrieve the handle of second window, extracting the handle which does not match to first window handle
			 
				 String secondWinHandle=winHandle; //Storing handle of second window handle
			 
			//Switch control to new window
			 
			 driver.switchTo().window(secondWinHandle);
			 
			 }
			 
			 // System.out.println(driver.switchTo().window(handle).getTitle());
			 */
	    }
	    
	    /*
		 * Example: File Download
		 * 10 minutes
		 */
	    
	  //http://ardesco.lazerycode.com/index.php/2012/07/how-to-download-files-with-selenium-and-why-you-shouldnt/
	    @Test(enabled=false)
	    public void FileDownload(){

	    	driver.get("http://the-internet.herokuapp.com/download");
	    	
	    	FirefoxProfile profile = new FirefoxProfile();
	        String path = "C:\\";
	        profile.setPreference("browser.download.folderList", 2);
	        profile.setPreference("browser.download.dir", path);
	        profile.setPreference("browser.download.alertOnEXEOpen", false);
	       // profile.setPreference("browser.helperApps.neverAsksaveToDisk", "application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel");
	        profile.setPreference("browser.helperApps.neverAsksaveToDisk", "application/txt");
	        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
	        profile.setPreference("browser.download.manager.showWhenStarting", false);
	        profile.setPreference("browser.download.manager.focusWhenStarting", false);
	        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
	        profile.setPreference("browser.download.manager.closeWhenDone", false);
	        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
	        profile.setPreference("browser.download.manager.useWindow", false);
	        profile.setPreference("browser.download.manager.showWhenStarting", false);
	        profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
	        profile.setPreference("pdfjs.disabled", true);

	        WebDriver downloaddriver = new FirefoxDriver(profile);
	        downloaddriver.get("http://the-internet.herokuapp.com/download/some-file.txt");
	        
	    }
	    
	    
	    
	    @Test(enabled=false)
	    public void FileUploader(){
	    	driver.get("http://the-internet.herokuapp.com/upload");
	    	driver.findElement(By.id("file-upload")).sendKeys("C:\\ttt.txt");
	    	driver.findElement(By.id("file-submit")).click();
	    }
	   
	    @Test(enabled=false)
	    public void FloatingMenu(){
	    	driver.get("http://the-internet.herokuapp.com/floating_menu");
	    	driver.findElement(By.linkText("About")).click();
	    	System.out.println(driver.getCurrentUrl());
	    }
	    
	    @Test(enabled=false)
	    public void ForgotPassword(){
	    	driver.get("http://the-internet.herokuapp.com/forgot_password");
	    	driver.findElement(By.id("email")).sendKeys("selvi@test.com");
	    	driver.findElement(By.id("form_submit")).click();
	    	Assert.assertTrue(driver.getPageSource().contains("Your e-mail's been sent!"));
	    	
	    }
	    
	    @Test(enabled=false)
	    public void LoginPageWithValidCredential(){
	    	driver.get("http://the-internet.herokuapp.com/login");
	    	driver.findElement(By.id("username")).sendKeys("tomsmith");
	    	driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
	    	driver.findElement(By.xpath("//button[@class='radius']")).click();
	    	Assert.assertTrue(driver.getPageSource().contains("Welcome to the Secure Area."));
	    }
	    
	    @Test(enabled=false)
	    public void LoginPageWithinValidCredential(){
	    	driver.get("http://the-internet.herokuapp.com/login");
	    	driver.findElement(By.id("username")).sendKeys("selvi@gmail.co");
	    	driver.findElement(By.id("password")).sendKeys("Password!");
	    	driver.findElement(By.xpath("//button[@class='radius']")).click();
	    	
	    	System.out.println(driver.findElement(By.id("flash")).getText());
	    	
	    	Assert.assertTrue((driver.findElement(By.id("flash")).getText().contains("Your username is invalid!")));
	    	
	    }
	    
	    @Test(enabled=false)
	    public void NestedFrames(){
	    	driver.get("http://the-internet.herokuapp.com/nested_frames");
	    	List<WebElement> iframeElements = driver.findElements(By.tagName("frame"));
	    	System.out.println("The total number of iframes are " + iframeElements.size());
	    	
	    	System.out.println(driver.switchTo().frame("frame-top").switchTo().frame("frame-left").getPageSource());
			driver.switchTo().defaultContent();
			System.out.println(driver.switchTo().frame("frame-top").switchTo().frame("frame-middle").getPageSource());
			driver.switchTo().defaultContent();
			System.out.println(driver.switchTo().frame("frame-top").switchTo().frame("frame-right").getPageSource());
			driver.switchTo().defaultContent();
			System.out.println(driver.switchTo().frame(1).getPageSource());
	    	
	    	//driver.findElement(By.id("link01")).click();
	    	
	    }
	   
	    @Test(enabled=false)
	    public void iframe(){
	    	driver.get("http://the-internet.herokuapp.com/iframe");
	    	
	    	// System.out.println(driver.switchTo().frame(0).findElement(By.xpath("//body[@id='tinymce']/p")).getText());
	    	 
	    	 WebElement frame = driver.findElement(By.tagName("iframe"));
	    	 driver.switchTo().frame(frame);
	    	 WebElement element = driver.findElement(By.tagName("body"));
	    	 element.clear();
	    	 element.sendKeys("Selvi loves Selenium");
	         
	            
	            
	  
	   // driver.switchTo().frame(0).findElement(By.xpath("//body[@id='tinymce']/p")).sendKeys("ddd");
	    //driver.switchTo().frame(0).findElement(By.xpath("//body[@id='tinymce']/p")).sendKeys("ddd");	
	    }
	   
	    @Test(enabled=false)
	    public void Geolocation() throws InterruptedException{
	    	
	    	FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("geo.prompt.testing", true);
			profile.setPreference("geo.prompt.testing.allow", true);
	     
	        WebDriver downloaddriver = new FirefoxDriver(profile);
	        downloaddriver.get("http://the-internet.herokuapp.com/geolocation");
	    	
	        downloaddriver.findElement(By.xpath("//div[@id='content']/div/button")).click();
	        
	       // driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	        Thread.sleep(10000);
	        
	       System.out.println(downloaddriver.findElement(By.id("lat-value")).getText());
	        
	       System.out.println(downloaddriver.findElement(By.id("long-value")).getText());
	        
	        
	    }
	    
	    @Test(enabled=false)
	    public void Hovers() throws InterruptedException{
	    	driver.get("http://the-internet.herokuapp.com/hovers");
	    	
	    	List<WebElement> AllFigure = driver.findElements(By.xpath("//div[@class='figure']"));
	    		    	
	    	Actions builder = new Actions(driver);

	    	Action dragAndDrop = builder.moveToElement(AllFigure.get(1)) //move to second avadar
	    	   .build();
	    	dragAndDrop.perform();
	    }
	    
	    @Test(enabled=false)
	    public void JQueryUIMenu() throws InterruptedException{
	    	driver.get("http://the-internet.herokuapp.com/jqueryui/menu");
	    	
	    	Actions builder = new Actions(driver);

	    	Action dragAndDrop = builder.moveToElement(driver.findElement(By.id("ui-id-2"))) //move to second avadar
	    			    			
	    			.moveToElement(driver.findElement(By.id("ui-id-4")))
	    			.click(driver.findElement(By.id("ui-id-8")))
	    			.build();
	    	
	 	    	dragAndDrop.perform();
	    }
	    
	    
	    @Test(enabled=false)
	    public void JavaScriptAlerts_Alert() {
	    	driver.get("http://the-internet.herokuapp.com/javascript_alerts");
	    	
	    	driver.findElement(By.xpath("//div[@class='example']/ul/li/button")).click();
	    	
	    	WebDriverWait wait = new WebDriverWait(driver, 5);
	    	wait.until(ExpectedConditions.alertIsPresent());
	    	
	    	//Switch to Alert
	    	Alert alert = driver.switchTo().alert();
	    	
	    	Assert.assertEquals(alert.getText(), "I am a JS Alert");
	    	
	    	
	    }
	    
	    @Test(enabled=false)
	    public void JavaScriptAlerts_Confirm() {
	    	driver.get("http://the-internet.herokuapp.com/javascript_alerts");
	    	
	    	driver.findElement(By.xpath("//div[@class='example']/ul/li[3]/button")).click();
	    	
	    	WebDriverWait wait = new WebDriverWait(driver, 5);
	    	wait.until(ExpectedConditions.alertIsPresent());
	    	
	    	//Switch to Alert
	    	Alert alert = driver.switchTo().alert();
	    	alert.dismiss();
	    	
	    	
	    	Assert.assertTrue(driver.getPageSource().contains("You clicked: Cancel"));
	    	
	    }
	    
	    
	    @Test(enabled=false)
	    public void JavaScriptAlerts_Prompt() {
	    	driver.get("http://the-internet.herokuapp.com/javascript_alerts");
	    	
	    	driver.findElement(By.xpath("//div[@class='example']/ul/li[3]/button")).click();
	    	
	    	WebDriverWait wait = new WebDriverWait(driver, 5);
	    	wait.until(ExpectedConditions.alertIsPresent());
	    	
	    	Alert alert=driver.switchTo().alert();
			driver.switchTo().alert().sendKeys("my name is selvi");
			alert.accept();
			
			Assert.assertTrue(driver.getPageSource().contains("You entered: my name is selvi"));
			
	    }
	    
	    
	    @Test(enabled=false)
	    public void JavaScriptonloadeventError() {
	    	driver.get("http://the-internet.herokuapp.com/javascript_error");
	    	
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	String errorRetrievalScript = "return window.__webdriver_javascript_errors;";
	    	
	    	System.out.println(js.executeScript(errorRetrievalScript));
	    }
	    
	    
	    
	    @Test(enabled=false)
	    public void LargeDOM() {
	    	driver.get("http://the-internet.herokuapp.com/large");
	    	
	    	WebElement e = driver.findElement(By.xpath("//tr[@class='row-50']/td[@class='column-50']"));
	    	
	    	Assert.assertTrue(e.getText().contains("50.50"));
	    	
	    }
	    
	    
	    @Test(enabled=false)
	    public void OpeningNewWindow() {
	    	driver.get("http://the-internet.herokuapp.com/windows");
	    	
	    	String parentHandle = driver.getWindowHandle(); // get the current window handle
	    	
	    	driver.findElement(By.linkText("Click Here")).click();
	    	
	    	System.out.println(driver.getCurrentUrl());
	    	
	    	for (String handle : driver.getWindowHandles()) {
	    	    driver.switchTo().window(handle);
	    	    }
	    	
	    	System.out.println(driver.getCurrentUrl());
	    	
	    	System.out.println(driver.getPageSource());
	    	
	    	boolean IsNewWindowDataExists = driver.getPageSource().contains("New Window");
	    	
	    	driver.close(); // close newly opened window when done with it
	    	
	    	driver.switchTo().window(parentHandle); // switch back to the original window
	    	
	    	Assert.assertTrue(IsNewWindowDataExists);
	    	
	    	
	    }
	    
	    
	    @Test(enabled=false)
	    public void NotificationMessage() {
	    	driver.get("http://the-internet.herokuapp.com/notification_message_rendered");
	    	
	    	driver.findElement(By.linkText("Click here")).click();
	    	
	    	System.out.println(driver.findElement(By.id("flash")).getText());
	    	
	    }
	    
	    
	    @Test(enabled=false)
	    public void StatusCodes_200() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
	    	WebClient webClient = new WebClient();
	        int code = webClient.getPage("http://the-internet.herokuapp.com/status_codes/200").getWebResponse().getStatusCode();
	        webClient.close();
	        System.out.println(code);
	    	
	    	Assert.assertEquals(code, 200);
	    }
	    
	    @Test(enabled=false)
	    public void StatusCodes_301() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
	   
	    	 HttpURLConnection.setFollowRedirects(false);
		      HttpURLConnection con =
		         (HttpURLConnection) new URL("http://the-internet.herokuapp.com/status_codes/301").openConnection();
		      con.setRequestMethod("HEAD");
		      
		      int code = con.getResponseCode();
		      
		      System.out.println(code);
		      
		      Assert.assertEquals(code, 301);
	    }
	    
	   
	    @Test(enabled=false)
	    public void StatusCodes_404() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
	   
	    	 HttpURLConnection.setFollowRedirects(false);
		      HttpURLConnection con =
		         (HttpURLConnection) new URL("http://the-internet.herokuapp.com/status_codes/404").openConnection();
		      con.setRequestMethod("HEAD");
		      
		      int code = con.getResponseCode();
		      
		      System.out.println(code);
		      
		      Assert.assertEquals(code, 404);
	    }
	    
	    @Test(enabled=false)
	    public void StatusCodes_500() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
	   
	    	 HttpURLConnection.setFollowRedirects(false);
		      HttpURLConnection con =
		         (HttpURLConnection) new URL("http://the-internet.herokuapp.com/status_codes/500").openConnection();
		      con.setRequestMethod("HEAD");
		      
		      int code = con.getResponseCode();
		      
		      System.out.println(code);
		      
		      Assert.assertEquals(code, 500);
	    }
	    
	   
	    
	    @Test(enabled=true)
	    public void SecureFileDownloader() {
	    	driver.get("admin:admin@http://the-internet.herokuapp.com/download_secure");
	    	
	    }
	      
	      
}
