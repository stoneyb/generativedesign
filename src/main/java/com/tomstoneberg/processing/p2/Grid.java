package com.tomstoneberg.processing.p2;

import processing.core.PApplet;

/**
 * changing strokeweight and strokecaps on diagonals in a grid
 *
 * MOUSE
 * position x          : left diagonal strokeweight
 * position y          : right diagonal strokeweight
 * left click          : new random layout
 *
 * KEYS
 * 1                   : round strokecap
 * 2                   : square strokecap
 * 3                   : project strokecap
 * s                   : save png
 * p                   : save pdf
 */

public class Grid extends PApplet
{
    int tileCount = 20;
    int actRandomSeed = 0;

    private int actStrokeCap = ROUND;

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
    public void draw()
    {
        background(255);
        smooth();
        noFill();
        strokeCap(actStrokeCap);

        randomSeed(actRandomSeed);

        for(int y = 0; y < tileCount; y++)
        {
            for(int x = 0; x < tileCount; x++)
            {
                int posX = width / tileCount * x;
                int posY = height / tileCount * y;

                int toggle = (int) random(0, 2);
                if(toggle == 0)
                {
                    strokeWeight(mouseX / 20);
                    line(posX, posY, posX + width / tileCount, posY + height / tileCount);
                }
                if(toggle == 1)
                {
                    strokeWeight(mouseY / 20);
                    line(posX, posY + width / tileCount, posX + height / tileCount, posY);

                }
            }
        }
    }

    @Override
    public void mousePressed()
    {
        actRandomSeed = (int) random(100000);
    }

    @Override
    public void keyReleased()
    {
        if(key == '1')
        {
            actStrokeCap = ROUND;
        }
        if(key == '2')
        {
            actStrokeCap = SQUARE;
        }
        if(key == '3')
        {
            actStrokeCap = PROJECT;
        }
    }


    public static void main(String[] args)
    {
        PApplet.main(Grid.class.getCanonicalName());
    }
}
