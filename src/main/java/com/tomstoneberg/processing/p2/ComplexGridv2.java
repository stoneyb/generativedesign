package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class ComplexGridv2 extends Template
{

    float tileCountX = 5;
    float tileCountY = 5;

    int count = 10;
    int colorStep = 20;

    int lineWeight = 0;
    int strokeColor = 0;

    int backgroundColor = 0;

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
        strokeWeight(0.5f);
        strokeCap(ROUND);

        tileCountX = mouseX / 30 + 1;
        tileCountY = mouseY / 30 + 1;

        background(backgroundColor);

        for(int y = 0; y <= tileCountY; y++)
        {
            for(int x = 0; x <= tileCountX; x++)
            {
               float tileWidth = width / tileCountX;
               float tileHeight = height / tileCountY;
               float posX = tileWidth * x;
               float posY = tileHeight * y;

               float x1 = tileWidth / 2;
               float y1 = tileHeight / 2;
               float x2 = 0;
               float y2 = 0;

               pushMatrix();
               translate(posX, posY);

               for(int side = 0; side < 4; side++)
               {
                   for(int i = 0; i < count; i++)
                   {
                       // move end point around the four sides of the tile
                       if(side == 0)
                       {
                           x2 += tileWidth / count;
                           y2 = 0;
                       }
                       if(side == 1)
                       {
                           x2 = tileWidth;
                           y2 += tileHeight / count;
                       }
                       if(side == 2)
                       {
                           x2 -= tileWidth / count;
                           y2 = tileHeight;
                       }
                       if(side == 3)
                       {
                           x2 = 0;
                           y2 -= tileHeight / count;
                       }

                       // adjust weight and color of the line
                       if(i < count / 2)
                       {
                           lineWeight += 1;
                           strokeColor += 60;
                       } else
                       {
                           lineWeight -= 1;
                           strokeColor -= 60;
                       }

                       // set colros depending on draw mode
                       switch(drawMode)
                       {
                           case 1:
                               backgroundColor = 360;
                               stroke(0);
                               break;
                           case 2:
                               backgroundColor = 360;
                               stroke(0);
                               strokeWeight(lineWeight);
                               break;
                           case 3:
                               backgroundColor = 0;
                               stroke(strokeColor);
                               strokeWeight(mouseX / 100);
                               break;
                       }

                       // draw the line
                       line(x1, y1, x2, y2);
                   }
               }

               popMatrix();
            }
        }
    }

    @Override
    public void keyPressed()
    {
        super.keyPressed();
        if (key == '1') drawMode = 1;
        if (key == '2') drawMode = 2;
        if (key == '3') drawMode = 3;
    }


    public static void main(String[] args)
    {
        PApplet.main(ComplexGridv2.class.getCanonicalName());
    }
}
