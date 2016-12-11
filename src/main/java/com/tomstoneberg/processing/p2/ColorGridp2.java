package com.tomstoneberg.processing.p2;

import processing.core.PApplet;

/**
 * changing strokeweight on diagonals in a grid with colors
 *
 * MOUSE
 * position x          : left diagonal strokeweight
 * position y          : right diagonal strokeweight
 * left click          : new random layout
 *
 * KEYS
 * s                   : save png
 * p                   : save pdf
 * 1                   : round strokecap
 * 2                   : square strokecap
 * 3                   : project strokecap
 * 4                   : color left diagonal
 * 5                   : color right diagonal
 * 6                   : transparency left diagonal
 * 7                   : transparency right diagonal
 * 0                   : default
 */
public class ColorGridp2 extends PApplet
{
    boolean savePdf = false;

    int tileCount = 20;
    int colorLeft = color(197, 0, 123);
    int colorRight = color(87, 35, 129);

    int alphaLeft = 100;
    int alphaRight = 100;

    int actRandomSeed = 0;
    int actStrokeCap = ROUND;


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
        if(savePdf)
        {
            beginRecord(PDF, "sample.pdf");
        }

        colorMode(HSB, 360, 100, 100, 100);
        background(360);
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
                    stroke(colorLeft, alphaLeft);
                    strokeWeight(mouseX / 20);
                    line(posX, posY, posX + width / tileCount, posY + height / tileCount);
                }
                if(toggle == 1)
                {
                    stroke(colorRight, alphaRight);
                    strokeWeight(mouseY / 20);
                    line(posX, posY + width / tileCount, posX + height / tileCount, posY);

                }
            }
        }

        if(savePdf)
        {
            savePdf = false;
            endRecord();
        }
    }

    @Override
    public void mousePressed() {
        actRandomSeed = (int) random(100000);
    }


    @Override
    public void keyReleased(){
        if (key == 's' || key == 'S') saveFrame("sample.png");
        if (key == 'p' || key == 'P') savePdf = true;

        if (key == '1') actStrokeCap = ROUND;
        if (key == '2') actStrokeCap = SQUARE;
        if (key == '3') actStrokeCap = PROJECT;

        if (key == '4'){
            if (colorLeft == color(0)) {
                colorLeft = color(323, 100, 77);
            } else {
                colorLeft = color(0);
            }
        }
        if (key == '5'){
            if (colorRight == color(0)) {
                colorRight = color(273, 73, 51);
            } else {
                colorRight = color(0);
            }
        }

        if (key == '6') {
            if (alphaLeft == 100) {
                alphaLeft = 50;
            } else {
                alphaLeft = 100;
            }
        }
        if (key == '7') {
            if (alphaRight == 100) {
                alphaRight = 50;
            } else {
                alphaRight = 100;
            }
        }

        if (key == '0'){
            actStrokeCap = ROUND;
            colorLeft = color(0);
            colorRight = color(0);
            alphaLeft = 100;
            alphaRight = 100;
        }
    }

    public static void main(String[] args)
    {
        PApplet.main(ColorGridp2.class.getCanonicalName());
    }
}
