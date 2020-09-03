package top.tianqi.vlitality.controller;

import cn.com.btg.safemonit.admin.system.webservice.impl.HomeWebService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试webservice服务调用
 *
 * @Author wkh
 * @Date 2020/9/3 15:08
 */
@RestController(value = "webServiceController")
@RequestMapping(value = "/webService")
public class WebServiceController {

    @GetMapping(value = "/websv")
    public String websv() {
        JaxWsProxyFactoryBean jwp = new JaxWsProxyFactoryBean();
        jwp.setServiceClass(HomeWebService.class);
        jwp.setAddress("http://127.0.0.1:8080/webservice/home?wsdl");
        HomeWebService homeWebService = (HomeWebService) jwp.create();
        String city = homeWebService.getCity("北京");
        System.out.println(city);
        return "Finished!Looking for console!";
    }
}
