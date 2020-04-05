package lxf.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import lxf.service.*;

/**
 * blog http://www.micmiu.com
 *
 * @author Michael
 *
 */
public class HelloClientDemo {

    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 9090;
    public static final int TIMEOUT = 30000;

    /**
     *
     * @param userName
     */
    public void startClient(String userName) {
        TTransport transport = null;
        try {
            transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            // TProtocol protocol = new TCompactProtocol(transport);
            // TProtocol protocol = new TJSONProtocol(transport);
            HelloWorldService.Client client = new HelloWorldService.Client(
                    protocol);
            transport.open();
            String result = client.sayHello(userName);
            System.out.println("Thrify client result =: " + result);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        HelloClientDemo client = new HelloClientDemo();
        int max = 10000;
        Long start = System.currentTimeMillis();
        for(int i=0; i<=max; i++)
        {
            client.startClient("Liangxifeng Hello!");
        }
        Long end = System.currentTimeMillis();
        Long elapse = end - start;

        int perform = Double.valueOf(max / (elapse / 1000d)).intValue();

        System.out.print("thrift " + max + " 次RPC调用，耗时：" + elapse + "毫秒，平均" + perform + "次/秒");


    }

}