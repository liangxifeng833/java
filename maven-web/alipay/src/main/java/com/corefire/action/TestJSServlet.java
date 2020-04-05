package com.corefire.action;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corefire.config.CorefireConfig;
import com.corefire.util.CorefireHttpPost;
import com.corefire.util.MD5;
import com.corefire.util.SignUtils;
import com.corefire.util.XmlUtils;

/**
 * <一句话功能简述>
 * <功能详细描述>测试支付
 * 
 * @author  Administrator
 * @version  [版本号, 2014-8-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TestJSServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static Map<String,String> orderResult; //用来存储订单的交易状态(key:订单号，value:状态(0:未支付，1：已支付))  ---- 这里可以根据需要存储在数据库中

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        
        @SuppressWarnings("unchecked")
		SortedMap<String,String> map = XmlUtils.getParameterMap(req);
        
        map.put("mch_id", CorefireConfig.mch_id);
        map.put("appid", CorefireConfig.appid);
        map.put("method", "mbupay.alipay.create");
        map.put("notify_url", CorefireConfig.notify_url);

        Map<String,String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() +1) * 10);
        SignUtils.buildPayParams(buf,params,false);
        String preStr = buf.toString();
        //System.out.println("preStr:"+preStr);
        String sign = MD5.sign(preStr, "&key=" + CorefireConfig.key, "utf-8").toUpperCase();
            
        map.put("sign", sign);
            
        String reqUrl = CorefireConfig.common_url;
        System.out.println("reqUrl：" + reqUrl);
            
        System.out.println("请求参数:" + XmlUtils.parseXML(map));
        String res = "";
        String result = null;
        boolean forward = false;
        req.setAttribute("req_message", XmlUtils.parseXML(map));
        try {
        	res = CorefireHttpPost.connect(reqUrl, map);
			Map<String,String> resultMap = XmlUtils.xml2map(res, "xml");
            System.out.println("请求返回数据：" + res);
            req.setAttribute("res_message", XmlUtils.parseXML(resultMap));
            
            result = "通信状态:" + resultMap.get("return_code");
            if("FAIL".equals(resultMap.get("return_code"))){
				result = result + "<br>" + "错误描述:" + resultMap.get("return_msg");
    		}else if("SUCCESS".equals(resultMap.get("return_code"))){
	            if(resultMap.containsKey("sign")){
	            	if(!SignUtils.checkParam(resultMap, CorefireConfig.key)){
	            		result = result + "<br>错误描述:" + "验证签名不通过";
	            	}else{
	            		result = result + "<br>" + "业务状态:" + resultMap.get("result_code");
	            		if("FAIL".equals(resultMap.get("result_code"))){
	            			result = result + "<br>" + "错误代码:" + resultMap.get("err_code");
	            			result = result + "<br>" + "错误描述:" + resultMap.get("err_code_des");
	               		}else if("SUCCESS".equals(resultMap.get("result_code"))){
	               			forward = true;
	               			String trade_no = resultMap.get("trade_no");
                            //System.out.println(code_img_url);
                            req.setAttribute("trade_no", trade_no);
                            req.setAttribute("out_trade_no", map.get("out_trade_no"));                        
                            req.setAttribute("total_fee", map.get("total_fee"));
                            req.setAttribute("body", map.get("body"));
                            
                            String code_url = "http://"+req.getServerName();
                            code_url += ":"+req.getServerPort();
                            code_url += "/ali.pay/testJSPay?trade_no="+trade_no;
                            req.setAttribute("code_url", code_url);
                            
                            req.getRequestDispatcher("index-js-result.jsp").forward(req, resp);
                           
	               		}
	            	}
	            } else{
	            	result = result + "<br>错误描述:" + "没有签名信息";
	            }
    		}
            req.setAttribute("result", result); 
        } catch (Exception e1) {
        	e1.printStackTrace();
        	result = "系统异常";
        	req.setAttribute("result", result);
        }
        if(!forward)
        {
        	req.getRequestDispatcher("index-result.jsp").forward(req, resp);
        }
    }
}

