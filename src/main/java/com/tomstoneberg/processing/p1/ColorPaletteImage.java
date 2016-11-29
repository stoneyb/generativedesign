package com.tomstoneberg.processing.p1;

import com.tomstoneberg.processing.Template;
import generativedesign.GenerativeDesign;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.Calendar;

/**
 * extract and sort the color palette of an image
 *
 * MOUSE
 * position x          : resolution
 *
 * KEYS
 * 1-3                 : load different images
 * 4                   : no color sorting
 * 5                   : sort colors on hue
 * 6                   : sort colors on saturation
 * 7                   : sort colors on brightness
 * 8                   : sort colors on grayscale (luminance)
 * s                   : save png
 * p                   : save pdf
 * c                   : save color palette
 */

public class ColorPaletteImage extends PApplet
{
    PImage img;
    int[] colors;

    String sortMode = null;

    @Override
    public void settings()
    {
        size(600, 600);
    }

    @Override
    public void setup()
    {
        colorMode(HSB, 360, 100, 100, 100);
        noStroke();
        noCursor();
        img = loadImage("pic4.jpg");
    }

    @Override
    public void draw()
    {
        int tileCount = width / max(mouseX, 1);
        float rectSize = width / (float)tileCount;

        // get colors from image
        int i = 0;
        colors = new int[tileCount*tileCount];
        for(int gridY = 0; gridY < tileCount; gridY++)
        {
            for(int gridX = 0; gridX < tileCount; gridX++)
            {
                int px = (int) (gridX * rectSize);
                int py = (int) (gridY * rectSize);
                colors[i] = img.get(px, py);
                i++;
            }
        }

        // sort colors
        if(sortMode != null) colors = GenerativeDesign.sortColors(this, colors, sortMode);

        // draw grid
        i = 0;
        for(int gridY = 0; gridY < tileCount; gridY++)
        {
            for(int gridX = 0; gridX < tileCount; gridX++)
            {
                fill(colors[i]);
                rect(gridX * rectSize, gridY * rectSize, rectSize, rectSize);
                i++;
            }
        }
    }

    @Override
    public void keyReleased()
    {
        if (key=='c' || key=='C') GenerativeDesign.saveASE(this, colors, timestamp()+".ase");
        if (key=='s' || key=='S') saveFrame(timestamp()+"_##.png");

        if (key == '1') img = loadImage("pic1.jpg");
        if (key == '2') img = loadImage("pic2.jpg");
        if (key == '3') img = loadImage("pic3.jpg");

        if (key == '4') sortMode = null;
        if (key == '5') sortMode = GenerativeDesign.HUE;
        if (key == '6') sortMode = GenerativeDesign.SATURATION;
        if (key == '7') sortMode = GenerativeDesign.BRIGHTNESS;
        if (key == '8') sortMode = GenerativeDesign.GRAYSCALE;
    }

    // timestamp
    String timestamp()
    {
        Calendar now = Calendar.getInstance();
        return String.format("%1$ty%1$tm%1$td_%1$tH%1$tM%1$tS", now);
    }

    public static void main(String[] args)
    {
        PApplet.main(ColorPaletteImage.class.getCanonicalName());
    }

}
