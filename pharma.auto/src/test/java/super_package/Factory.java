package super_package;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class Factoryclass {

	/*@Factory(dataProvider="dp")
    public Object[] locatordata(int id, String account) {
        return new Object[] {new SomeTest(id, account)};
    }*/
     
    @DataProvider(name="dp")
    public static Object[][] dataProvider() {
        Object[][] dataArray = {
                {"NDMM", "EPI_NDMM_click.xpath"}
             //   {"Health outcome", "HOC_Ovarion.xpath"}
        };
        return dataArray;
    }
    
		@Factory(dataProvider="dp")
		public Object[] test(String name,String locator)
		{
		//Actionclass act = new Actionclass(null);   // Like this we can create instance of any class.
		//Object[] obj = {};
		//return obj;
		
		 return new Object[] {new Actionclass(name,locator) };
		}
}
