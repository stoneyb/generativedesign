package com.tomstoneberg.processing.p0;

import processing.core.PApplet;

public class Basic extends PApplet
{
    @Override
    public void setup()
    {
        size(800, 800);
        frameRate(30);
    }

    @Override
    public void draw()
    {
        background(255);
        ellipse(50, 50, 60, 60);
        strokeWeight(4);
        fill(128);
        rect(50, 50, 40, 30);
    }

    public static void main(String [] args)
    {
        PApplet.main(Basic.class.getCanonicalName());
    }

}
