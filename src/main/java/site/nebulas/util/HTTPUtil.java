package site.nebulas.util;

import java.io.IOException;
import java.net.URLEncoder;
import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

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
    public static String get(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        HttpGet httpget = new HttpGet(url);
        try {
            response = httpclient.execute(httpget);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args) {
        //账号关联
        String linkURL = linkUser+"?ak="+ak+"&timestamp="+ URLEncoder.encode(DateUtil.getTime())+"&memobirdID="+memobirdID+"&useridentifying="+useridentifying;
        String content = get(linkURL);
        com.alibaba.fastjson.JSONObject parse = (com.alibaba.fastjson.JSONObject) JSON.parse(content);
        System.out.println(parse.get("showapi_userid"));
    }
}
