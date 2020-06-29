package testframework;

import org.junit.jupiter.api.Test;

class UiAutoFactoryTest {

    @Test
    void create() {
        BasePage web= UiAutoFactory.create("web");
        UiAuto uiAuto=web.load("/testframework/webauto.yaml");
        web.run(uiAuto);
    }
}