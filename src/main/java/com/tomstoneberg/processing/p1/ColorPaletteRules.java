package com.tomstoneberg.processing.p1;

import processing.core.PApplet;

import static generativedesign.GenerativeDesign.timestamp;

/**
 * generates specific color palettes
 *
 * MOUSE
 * position x/y        : row and coloum count
 *
 * KEYS
 * 0-9                 : creates specific color palettes
 * s                   : save png
 * p                   : save pdf
 * c                   : save color palette
 */

public class ColorPaletteRules extends PApplet
{

    int tileCountX = 50;
    int tileCountY = 10;

    int[] hueValues = new int[tileCountX];
    int[] saturationValues = new int[tileCountX];
    int[] brightnessValues = new int[tileCountX];


    @Override
    public void settings()
    {
        size(800, 800);
    }

    @Override
    public void setup()
    {
        colorMode(HSB, 360, 100, 100, 100);
        noStroke();

        randomize();
    }

    @Override
    public void draw()
    {
        // white background
        background(0, 0, 100);

        // count every tile
        int counter = 0;

        // map mouse to grid resolution
        int currentTileCountX = (int) map(mouseX, 0, width, 1, tileCountX);
        int currentTileCountY = (int) map(mouseY, 0, height, 1, tileCountY);
        float tileWidth = width / (float) currentTileCountX;
        float tileHeight = width / (float) currentTileCountY;

        for(int y = 0; y < tileCountY; y++)
        {
            for(int x = 0; x < tileCountX; x++)
            {
                float posX = tileWidth * x;
                float posY = tileHeight * y;
                int index = counter % currentTileCountX;

                // get component color values
                fill(hueValues[index], saturationValues[index], brightnessValues[index]);
                rect(posX, posY, tileWidth, tileHeight);
                counter++;
            }
        }
    }

    @Override
    public void keyReleased()
    {
        switch(key)
        {
            case 's':
                saveFrame(timestamp()+"_##.png");
                break;
            case '1':
                randomize();
                break;
            case '2':
                randomize();
                // init with random values
                for(int i = 0; i < tileCountX; i++)
                {
                    brightnessValues[i] = 100;
                }
                break;
            case '3':
                randomize();
                // init with random values
                for(int i = 0; i < tileCountX; i++)
                {
                    saturationValues[i] = 100;
                }
                break;
            case '4':
                randomize();
                // init with random values
                for(int i = 0; i < tileCountX; i++)
                {
                    hueValues[i] = 0;
                    saturationValues[i] = 0;
                }
                break;
            case '5':
                randomize();
                // init with random values
                for(int i = 0; i < tileCountX; i++)
                {
                    hueValues[i] = 195;
                    saturationValues[i] = 100;
                }
                break;
            case '6':
                randomize();
                // init with random values
                for(int i = 0; i < tileCountX; i++)
                {
                    hueValues[i] = 195;
                    brightnessValues[i] = 100;
                }
                break;
            case '7':
                // init with random values
                for(int i = 0; i < tileCountX; i++)
                {
                    hueValues[i] = (int) random(0, 180);
                    saturationValues[i] = (int) random(80, 100);
                    brightnessValues[i] = (int) random(50, 90);
                }
                break;
            case '8':
                // init with random values
                for(int i = 0; i < tileCountX; i++)
                {
                    hueValues[i] = (int) random(180, 360);
                    saturationValues[i] = (int) random(80, 100);
                    brightnessValues[i] = (int) random(50, 90);
                }
                break;
            case '9':
                // init with random values
                for(int i = 0; i < tileCountX; i++)
                {
                    if(i % 2 == 0)
                    {
                        hueValues[i] = (int) random(0, 360);
                        saturationValues[i] = 100;
                        brightnessValues[i] = (int) random(0, 100);
                    } else
                    {
                        hueValues[i] = 195;
                        saturationValues[i] = (int) random(0, 100);
                        brightnessValues[i] = 100;
                    }
                }
                break;
            case '0':
                // init with random values
                for(int i = 0; i < tileCountX; i++)
                {
                    if(i % 2 == 0)
                    {
                        hueValues[i] = 192;
                        saturationValues[i] = (int) random(0, 100);
                        brightnessValues[i] = (int) random(10, 100);
                    } else
                    {
                        hueValues[i] = 273;
                        saturationValues[i] = (int) random(0, 100);
                        brightnessValues[i] = (int) random(10, 90);
                    }
                }
                break;
        }
    }

    private void randomize()
    {
        // init with random values
        for(int i = 0; i < tileCountX; i++)
        {
            hueValues[i] = (int) random(0, 360);
            saturationValues[i] = (int) random(0, 100);
            brightnessValues[i] = (int) random(0, 100);
        }
    }


    public static void main(String[] args)
    {
        PApplet.main(ColorPaletteRules.class.getCanonicalName());
    }
}
