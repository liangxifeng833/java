package com.corefire.util;
/**
 * 连接couchdb类，获取连接句柄
 * @author liangxifeng 
 * @date 2017-04-12
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;

import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;

import com.fourspaces.couchdb.Session;
import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;

import net.sf.json.JSONObject;

public class CouchDB {
    //连接couchdb句柄
    private static Session sess = null;
    //数据库
    private static Database db = null;
    //Canlendar对象时间
    private Calendar cal =null;
    //couchDB字段后缀
    private String postFix = null;
    static
    {
        Properties props = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
        try {
            props.load(is);
            //从配置文件读取couchdb连接host和端口号,并连接couchdb
            sess =  new Session(props.getProperty("couchdb_url"),Integer.parseInt(props.getProperty("couchdb_port")));
            db = sess.getDatabase(props.getProperty("couchdb_offline_dbname"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    {
        Calendar cal = Calendar.getInstance();
        postFix = "---"+cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);
    }
    /**
     * 获取连接句柄
     */
	public  static Session getSession() {
	        return sess;
	}
	/**
	 * 获取当前连接的数据库
	 */
	public static Database getDB()
	{
	    return db;
	}
	/**
	 * 新增或修改
	 * @param documentId couchdb文档id
     * @param map 新增couchdb文档map数据
     * @isPostFix 字段后缀
	 * @return boolean
	 */
	public boolean addOrUpdate(String documentId, Map<String,String> map, boolean isPostFix)
	{
	    //couchdb文档
        Document srcDoc = null;
        boolean res = false;
        try {
            //根据文档id获取文档
            srcDoc = db.getDocumentWithRevisions(documentId);            
            //如果文档不存在新增，存在则修改
            if(srcDoc == null )
            {
                res =  add(documentId,map,isPostFix);
            }else
            {
                res =  update(documentId,map,isPostFix);
            }  
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
	}
	/**
	 * 新增文档信息
	 * @param documentId couchdb文档id
	 * @param map 新增couchdb文档map数据
	 * @isPostFix 字段后缀
	 * @throws IOException 
	 */
	public boolean add( String documentId, Map<String,String> map, boolean isPostFix )
	{
	         JSONObject obj = new JSONObject();
	        //通过entrySet方法，返回Map中的所有键值对
	        Set <Entry<String,String>> entrySet = map.entrySet();
	        postFix = isPostFix ? postFix : "";
	        for (Entry<String, String> entry : entrySet) 
	        {
	            obj.put( entry.getKey()+postFix, entry.getValue());
	        }
	        Document doc = new Document(obj);
           try {
            db.saveDocument(doc, documentId);
            return true;
           } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
           }
	}
	
    /**
     * 修改文档信息
     * @param documentId couchdb文档id
     * @param map 修改couchdb文档map数据
     * @throws IOException 
     */
    public boolean update( String documentId, Map<String,String> map,boolean isPostFix  )
    {
           postFix = isPostFix ? postFix : "";
            try {
                //根据documentid查询要修改的文档信息
                Document srcDoc = db.getDocumentWithRevisions(documentId);
                //通过entrySet方法，返回Map中的所有键值对
                Set <Entry<String,String>> entrySet = map.entrySet();
                for (Entry<String, String> entry : entrySet) 
                {
                    srcDoc.put( entry.getKey()+postFix, entry.getValue());
                }
                /*
                String oldId = foodoc.getId();
                String oldRev = foodoc.getRev();
                */
                db.saveDocument(srcDoc);
                return true;
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return false;
            }
    }
}
