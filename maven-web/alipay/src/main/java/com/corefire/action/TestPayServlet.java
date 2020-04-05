package com.corefire.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;

import com.corefire.config.CorefireConfig;
import com.corefire.dao.AlipayAnnalDao;
import com.corefire.model.AlipayAnnal;
import com.corefire.util.CorefireHttpPost;
import com.corefire.util.CouchDB;
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
public class TestPayServlet extends HttpServlet {
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
        map.put("method", "mbupay.alipay.micropay");
        map.put("scene", "bar_code");
        

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
        CouchDB couch = new CouchDB();
        
        //add couchDB log liangxifeng 2017-04-14
        String docId = "151200588";
        couch.addOrUpdate(docId, map,true);
        //add mysql alipay liangxifeng 2017-04-15
        //实例支付宝收款实体
        AlipayAnnal pay = new AlipayAnnal();
        pay.setAppid(map.get("appid"));
        pay.setValue(new BigDecimal(map.get("total_fee")));
        pay.setCharge(new BigDecimal("0.99"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            pay.setAdd_time(sdf.parse("2016-12-12"));
            pay.setModify_time(sdf.parse("2016-12-31"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        AlipayAnnalDao payDao = new AlipayAnnalDao();
        try {
            payDao.add(pay);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
        String res = "";
        req.setAttribute("req_message", XmlUtils.parseXML(map));
        try {
        	res = CorefireHttpPost.connect(reqUrl, map);
			Map<String,String> resultMap = XmlUtils.xml2map(res, "xml");
            System.out.println("请求返回数据：" + res);
            req.setAttribute("res_message", XmlUtils.parseXML(resultMap));
            String result = null;
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
	               		}
	            	}
	            } else{
	            	result = result + "<br>错误描述:" + "没有签名信息";
	            }
    		}
            req.setAttribute("result", result); 
        } catch (Exception e1) {
        	e1.printStackTrace();
        }
        req.getRequestDispatcher("index-result.jsp").forward(req, resp);
    }
}

