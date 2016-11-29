package com.tomstoneberg.processing.p1;

import processing.core.PApplet;

/**
 * generates a specific color palette and some random "rect-tilings"
 *
 * MOUSE
 * left click          : new composition
 *
 * KEYS
 * s                   : save png
 * p                   : save pdf
 * c                   : save color palette
 */

public class ColorPaletteRulesv2 extends PApplet
{
    int colorCount = 20;
    int[] hueValues = new int[colorCount];
    int[] saturationValues = new int[colorCount];
    int[] brightnessValues = new int[colorCount];

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
    }

    @Override
    public void draw()
    {
        // colors
        for(int i = 0; i < colorCount; i++)
        {
            if(i % 2 == 0)
            {
                hueValues[i] = (int) random(0, 360);
                saturationValues[i] = 100;
                brightnessValues[i] = (int) random(0, 100);
            } else
            {
                hueValues[i] = 195;
                saturationValues[i] = (int) random(0, 100);
                brightnessValues[i] = 100;
            }
        }

        // area tiling
        // count tiles
        int counter = 0;
        // row count and row height
        int rowCount = (int) random(5, 40);
        float rowHeight = (float) height / (float) rowCount;

        for(int i = 0; i < rowCount; i++)
        {
            // separate each line into parts
            // how many fragments
            int partCount = i + 1;
            float[] parts = new float[0];

            for(int ii = 0; ii < partCount; ii++)
            {
                // sub fragments or not?
                if(random(1.0f) < 0.075)
                {
                    // take care of big values
                    int fragments = (int) random(2, 20);
                    partCount = partCount + fragments;
                    for(int iii = 0; iii < fragments; iii++)
                    {
                        parts = append(parts, random(2));
                    }
                } else
                {
                    parts = append(parts, random(2,20));
                }
            }

            // add all subparts
            float sumPartsTotal = 0;
            for(int ii = 0; ii < partCount; ii++) sumPartsTotal += parts[ii];

            // draw rects
            float sumPartsNow = 0;
            for(int ii = 0; ii < parts.length; ii++)
            {
                // get component color values
                int index = counter % colorCount;
                fill(hueValues[index], saturationValues[index], brightnessValues[index]);

                sumPartsNow += parts[ii];
                rect(map(sumPartsNow, 0, sumPartsTotal, 0, width), rowHeight * i,
                        map(parts[ii], 0, sumPartsTotal, 0, width) * -1, rowHeight);
                counter++;
            }

            noLoop();
        }
    }

    @Override
    public void mouseReleased()
    {
        loop();
    }

    public static void main(String[] args)
    {
        PApplet.main(ColorPaletteRulesv2.class.getCanonicalName());
    }
}
