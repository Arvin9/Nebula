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
        String str = "好";
        int imageWidth = 384;
        int imageHeight = 384;
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        int fontSize = 20;
        Font font = new Font("楷体", Font.PLAIN, fontSize);
        Graphics graphics = image.getGraphics();
        graphics.setFont(font);
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        graphics.setColor(new Color(0, 0, 0));
        int strWidth = graphics.getFontMetrics().stringWidth(str);
        System.out.println(strWidth);
        graphics.drawString(str, 0, strWidth - (strWidth / 6));
        try {
            ImageIO.write(image, "PNG", new File("E:\\test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics.dispose();
    }
}
