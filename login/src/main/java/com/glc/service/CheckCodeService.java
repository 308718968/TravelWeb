package com.glc.service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CheckCodeService {
    Random rd = new Random();
    public String createCheckCode() {
        String string = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(string.charAt(rd.nextInt(string.length())));
        }
        return stringBuilder.toString();

    }
    public BufferedImage checkcodeSreing2Imag(String checkcode) {
        //创建一个画布
        BufferedImage image = new BufferedImage(75, 28, BufferedImage.TYPE_INT_RGB);
        //画笔
        Graphics g = image.getGraphics();
        //给画笔设置颜色
        g.setColor(new Color(240,240,240));  //#00000   FFFFFF
        //设置验证码的 背景色
        g.fillRect(0, 0, 75, 28);

        g.setFont(new Font("宋体",Font.BOLD,16));

        g.setColor(new Color(0,0,0));  //#00000   FFFFFF
        // g.drawString(checkCodeStr, 20, 20);
        for (int i = 0; i <4 ; i++) {
            //画字符
            g.setColor(new Color(rd.nextInt(120),rd.nextInt(120),rd.nextInt(120)));
            g.drawString(checkcode.charAt(i)+"", 16*i + rd.nextInt(16), 15 + rd.nextInt(10) );
            if(i % 2 == 0) {//画线
                g.setColor(new Color(rd.nextInt(120), rd.nextInt(120), rd.nextInt(120)));
                g.drawLine(rd.nextInt(75), rd.nextInt(28), rd.nextInt(75), rd.nextInt(28));
            }
        }
        return image;
    }
}
