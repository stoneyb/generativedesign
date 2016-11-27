package com.tomstoneberg.processing.p1;

import processing.core.PApplet;

public class ColorSpectrum extends PApplet
{

    private int segmentCount = 10;
    int radius = 360;

    @Override
    public void settings()
    {
        size(800, 800);
    }

    @Override
    public void setup()
    {
        noStroke();
    }

    @Override
    public void draw()
    {
        colorMode(HSB, 360, width, height);
        background(360);

        float angleStep = 360 / segmentCount;

        beginShape(TRIANGLE_FAN);
        vertex(width / 2, height / 2);
        for(float angle = 0; angle <= 360; angle+=angleStep)
        {
            float vx = width / 2 + cos(radians(angle)) * radius;
            float vy = height / 2 + sin(radians(angle)) * radius;
            vertex(vx, vy);
            fill(angle, mouseX, mouseY);
        }
        endShape();
    }

    @Override
    public void keyReleased()
    {
        switch(key)
        {
            case '1':
                segmentCount = 360;
                break;
            case '2':
                segmentCount = 45;
                break;
            case '3':
                segmentCount = 24;
                break;
            case '4':
                segmentCount = 12;
                break;
            case '5':
                segmentCount = 6;
                break;
        }
    }

    public static void main(String[] args)
    {
        PApplet.main(ColorSpectrum.class.getCanonicalName());
    }
}
