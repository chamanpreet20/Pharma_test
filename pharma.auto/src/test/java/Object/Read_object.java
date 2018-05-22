package Object;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class Read_object {
Properties p;

//===============================================Load property file=================================================
public Read_object(String strpath)
{
	p=new Properties();
    try {
	File src=new File(strpath);
	FileInputStream fis=new FileInputStream(src);
	
	p.load(fis);
	fis.close();
	System.out.println("Property class loaded");
	}
catch (IOException e) {
    System.out.println(e.getMessage());
}
}

//=====================================Get locator type from object repository and add to By tag==============================
public By getLocator(String strElement) throws Exception {
         
        // retrieve the specified object from the object list
        String locator = p.getProperty(strElement);
         
        // extract the locator type and value from the object
        String locatorType = locator.split(":")[0];
        String locatorValue = locator.split(":")[1];
         
        // for testing and debugging purposes
    //    System.out.println("Retrieving object of type '" + locatorType + "' and value '" + locatorValue + "' from the object map");
         
        // return a instance of the By class based on the type of the locator
        // this By can be used by the browser object in the actual test
        switch (locatorType.toUpperCase()) {
		case "ID":
			return By.id(locatorValue);
		case "NAME":
			return By.name(locatorValue);
		case "TAGNAME":
			return By.tagName(locatorValue);
		case "LINKTEXT":
			return By.linkText(locatorValue);
		case "PARTIALLINKTEXT":
			return By.partialLinkText(locatorValue);
		case "XPATH":
			return By.xpath(locatorValue);
		case "CSS":
			return By.cssSelector(locatorValue);
		case "CLASSNAME":
			return By.className(locatorValue);
		default:
			return null;
		}
    }
}
