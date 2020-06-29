package testweb.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testframework.BasePage;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class WebBasePage extends BasePage {
    RemoteWebDriver driver;
    WebDriverWait wait;

    public WebBasePage() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        selenium 4.0 use duration
//        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, 10);
    }

    public WebBasePage(RemoteWebDriver driver) {
        this.driver = driver;
//        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, 10);

    }


    public void quit() {
        driver.quit();
    }

    public void click(By by) {
        //todo: 异常处理
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public void sendKeys(By by, String content) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(content);
    }

    public void upload(By by, String path) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(path);
    }


    /**
     * @param map
     * @Override 是Java5的元数据，自动加上去的一个标志，告诉你说下面这个方法是从父类/接口 继承过来的，需要你重写一次(当然不写也可以)
     */
    @Override
    public void click(HashMap<String, Object> map) {
        /**
         * 当子类和父类有属性或方法重名时，需要super才能调用到父类的属性或方法，
         * 直接调用会调用到子类的属性或方法
         * 如果不重名，可直接调用且调用的是父类的属性或方法
         */
        super.click(map);
        /**
         * 取出map里面所有的key,再转换成一个Array数组，取出第一个，让String key接收
         * 取出map里面所有的value,再转换成一个Array数组，取出第一个，让String value接收
         * 取出的是数组类型，需要强制转换成(String)类型
         */
        String key = (String) map.keySet().toArray()[0];
        String value = (String) map.values().toArray()[0];

        /**
         * toLowerCase()-转换成小写
         * 判断传入的是id定位的，就By.id(value)进行定位
         * 判断传入的是linkText（文本定位），就By.linkText(value)进行定位
         * 判断传入的是partialLinkText（部分文本定位），就By.linkText(value)进行定位
         */
        String KEY_ID = "id";
        String KEY_LinkText = "linkText";
        String KEY_PartialLinkText = "partialLinkText";

        By by = null;
        if (KEY_ID.equals(key.toLowerCase())) {
            by = By.id(value);
        }
        if (key.toLowerCase().equals(KEY_LinkText.toLowerCase())) {
            by = By.linkText(value);
        }

        if (key.toLowerCase().equals(KEY_PartialLinkText.toLowerCase())) {
            by = By.partialLinkText(value);
        }
        //判断完成之后，进行click的操作
        click(by);
    }

    @Override
    public void action(HashMap<String, Object> map) {
        super.action(map);

        if (map.containsKey("action")) {
            String action = map.get("action").toString().toLowerCase();
            if ("get".equals(action)) {
                driver.get(map.get("url").toString());
            }
        }
    }
}
