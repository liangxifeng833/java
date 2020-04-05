package com.fourspaces.couchdb.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.print.attribute.standard.DocumentName;

import org.junit.Test;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;

import net.sf.json.JSONObject;

public class TestSession {
	public  static Session getTestSession() {
		Properties props = new Properties();
		Session sess = null;
		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
			props.load(is);
			sess =  new Session(props.getProperty("couchdb_url"),Integer.parseInt(props.getProperty("couchdb_port")));
		} catch (Exception e) {
			//throw new RuntimeException(e);
		}		
		return sess;
	}
   public static void main(String[] args) throws IOException {
       Session sess = TestSession.getTestSession();
       System.out.println(sess.toString());
       TestSession.update();
//       Database db = sess.getDatabase("lxf_test");
//       JSONObject obj = new JSONObject();
//       //obj.put("transaction_id", "aaa-0001");
//       obj.put("transaction_id-2", "aaa-0002");
//       obj.accumulate("array", "ar1");
//       obj.accumulate("array", "ar2");
//       obj.accumulate("array", "ar3");
//       Document doc = new Document(obj);
//       db.saveDocument(doc, "doucment-id-001");
//       System.out.println(doc.getId());
//       System.out.println(doc.getRev());
    }
   
   public static void update() throws IOException
   {
       Session sess = TestSession.getTestSession();
       Database db = sess.getDatabase("lxf_test");
       Document foodoc = db.getDocumentWithRevisions("doucment-id-001");
 
       System.out.println(foodoc.getRev());
       String oldId = foodoc.getId();
       String oldRev = foodoc.getRev();
       //assertEquals(foodoc.getRevisions().length, 1);

       foodoc.put("transaction_id-3", new Date());
       db.saveDocument(foodoc);
   }
   
	@Test
	public void noop() {
	    System.out.println("hihi");
	} // just to avoid junit warning
}
