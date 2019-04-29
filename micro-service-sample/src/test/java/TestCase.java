import com.winjean.sample.SampleApplication;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/29 13:58
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCase {

    @Resource
    StringEncryptor encryptor;

    //加密
    @Test
    public void getPass(){
        String name = encryptor.encrypt("${win:false}");
        System.out.println(name); //BEJsOY+ny6/Bo+B8Rv2f9Q==
    }

    //解密
    @Test
    public void passDecrypt(){
        String username = encryptor.decrypt("Lu7307t+yqynugj+8YZQRBwGrZtoY4ym");
        System.out.println(username);
    }

}
