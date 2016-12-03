package com.tomstoneberg.processing.p2;

import processing.core.PApplet;

/**
 * drawing with a changing shape by draging the mouse.
 *
 * MOUSE
 * position x          : length
 * position y          : thickness and number of lines
 * drag                : draw
 *
 * KEYS
 * del, backspace      : erase
 * s                   : save png
 * r                   : start pdf record
 * e                   : end pdf record
 */
public class HelloShapesv2 extends PApplet
{
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
        if(mousePressed)
        {
            pushMatrix();
            translate(width/2,height/2);

            int circleResolution = (int)map(mouseY+100,0,height,2, 10);
            float radius = mouseX-width / 2 + 0.5f;
            float angle = TWO_PI/circleResolution;

            strokeWeight(2);
            stroke(0, 25);

            beginShape();
            for (int i=0; i<=circleResolution; i++)
            {
                float x = 0 + cos(angle*i) * radius;
                float y = 0 + sin(angle*i) * radius;
                vertex(x, y);
            }
            endShape();

            popMatrix();
        }
    }

    @Override
    public void keyReleased(){
        if (key == DELETE || key == BACKSPACE) background(255);
    }

    public static void main(String[] args)
    {
        PApplet.main(HelloShapesv2.class.getCanonicalName());
    }
}
