package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class IntelligentAgent extends Template
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
    int minLength = 100;

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
        for(int i = 0; i <= mouseX / 10; i++) {
            // make step
            posX += cos(radians(angle)) * stepSize;
            posY += sin(radians(angle)) * stepSize;

            // check if agent is near one of the display borders
            boolean reachedBorder = false;

            if (posY <= 5) {
                direction = SOUTH;
                reachedBorder = true;
            } else if (posX >= width - 5) {
                direction = WEST;
                reachedBorder = true;
            } else if (posY >= height - 5)
            {
                direction = NORTH;
                reachedBorder = true;
            } else if(posX <= 5)
            {
                direction = EAST;
                reachedBorder = true;
            }

            // if agent is crossing his path or border was reached
            int px = (int) posX;
            int py = (int) posY;
            if(get(px, py) != color(360) || reachedBorder)
            {
                angle = getRandomAngle(direction);
                float distance = dist(posX, posY, posXCross, posYCross);
                if(distance >= minLength)
                {
                    strokeWeight(3);
                    stroke(0);
                    line(posX, posY, posXCross, posYCross);
                }
                posXCross = posX;
                posYCross = posY;
            }
        }
    }

    public float getRandomAngle(int direction)
    {
        float a = (floor(random(-angleCount, angleCount)) + 0.5f) * 90.0f/ angleCount;
        if(direction == NORTH) return a - 90;
        if(direction == EAST) return a;
        if(direction == SOUTH) return a + 90;
        if(direction == WEST) return a + 180;
        return 0;
    }

    public static void main(String[] args)
    {
        PApplet.main(IntelligentAgent.class.getCanonicalName());
    }
}
