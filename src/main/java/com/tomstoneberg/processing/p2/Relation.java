package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class Relation extends Template
{

   int drawMode = 1;
   int col = color(random(255), random(255), random(255));
   float x = 0, y = 0;
   float stepSize = 5.0f;
   float lineLength = 25;

   @Override
   public void settings()
   {
      size(displayWidth, displayHeight);
   }

   @Override
   public void setup()
   {
      background(255);
      smooth();
      x = mouseX;
      y = mouseY;
      cursor(CROSS);
   }

   @Override
   public void doDraw()
   {
      if(mousePressed)
      {
         float d = dist(x, y, mouseX, mouseY);
         if(d > stepSize)
         {
            float angle = atan2(mouseY - y, mouseX - x);

            pushMatrix();
            translate(x, y);
            rotate(angle);
            stroke(col);
            if(frameCount % 2 == 0) stroke(150);
            line(0, 0, 0, lineLength * random(0.95f, 1.0f) * d / 10);
            popMatrix();

            if(drawMode == 1)
            {
               x = x + cos(angle) * stepSize;
               y = y + sin(angle) * stepSize;
            } else
            {
               x = mouseX;
               y = mouseY;
            }
         }
      }
   }

   @Override
   public void mousePressed()
   {
      x = mouseX;
      y = mouseY;
      col = color(random(255), random(255), random(255));
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();

      if (key == '1') drawMode = 1;
      if (key == '2') drawMode = 2;
   }

   public static void main(String[] args)
   {
      PApplet.main(Relation.class.getCanonicalName());
   }
}
