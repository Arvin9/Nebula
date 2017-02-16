package site.nebulas.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/2/14.
 */
public class GugujiUtil {
    final private static Logger logger = LoggerFactory.getLogger(GugujiUtil.class);
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

    private static final String url = "http://php.weather.sina.com.cn/xml.php?city=%C3%F6%BA%EE&password=DJOYnieT8234jlsK&day=0";
    /**
     * 账号关联
     * return -1为失败，其他为showapi_userid
     * */
    public static int accountRelevance()  {
        //账号关联
        String linkURL = linkUser+"?ak="+ak+"&timestamp="+ URLEncoder.encode(DateUtil.getTime())+"&memobirdID="+memobirdID+"&useridentifying="+useridentifying;
        String content = HTTPUtil.get(linkURL);
        JSONObject parse = (JSONObject) JSON.parse(content);
        //账号关联返回的 showapi_userid
        int showapi_userid = (Integer) parse.get("showapi_userid");
        int showapi_res_code = (Integer) parse.get("showapi_res_code");
        String showapi_res_error = (String) parse.get("showapi_res_error");

        if (1 != showapi_res_code) {
            showapi_userid = -1;
            logger.info("------------ 账号关联失败 -----------");
            logger.info("------------ " + showapi_res_error + " -----------");
        }
        logger.info("------------ 账号关联成功！showapi_userid：" + showapi_userid + " -----------");
        return showapi_userid;
    }
    /**
     * 纸条打印
     * */
    public static void scripPrint(String str){
        int showapi_userid = accountRelevance();
        if (-1 == showapi_userid) {
            logger.info("------------ 账号关联失败 -----------");
            return;
        }

        byte[] base = new byte[0];
        try {
            base = Base64.encodeBase64(str.getBytes("gbk"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String printURL = printPaper+"?ak="+ak+"&timestamp="+URLEncoder.encode(DateUtil.getTime())+"&memobirdID="+memobirdID+"&userID="+showapi_userid+"&printcontent=T:"+new String(base);
        logger.info("printURL：" + printURL);
        String content = HTTPUtil.get(printURL);
        JSONObject parse = (JSONObject) JSON.parse(content);
        int showapi_res_code = (Integer) parse.get("showapi_res_code");
        String showapi_res_error = (String) parse.get("showapi_res_error");
        if (1 != showapi_res_code) {
            logger.info("------------ 纸条打印失败："+ showapi_res_error +" -----------");
            return;
        }

        String smartGuid = (String) parse.get("smartGuid"); //打印设备的编号
        int result = (Integer) parse.get("result"); //返回标志， 1 为已打印，其他为未打印。
        Integer printcontentid = (Integer) parse.get("printcontentid"); //返回打印内容的唯一ID
        logger.info("------------ 打印设备的编号："+ smartGuid +" -----------");
        logger.info("------------ 返回打印内容的唯一ID："+ printcontentid +" -----------");
        logger.info("------------ 返回标志， 1 为已打印，其他为未打印："+ result +" -----------");
        /*
        while ( 1 != getPrintStatus(printcontentid)){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        */
    }
    /**
     * 获取纸条打印状态
     * return 1为已打印或获取失败，其他为未打印
     * */
    public static int getPrintStatus(Integer printcontentid){
        String statusURL = printStatus +"?ak="+ak+"&timestamp="+URLEncoder.encode(DateUtil.getTime())+"&printcontentid="+printcontentid;
        String content = HTTPUtil.get(statusURL);
        JSONObject parse = (JSONObject) JSON.parse(content);
        int showapi_res_code = (Integer) parse.get("showapi_res_code");
        String showapi_res_error = (String) parse.get("showapi_res_error");
        if (1 != showapi_res_code) {
            logger.info("------------ 获取纸条打印状态失败："+ showapi_res_error +" -----------");
            return 1;
        }
        int printflag = (Integer) parse.get("printflag"); //返回标志， 1 为已打印，其他为未打印。
        printcontentid = (Integer) parse.get("printcontentid"); //返回打印内容的唯一ID
        logger.info("------------ 打印内容的唯一ID："+ printcontentid +" -----------");
        logger.info("------------ 打印标志， 1 为已打印，其他为未打印："+ printflag +" -----------");
        return printflag;
    }



    public static String getWeather(){
        Document doc = null;
        StringBuffer sb = new StringBuffer();
        try {
            doc = Jsoup.connect(url).get();
            String city = doc.getElementsByTag("city").text(); // 城市
            String udatetime = doc.getElementsByTag("udatetime").text(); // 更新时间

            String status1 = doc.getElementsByTag("status1").text();
            String direction1 = doc.getElementsByTag("direction1").text();
            String power1 = doc.getElementsByTag("power1").text();
            String temperature1 = doc.getElementsByTag("temperature1").text();

            String status2 = doc.getElementsByTag("status2").text();
            String direction2 = doc.getElementsByTag("direction2").text();
            String power2 = doc.getElementsByTag("power2").text();
            String temperature2 = doc.getElementsByTag("temperature2").text();

            String chy_shuoming = doc.getElementsByTag("chy_shuoming").text();
            String gm_s = doc.getElementsByTag("gm_s").text();
            String yd_s = doc.getElementsByTag("yd_s").text();


            sb.append(city + "\n");
            sb.append("白天 " + temperature1 + "度 " + status1 + "\n" + direction1 + " 风力" + power1 + "级\n");
            sb.append("夜间 " + temperature2 + "度 " + status2 + "\n" + direction2 + " 风力" + power2 + "级\n\n");
            sb.append("适合穿"+ chy_shuoming + "\n\n");
            sb.append(gm_s + "\n\n");
            sb.append(yd_s + "\n\n");
            sb.append("更新时间：" + udatetime);

            logger.info(doc.html());
            logger.info("--------------");
            logger.info(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "HELLO WORLD!\n 你好";
        scripPrint(getWeather());

        //getPrintStatus(2889581);
    }

}
