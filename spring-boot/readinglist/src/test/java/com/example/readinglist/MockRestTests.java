package com.example.readinglist;

import com.example.readinglist.repository.ReadingListRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * 单元测试Rest
 */
@RunWith(SpringRunner.class)
//开启Web上下文测试
@WebAppConfiguration
 @SpringBootTest
public class MockRestTests {
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

    /**
     * 单元测试rest,新增图书数据
     */
    @Test
    public void testAddBook() throws Exception
    {
        String uri = "/books/liangxifeng-new";
        JSONObject paramJson=new JSONObject();
        paramJson.put("author","sln");
        paramJson.put("reader","lxf");
        paramJson.put("title","womdeaiqing");
        String jsonString = paramJson.toString();
        System.out.println("================================="+jsonString);
        MvcResult mvcResult = mockMvc.perform(
                post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(paramJson.toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        //返回字符串
        String returnString = mvcResult.getResponse().getContentAsString();
        //返回jsonObject
        JSONObject returnJsonObj = JSONObject.fromObject(returnString);
        System.out.println("=======================responseObject="+returnJsonObj.getString("author"));
        System.out.println("=======================responseString = "+returnString);
    }

    /**
     * 单元测试controller 测试通过id查询一条记录
     * @throws Exception
     */
    @Test
    public void testGetOne() throws Exception {
        String uri = "/books/1";
        MvcResult mvcRest = mockMvc.perform(
                get(uri)
                        .contentType(MediaType.APPLICATION_JSON) //请求数据类型为json
                        .accept(MediaType.APPLICATION_JSON))//返回数据类型为json
                .andExpect(status().isOk())//请求状态是否返回是200
                //.andDo(print())//打印出请求和响应的内容
                .andReturn();//将响应结果返回
        int status = mvcRest.getResponse().getStatus();
        //将响应结果转换为字符串
        String returnData = mvcRest.getResponse().getContentAsString();
        System.out.println("=======================responseObj="+mvcRest.getResponse());
        System.out.println("=======================responseString = "+returnData);
        Assert.assertTrue("错误，正确的返回值为200", status == 200);
    }

    /**
     * 单元测试controller 查询图书表所有数据
     * @throws Exception
     */
    @Test
    public void testGetBookAll() throws Exception {
        String uri = "/booksAll";
        MvcResult mvcRest = mockMvc.perform(
                get(uri)
                        .contentType(MediaType.APPLICATION_JSON) //请求数据类型为json
                        .accept(MediaType.APPLICATION_JSON))//返回数据类型为json
                .andExpect(status().isOk())//请求状态是否返回是200
                .andDo(print())//打印出请求和响应的内容
                .andReturn();//将响应结果返回
        //返回字符串
        String returnString = mvcRest.getResponse().getContentAsString();
        System.out.println("==========================returnString = " + returnString);
        //返回jsonObject
        JSONArray returnJsonArr = JSONArray.fromObject(returnString);
        //JSONObject returnJsonObj = JSONObject.fromObject(returnString);
        System.out.println("==========================returnJsonArr = " + returnJsonArr);

    }
    /**
     * 单元测试controller 通过主键修改图书表book数据
     * @throws Exception
     */
    @Test
    public void testUpdateBook() throws Exception
    {
        JSONObject paramJson=new JSONObject();
        paramJson.put("author","sln-update-2");
        paramJson.put("reader","lxf-update-2");
        String jsonString = paramJson.toString();
        String uri = "/books/2";
        MvcResult mvcRest = mockMvc.perform(
                put(uri)
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON) //请求数据类型为json
                        .accept(MediaType.APPLICATION_JSON))//返回数据类型为json
                .andExpect(status().isOk())//请求状态是否返回是200
                .andDo(print())//打印出请求和响应的内容
                .andReturn();//将响应结果返回
        //返回字符串
        String returnString = mvcRest.getResponse().getContentAsString();
        System.out.println("==========================returnString = " + returnString);
        //返回jsonObject
        JSONObject returnJsonObj = JSONObject.fromObject(returnString);
        System.out.println("==========================returnJsonObj = " + returnJsonObj);
    }
    /**
     * 单元测试controller 通过主键删除book表数据
     * @throws Exception
     */
    @Test
    public void testDelBook() throws Exception
    {
        String uri = "/books/2";
        MvcResult mvcRest = mockMvc.perform(
                delete(uri)
                        .contentType(MediaType.APPLICATION_JSON) //请求数据类型为json
                        .accept(MediaType.APPLICATION_JSON))//返回数据类型为json
                .andExpect(status().isOk())//请求状态是否返回是200
                .andDo(print())//打印出请求和响应的内容
                .andReturn();//将响应结果返回
        int status = mvcRest.getResponse().getStatus();
        //将响应结果转换为字符串
        String returnData = mvcRest.getResponse().getContentAsString();
        System.out.println("=======================responseString = "+returnData);
        Assert.assertTrue("错误，正确的返回值为200", status == 200);
    }


}
