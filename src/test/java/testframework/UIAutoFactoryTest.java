package testframework;

import org.junit.jupiter.api.Test;

class UIAutoFactoryTest {

    @Test
    void create() {
        BasePage web= UIAutoFactory.create("web");
        UIAuto uiAuto=web.load("/testframework/webauto.yaml");
        web.run(uiAuto);
    }
}