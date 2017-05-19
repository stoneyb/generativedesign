package com.tomstoneberg.processing.m1;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class RandomnessAndOrder extends Template
{
   private int seed = 0;
   private int count = 150;

   @Override
   public void settings()
   {
      size(800, 800);
      pixelDensity(displayDensity());
   }

   @Override
   public void setup()
   {
      smooth();
      cursor(CROSS);
   }

   @Override
   public void doDraw()
   {
      background(255);
      noStroke();
      float faderX = (float) mouseX / width;

      randomSeed(seed);
      float angle = radians(360 / (float) count);
      for(int i = 0; i < count; i++)
      {
         float randomX = random(0, width);
         float randomY = random(0, height);
         float circleX = width / 2 + cos(angle * i) * 300;
         float circleY = height / 2 + sin(angle * i) * 300;

         float x = lerp(randomX, circleX, faderX);
         float y = lerp(randomY, circleY, faderX);

         fill(0, 130, 164);
         ellipse(x, y, 11, 11);

      }
   }

   @Override
   public void mouseReleased()
   {
      seed = (int) random(100000);
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
   }

   public static void main(String[] args)
   {
      PApplet.main(RandomnessAndOrder.class.getCanonicalName());
   }
}
