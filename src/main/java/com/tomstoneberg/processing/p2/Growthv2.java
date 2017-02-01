package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class Growthv2 extends Template
{

   int maxCount = 5000; // max count of the circles
   int currentCount = 1;
   float[] x = new float[maxCount];
   float[] y = new float[maxCount];
   float[] newXarr = new float[maxCount];
   float[] newYarr = new float[maxCount];
   float[] r = new float[maxCount]; // radius

   boolean drawGhosts = true;

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
      fill(50);

      // first circle
      x[0] = width / 2;
      y[0] = width / 2;
      r[0] = 360;
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

      newXarr[currentCount] = newX;
      newYarr[currentCount] = newY;
      x[currentCount] = x[closestIdx] + cos(angle) * (r[closestIdx] + newR);
      y[currentCount] = y[closestIdx] + sin(angle) * (r[closestIdx] + newR);
      r[currentCount] = newR;
      currentCount++;

      if(drawGhosts)
      {
         for (int i=1 ; i < currentCount; i++)
         {
            fill(230);
            ellipse(newXarr[i], newYarr[i], r[i]*2,r[i]*2);
            line(newXarr[i], newYarr[i], x[i], y[i]);
         }
      }

      noStroke();

      // draw them
      for(int i = 0; i < currentCount; i++)
      {
         if(i==0)
         {
            continue;
         }

         if(dist(x[i], y[i], width / 2, height / 2) < 360)
         {
            ellipse(x[i], y[i], r[i] * 2, r[i] * 2);
         }
      }

      if(currentCount >= maxCount) noLoop();

   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
      if(key == '1') drawGhosts = !drawGhosts;
      if(key == 'r') fill(random(255), random(255), random(255));
      if(key == 'd') fill(50);

   }

   public static void main(String[] args)
   {
      PApplet.main(Growthv2.class.getCanonicalName());
   }
}
