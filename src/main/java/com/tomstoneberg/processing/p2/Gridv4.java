package com.tomstoneberg.processing.p2;

import processing.core.PApplet;
import processing.core.PShape;

/**
 * shapes in a grid, that are always facing the mouse
 *
 * MOUSE
 * position x/y        : position to face
 *
 * KEYS
 * 1-7                 : choose shapes
 * arrow up/down       : scale of shapes
 * arrow left/right    : additional rotation of shapes
 * c                   : toggle. color mode
 * d                   : toggle. size depending on distance
 * g                   : toggle. grid resolution
 * s                   : save png
 * p                   : save pdf
 */
public class Gridv4 extends PApplet
{
    boolean savePdf = false;

    PShape currentShape;

    int tileCount = 10;
    float tileWidth, tileHeight;
    float shapeSize = 50;
    float newShapeSize = shapeSize;
    float shapeAngle = 0;
    float maxDist;

    int shapeColor = color(0, 130, 164);
    int fillMode = 0;
    int sizeMode = 0;

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

        tileWidth = width / (float) tileCount;
        tileHeight = height / (float) tileCount;
        maxDist =  sqrt(sq(width)+sq(height));

        currentShape = loadShape("module_1.svg");
    }

    @Override
    public void draw()
    {
        if(savePdf)
        {
            beginRecord(PDF, "sample.pdf");
        }

        background(255);
        smooth();

        for(int y = 0; y < tileCount; y++)
        {
            for(int x = 0; x < tileCount; x++)
            {
                float posX = tileWidth * x + tileWidth / 2;
                float posY = tileHeight * y + tileHeight / 2;

                // calculate angle between mouse position and actual position of shape
                float angle = atan2(mouseY - posY, mouseX - posX) + radians(shapeAngle);

                if(sizeMode == 0) newShapeSize = shapeSize;
                if(sizeMode == 1) newShapeSize = shapeSize * 1.5f - map(dist(mouseX, mouseY, posX, posY), 0, 500, 5, shapeSize);
                if(sizeMode == 2) newShapeSize = map(dist(mouseX, mouseY, posX, posY), 0, 500, 5, shapeSize);

                if(fillMode == 0) currentShape.enableStyle();
                if(fillMode == 1)
                {
                    currentShape.disableStyle();
                    fill(shapeColor);
                }
                if(fillMode == 2)
                {
                    currentShape.disableStyle();
                    float a = map(dist(mouseX, mouseY, posX, posY), 0, maxDist, 255, 0);
                    fill(shapeColor, a);
                }
                if(fillMode == 3)
                {
                    currentShape.disableStyle();
                    float a = map(dist(mouseX, mouseY, posX, posY), 0, maxDist, 0, 255);
                    fill(shapeColor, a);
                }

                pushMatrix();
                translate(posX, posY);
                rotate(angle);
                shapeMode(CENTER);

                noStroke();
                shape(currentShape, 0, 0, newShapeSize, newShapeSize);
                popMatrix();
            }
        }

        if(savePdf)
        {
            savePdf = false;
            endRecord();
        }
    }

    @Override
    public void keyPressed() {
        if (key == 's' || key == 'S') saveFrame("sample.png");
        if (key == 'p' || key == 'P') savePdf = true;
        if (key == 'c' || key == 'C') fillMode = (fillMode+1) % 4;
        if (key == 'd' || key == 'D') sizeMode = (sizeMode+1) % 3;

        if (key == 'g' || key == 'G') {
            tileCount = tileCount+5;
            if (tileCount > 20) {
                tileCount = 10;
            }
            tileWidth = width / (float) tileCount;
            tileHeight = height / (float) tileCount;
        }

        if (key == '1') currentShape = loadShape("module_1.svg");
        if (key == '2') currentShape = loadShape("module_2.svg");
        if (key == '3') currentShape = loadShape("module_3.svg");
        if (key == '4') currentShape = loadShape("module_4.svg");
        if (key == '5') currentShape = loadShape("module_5.svg");
        if (key == '6') currentShape = loadShape("module_6.svg");
        if (key == '7') currentShape = loadShape("module_7.svg");

        if (keyCode == UP) shapeSize +=5;
        if (keyCode == DOWN) shapeSize = max(shapeSize-5, 5);
        if (keyCode == LEFT) shapeAngle -=5;
        if (keyCode == RIGHT) shapeAngle +=5;
    }



    public static void main(String[] args)
    {
        PApplet.main(Gridv4.class.getCanonicalName());
    }
}
