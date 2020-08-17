package top.tianqi.vitality.test.demo;

/**
 * @Author wkh
 * @Date 2020/7/3 16:18
 */
public class CloneC implements Serializable {

    private static final long serialVersionUID = -4735996379126302217L;

    public void hello() {
        System.out.println("hello");
    }

    public static void main(String[] args) throws Exception {
        CloneC c = new CloneC();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.obj"));
        oos.writeObject(c);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.obj"));
        CloneC obj = (CloneC) ois.readObject();
        ois.close();
        obj.hello();
    }
}
