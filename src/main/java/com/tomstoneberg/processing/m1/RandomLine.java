package com.tomstoneberg.processing.m1;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class RandomLine extends Template
{
   private int seed = 42;

   @Override
   public void settings()
   {
      size(1024, 256);
      pixelDensity(displayDensity());
   }

   @Override
   public void setup()
   {
         smooth();
   }

   @Override
   public void doDraw()
   {
      background(255);

      stroke(0, 130, 164);
      strokeWeight(1);
      strokeJoin(ROUND);
      noFill();

      randomSeed(seed);
      beginShape();
      for(int x = 0; x < width; x += 10)
      {
         float y = random(0, height);
         vertex(x, y);
      }
      endShape();

      noStroke();
      fill(0);

      randomSeed(seed);
      for(int x = 0; x < width; x += 10)
      {
         float y = random(0, height);
         ellipse(x, y, 3, 3);
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
      PApplet.main(RandomLine.class.getCanonicalName());
   }
}
