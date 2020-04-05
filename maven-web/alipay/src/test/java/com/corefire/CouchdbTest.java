package com.corefire;
/**
 * 单元测试couchDB数据库操作
 * @author liangxifeng
 * @date 2017-04-15
 *
 */
import static com.fourspaces.couchdb.util.JSONUtils.urlEncodePath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.junit.*;
import org.junit.Assert.*;

import org.junit.*;
import org.junit.Assert.*;

import com.corefire.util.CouchDB;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;
public class CouchdbTest {
    //couchdb句柄
    private CouchDB couch = null;
    //Canlendar对象时间
    private Calendar cal =null;
    //couch 数据库
    private Database db = null;
    //couchdb文档id
    private String docId = null;
    @Before
    public void init() throws IOException
    {
        couch = new CouchDB();
        cal = Calendar.getInstance();
        Properties props = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
        props.load(is);
        //System.out.println(props.getProperty("couchdb_offline_dbname"));
        db = couch.getDB();
        docId = "001-"+cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH);
    }
   @Test
    public void testGetSession()
    {
        Assert.assertNotNull(couch.getSession());
    }
   @Test
   public void testAdd()
   {
       String fix = ""+cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);       
       //根据documentid查询文档信息
       Document srcDoc = null;
       System.out.println(docId);
    try {
            srcDoc  = db.getDocumentWithRevisions(docId);
            if(srcDoc == null )
            {
                System.out.println(docId);
                Map<String,String> map = new HashMap<String,String>();
                map.put("transaction_id_"+fix, "a-000001");
                map.put("appid_"+fix, "appid-value");
                map.put("openid_"+fix, "openid-value");
                Assert.assertTrue(couch.add(docId, map,true));
                System.out.println(docId + " add success");
            }else
            {
                System.out.println(docId + " existed");
            }   
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
           
        }
   }
   @Test
   public void testUpdate() throws IOException
   {
       String fix = ""+cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);
       Map<String,String> map = new HashMap<String,String>();
       map.put("transaction_id_"+fix, "a-000001");
       map.put("appid_"+fix, "appid-value");
       map.put("openid_"+fix, "openid-value");
       //更新前文档
       Document srcDoc = db.getDocumentWithRevisions(docId);
       String oldId = srcDoc.getId();
       String oldRev = srcDoc.getRev();
       Assert.assertTrue(couch.update(docId, map,true));
       
       //更新后文档
       Document newDoc = db.getDocumentWithRevisions(docId); 
       //assertEquals(srcDoc.getRevisions().length, 1);
       //断言验证
       assertEquals(oldId, newDoc.getId());
       assertFalse(oldRev.equals(newDoc.getRev()));
   } 
   
   @Test
   public void testAddOrUpdate()
   {
       Map<String,String> map = new HashMap<String,String>();
       map.put("transaction_id", "a-000001");
       map.put("appid", "appid-value");
       map.put("openid", "openid-value");
       Assert.assertTrue(couch.addOrUpdate(docId, map,true));
   }
}
