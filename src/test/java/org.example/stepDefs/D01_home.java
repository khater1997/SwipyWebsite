package org.example.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;

import static org.example.stepDefs.Hooks.driver;

public class D01_home {
    ArrayList<String> tabs;

    @When("User open the Swipy homepage and getting the page URl to confirm")
    public void userOpenTheSwipyHomepageAndGettingThePageURlToConfirm() {
        String currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);
    }

    @Then("User show what about Swipy introduction sentence")
    public void userShowWhatAboutSwipyIntroductionSentence() {
        String introSentenceActualRes = driver.findElement(By.xpath("//div[@id='first_sec']//p[2]")).getText();
        Assert.assertTrue(introSentenceActualRes.contains(
                "Welcome to Swipy, where we're redefining the way you network and connect by providing a fresh, simple,"));
    }

    @When("User hover and click on our features")
    public void userHoverAndClickOnOurFeatures() {
        WebElement featureBtn = driver.findElement(By.xpath("//*[@class=\"Header_links__OVXsq\"]/p[2]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(featureBtn).click().perform();
    }

    @Then("User show all Swipy features")
    public void userShowAllSwipyFeatures() throws InterruptedException {
        String actualResult = driver.findElement(By.xpath(
                "//p[contains(text(),'Your contact information changes reflect instantly')]")).getText();
        System.out.println(actualResult);
        Assert.assertTrue(actualResult.contains("Your contact information changes reflect instantly"));
        Thread.sleep(3000);
    }

    @When("User hover and click on For Corporations and subscribe")
    public void userHoverAndClickOnForCorporationsAndSubscribe() {
        WebElement corporateBtn = driver.findElement(By.xpath("//*[@class=\"Header_links__OVXsq\"]/p[3]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(corporateBtn).click().perform();

        String forCorporateSentenceTitle = driver.findElement(By.xpath(
                "//div[@class='MainPage_content__P2HsX']//p[@class='MainPage_midPar__In72r']")).getText();
        System.out.println(forCorporateSentenceTitle);
        Assert.assertTrue(forCorporateSentenceTitle.contains("Digital Business Card Solution"));

        WebElement subscribeBtn = driver.findElement(By.xpath(
                "//div[@class='MainPage_content__P2HsX']//button[@type='button'][normalize-space()='Subscribe Now']"));
        actions.click(subscribeBtn).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        tabs = new ArrayList<>(driver.getWindowHandles());
    }

    @Then("Will navigate to Swipy web app {string}")
    public void willNavigateToSwipyWebApp(String webUrl) throws InterruptedException {
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(300);
        String actualSwipyUrl = driver.getCurrentUrl();
        System.out.println(actualSwipyUrl);
        Assert.assertEquals(actualSwipyUrl, webUrl, "Navigation assertion is failed");
        Thread.sleep(4000);
    }

    @When("User hover and click on For Individuals and download the app")
    public void userHoverAndClickOnForIndividualsAndDownloadTheApp() throws InterruptedException {
        driver.switchTo().window(tabs.get(0));
        WebElement individualsBtn = driver.findElement(By.xpath("//*[@class=\"Header_links__OVXsq\"]/p[4]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(individualsBtn).click().perform();
        Thread.sleep(4000);
    }

    @And("User click on download for IOS to download Swipy")
    public void userClickOnDownloadForIOSToDownloadSwipy() {
        WebElement iosButton = driver.findElement(By.xpath(
                "//div[@class='MainPage_downloads__icoMt']//div[@class='DownloadCard_card__jrI+g']//div//p[contains(text(),'Download for IOS')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", iosButton);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.numberOfWindowsToBe(3));

        tabs = new ArrayList<>(driver.getWindowHandles());
    }

    @Then("user navigate to ios page {string} and opened in new tab")
    public void userNavigateToIosPageAndOpenedInNewTab(String expectedIOSUrl) throws InterruptedException {
        driver.switchTo().window(tabs.get(2));
        Thread.sleep(2000);
        String actualIOSUrl = driver.getCurrentUrl();                                         //get tab 1 (IOS page) url
        System.out.println(actualIOSUrl);                                                      // to confirm when run
        //assertion check if actual IOS page url equal expected one
        Assert.assertEquals(actualIOSUrl, expectedIOSUrl, "IOS Downloading assertion is failed");
    }

    @When("User click on download for Android to download Swipy")
    public void userClickOnDownloadForAndroidToDownloadSwipy() {
        driver.switchTo().window(tabs.get(0));
        WebElement androidButton = driver.findElement(By.xpath(
                "//div[@class='MainPage_downloads__icoMt']//div[@class='DownloadCard_card__jrI+g']//div//p[contains(text(),'Download for Android')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", androidButton);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.numberOfWindowsToBe(4));

        tabs = new ArrayList<>(driver.getWindowHandles());
    }

    @Then("user navigate to android page {string} and opened in new tab")
    public void userNavigateToAndroidPageAndOpenedInNewTab(String expectedAndroidUrl) throws InterruptedException {
        driver.switchTo().window(tabs.get(3));
        Thread.sleep(2000);
        String actualAndroidUrl = driver.getCurrentUrl();        //get tab 1 (Android page) url
        System.out.println(actualAndroidUrl);                         // to confirm when run

        //assertion check if actual Android page url equal expected one
        Assert.assertEquals(actualAndroidUrl, expectedAndroidUrl, "Android Downloading assertion is failed");
    }

    @When("user hover and click our clients button")
    public void userHoverAndClickOurClientsButton() {
        driver.switchTo().window(tabs.get(0));
        WebElement ourClientBtn = driver.findElement(By.xpath("//*[@class=\"Header_links__OVXsq\"]/p[5]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(ourClientBtn).click().perform();
    }

    @Then("Show our clients list")
    public void showOurClientsList() {
        String actSource = driver.findElement(By.xpath("//div[@id='clients_sec']//img[1]")).getAttribute("src");
        Assert.assertTrue(actSource.contains("https://www.swipy.pro/static/media/client1.a4150e83ec09345e87eb.jpg"));
    }

    @When("user hover and click about Us button")
    public void userHoverAndClickAboutUsButton() throws InterruptedException {
        WebElement aboutBtn = driver.findElement(By.xpath("//*[@class=\"Header_links__OVXsq\"]/p[6]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(aboutBtn).click().perform();

        WebDriverWait videoLoadWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        videoLoadWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='react-player__preview']")));
        WebElement playBtn = driver.findElement(By.xpath("//img[@alt='playicon']"));
        playBtn.click();

        String actualResult = driver.findElement(By.xpath("//p[contains(text(),'Your ultimate digital business connection. Say goo')]")).getText();
        System.out.println(actualResult);
        String expectedResult = "Your ultimate digital business connection. Say goo";
        Assert.assertTrue(actualResult.contains(expectedResult), "Assertion is failed");
        Thread.sleep(20000);
        WebElement subscribeBtn = driver.findElement(By.xpath(
                "//div[@class='MainPage_content__P2HsX']//button[@type='button'][normalize-space()='Subscribe Now']"));
        actions.click(subscribeBtn).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(5 ));

        tabs = new ArrayList<>(driver.getWindowHandles());

    }

    @When("user hover and click get in touch button")
    public void userHoverAndClickGetInTouchButton() throws InterruptedException {
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(2000);
        WebElement getInBtn = driver.findElement(By.xpath("//*[@class=\"Header_links__OVXsq\"]/p[7]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(getInBtn).click().perform();

        WebElement aboutBtn = driver.findElement(By.xpath("//button[normalize-space()='Get In Touch']"));
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(aboutBtn).click().perform();
    }

    @And("User enter his data and click send button")
    public void userEnterHisDataAndClickSendButton() {
        WebElement fullName = driver.findElement(By.id("fullName"));
        fullName.sendKeys("khater");

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("mohamed.khater1@ayamedica.org");

        WebElement countryBox = driver.findElement(By.xpath("//div[@class='selected-flag']"));
        countryBox.click();

        WebElement countryCode = driver.findElement(By.xpath("//li[56]"));
        countryCode.click();

        WebElement phoneNum = driver.findElement(By.xpath("//input[@name='[object Object]']"));
        phoneNum.sendKeys("01096347693");

        WebElement message = driver.findElement(By.id("message"));
        message.sendKeys("Swipy, let's talk");
    }

    @Then("User click on send button to send his data")
    public void userClickOnSendButtonToSendHisData() throws InterruptedException {
        WebElement sendButton = driver.findElement(By.xpath(
                "//button[normalize-space()='Send']"));
        sendButton.click();
        Thread.sleep(1000);
        String actualSuccessMessage = driver.findElement(By.xpath("//div[@class='modal-title h4']")).getText();
        System.out.println(actualSuccessMessage);
        Assert.assertTrue(actualSuccessMessage.equals("Your message has been sent successfully"), "Assertion is Failed");

        WebElement doneButton = driver.findElement(By.xpath(
                "//button[normalize-space()='Done']"));
        doneButton.click();
        Thread.sleep(1000);
    }

    @When("user hover and click on Get Started button to create corporate")
    public void userHoverAndClickOnGetStartedButtonToCreateCorporate() {
        WebElement getStartedBtn = driver.findElement(By.xpath("//button[normalize-space()='Get Started']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(getStartedBtn).click().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(6));

        tabs = new ArrayList<>(driver.getWindowHandles());
    }

    @When("user hover and click our contact us button")
    public void userHoverAndClickOurContactUsButton() {
        WebElement contactUsBtn = driver.findElement(By.xpath("//button[normalize-space()='Contact Us']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(contactUsBtn).click().perform();
    }

    @And("user click on facebook icon")
    public void userClickOnFacebookIcon() {
        WebElement faceIcon = driver.findElement(By.xpath("//div[@class='CardTheme_links__0e+ti']//a[1]"));
        faceIcon.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        //S1.2 - get window list inside array
        tabs = new ArrayList<>(driver.getWindowHandles());
    }

    @Then("user navigate to facebook page {string} and opened in new tab")
    public void userNavigateToFacebookPageAndOpenedInNewTab(String faceLink) throws InterruptedException {
        //S1.3 - switch from tab 0 to tab 1
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(2000);
        String actualFacebookUrl = driver.getCurrentUrl();     //get tab 1 (facebook page) url
        System.out.println(actualFacebookUrl);                         // to confirm when run

        //assertion check if actual facebook page url equal expected one
        Assert.assertEquals(actualFacebookUrl, faceLink, "facebook assertion is failed");
    }

    @And("user click on instagram icon")
    public void userClickOnInstagramIcon() {
        WebElement instagramIcon = driver.findElement(By.xpath("//div[@class='CardTheme_links__0e+ti']//a[2]"));
        instagramIcon.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        //S2.2 - get window list inside array
        tabs = new ArrayList<>(driver.getWindowHandles());
    }

    @Then("user navigate to instagram page {string} and opened in new tab")
    public void userNavigateToInstagramPageAndOpenedInNewTab(String instagramLink) throws InterruptedException {
        //S2.3 - switch from tab 0 to tab 1
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(2000);
        String actualInstagramUrl = driver.getCurrentUrl();     //get tab 1 (instagram page) url
        System.out.println(actualInstagramUrl);                         // to confirm when run

        //assertion check if actual instagram page url equal expected one
        Assert.assertEquals(actualInstagramUrl, instagramLink, "instagram assertion is failed");
    }

    @And("user click on linkedin icon")
    public void userClickOnLinkedinIcon() {
        WebElement linkedin = driver.findElement(By.xpath("//div[@class='CardTheme_links__0e+ti']//a[4]"));
        linkedin.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        //S4.2 - get window list inside array
        tabs = new ArrayList<>(driver.getWindowHandles());

    }

    @Then("user navigate to linkedin page {string} and opened in new tab")
    public void userNavigateToLinkedinPageAndOpenedInNewTab(String linkedInLink) throws InterruptedException {
        Thread.sleep(2000);
        //S4.3 - switch from tab 0 to tab 1
        driver.switchTo().window(tabs.get(1));
        String actualLinkedInUrl = driver.getCurrentUrl();     //get tab 1 (linkedIn  page) url
        System.out.println(actualLinkedInUrl);                         // to confirm when run

        //assertion check if actual LinkedIn  page url equal expected one
        Assert.assertEquals(actualLinkedInUrl, linkedInLink, "youtube assertion is failed");

    }

    @And("user click on twitter  icon")
    public void userClickOnTwitterIcon() {
        WebElement twitter = driver.findElement(By.xpath("//div[@class='CardTheme_links__0e+ti']//a[3]"));
        twitter.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        //S4.2 - get window list inside array
        tabs = new ArrayList<>(driver.getWindowHandles());
    }

    @Then("user navigate to twitter  page {string} and opened in new tab")
    public void userNavigateToTwitterPageAndOpenedInNewTab(String twitterLink) throws InterruptedException {
        //S4.3 - switch from tab 0 to tab 1
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(2000);
        String actualTwitterUrl = driver.getCurrentUrl();     //get tab 1 (twitter  page) url
        System.out.println(actualTwitterUrl);                         // to confirm when run

        //assertion check if actual twitter  page url equal expected one
        Assert.assertEquals(actualTwitterUrl, twitterLink, "youtube assertion is failed");
    }

    @And("user click on whatsapp icon")
    public void userClickOnWhatsappIcon() {
        WebElement whatsUp = driver.findElement(By.xpath("//a[normalize-space()='+201091808186']"));
        whatsUp.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.numberOfWindowsToBe(1));

        //S4.2 - get window list inside array
        tabs = new ArrayList<>(driver.getWindowHandles());
    }

    @Then("user navigate to whatsapp page {string} and opened in new tab")
    public void userNavigateToWhatsappPageAndOpenedInNewTab(String whatsUpLink) throws InterruptedException {
        //S4.3 - switch from tab 0 to tab 1
      //  driver.switchTo().window(tabs.get(1));
        Thread.sleep(2000);
        String actualWhatsUprUrl = driver.getCurrentUrl();     //get tab 1 (whatsUp  page) url
        System.out.println(actualWhatsUprUrl);                         // to confirm when run

        //assertion check if actual whatsUp  page url equal expected one
        Assert.assertEquals(actualWhatsUprUrl, whatsUpLink, "youtube assertion is failed");
    }


}
