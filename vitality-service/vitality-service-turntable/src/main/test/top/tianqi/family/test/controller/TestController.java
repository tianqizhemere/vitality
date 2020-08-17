package top.tianqi.vitality.test.controller;

import top.tianqi.vitality.test.annotation.Autowired;
import top.tianqi.vitality.test.service.TestService;

/**
 * @Author wkh
 * @Date 2020/7/3 14:50
 */
public class TestController {

    @Autowired
    private TestService testService;

    public TestService getTestService() {
        return testService;
    }
}
