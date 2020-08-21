package top.tianqi.vitality.test;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {

    @org.junit.Test
    public void test01() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm("PBEWithMD5AndDES");          // 加密的算法
        config.setPassword("TianQi");                        // 加密的密钥
        standardPBEStringEncryptor.setConfig(config);
        String plainText = "Wkh4449456";         //加密字段
        String encryptedText = standardPBEStringEncryptor.encrypt(plainText);
        System.out.println(encryptedText);
    }

    @org.junit.Test
    public void testDe() throws Exception {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword("TianQi");
        standardPBEStringEncryptor.setConfig(config);
        String encryptedText = "Gri54jrP8xEGxDnSCI90Ng==";   //加密后的密码
        String plainText = standardPBEStringEncryptor.decrypt(encryptedText);
        System.out.println(plainText);
    }
}
