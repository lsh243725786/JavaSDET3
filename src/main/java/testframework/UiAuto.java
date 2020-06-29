package testframework;

import java.util.HashMap;
import java.util.List;

/**
 * 自动化领域模型
 * 测试用例的模型:name 用例名称，description：用例描述，steps：通用测试步骤
 */
public class UiAuto {
    public String name="";
    public String description="";
    public List<HashMap<String, Object>> steps;
}
