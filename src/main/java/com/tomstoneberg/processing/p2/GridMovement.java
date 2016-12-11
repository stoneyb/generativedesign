package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;


/**
 * changing size and position of circles in a grid
 *
 * MOUSE
 * position x          : circle position
 * position y          : circle size
 * left click          : random position
 */

public class GridMovement extends Template
{
    float tileCount = 20;
    int circleColor = color(0);
    int circleAlpha = 180;
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
        translate(width / tileCount / 2, height / tileCount / 2); // Shift origin so circles are centered in tiles

        background(255);
        smooth();
        noFill();

        randomSeed(actRandomSeed);

        stroke(circleColor, circleAlpha);
        strokeWeight(mouseY / 60);

        for(int y = 0; y < tileCount; y++)
        {
            for(int x = 0; x < tileCount; x++)
            {
                float posX = width / tileCount * x;
                float posY = height / tileCount * y;

                float shiftX = random(-mouseX, mouseX) / 20;
                float shiftY = random(-mouseX, mouseX) / 20;

                ellipse(posX + shiftX, posY + shiftY, mouseY / 15, mouseX / 15);
            }
        }
    }

    @Override
    public void mousePressed()
    {
        actRandomSeed = (int) random(100000);
    }

    public static void main(String[] args)
    {
        PApplet.main(GridMovement.class.getCanonicalName());
    }

}
