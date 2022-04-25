import com.spt.springboot.Starter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Starter.class})
@AutoConfigureMockMvc
public class TestUserController {

    // 注入MockMvc对象
    @Resource
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        // 构建请求
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/user/name/ycg")
                .contentType("text/html") // 设置contentType请求头
                .characterEncoding("UTF-8") // 设置编码
                .accept(MediaType.APPLICATION_JSON); // 设置接收对象

        // 发送结果，获取请求结果
        ResultActions perform = mockMvc.perform(builder);

        // 请求校验判断
        perform.andExpect(MockMvcResultMatchers.status().isOk());

        // 获取返回的结果
        MvcResult result = perform.andReturn();

        // 得到响应的对象
        MockHttpServletResponse response = result.getResponse();

        // 得到响应的状态码与数据
        System.out.println("状态码：" + response.getStatus());
        System.out.println("响应数据：" + response.getContentAsString());

    }

}
