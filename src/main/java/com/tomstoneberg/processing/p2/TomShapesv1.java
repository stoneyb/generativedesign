package com.tomstoneberg.processing.p2;

import processing.core.PApplet;

public class TomShapesv1 extends PApplet
{
    private int strokeColor = color(0, 10);

    @Override
    public void settings()
    {
        size(720, 720);
    }

    @Override
    public void setup()
    {
        smooth();
        noFill();
        background(255);
    }

    @Override
    public void draw()
    {
        pushMatrix();
        translate(width/2,height/2);

        int circleResolution = (int) random(5,10);
        float radius = (int) random(100, 180);
        float angle = TWO_PI / circleResolution;

        strokeWeight(3);
        stroke(strokeColor);

        beginShape();
        for (int i=0; i <= circleResolution; i++)
        {
            float x = 0 + cos(angle*i) * radius;
            float y = 0 + sin(angle*i) * radius;
            vertex(x, y);
        }
        endShape();

        popMatrix();
        delay(100);
    }

    @Override
    public void keyReleased()
    {
        if (key == DELETE || key == BACKSPACE) background(255);

        switch(key){
            case '1':
                strokeColor = color(0, 10);
                break;
            case '2':
                strokeColor = color(192, 100, 64, 10);
                break;
            case '3':
                strokeColor = color(52, 100, 71, 10);
                break;
        }

    }

    public static void main(String[] args)
    {
        PApplet.main(TomShapesv1.class.getCanonicalName());
    }

}
