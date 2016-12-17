package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

/**
 * changing positions of stapled circles in a grid
 *
 * MOUSE
 * position x          : module detail
 * position y          : module parameter
 *
 * KEYS
 * 1-3                 : draw mode
 * arrow left/right    : number of tiles horizontally
 * arrow up/down       : number of tiles vertically
 */
public class ComplexGridv3 extends Template
{
    float tileCountX = 6;
    float tileCountY = 6;

    int count = 0;

    int drawMode = 1;


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
        rectMode(CENTER);
        smooth();
        stroke(0);
        noFill();
        background(360);

        count = mouseX  / 20 + 5;
        float para = mouseY / height - 0.5f;

        for(int y = 0; y < tileCountY; y++)
        {
            for(int x = 0; x < tileCountX; x++)
            {
                float tileWidth = width / tileCountX;
                float tileHeight = height / tileCountY;
                float posX = tileWidth * x + tileWidth / 2;
                float posY = tileHeight * y + tileHeight / 2;

                pushMatrix();
                translate(posX, posY);

                // switch between modules
                switch(drawMode)
                {
                    case 1:
                        translate(-tileWidth / 2, -tileHeight / 2);
                        for(int i = 0; i < count; i ++)
                        {
                            line(0, (para + 0.5f) * tileHeight, tileWidth, i * tileHeight / count);
                            line(0, i * tileHeight / count, tileWidth, i * tileHeight - (para + 0.5f) * tileHeight);
                        }
                        break;
                    case 2:
                        for(float i=0; i<=count; i++)
                        {
                            line(para * tileWidth, para * tileHeight, tileWidth / 2, (i / count - 0.5f) * tileHeight);
                            line(para * tileWidth, para * tileHeight, -tileWidth / 2, (i / count - 0.5f) * tileHeight);
                            line(para * tileWidth, para * tileHeight, (i / count - 0.5f) * tileWidth, tileHeight / 2);
                            line(para * tileWidth, para * tileHeight, (i / count - 0.5f) * tileWidth, -tileHeight / 2);
                        }
                        break;
                    case 3:
                        for(float i=0; i<=count; i++)
                        {
                            line(0, para * tileHeight, tileWidth / 2, (i / count - 0.5f) * tileHeight);
                            line(0, para * tileHeight, -tileWidth / 2, (i / count - 0.5f) * tileHeight);
                            line(0, para * tileHeight, (i / count - 0.5f) * tileWidth, tileHeight / 2);
                            line(0, para * tileHeight, (i / count - 0.5f) * tileWidth, -tileHeight / 2);
                        }
                        break;
                }
                popMatrix();
            }
        }
    }

    @Override
    public void keyPressed()
    {
        if (key == '1') drawMode = 1;
        if (key == '2') drawMode = 2;
        if (key == '3') drawMode = 3;

        if (keyCode == DOWN) tileCountY = max(tileCountY-1, 1);
        if (keyCode == UP) tileCountY += 1;
        if (keyCode == LEFT) tileCountX = max(tileCountX-1, 1);
        if (keyCode == RIGHT) tileCountX += 1;
    }

    public static void main(String[] args)
    {
        PApplet.main(ComplexGridv3.class.getCanonicalName());
    }
}
