package site.nebulas.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Administrator on 2017/2/15.
 */
public class WeatherUtil {
    final private static Logger logger = LoggerFactory.getLogger(GugujiUtil.class);
    private static String url = "http://php.weather.sina.com.cn/xml.php?city=%C3%F6%BA%EE&password=DJOYnieT8234jlsK&day=0";
    public static void main(String[] args) {
        Document doc = null;
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



            StringBuffer sb = new StringBuffer();
            sb.append(city + "\n");
            sb.append("白天 " + temperature1 + "度 " + status1 + " " + direction1 + " 风力" + power1 + "级\n");
            sb.append("夜间 " + temperature2 + "度 " + status2 + " " + direction2 + " 风力" + power2 + "级\n\n");
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

    }
}
