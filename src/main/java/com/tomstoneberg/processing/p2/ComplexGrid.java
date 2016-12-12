package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class ComplexGrid extends Template
{

    float tileCountX = 10;
    float tileCountY = 10;
    float tileWidth, tileHeight;

    int count = 0;
    int colorStep = 15;
    int circleCount;
    float endSize, endOffset;

    int actRandomSeed = 0;

    @Override
    public void settings()
    {
        size(600, 600);
    }

    @Override
    public void setup()
    {
        tileWidth = width / tileCountX;
        tileHeight = height / tileCountY;
    }

    @Override
    public void doDraw()
    {
        noFill();
        stroke(0, 128);
        background(255);
        randomSeed(actRandomSeed);

        translate(width / tileCountX / 2, height / tileCountY / 2);

        circleCount = mouseX / 30 + 1;
        endSize = map(mouseX, 0, width, tileWidth / 2.0f, 0);
        endOffset = map(mouseY, 0, height, 0, (tileWidth - endSize) / 2);

        for(int y = 0; y < tileCountY; y++)
        {
            for(int x = 0; x < tileCountX; x++)
            {
                pushMatrix();
                translate(tileWidth * x, tileHeight * y);
                scale(1, tileHeight / tileWidth);

                int toggle = (int) random(0, 4);
                if(toggle == 0) rotate(-HALF_PI);
                if(toggle == 1) rotate(0);
                if(toggle == 2) rotate(HALF_PI);
                if(toggle == 3) rotate(PI);

                // draw module
                for(int i = 0; i < circleCount; i++)
                {
                    float diameter = map(i, 0, circleCount - 1, tileWidth, endSize);
                    float offset = map(i, 0, circleCount - 1, 0, endOffset);
                    ellipse(offset, 0, diameter, diameter);
                }
                popMatrix();
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
        PApplet.main(ComplexGrid.class.getCanonicalName());
    }
}
