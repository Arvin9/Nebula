package site.nebulas.util;

import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.io.InputStream;
import org.apache.commons.codec.binary.Base64;
import net.sf.json.JSONObject;
import org.apache.http.util.EncodingUtils;

/**
 * Created by Administrator on 2017/2/13.
 */
public class HTTPUtil {
    /**
     * 自定义身份标识
     */
    private static final  String useridentifying = "nebula";
    /**
     * 设备编号
     */
    private static final  String memobirdID = "a3c2d6a136c5a37e";
    private static final String ak = "af8d0b7ca6d9443788bb5fef0da190ac";
    //帐号关联
    private static final String linkUser = "http://open.memobird.cn/home/setuserbind";
    //纸条打印
    private static final String printPaper = "http://open.memobird.cn/home/printpaper";
    //获取打印状态
    private static final String printStatus ="http://open.memobird.cn/home/getprintstatus";
    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public void post(String url) {
        /*
        HttpPost httpPost = new HttpPost(url);
        try {
            //设置参数
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        //return sendHttpPost(httpPost);
    }

    /**
     * 发送 get请求
     */
    public void get() {

    }
    /**
     *  发送GET请求并获取服务器端返回值
     */
    public static String handleGet(String strUrl) {
        String result = "";
        try {
            URL url = new URL(strUrl);
            // 使用HttpURLConnection打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();//打开到此 URL 引用的资源的通信链接
            // 得到读取的内容(流)
            InputStream stream = conn.getInputStream();
            byte[] data = new byte[100*1024];
            int len = stream.read(data);
            result = new String(data, 0, len);
            result =  EncodingUtils.getString(result.getBytes(), "UTF-8");//编码工具类解决中文乱码
            conn.disconnect();//指示服务器近期不太可能有其他请求
            stream.close();
        } catch (Exception ee) {
            System.out.print("ee:" + ee.getMessage());
        }
        return result;
    }

    public static void main(String[] args) {
        //账号关联
        String linkURL = linkUser+"?ak="+ak+"&timestamp="+ URLEncoder.encode(DateUtil.getTime())+"&memobirdID="+memobirdID+"&useridentifying="+useridentifying;

//		doGetMethod(linkURL);

        String result = handleGet(linkURL);
        JSONObject link = JSONObject.fromObject(result);
        String showapi_userid = link.getString("showapi_userid");
        System.out.println(result);
        System.out.println(showapi_userid);


        String str = "HELLO WORLD!";
        byte[] base =  Base64.encodeBase64(str.getBytes());
        String printURL = printPaper+"?ak="+ak+"&timestamp="+URLEncoder.encode(DateUtil.getTime())+"&memobirdID="+memobirdID+"&userID="+showapi_userid+"&printcontent=T:"+new String(base);
        System.out.println(printURL);

        String print = handleGet(printURL);
        JSONObject printJson = JSONObject.fromObject(print);
        String printcontentid = printJson.getString("printcontentid");
        System.out.println(print);

        String statusURL = printStatus +"?ak="+ak+"&timestamp="+URLEncoder.encode(DateUtil.getTime())+"&printcontentid="+printcontentid;
        String status = handleGet(statusURL);
        JSONObject statusJson = JSONObject.fromObject(status);
        System.out.println(status);
    }
}
