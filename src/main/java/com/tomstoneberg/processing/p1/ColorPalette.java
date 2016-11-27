package com.tomstoneberg.processing.p1;

import generativedesign.GenerativeDesign;
import processing.core.PApplet;

import java.util.Calendar;

/**
 * shows how to interpolate colors in different styles/ color modes
 *
 * MOUSE
 * left click          : new random color set
 * position x          : interpolation resolution
 * position y          : row count
 *
 * KEYS
 * 1-2                 : switch interpolation style
 * s                   : save png
 * p                   : save pdf
 * c                   : save color palette
 */
public class ColorPalette extends PApplet{

    boolean savePDF = false;

    int tileCountX = 2;
    int tileCountY = 10;

    int[] colorsLeft = new int[tileCountY];
    int[] colorsRight = new int[tileCountY];
    int[] colors;

    boolean interpolateShortest = true;

    @Override
    public void settings()
    {
        size(800, 800);
    }

    @Override
    public void setup()
    {
        colorMode(HSB, 360, 100, 100, 100);
        noStroke();
        shakeColors();
    }

    @Override
    public void draw()
    {
        if(savePDF)
        {
            beginRecord(PDF, timestamp()+".pdf");
            noStroke();
            colorMode(HSB, 360, 100, 100, 100);
        }

        tileCountX = (int) map(mouseX, 0, width, 2, 100);
        tileCountY = (int) map(mouseY, 0, height, 2, 10);
        float tileWidth = width / (float) tileCountX;
        float tileHeight = height / (float) tileCountY;
        int interCol;
        // just for ase export
        colors = new int[tileCountX*tileCountY];
        int i = 0;

        for(int gridY = 0; gridY < tileCountY; gridY++)
        {
            int col1 = colorsLeft[gridY];
            int col2 = colorsRight[gridY];

            for(int gridX = 0; gridX < tileCountX; gridX++)
            {
                float amount = map(gridX, 0, tileCountX - 1, 0, 1);
                if(interpolateShortest)
                {
                    // switch to rgb
                    colorMode(RGB, 255, 255, 255, 255);
                    interCol = lerpColor(col1, col2, amount);
                    // switch back
                    colorMode(HSB, 360, 100, 100, 100);
                } else
                {
                    interCol = lerpColor(col1, col2, amount);
                }

                fill(interCol);

                float posX = tileWidth * gridX;
                float posY = tileHeight * gridY;
                rect(posX, posY, tileWidth, tileHeight);

                // just for ase export
                colors[i] = interCol;
                i++;
            }
        }

        if(savePDF)
        {
            savePDF = false;
            endRecord();
        }

    }

    private void shakeColors()
    {
        for(int i = 0; i < tileCountY; i++)
        {
            colorsLeft[i] = color(random(0, 60), random(0, 100),  100);
            colorsRight[i] = color(random(160, 190), 100, random(0, 100));
        }
    }

    @Override
    public void mouseReleased()
    {
        shakeColors();
    }

    @Override
    public void keyReleased()
    {
        if (key == 'c' || key == 'C') GenerativeDesign.saveASE(this, colors, timestamp()+".ase");
        if (key == 's' || key == 'S') saveFrame(timestamp()+"_##.png");
        if (key == 'p' || key == 'P') savePDF = true;

        if (key == '1') interpolateShortest = true;
        if (key == '2') interpolateShortest = false;
    }

    private String timestamp()
    {
        Calendar now = Calendar.getInstance();
        return String.format("%1$ty%1$tm%1$td_%1$tH%1$tM%1$tS", now);
    }


    public static void main(String[] args)
    {
        PApplet.main(ColorPalette.class.getCanonicalName());
    }

}
