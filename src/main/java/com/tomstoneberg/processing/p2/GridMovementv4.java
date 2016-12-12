package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class GridMovementv4 extends Template
{
    int tileCount = 20;
    int rectSize = 30;

    int actRandomSeed = 0;

    @Override
    public void settings()
    {
        size(600, 600);
    }

    @Override
    public void setup()
    {
    }

    @Override
    public void doDraw()
    {
        colorMode(HSB, 360, 100, 100, 100);
        background(360);
        smooth();
        noStroke();

        fill(192, 100, 64, 60);

        randomSeed(actRandomSeed);

        for(int y = 0; y < tileCount; y++)
        {
            for(int x = 0; x < tileCount; x++)
            {
                int posX = width / tileCount * x;
                int posY = height / tileCount * y;

                float shiftX1 = mouseX / 20 * random(-1, 1);
                float shiftY1 = mouseY / 20 * random(-1, 1);
                float shiftX2 = mouseX / 20 * random(-1, 1);
                float shiftY2 = mouseY / 20 * random(-1, 1);
                float shiftX3 = mouseX / 20 * random(-1, 1);
                float shiftY3 = mouseY / 20 * random(-1, 1);
                float shiftX4 = mouseX / 20 * random(-1, 1);
                float shiftY4 = mouseY / 20 * random(-1, 1);

                beginShape();
                vertex(posX + shiftX1, posY + shiftY1);
                vertex(posX + rectSize + shiftX2, posY + shiftY2);
                vertex(posX + rectSize + shiftX3, posY + rectSize + shiftY3);
                vertex(posX + shiftX4, posY + rectSize + shiftY4);
                endShape(CLOSE);

            }
        }
    }

    @Override
    public void mousePressed()
    {
        actRandomSeed = (int) random(10000);
    }

    public static void main(String[] args)
    {
        PApplet.main(GridMovementv4.class.getCanonicalName());
    }
}
