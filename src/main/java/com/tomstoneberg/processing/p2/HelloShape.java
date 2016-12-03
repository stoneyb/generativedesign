package com.tomstoneberg.processing.p2;

import processing.core.PApplet;

/**
 * drawing a filled circle with lines.
 *
 * MOUSE
 * position x          : length
 * position y          : thickness and number of lines
 *
 * KEYS
 * s                   : save png
 * p                   : save pdf
 */
public class HelloShape extends PApplet
{
    @Override
    public void settings()
    {
        size(800, 800);
    }

    @Override
    public void setup()
    {
    }

    @Override
    public void draw()
    {
        strokeCap(SQUARE);
        smooth();
        noFill();
        background(255);
        translate(width / 2, height / 2);

        int circleResolution = (int) map(mouseY, 0, height, 2, 80);
        float radius = mouseX - width / 2 + 0.5f;
        float angle = TWO_PI / circleResolution;

        strokeWeight(mouseY / 20);

        beginShape();
        for(int i = 0; i <= circleResolution; i++)
        {
            float x = cos(angle * i) * radius;
            float y = sin(angle * i) * radius;
            line(0, 0, x, y);
        }
        endShape();
    }

    public static void main(String[] args)
    {
        PApplet.main(HelloShape.class.getCanonicalName());
    }
}
