package com.tomstoneberg.processing.m1;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class NoiseVRandomness extends Template
{
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

      int noiseXRange = mouseX / 10;

      beginShape();
      for(int x = 0; x < width; x += 10)
      {
         float noiseX = map(x, 0, width, 0, noiseXRange);
         float y = noise(noiseX) * height;
         vertex(x, y);
      }
      endShape();

      noStroke();
      fill(0);

      for(int x = 0; x < width; x += 10)
      {
         float noiseX = map(x, 0, width, 0, noiseXRange);
         float y = noise(noiseX) * height;
         ellipse(x, y, 3, 3);
      }
   }

   @Override
   public void mouseReleased()
   {
      noiseSeed((int) random(100000));
   }



   @Override
   public void keyReleased()
   {
      super.keyReleased();
   }

   public static void main(String[] args)
   {
      PApplet.main(NoiseVRandomness.class.getCanonicalName());
   }
}
