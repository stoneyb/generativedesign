package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class GridMovementv3 extends Template
{
    float tileCount = 20;
    int moduleColor = color(0);
    int moduleAlpha = 180;
    int actRandomSeed = 0;
    int max_distance = 500;

    @Override
    public void settings()
    {
        size(600, 600, P3D);
    }

    @Override
    public void setup()
    {
    }

    @Override
    public void doDraw()
    {
        background(255);
        smooth();
        noFill();

        randomSeed(actRandomSeed);

        stroke(moduleColor, moduleAlpha);
        strokeWeight(3);

        for(int y = 0; y < width; y += 25)
        {
            for(int x = 0; x < width; x += 25)
            {
                float diameter = dist(mouseX, mouseY, x, y);
                diameter = diameter / max_distance * 40;
                pushMatrix();
                translate(x, y, diameter * 5);
                //rect(0, 0, diameter, diameter);
                ellipse(0, 0, diameter, diameter);
                popMatrix();
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
        PApplet.main(GridMovementv3.class.getCanonicalName());
    }
}
