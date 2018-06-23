package porsche;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Porsche718 {

	// method that  return digits from string 
	public static int stringToDigit(String a) {
		String priceInString = "";
		for (int i = 0; i < a.length(); i++) {
			char digitsInChar = a.charAt(i);
			if (a.charAt(i) == '.')
				break;
			if (Character.isDigit(digitsInChar)) {
				priceInString += digitsInChar;
			}
		}
		int priceInInt = Integer.parseInt(priceInString);
		return priceInInt;
	}

	// =======================================================================================================
	public static void main(String[] args) throws InterruptedException {
		// case 1,2
		WebDriverManager.chromedriver().setup();
		WebDriver d = new ChromeDriver();
		d.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		d.manage().window().fullscreen();
		
		String url = "https://www.porsche.com/usa/modelstart/";
		d.get(url);
		// case 3
		d.findElement(By.xpath("//img[@alt='Porsche - 718']")).click();

		// ===============================================================================================================

		// case 4
		String basePricebefore = d
				.findElement(By.xpath("//div[@id='model-result-row']/div[1]/div/div[1]/div[2]/div[2]")).getText();

		int priceBaseBefore = stringToDigit(basePricebefore);

		// ===============================================================================================================
		// case 5
		d.findElement(By.linkText("Build & Price")).click();

		String parentWindow = d.getWindowHandle();
		Set<String> handles = d.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				d.switchTo().window(windowHandle);
			}
		}
		// ===============================================================================================================
		// case 6

		String basePriceAfter = d.findElement(By.xpath("//section[@id='s_price']/div[1]/div[1]/div[2]")).getText();

		int priceBaseAfter = stringToDigit(basePriceAfter);

		if (priceBaseBefore == priceBaseAfter) {
			System.out.println("Pass.price the same - test 6");
		} else {
			System.out.println("Fail.Price is different - test 6");
		}

		// ===============================================================================================================
		// case 7

		String equipmPr = d.findElement(By.xpath("//section[@id='s_price']/div[1]/div[2]")).getText();

		int actualEqPrice = stringToDigit(equipmPr); // actual equipment price
		int expectedEqPrice = 0;

		if (actualEqPrice == expectedEqPrice) {
			System.out.println("Pass.Price of Equipment is correct - test 7");
		} else {
			System.out.println("Fail.Price of Equipment is not correct - test 7");
		}

		// ===============================================================================================================
		// case 8

		String delivery = d.findElement(By.xpath("//section[@id='s_price']/div[1]/div[3]")).getText();
		int priceDelivery = stringToDigit(delivery);

		// ===============================================================================================================

		// totalprice 1
		String lastPrice = d.findElement(By.xpath("//section[@id='s_price']/div[1]/div[4]")).getText();

		int totalPrice8 = stringToDigit(lastPrice);

		if (totalPrice8 == priceDelivery + priceBaseAfter) {
			System.out.println("Pass.Total price is correct - test 8");
		} else {
			System.out.println("Fail.Total price is not correct - test 8");
		}
		// ===============================================================================================================
		// case 9

		WebElement doublCl = d.findElement(By.xpath("//span[@style='background-color: rgb(0, 120, 138);']"));

		Actions action = new Actions(d);// create Action object for mouse

		action.moveToElement(doublCl).doubleClick().build().perform();

		// ===============================================================================================================
		// case 10

		String equipmPrColor = d.findElement(By.xpath("//section[@id='s_price']/div[1]/div[2]")).getText();
		int priceEquipmentTotal = stringToDigit(equipmPrColor);

		// price for color
		String exPri = d.findElement(By.xpath("//div[@id='s_exterieur_x_IAF']/div[2]/div[1]/div[1]/div[2]")).getText();
		int priceColor = stringToDigit(exPri);

		if (priceEquipmentTotal == priceColor) {
			System.out.println("Pass.Price of Equipment is correct! After adding color - test 10");
		} else {
			System.out.println("Fail.Price of Equipment is not correct! After adding color - test 10");
		}

		// ===============================================================================================================
		// case 11

		String totalPrice11 = d.findElement(By.xpath("//section[@id='s_price']/div[1]/div[4]")).getText();

		int priceTotal11 = stringToDigit(totalPrice11);

		if (priceTotal11 == priceEquipmentTotal + priceBaseAfter + priceDelivery) {
			System.out.println("Pass.Total price is correct - test 11");
		} else {
			System.out.println("Fail.Total price is not correct - test 11");
		}

		// ===============================================================================================================
		// case 12

		JavascriptExecutor jse = (JavascriptExecutor) d;
		jse.executeScript("scroll(0, 400)");

		WebElement wheel = d.findElement(By.xpath("//div[@id='ARA']/ul/li[8]/span/span"));
		Thread.sleep(2000);
		action.moveToElement(wheel).click().build().perform();

		// =================================================================================================================

		// case 13

		String totalPriceEquipment = d
				.findElement(By.xpath("//div[@id='main']/div[4]/section[2]/section[2]/div[1]/div[2]/div[2]")).getText();
		int priceTotalEquipment13 = stringToDigit(totalPriceEquipment);

		List<WebElement> a4 = d.findElements(By.xpath("//div[@class='tt_price tt_cell']"));
		String wheelPrice1 = a4.get(1).getText();
		int priceWheel = stringToDigit(wheelPrice1);

		if (priceTotalEquipment13 == priceWheel + priceEquipmentTotal) {
			System.out.println("Pass.Total equipment price is correct with wheel and color - test 13");
		} else {
			System.out.println("Fail.Total equipment price is not correct with wheel and color - test 13");
			System.out.println("Total price - " + priceTotalEquipment13);
			System.out.println("Wheel price - " + priceWheel);
			System.out.println("Color price - " + priceEquipmentTotal);
		}

		// =================================================================================================================
		// case 14

		String totalPrice5 = d
				.findElement(By.xpath("//div[@id='main']/div[4]/section[2]/section[2]/div[1]/div[4]/div[2]")).getText();
		int priceTotal14 = stringToDigit(totalPrice5);

		if (priceTotal14 == priceBaseAfter + priceTotalEquipment13 + priceDelivery) {
			System.out.println("Pass. All correct. - test 14");
		} else {
			System.out.println("Fail.Price is not correct - test 14");
			System.out.println("Total price - " + priceTotal14);
			System.out.println("Equipment price - " + priceTotalEquipment13);
			System.out.println("Delivery price - " + priceDelivery);
		}
		// =================================================================================================================
		// case 15

		Thread.sleep(2000);
		jse.executeScript("scroll(0, 1400)");
		Thread.sleep(3000);
		WebElement radio = d.findElement(By.xpath("//div[@id='seats_73']/div[2]/span"));
		action.moveToElement(radio).click().build().perform();

		// ====================================================================================================================\
		// case 16
		String seat = d.findElement(By.xpath("//div[@id='seats_73']/div[2]/div[1]/div[3]/div[1]")).getText();
		int priceSeat = stringToDigit(seat);
		//
		String totalPriceEquipment16 = d
				.findElement(By.xpath("//div[@id='main']/div[4]/section[2]/section[2]/div[1]/div[2]/div[2]")).getText();
		int priceTotalEquipment16 = stringToDigit(totalPriceEquipment16);

		if (priceTotalEquipment16 == priceSeat + priceWheel + priceColor) {
			System.out.println("Pass. Total equipment price with color,wheel,seat are correct - test 16");
		} else {
			System.out.println("Fail. Total equipment price with color,wheel,seat are not correct - test 16");
			System.out.println("Total price is - " + priceTotalEquipment16 + " Seat price is -" + priceSeat
					+ " Wheel price is " + priceWheel + " Color price is - " + priceColor);

		}
		// =================================================================================================================
		// case 17
		String totalPrice7 = d
				.findElement(By.xpath("//div[@id='main']/div[4]/section[2]/section[2]/div[1]/div[4]/div[2]")).getText();
		int priceTotal17 = stringToDigit(totalPrice7);
		if (priceTotal17 == priceBaseAfter + priceDelivery + priceTotalEquipment16) {
			System.out.println("Pass.Total price is correct - test 17");
		} else {
			System.out.println("Fail. Total price is not correct - test 17");
		}
		// =================================================================================================================
		// case 18

		Thread.sleep(1000);
		jse.executeScript("scroll(0, 2200)");
		WebElement click1 = d.findElement(By.xpath("//div[@id='IIC_wrapper']/div/div"));
		Thread.sleep(1000);
		// d.findElement(By.xpath("//div[@id='IIC_subHdl']']")).click();
		action.moveToElement(click1).click().build().perform();

		// =================================================================================================================
		// case 19
		WebElement carbon = d.findElement(By.xpath("//span[@id='vs_table_IIC_x_PEKH_x_c01_PEKH']"));
		Thread.sleep(1000);
		action.moveToElement(carbon).click().build().perform();
		// =================================================================================================================
		// case 20

		String intPrice = d.findElement(By.xpath("//div[@id='vs_table_IIC_x_PEKH']/div[1]/div[2]/div[1]")).getText();
		int priceInterior = stringToDigit(intPrice);

		String totalPriceEquipment1 = d
				.findElement(By.xpath("//div[@id='main']/div[4]/section[2]/section[2]/div[1]/div[2]/div[2]")).getText();
		int priceTotalEquipment20 = stringToDigit(totalPriceEquipment1);

		if (priceTotalEquipment20 == priceInterior + priceSeat + priceWheel + priceColor) {
			System.out.println("Pass. All prices are correct - test 20");
		} else {
			System.out.println("Fail. Prices are not correct - test 20");

		}
		// =================================================================================================================
		// case 21

		String totalPrice21 = d
				.findElement(By.xpath("//div[@id='main']/div[4]/section[2]/section[2]/div[1]/div[4]/div[2]")).getText();
		int priceTotal21 = stringToDigit(totalPrice21);

		if (priceTotal21 == priceBaseAfter + priceTotalEquipment20 + priceDelivery) {
			System.out.println("Pass - test 21");
		} else {
			System.out.println("Fail - test 21");
		}
		// =================================================================================================================
		// case 22

		Thread.sleep(1000);
		d.findElement(By.xpath("//div[@id='IMG_subHdl']")).click();
		// =================================================================================================================
		// case 23

		Thread.sleep(1000);
		d.findElement(By.xpath("//span[@id='vs_table_IMG_x_M250_x_c11_M250']")).click();
		// =================================================================================================================
		// case 24

		Thread.sleep(1000);

		jse.executeScript("scroll(0, 2800)");

		Thread.sleep(1000);
		d.findElement(By.xpath("//span[@id='vs_table_IMG_x_M450_x_c91_M450']")).click();
		// =================================================================================================================
		// case 25

		// case 7 speed
		String SevenSpeed = d.findElement(By.xpath("//div[@id='vs_table_IMG_x_M250']/div[1]/div[2]/div")).getText();
		int priceSevenSpeed = stringToDigit(SevenSpeed);

		// price ceramic brakes
		String brakes = d.findElement(By.xpath("//div[@id='vs_table_IMG_x_M450']/div/div[2]/div")).getText();
		int priceBrakes = stringToDigit(brakes);

		String totalPriceEquipment25 = d
				.findElement(By.xpath("//div[@id='main']/div[4]/section[2]/section[2]/div[1]/div[2]/div[2]")).getText();
		int priceTotalEquipment25 = stringToDigit(totalPriceEquipment25);

		if (priceTotalEquipment25 == priceColor + priceSeat + priceWheel + priceInterior + priceSevenSpeed
				+ priceBrakes) {
			System.out.println("Pass - test 25");
		} else {
			System.out.println("Fail - test 25");
		}
		// =================================================================================================================
		// case 26
		String totalPrice26 = d
				.findElement(By.xpath("//div[@id='main']/div[4]/section[2]/section[2]/div[1]/div[4]/div[2]")).getText();
		int priceTotal26 = stringToDigit(totalPrice26);

		if (priceTotal26 == priceTotalEquipment25 + priceBaseAfter + priceDelivery) {
			System.out.println("Pass - test 26");
		} else {
			System.out.println("Fail - test 26");
		}

		// =================================================================================================================
		// d.switchTo().window(parentWindow); // return to parent window
		Thread.sleep(2000);
		// d.quit();
	}

}
