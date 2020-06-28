package testapp.xueqiu.page;

import org.openqa.selenium.By;

public class MainPage extends BasePage{

    public SearchPage toSearch(){
        click(By.id("home_search"));
//        MobileElement el4 = (MobileElement) driver.findElementById("com.xueqiu.android:id/home_search");
//        el4.click();
        return new SearchPage(driver);
    }

    public void toStock(){

    }

}
