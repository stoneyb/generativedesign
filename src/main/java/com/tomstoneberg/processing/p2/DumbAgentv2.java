package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class DumbAgentv2 extends Template
{
    enum Direction {
        NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST
    }

    float stepSize = 1;
    float diameter = 1;

    float drawMode = 1;
    int counter = 0;

    Direction direction;
    float posX, posY;

    @Override
    public void settings()
    {
        size(800, 800);
    }

    @Override
    public void setup()
    {
        colorMode(HSB, 360, 100, 100, 100);
        background(360);
        smooth();
        noStroke();
        posX = width / 2;
        posY = height / 2;
    }

    @Override
    public void doDraw()
    {
        for(int i = 0; i <= mouseX; i++)
        {
            counter++;

            if(drawMode == 2)
            {
                direction = Direction.values()[round(random(0, 3))];
            } else
            {
                direction = Direction.values()[(int)random(0,7)];
            }

            switch(direction)
            {
                case NORTH:
                    posY -= stepSize;
                    break;
                case NORTHEAST:
                    posX += stepSize;
                    posY -= stepSize;
                    break;
                case EAST:
                    posX += stepSize;
                    break;
                case SOUTHEAST:
                    posX += stepSize;
                    posY += stepSize;
                    break;
                case SOUTH:
                    posY += stepSize;
                    break;
                case SOUTHWEST:
                    posX -= stepSize;
                    posY += stepSize;
                    break;
                case WEST:
                    posX -= stepSize;
                    break;
                case NORTHWEST:
                    posX -= stepSize;
                    posY -= stepSize;
                    break;
            }
            if(posX > width) posX = 0;
            if(posX < 0) posX = width;
            if(posY < 0) posY = height;
            if(posY > height) posY = 0;

            if(drawMode == 3)
            {
                if(counter >= 100)
                {
                    counter = 0;
                    fill(192, 100, 64, 80);
                    ellipse(posX + stepSize / 2, posY + stepSize / 2, diameter + 7, diameter + 7);
                }
            }

            fill(0, 40);
            ellipse(posX + stepSize / 2, posY + stepSize / 2, diameter, diameter);
        }
    }

    @Override
    public void keyReleased()
    {
        super.keyReleased();
        if(key == DELETE || key == BACKSPACE) background(360);
        if(key == '1')
        {
            drawMode = 1;
            stepSize = 1;
            diameter = 1;
        }
        if(key == '2')
        {
            drawMode = 2;
            stepSize = 1;
            diameter = 1;
        }
        if(key == '3')
        {
            drawMode = 3;
            stepSize = 10;
            diameter = 5;
        }
    }

    public static void main(String[] args)
    {
        PApplet.main(DumbAgentv2.class.getCanonicalName());
    }
}
