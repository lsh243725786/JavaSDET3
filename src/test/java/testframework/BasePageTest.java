package testframework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasePageTest {

    private static BasePage basePage;

    @BeforeAll
    static void beforeAll(){
        basePage = new BasePage();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void run() {
        UiAuto uiauto=basePage.load("/testframework/uiauto.yaml");
        basePage.run(uiauto);
    }

    @Test
    void runPOM(){
        basePage.loadPages("src/main/resources/testframework");
        UiAuto uiauto=basePage.load("/testframework/webauto_3.yaml");
        basePage.run(uiauto);

    }

    @Test
    void load() throws JsonProcessingException {
        UiAuto uiauto=basePage.load("/testframework/uiauto.yaml");
        ObjectMapper mapper=new ObjectMapper();
        System.out.println(mapper.writeValueAsString(uiauto));
    }
}