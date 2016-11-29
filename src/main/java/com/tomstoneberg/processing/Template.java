package com.tomstoneberg.processing;

import processing.core.PApplet;

public class Template extends PApplet
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
    }

    public static void main(String[] args)
    {
        PApplet.main(Template.class.getCanonicalName());
    }
}
