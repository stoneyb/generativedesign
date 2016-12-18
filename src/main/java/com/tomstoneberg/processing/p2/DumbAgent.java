package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

import static com.tomstoneberg.processing.p2.DumbAgent.Direction.NORTH;

public class DumbAgent extends Template
{
    enum Direction {
        NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST
    }

    float stepSize = 1;
    float diameter = 1;

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
        background(255);
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
            direction = Direction.values()[(int)random(0, 8)];
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

            fill(0, 40);
            ellipse(posX + stepSize / 2, posY + stepSize / 2, diameter, diameter);
        }
    }

    public static void main(String[] args)
    {
        PApplet.main(DumbAgent.class.getCanonicalName());
    }


}
