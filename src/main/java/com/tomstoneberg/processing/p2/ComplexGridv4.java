package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class ComplexGridv4 extends Template
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

        count = mouseX / 10 + 10;
        float para = (float)mouseY / height;

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

                // swithc between modules
                switch(drawMode)
                {
                    case 1:
                        for(int i = 0; i < count; i++)
                        {
                            rect(0, 0, tileWidth, tileHeight);
                            scale(1 - 3.0f / count);
                            rotate(para * 0.1f);
                        }
                        break;
                    case 2:
                        for(float i = 0; i < count; i++)
                        {
                            noStroke();
                            int gradient = lerpColor(color(0), color(52, 100, 100, 71), i / count);
                            fill(gradient, i / count * 200);
                            rotate(PI / 4);
                            rect(0, 0, tileWidth, tileHeight);
                            scale(1 - 3.0f / count);
                            rotate(para * 1.5f);
                        }
                        break;
                    case 3:
                        color(RGB, 255);
                        for(float i = 0; i < count; i++)
                        {
                            noStroke();
                            int gradient = lerpColor(color(0, 130, 164), color(255), i / count);
                            fill(gradient, 170);

                            pushMatrix();
                            translate(4 * i, 0);
                            ellipse(0, 0, tileWidth / 4, tileHeight / 4);
                            popMatrix();

                            pushMatrix();
                            translate(-4 * i, 0);
                            ellipse(0, 0, tileWidth / 4, tileHeight / 4);
                            popMatrix();

                            scale(1 - 1.5f / count);
                            rotate(para * 1.5f);
                        }
                        break;
                }
                popMatrix();
            }
        }
    }

    @Override
    public void keyReleased()
    {
        super.keyReleased();
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
        PApplet.main(ComplexGridv4.class.getCanonicalName());
    }
}
