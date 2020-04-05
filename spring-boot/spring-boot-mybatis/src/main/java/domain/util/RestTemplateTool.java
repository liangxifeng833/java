package domain.util;

import domain.domain.DomainResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Iterator;
import java.util.Map;

/**
 * restTemplate工具类,发送rest请求的唯一入口
 * @author : ningyachao
 * @date : 2018-02-02
 */
@Component
public class RestTemplateTool {
    public RestTemplate restTemplate;    //resttemplate句柄
    @Autowired
    public  YmlConfig config;
    {
        restTemplate = new RestTemplate();
    }
    /**
     * 根据接口主键查询接口url路径
     * @param id    请求主键
     * @return  url 资源路径
     */
    public  String getUrl (Long id)
    {
        //初始化变量
        String url = "";
        String urlApi = config.getModuleApiHost();
        DomainResponse resUrl = getOne(urlApi,id);
        if (resUrl.getCode() == 1) {
            JSONObject urlJson = (JSONObject)resUrl.getData();
            url = urlJson.getString("url");
        }
        return url;
    }
    /**
     * 根据主键查询记录
     * @param url  资源路径
     * @param id   请求主键
     * @return DomainResponse
     *          code        调用标识,1为成功
     *          message     错误信息
     *          data        数据句柄 json对象
     */
    public  DomainResponse getOne (String url, Long id)
    {
        /*---------------------get请求单条记录----------------start------------*/
        DomainResponse response = new DomainResponse();
        if(id == null || id == 0L || url == null || url.equals(""))
        {
            response.setCode(0);
            response.setMessage("所传参数错误");
            return  response;
        }else{

            response = restTemplate.getForObject(
                    url+"/"+id,
                    DomainResponse.class);
            if (response.getCode() == 1)
            {
                //将json串转换为json对象
                JSONObject jsonObject= JSONObject.fromObject(response.getData());
                response.setData(jsonObject);
            }
        }
        return response;
        /*---------------------get请求单条记录----------------end------------*/
    }
    /**
     * 根据查询条件查询记录
     * @param url  资源路径
     * @param params   查询条件
     * @return DomainResponse
     *          code        调用标识,1为成功
     *          message     错误信息
     *          data  数据存储句柄
     *              totalNum    数据句柄 json对象
     *              list        数据句柄 json对象
     */
    public  DomainResponse getList (String url, Map params)
    {
        DomainResponse response = new DomainResponse();
        /*---------------------get请求多条记录----------------start------------*/
        if(params == null || params.isEmpty() || url == null || url.equals(""))
        {
            response.setCode(0);
            response.setMessage("所传参数错误");
            return  response;
        }else{
            //获取map 的 key值
            Iterator<String> iter = params.keySet().iterator();
            String urlParam = "?";
            //拼接查询多条的参数串
            while (iter.hasNext()) {

                String key = iter.next();
                urlParam = urlParam + key +"={"+key+"}&";
            }
            //将map转化为json对象
            JSONObject jsonObj = JSONObject.fromObject(params);
            response = restTemplate.getForObject(
                    url+urlParam,
                    DomainResponse.class,jsonObj);
            System.out.println(response.getCode());
            //将返回值进行封装
            if(response.getCode() == 1)
            {
                JSONObject jsonObject= JSONObject.fromObject(response.getData());
                response.setData(jsonObject);
            }
        }
        return response;
        /*---------------------get请求多条记录----------------end------------*/
    }

    /**
     * 根据主键删除记录
     * @param url   资源路径
     * @param id    资源表主键
     * @return DomainResponse
     *          code        调用标识,1为成功
     *          message     错误信息
     *          data        数据句柄 json对象
     * 说明:  1）url: 请求地址；
     *       2）method: 请求类型(如：POST,PUT,DELETE,GET)；
     *       3）requestEntity: 请求实体，封装请求头，请求内容
     *       4）responseType: 响应类型，根据服务接口的返回类型决定
     *       5）uriVariables: url中参数变量值
     */
    public  DomainResponse delete (String url, Long id)
    {
        DomainResponse response = new DomainResponse();
        if(id == null || id == 0L || url == null || url.equals(""))
        {
            response.setCode(0);
            response.setMessage("所传参数错误");
            return  response;
        }
        ResponseEntity<DomainResponse> resp = restTemplate.exchange(url+"/{id}", HttpMethod.DELETE,null,DomainResponse.class,id);
        response = resp.getBody();
        return response;
    }
    /**
     * 根据主键修改记录
     * @param url       资源路径
     * @param params    map类型 key=>value
     * @return DomainResponse
     *          code        调用标识,1为成功
     *          message     错误信息
     *          data        数据句柄 json对象
     */
    public  DomainResponse update (String url, Map params)
    {
        DomainResponse response = new DomainResponse();
        if(params == null || params.isEmpty() || url == null || url.equals(""))
        {
            response.setCode(0);
            response.setMessage("所传参数错误");
            return  response;
        }else{
            //获取map 的 key值
            Iterator<String> iter = params.keySet().iterator();
            String urlParam = "?";
            //拼接url请求串
            while (iter.hasNext()) {
                String key = iter.next();
                Object value =  params.get(key);
                urlParam = urlParam + key +"="+value+"&";
            }
            ResponseEntity<DomainResponse> resp = restTemplate.exchange(url+urlParam, HttpMethod.PUT,null,DomainResponse.class);
            response = resp.getBody();
            return response;
        }
        /******若controller 使用@RequestBody接受参数,则使用下面的方法
        String jsonString = com.alibaba.fastjson.JSON.toJSONString(params);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(jsonString,headers);
        ResponseEntity<DomainResponse> resp = restTemplate.exchange(url, HttpMethod.PUT,entity,DomainResponse.class);
        response = resp.getBody();
        ************************************************************/
    }
    /**
     * 新增记录
     * @param url       资源路径
     * @param params    map类型 key=>value
     * @return DomainResponse
     *          code        调用标识,1为成功
     *          message     错误信息
     *          data        数据句柄 json对象
     */
    public  DomainResponse insert(String url, Map params)
    {
        DomainResponse response = new DomainResponse();
        if(params == null || params.isEmpty() || url == null || url.equals(""))
        {
            response.setCode(0);
            response.setMessage("所传参数错误");
            return  response;
        }else{
            //获取map 的 key值
            Iterator<String> iter = params.keySet().iterator();
            String urlParam = "?";
            //拼接url请求串
            while (iter.hasNext()) {
                String key = iter.next();
                Object value = params.get(key);
                urlParam = urlParam + key +"="+value+"&";
            }
            response = restTemplate.postForObject(url+urlParam, null, DomainResponse.class);
            /******若controller 使用@RequestBody接受参数,则使用下面的方法
            JSONObject jsonObject= JSONObject.fromObject(params);
            response = restTemplate.postForObject(url, jsonObject, DomainResponse.class);
             *****************************************************/
            return response;
        }
    }
}
