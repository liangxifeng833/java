package com.example.readinglist;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.readinglist.domain.Book;
import com.example.readinglist.repository.ReadingListRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;
import java.util.regex.Matcher;

@RunWith(SpringRunner.class)
//开启Web上下文测试
@WebAppConfiguration
 @SpringBootTest
public class MockMvcWebTests {
    //注入WebAppliationContext
    @Autowired(required = false)
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Autowired
    private ReadingListRepository bookList;

    //设置MocMvc
    @Before
    public void setupMockMvc() {
        //创建mocMvc实例
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    @Transactional
    public void testRepository()
    {
        Book book = new Book();
        book.setAuthor("liangxifeng");
        book.setReader("zhansan");
        Book bookRes = bookList.save(book);
        Book bookRead = bookList.getOne(bookRes.getId());
        System.out.println(bookRead.getAuthor()+"reader: "+bookRead.getReader());
        Assert.assertTrue("数据集不对", bookRead.getAuthor() == "liangxifeng");
    }


    /**
     * 单元测试controller get
     * @throws Exception
     */
    @Test
    public void homePage() throws Exception {
        String expectedResult = "hello world!liangxifeng-develo";
        String uri = "/hello";
        MvcResult mvcResult = mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertTrue("错误，正确的返回值为200", status == 200);
        //Assert.assertFalse("错误，正确的返回值为200,status = "+status, status == 200);
        //Assert.assertTrue("数据不一致", expectedResult.equals(content));

    }
    /**
     * 单元测试Controller post
     */
    @Test
    public void testPost() throws Exception {
        /*
        MvcResult mvcResult = mockMvc.perform(post("/wangwu")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "BOOK TITLE")
                .param("reader","lisi"))
                .andReturn();
                        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        Assert.assertTrue("错误，status = "+status, status == 200);*/
       mockMvc.perform(post("/wangwu")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "BOOK TITLE")
                .param("reader","lisi"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/wangwu"));

    }

}
