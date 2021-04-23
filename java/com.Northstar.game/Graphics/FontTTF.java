package com.Northstar.game.Graphics;

import java.awt.*;
import java.io.InputStream;

public class FontTTF {
    public FontTTF(String name, Float size) {
        getFont(name, size);
    }

    public FontTTF(Graphics g, String text, Rectangle rect, String name, Integer height, Float size) {
        Font font = getFont(name, size);
        drawCenteredString(g, text, rect, font, height);
    }

    public static Font getFont(String name, Float size) {
        Font font;
        String fname = "Font/" + name;
        try {
            //Load font file
            InputStream is = FontTTF.class.getClassLoader().getResourceAsStream(fname);
            assert is != null;
            Font origin_font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = origin_font.deriveFont(size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(fname + " not loaded.  Using serif font.");
            font = new Font("serif", Font.PLAIN, 24);
        }
        //Return font format
        return font;
    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font, Integer height) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / height) + metrics.getAscent();
        // Set the font
        g.setColor(Color.black);
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }
}
