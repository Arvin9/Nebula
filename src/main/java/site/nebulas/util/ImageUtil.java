package site.nebulas.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/2/16.
 */
public class ImageUtil {
    public static void main(String[] args) {
        String str = "零壹";
        int imageWidth = 222;
        int imageHeight = 222;
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        int fontSize = 80;
        Font font = new Font("隶书", Font.PLAIN, fontSize);
        Graphics graphics = image.getGraphics();
        graphics.setFont(font);
        graphics.setColor(new Color(82, 255, 228));
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        graphics.setColor(new Color(214, 206, 196));
        int strWidth = graphics.getFontMetrics().stringWidth(str);
        System.out.println(strWidth);
        //graphics.drawString(str, 0, fontSize - (fontSize / 6));
        graphics.drawString(str, 31, 135);
        try {
            ImageIO.write(image, "PNG", new File("E:\\test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics.dispose();
    }
}
