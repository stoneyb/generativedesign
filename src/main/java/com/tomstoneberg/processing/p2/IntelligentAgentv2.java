package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class IntelligentAgentv2 extends Template
{
    int NORTH = 0;
    int EAST = 1;
    int SOUTH = 2;
    int WEST = 3;

    float posX, posY;
    float posXCross, posYCross;

    int direction = SOUTH;
    float angleCount = 7;
    float angle = getRandomAngle(direction);
    float stepSize = 3;
    int minLength = 10;

    // width and brightness of the stroke depend on line length
    int dWeight = 50;
    int dStroke = 4;

    int drawMode = 1;


    @Override
    public void settings()
    {
        size(600, 600);
    }

    @Override
    public void setup()
    {
        colorMode(HSB, 360, 100, 100, 100);
        smooth();
        background(360);

        posX = (int) random(0, width);
        posY = 5;
        posXCross = posX;
        posYCross = posY;
    }

    @Override
    public void doDraw()
    {
        for(int i = 0; i < mouseX; i++)
        {
            posX += cos(radians(angle)) * stepSize;
            posY += sin(radians(angle)) * stepSize;

            boolean reachedBorder = false;

            if(posY <= 5)
            {
                direction = SOUTH;
                reachedBorder = true;
            } else if (posX >= width - 5)
            {
                direction = WEST;
                reachedBorder = true;
            } else if (posY >= height - 5)
            {
                direction = NORTH;
                reachedBorder = true;
            } else if (posX <= 5)
            {
                direction = EAST;
                reachedBorder = true;
            }

            int px = (int) posX;
            int py = (int) posY;
            if(get(px, py) != color(360) || reachedBorder)
            {
                angle = getRandomAngle(direction);
                float distance = dist(posX, posY, posXCross, posYCross);
                if (distance >= minLength)
                {
                    strokeWeight(distance / dWeight);
                    if(drawMode == 1) stroke(0);
                    if(drawMode == 2) stroke(52, 100, distance / dStroke);
                    if(drawMode == 3) stroke( 192, 100, 64, distance / dStroke);
                    line(posX, posY, posXCross, posYCross);
                }
                posXCross = posX;
                posYCross = posY;
            }
        }
    }

    float getRandomAngle(int theDirection) {
        float a = (floor(random(-angleCount, angleCount)) + 0.5f) * 90.0f/angleCount;

        if (theDirection == NORTH) return (a - 90);
        if (theDirection == EAST) return (a);
        if (theDirection == SOUTH) return (a + 90);
        if (theDirection == WEST) return (a + 180);

        return 0;
    }

    @Override
    public void keyReleased()
    {
        super.keyReleased();
        if (key == DELETE || key == BACKSPACE) background(360);
        if (key == '1') drawMode = 1;
        if (key == '2') drawMode = 2;
        if (key == '3') drawMode = 3;
    }

    public static void main(String[] args)
    {
        PApplet.main(IntelligentAgentv2.class.getCanonicalName());
    }

}
