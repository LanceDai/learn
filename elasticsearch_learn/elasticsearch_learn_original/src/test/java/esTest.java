import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author LanceDai
 * @date 2018/11/30 20:18
 * @description *
 */
public class esTest {

    private final static String HOST = "47.107.69.241";
    private final static int PORT = 9300;//http请求的端口是9200，客户端是9300

    @Test
    public void esConnect() throws UnknownHostException {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(HOST), PORT));
        System.out.println("client = " + client.toString());
        client.close();
    }
}
