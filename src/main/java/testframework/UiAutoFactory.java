package testframework;

import org.jetbrains.annotations.Nullable;
import testapp.wework.page.AppBasePage;
import testweb.wework.page.WebBasePage;

public class UiAutoFactory {
    /**
     * 工厂方法，由方法决定具体运行的driver
     *
     * @param driverName 传入的driver引擎
     * @return
     * @Nullable 表示传入的值可以为空
     */
    public static @Nullable BasePage create(String driverName) {
        String webName = "web";
        String selenium = "selenium";
        String appName = "app";
        String appiumName = "appium";
        /**
         * 如果传入的driverName是web或者selenium，那就返回一个WebBasePage对象
         */
        if (webName.equals(driverName) || selenium.equals(driverName)) {
            return new WebBasePage();
        }
        /**
         * 如果传入的driverName是app或者appium，那就返回一个AppBasePage()对象
         */
        if (appName.equals(driverName) || appiumName.equals(driverName)) {
            return new AppBasePage();
        }
//
//        if ("uiautomator".equals(driverName)) {
////            return new AppBasePage();
//        }
//        if ("atx".equals(driverName)) {
////            return new AppBasePage();
//        }
//        if ("macaca".equals(driverName)) {
////            return new AppBasePage();
//        }
        return null;
    }
}
