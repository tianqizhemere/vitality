package top.tianqi.vitality.test.demo;

/**
 * @Author wkh
 * @Date 2020/7/3 15:42
 */
public class HelloA implements Cloneable {

    public void hello() {
        System.out.println("hello");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        HelloA helloA = new HelloA();
        helloA.hello();

        HelloA obj = (HelloA) helloA.clone();
        obj.hello();
    }
}
