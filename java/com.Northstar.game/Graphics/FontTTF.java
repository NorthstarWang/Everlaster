package com.Northstar.game.Graphics;

import java.awt.*;
import java.awt.Font;
import java.io.InputStream;

public class FontTTF {
    public FontTTF(String name){
        getFont(name);
    }

    public FontTTF(Graphics g, String text, Rectangle rect,String name){
        Font font=getFont(name);
        drawCenteredString(g,text,rect,font);
    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect,Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 4) + metrics.getAscent();
        // Set the font
        g.setColor(Color.black);
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }

    public static Font getFont(String name){
        Font font;
        String fname = "Font/"+name;
        try{
            InputStream is = FontTTF.class.getClassLoader().getResourceAsStream(fname);
            assert is != null;
            Font origin_font = Font.createFont(Font.TRUETYPE_FONT,is);
            font = origin_font.deriveFont(100f);
        }catch (Exception ex){
            ex.printStackTrace();
            System.err.println(fname + " not loaded.  Using serif font.");
            font = new Font("serif", Font.PLAIN, 24);
        }
        return font;
    }
}