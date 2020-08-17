package top.tianqi.vitality.test;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.tianqi.vitality.test.annotation.Autowired;
import top.tianqi.vitality.test.controller.TestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author wkh
 * @Date 2020/7/3 14:59
 */
@SpringBootTest
public class AnnotationTest {

    /**
     * 注解注入
     */
    @Test
    public void test(){
        List<Integer> list = new ArrayList<>();
        list.add(111);
        list.add(222);
        list.add(333);
        list.add(444);

        list.forEach((i) -> {
            System.out.println(i);
        });

        TestController testController = new TestController();
        Class<? extends TestController> clazz = testController.getClass();
        Stream.of(clazz.getDeclaredFields()).forEach(field -> {
            Autowired annotation = field.getAnnotation(Autowired.class);
            field.setAccessible(true);
            if (annotation != null) {
                try {
                    field.set(testController, field.getType().newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(testController.getTestService());
    }
}
