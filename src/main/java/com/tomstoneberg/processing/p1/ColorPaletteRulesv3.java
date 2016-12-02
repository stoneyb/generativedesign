package com.tomstoneberg.processing.p1;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

/**
 * generates a specific color palette and some random "rect-tilings"
 *
 * MOUSE
 * left click          : new composition
 *
 * KEYS
 * s                   : save png
 * c                   : save color palette
 */
public class ColorPaletteRulesv3 extends PApplet
{
    int colorCount = 20;
    int[] hueValues = new int[colorCount];
    int[] saturationValues = new int[colorCount];
    int[] brightnessValues = new int[colorCount];
    int alphaValue = 27;

    int actRandomSeed = 0;

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
        background(0,0,0);
        randomSeed(actRandomSeed);

        // colors
        // create palette
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
        int rowCount = (int) random(5, 30);
        float rowHeight = (float) height / (float) rowCount;

        // separate each line in parts
        for(int i = rowCount; i >= 0; i--)
        {
            // how many fragments
            int partCount = i+1;
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
                    parts = append(parts, random(2, 20));
                }
            }

            // add all subparts
            float sumPartsTotal = 0;
            for(int ii = 0; ii < partCount; ii++) sumPartsTotal += parts[ii];

            // draw rects
            float sumPartsNow = 0;
            for(int ii = 0; ii < parts.length; ii++)
            {
                sumPartsNow += parts[ii];

                float x = map(sumPartsNow, 0, sumPartsTotal, 0, width);
                float y = rowHeight * i;
                float w = map(parts[ii], 0, sumPartsTotal, 0, width);
                float h = rowHeight * 1.5f;

                beginShape();
                fill(0, 0, 0);
                vertex(x, y);
                vertex(x + w, y);
                // get component color values + alpha
                int index = counter % colorCount;
                fill(hueValues[index], saturationValues[index], brightnessValues[index], alphaValue);
                vertex(x + w, y + h);
                vertex(x, y + h);
                endShape(CLOSE);

                counter++;

            }
        }
    }

    @Override
    public void mouseReleased()
    {
        actRandomSeed = (int) random(10000);
    }

    public static void main(String[] args)
    {
        PApplet.main(ColorPaletteRulesv3.class.getCanonicalName());
    }
}
