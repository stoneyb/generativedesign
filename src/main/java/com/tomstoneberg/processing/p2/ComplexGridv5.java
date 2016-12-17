package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class ComplexGridv5 extends Template
{
    float tileCountX = 10;
    float tileCountY = 10;
    int count = 0;
    int colorStep = 6;
    int endSize = 0;
    int stepSize = 0;
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
        colorMode(HSB, 360, 100, 100);
        smooth();
        noStroke();
        background(360);
        randomSeed(actRandomSeed);
        stepSize = mouseX / 10;
        endSize = mouseY / 10;
        for(int y = 0; y < tileCountY; y++)
        {
            for(int x = 0; x < tileCountX; x++)
            {
                float tileWidth = width / tileCountX;
                float tileHeight = height / tileCountY;
                float posX = tileWidth * x;
                float posY = tileHeight * y;
                switch((int)random(4))
                {
                    case 0:
                        for(int i = 0; i < stepSize; i++)
                        {
                            float diameter = map(i, 0, stepSize, tileWidth, endSize);
                            fill(360 - (i * colorStep));
                            ellipse(posX, posY + i, diameter, diameter);
                        }
                        break;
                    case 1:
                        for(int i = 0; i < stepSize; i++)
                        {
                            float diameter = map(i, 0, stepSize, tileHeight, endSize);
                            fill(360 - (i * colorStep));
                            ellipse(posX, posY + i, diameter, diameter);
                        }
                        break;
                    case 2:
                        for(int i = 0; i < stepSize; i++)
                        {
                            float diameter = map(i, 0, stepSize, tileWidth, endSize);
                            fill(360 - (i * colorStep));
                            ellipse(posX - i, posY, diameter, diameter);
                        }
                        break;
                    case 3:
                        for(int i = 0; i < stepSize; i++)
                        {
                            float diameter = map(i, 0, stepSize, tileHeight, endSize);
                            fill(360 - (i * colorStep));
                            ellipse(posX, posY - i, diameter, diameter);
                        }
                        break;

                }
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
        PApplet.main(ComplexGridv5.class.getCanonicalName());
    }
}
