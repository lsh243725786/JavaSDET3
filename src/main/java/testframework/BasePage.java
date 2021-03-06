package testframework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * 自动化领域建模
 */
public class BasePage {
    List<PageObjectModel> pages = new ArrayList<>();

    public void click(HashMap<String, Object> map) {
        System.out.println("click");
        System.out.println(map);
//        driver.findElement(by).click
    }

    public void sendKeys(HashMap<String, Object> map) {
        System.out.println("sendKeys");
        System.out.println(map);
    }

    public void action(HashMap<String, Object> map) {
        System.out.println("action");
        System.out.println(map);

//        如果是page级别的关键字
        if (map.containsKey("page")) {
            String action = map.get("action").toString();
            String pageName = map.get("page").toString();
            pages.forEach(pom-> System.out.println(pom.name));

            pages.stream()
                    .filter(pom -> pom.name.equals(pageName))
                    .findFirst()
                    .get()
                    .methods.get(action).forEach(step -> {
                action(step);
            });
        } else {
//            自动化级别
            if (map.containsKey("click")) {
                HashMap<String, Object> by = (HashMap<String, Object>) map.get("click");
                click(by);
            }

            if (map.containsKey("sendKeys")) {
                sendKeys(map);
            }
        }


    }

    public void find() {

    }

    public void getText() {

    }


    /**
     * 读取的测试用例yaml配置文件字段进行点击，元素识别等操作解析
     * 
     * @param uiAuto
     */
    public void run(@org.jetbrains.annotations.NotNull UiAuto uiAuto) {
        //读取yaml文件里的steps步骤，完成流式操作
        uiAuto.steps.stream().forEach(m -> {
//            if (m.keySet().contains("click")) {
//                click((HashMap<String, Object>) m.get("click"));
//            }
            //如果map存在click的key
            if (m.containsKey("click")) {
                //取出click的内容，以HashMap的形式传给by
                HashMap<String, Object> by = (HashMap<String, Object>) m.get("click");
                //对click完成调用
                click(by);
            }

            if (m.containsKey("sendKeys")) {
                sendKeys(m);
            }

            if (m.containsKey("action")) {
                action(m);
            }

//            if(m.containsKey("page")){
//                page(m);
//            }

        });

    }

    /**
     * 读取测试用例yaml配置文件
     *
     * @param path 文件路径
     * @return
     */
    public UiAuto load(String path) {
        //先初始化出来一个基本的map对象
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        UiAuto uiauto = null;
        try {
            //读取获取到的资源路径，再转换成UIAuto这个模型
            uiauto = mapper.readValue(
                    BasePage.class.getResourceAsStream(path),
                    UiAuto.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        //转换完成后返回，默认是个null,如果文件不存在，则返回null
        return uiauto;

    }


    /**
     * 读取单个po页面yaml配置文件
     *
     * @param path
     * @return
     */
    public PageObjectModel loadPage(String path) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        PageObjectModel pom = null;
        try {
            pom = mapper.readValue(
                    new File(path),
                    PageObjectModel.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pom;
    }

    /**
     *读取加载所有page页面yaml文件
     *
     * @param dir
     */
    public void loadPages(String dir) {
        Stream.of(new File(dir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains("_page");
            }
        })).forEach(path -> {
            path = dir + "/" + path;
            System.out.println(path);
            pages.add(loadPage(path));
        });
    }
}
