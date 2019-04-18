import com.winjean.mongo.MongoApplication;
import com.winjean.mongo.model.entity.User;
import com.winjean.mongo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/18 19:11
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MongoApplication.class})
@SpringBootTest
public class UserDaoTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void testSaveUser() throws Exception {
        User user=new User();
        user.setId(2l);
        user.setName("小明");
        user.setPassWord("fffooo123");
        userRepository.saveUser(user);
    }

    @Test
    public void findUserByUserName(){
        User user= userRepository.findUserByUserName("小明");
        System.out.println("user is "+user);
    }

    @Test
    public void updateUser(){
        User user=new User();
        user.setId(2l);
        user.setName("天空");
        user.setPassWord("fffxxxx");
        userRepository.updateUser(user);
    }

    @Test
    public void deleteUserById(){
        userRepository.deleteUserById(1l);
    }
}
