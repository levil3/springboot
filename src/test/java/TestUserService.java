import com.spt.springboot.Starter;
import com.spt.springboot.pojo.User;
import com.spt.springboot.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

// 配置测试的环境
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Starter.class})
public class TestUserService {

    @Resource
    private UserService userService;

    @Before
    public void before() {
        System.out.println("单元测试方法前执行...");
    }

    @After
    public void after() {
        System.out.println("单元测试方法后执行...");
    }

    /**
     *  单元测试方法
     */
    @Test
    public void testUserByName() {
        User user = userService.queryUserByName("ycg");
        System.out.println(user);
    }
}
