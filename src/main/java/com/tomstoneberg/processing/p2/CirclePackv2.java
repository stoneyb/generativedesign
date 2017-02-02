package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PShape;

public class CirclePackv2 extends Template
{

   boolean freeze = false;

   int maxCount = 5000;
   int currentCount = 1;
   float[] x = new float[maxCount];
   float[] y = new float[maxCount];
   float[] r = new float[maxCount];
   int[] closestIndex = new int[maxCount];

   float minRadius = 3;
   float maxRadius = 50;

   // mouse and arrow up/down interaction
   float mouseRect = 30;

   // svg vector import
   PShape module1, module2;

   // style selector, hotkeys 1,2,3
   boolean showSvg = true;
   boolean showLine = false;
   boolean showCircle = false;

   @Override
   public void settings()
   {
      size(800, 800);
   }

   @Override
   public void setup()
   {
      noFill();
      smooth();
      cursor(CROSS);

      module1 = loadShape("01.svg");
      module2 = loadShape("02.svg");

      // first circle
      x[0] = 200;
      y[0] = 100;
      r[0] = 50;
      closestIndex[0] = 0;
   }

   @Override
   public void doDraw()
   {
      shapeMode(CENTER);
      ellipseMode(CENTER);
      background(255);

      for(int j = 0; j < 40; j++)
      {
         // create a random position
         float tx = random(0 + maxRadius, width - maxRadius);
         float ty = random(0 + maxRadius, height - maxRadius);
         float tr = minRadius;

         // create a random position according to mouse position
         if(mousePressed)
         {
            tx = random(mouseX - mouseRect / 2, mouseX + mouseRect / 2);
            ty = random(mouseY - mouseRect / 2, mouseY + mouseRect / 2);
            tr = 1;
         }

         boolean intersection = true;
         // find a pos with no intersection with other circles
         for(int i = 0; i < currentCount; i++)
         {
            float d = dist(tx, ty, x[i], y[i]);
            if(d >= (tr + r[i]))
            {
               intersection = false;
            }
            else
            {
               intersection = true;
               break;
            }
         }

         // stop process by pressing hotkey 'F'
         if(freeze) intersection = true;

         // no intersection .. add new circle
         if(!intersection)
         {
            // get closest neighbor and closest possible radius
            float tRadius = width;
            for(int i = 0; i < currentCount; i++)
            {
               float d = dist(tx, ty, x[i], y[i]);
               if(tRadius > d - r[i])
               {
                  tRadius = d - r[i];
                  closestIndex[currentCount] = i;
               }
            }

            if(tRadius > maxRadius) tRadius = maxRadius;

            x[currentCount] = tx;
            y[currentCount] = ty;
            r[currentCount] = tRadius;
            currentCount++;
         }
      }

      // draw them
      for(int i = 0; i < currentCount; i++)
      {
         pushMatrix();
         translate(x[i], y[i]);
         // we abuse radius as random angle
         rotate(radians(r[i]));

         if(showSvg)
         {
            // drawSvgs
            if(r[i] == maxRadius) shape(module1, 0, 0, r[i] * 2, r[i] * 2);
            else shape(module2, 0, 0, r[i] * 2, r[i] * 2);
         }

         if(showCircle)
         {
            stroke(0);
            strokeWeight(1.5f);
            ellipse(0, 0, r[i] * 2, r[i] * 2);
         }
         popMatrix();

         if(showLine)
         {
            stroke(150);
            strokeWeight(1);
            int n = closestIndex[i];
            line(x[i], y[i], x[n], y[n]);
         }
      }

      // visualize the random range of the new positions
      if(mousePressed)
      {
         stroke(255, 200, 0);
         strokeWeight(2);
         rect(mouseX - mouseRect / 2, mouseY - mouseRect / 2, mouseRect, mouseRect);
      }

      if(currentCount >= maxCount) noLoop();
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();

      // freeze process, toggle on/off
      if (key == 'f' || key == 'F') freeze = !freeze;

      // skin style, toggle on/off
      if (key == '1') showSvg = !showSvg;
      if (key == '2') showLine = !showLine;
      if (key == '3') showCircle = !showCircle;
   }

   @Override
   public void keyPressed()
   {
      // mouseRect ctrls arrowkeys up/down
      if (keyCode == UP) mouseRect += 4;
      if (keyCode == DOWN) mouseRect -= 4;
   }

   public static void main(String[] args)
   {
      PApplet.main(CirclePackv2.class.getCanonicalName());
   }
}
