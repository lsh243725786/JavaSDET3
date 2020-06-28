package testframework;

import testapp.wework.page.AppBasePage;
import testweb.wework.page.WebBasePage;

public class UIAutoFactory {
    public static BasePage create(String driverName){
        if("web".equals(driverName) || "selenium".equals(driverName)){
            return new WebBasePage();
        }

        if("app".equals(driverName) || "appium".equals(driverName)){
            return new AppBasePage();
        }

        if("uiautomator".equals(driverName)){
//            return new AppBasePage();
        }

        if("atx".equals(driverName)){
//            return new AppBasePage();
        }

        if("macaca".equals(driverName)){
//            return new AppBasePage();
        }

        return null;
    }
}
