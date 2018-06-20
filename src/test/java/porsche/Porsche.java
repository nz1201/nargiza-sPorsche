package porsche;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Porsche {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
 
		WebDriver p = new ChromeDriver();
		p.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		String url = "https://www.porsche.com/usa/modelstart/";
		p.get(url);
		p.findElement(By.className("b-teaser-preview-wrapper")).click();
		String base =p.findElement(By.className("m-14-model-price")).getText();
		String c="";
		for(int i=0;i<base.length();i++) {
			if(base.charAt(i)=='.')  break;
			if(Character.isDigit(base.charAt(i))) {
				c +=base.charAt(i);
			}

		}System.out.println("c is: "+ c);

		p.findElement(By.className("m-14-quick-link")).click();
	
			Thread.sleep(3000);
		
		String firstWin=p.getWindowHandle();
		
		for(String secondWin:p.getWindowHandles()) {//switching window
			p.switchTo().window(secondWin);
		}
		Thread.sleep(3000);
		String base2 = p.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[1]")).getText();

		String c2="";

		for(int i=0;i<base2.length();i++) {
			if(base2.charAt(i)=='.')  break;
			if(Character.isDigit(base2.charAt(i))) {
				c2 +=base2.charAt(i);
			}
		}System.out.println("c2 is: "+c2);
		
		if(c.equals(c2)) {
			System.out.println("pass");
		}
		else{ System.out.println("fail");
		}
	
	
	String equipment = p.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[2]")).getText();
		String c3 ="";
	for(int i=0;i<equipment.length();i++) {
		if(equipment.charAt(i)=='.')  break;
		if(Character.isDigit(equipment.charAt(i))) {
			c3 +=equipment.charAt(i);
		}
	}
	System.out.println("c3 is equipement: "+c3);
	
	if(c3.equals("0")) {
		System.out.println("true");
	}else {
		System.out.println("false");
	}
	
	String total =p.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[4]")).getText();
	String t ="";
	for(int i=0;i<total.length();i++) {

		if(Character.isDigit(total.charAt(i))) {
			t +=total.charAt(i);
		}
	}System.out.println("total 1 is: "+t);
	String fee = p.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[3]")).getText();
	String f ="";
	for(int i=0;i<fee.length();i++) {

		if(Character.isDigit(fee.charAt(i))) {
			f +=fee.charAt(i);
		}
	}
	System.out.println("fee is : "+f);
	int fee1 = Integer.parseInt(f);
	int total1 = Integer.parseInt(t);
	int base22= Integer.parseInt(c2);
	int base0= Integer.parseInt(c);
	
	int sum =base0+fee1;
	System.out.println("sum is: "+sum);
	if(sum==total1) {
	System.out.println("(sum and total1 are equal)");
	}else {
		System.out.println("(sum and total1 are not equal)");	
	}
	p.findElement(By.xpath("//li[@id='s_exterieur_x_FJ5']")).click();
	String miami = p.findElement(By.xpath("//div[@class='tt_price tt_cell']")).getText();
	
	String miamiString ="";
	System.out.println("miami is "+miami);
	for(int i=0;i<miami.length();i++) {
//		if(miami.charAt(i)=='.')  break;
		if(Character.isDigit(miami.charAt(i))) {
			miamiString +=miami.charAt(i);
		}
	}
	System.out.println("miami string is : "+miamiString);
	String equipmentprice = p.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[2]")).getText();
	System.out.println(" equipment is "+equipmentprice);
	String equipmentpricestring ="";
	
	for(int i=0;i<equipmentprice.length();i++) {
		if(equipmentprice.charAt(i)=='.')  break;
		if(Character.isDigit(equipmentprice.charAt(i))) {
			equipmentpricestring +=equipmentprice.charAt(i);
		}
	}System.out.println("equipmentprice int is : "+equipmentpricestring);
	int equipmentpriceint =Integer.parseInt(equipmentpricestring);
	int miamiint =Integer.parseInt(miamiString); 
	if(miamiint==equipmentpriceint) {
	System.out.println("true");
	}else {
		System.out.println("false");
	}
	//wheels =//li[@id='s_exterieur_x_MXRD']
	
	String totalpricexpath =p.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[4]")).getText();
	String totalpriceString ="";
	for(int i=0;i<totalpricexpath.length();i++) {
		if(totalpricexpath.charAt(i)=='.')  break;
		if(Character.isDigit(totalpricexpath.charAt(i))) {
			totalpriceString +=totalpricexpath.charAt(i);
		}
	}System.out.println("totalpriceString int is : "+totalpriceString);
	
	int totalpriceint =Integer.parseInt(totalpriceString);
	int totalsum = base22+equipmentpriceint+fee1;
	System.out.println("total sum is: "+totalsum );
	if(totalpriceint==totalsum) {
		System.out.println("total price is equal");
	}else {
		System.out.println("total price is not equal");
	}
	Thread.sleep(10000);
	p.findElement(By.xpath("//li[@id='s_exterieur_x_MXRD']")).click();
	Thread.sleep(3000);
	String carreraWheel = p.findElement(By.xpath("(//div[@class='tt_price tt_cell'])[2]")).getText();
	
	System.out.println("carrera wheels is: "+ carreraWheel);
	
	String carreraWheelString ="";
	for(int i=0;i<carreraWheel.length();i++) {
		if(carreraWheel.charAt(i)=='.')  break;
		if(Character.isDigit(carreraWheel.charAt(i))) {
			carreraWheelString +=carreraWheel.charAt(i);
		}
	}System.out.println("carreraWheelString string is : "+carreraWheelString);
	
	String blueWheelEquipment =p.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
	System.out.println(blueWheelEquipment);
	String blueWheelEquipmentString ="";
	for(int i=0;i<blueWheelEquipment.length();i++) {
		if(blueWheelEquipment.charAt(i)=='.')  break;
		if(Character.isDigit(blueWheelEquipment.charAt(i))) {
			blueWheelEquipmentString +=blueWheelEquipment.charAt(i);
		}
	}System.out.println("blueWheelEquipmentString string is : "+blueWheelEquipmentString);
	
	int blueWheelEquipmentInt = Integer.parseInt(blueWheelEquipmentString);
	int carreraWheelInt = Integer.parseInt(carreraWheelString);
	
	
//	 List<WebElement> a4=p.findElements(By.xpath("//div[@class='tt_price tt_cell']"));
//		
//		String wheelPrice1=a4.get(1).getText();
//		 
//		int wheelPrice = digitFromString(wheelPrice1);
	if( blueWheelEquipmentInt==carreraWheelInt+miamiint) {
		System.out.println("true");
	}else {
		System.out.println("false");
	}
	
	
	String blueWheeltotal = p.findElement(By.xpath("(//div[@class='ccaPrice'])[4]")).getText();
	System.out.println(blueWheeltotal);
	
	
	}
}
