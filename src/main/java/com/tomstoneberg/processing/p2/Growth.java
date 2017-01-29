package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class Growth extends Template
{

   int maxCount = 5000; // max count of the circles
   int currentCount = 1;
   float[] x = new float[maxCount];
   float[] y = new float[maxCount];
   float[] r = new float[maxCount]; // radius

   @Override
   public void settings()
   {
      size(800, 800);
   }

   @Override
   public void setup()
   {
      smooth();
      //frameRate(10);

      // first circle
      x[0] = width / 2;
      y[0] = width / 2;
      r[0] = 10;
   }

   @Override
   public void doDraw()
   {
      background(255);

      strokeWeight(0.5f);

      // create a random set of parameters
      float newR = random(1, 7);
      float newX = random(0 + newR, width - newR);
      float newY = random(0 + newR, height - newR);

      float closestDist = 100000000;
      int closestIdx = 0;
      // which circle is the closest
      for(int i = 0; i <  currentCount; i++)
      {
         float newDist = dist(newX, newY, x[i], y[i]);
         if(newDist < closestDist)
         {
            closestDist = newDist;
            closestIdx = i;
         }
      }

      // align it to the closest circle outline
      float angle = atan2(newY - y[closestIdx], newX - x[closestIdx]);

      x[currentCount] = x[closestIdx] + cos(angle) * (r[closestIdx] + newR);
      y[currentCount] = y[closestIdx] + sin(angle) * (r[closestIdx] + newR);
      r[currentCount] = newR;
      currentCount++;

      // draw them
      for(int i = 0; i < currentCount; i++)
      {
         fill(50);
         ellipse(x[i], y[i], r[i] * 2, r[i] * 2);
      }

      if(currentCount >= maxCount) noLoop();

   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
   }

   public static void main(String[] args)
   {
      PApplet.main(Growth.class.getCanonicalName());
   }
}
