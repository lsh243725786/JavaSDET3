package testframework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class WebTest {

    private static BasePage basePage;

    @BeforeAll
    static void beforeAll(){
        //todo: 加载通用配置
    }

    @BeforeEach
    void beforeEach(){
        //todo: 每个用例相关
    }

    @ParameterizedTest(name = "{index} {1}")
    @MethodSource
    void classic(UiAuto uiAuto, String path){
        basePage.run(uiAuto);
    }

    static List<Arguments> classic(){
        basePage = UiAutoFactory.create("web");
        basePage.loadPages("src/main/resources/testframework");
        List<Arguments> all= new ArrayList<Arguments>();

        Arrays.asList(
//                "/testframework/webauto_1.yaml",
//                "/testframework/webauto_2.yaml",
                "/testframework/webauto_3.yaml"
        ).stream().forEach(path->{
            UiAuto uiAuto= basePage.load(path);
            uiAuto.description=path;
            all.add(arguments(uiAuto, path));
        });
        return all;
    }
}
