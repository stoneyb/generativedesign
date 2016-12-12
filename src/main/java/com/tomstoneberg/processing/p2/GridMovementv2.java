package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

/**
 * changing module color and positions in a grid
 *
 * MOUSE
 * position x          : offset x
 * position y          : offset y
 * left click          : random position
 *
 * KEYS
 * 1-3                 : different sets of colors
 * 0                   : default
 * arrow up/down       : background module size
 * arrow left/right    : foreground module size
 */
public class GridMovementv2 extends Template
{
    int moduleColorBackground = color(0);
    int moduleColorForeground = color(255);

    int moduleAlphaBackground = 100;
    int moduleAlphaForeground = 100;

    float moduleRadiusBackground = 30;
    float moduleRadiusForeground = 30;

    int backColor = color(255);

    float tileCount = 20;
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
        translate(width / tileCount / 2, height / tileCount / 2);

        colorMode(HSB, 360, 100, 100, 100);
        background(backColor);
        smooth();
        noStroke();

        randomSeed(actRandomSeed);

        for(int y = 0; y < tileCount; y++)
        {
            for(int x = 0; x < tileCount; x++)
            {
                float posX = width / tileCount * x;
                float posY = height / tileCount * y;

                float shiftX = random(-1, 1) * mouseX / 20;
                float shiftY = random(-1, 1) * mouseY / 20;

                fill(moduleColorBackground, moduleAlphaBackground);
                ellipse(posX + shiftX, posY + shiftY, moduleRadiusBackground, moduleRadiusBackground);

            }
        }

        for(int y = 0; y < tileCount; y++)
        {
            for(int x = 0; x < tileCount; x++)
            {
                float posX = width / tileCount * x;
                float posY = height / tileCount * y;

                fill(moduleColorForeground, moduleAlphaForeground);
                ellipse(posX, posY, moduleRadiusForeground, moduleRadiusForeground);

            }
        }
    }

    @Override
    public void mousePressed()
    {
        actRandomSeed = (int) random(100000);
    }

    @Override
    public void keyPressed()
    {
        super.keyPressed();
        if(key == '1')
        {
            if(moduleColorBackground == color(0))
            {
                moduleColorBackground = color(273, 73, 51);
            } else
            {
                moduleColorBackground = color(0);
            }
        }
        if(key == '2')
        {
            if(moduleColorForeground == color(360))
            {
                moduleColorForeground = color(323, 100, 77);
            } else
            {
                moduleColorForeground = color(360);
            }
        }
        if(key == '3')
        {
            if(moduleAlphaBackground == 100)
            {
                moduleAlphaBackground = 50;
                moduleAlphaForeground = 50;
            } else
            {
                moduleAlphaBackground = 100;
                moduleAlphaForeground = 100;
            }
        }
        if(key == '0')
        {
            moduleColorBackground = color(0);
            moduleColorForeground = color(360);
            moduleAlphaBackground = 100;
            moduleAlphaForeground = 100;
            moduleRadiusBackground = 20;
            moduleRadiusForeground = 10;
        }
        if(keyCode == UP)
        {
            moduleRadiusBackground += 2;
        }
        if(keyCode == DOWN)
        {
            moduleRadiusBackground = max(moduleRadiusBackground - 2, 10);
        }
        if(keyCode == LEFT)
        {
            moduleRadiusForeground = max(moduleRadiusForeground - 2, 5);
        }
        if(keyCode == RIGHT)
        {
            moduleRadiusForeground += 2;
        }
    }

    public static void main(String[] args)
    {
        PApplet.main(GridMovementv2.class.getCanonicalName());
    }
}
