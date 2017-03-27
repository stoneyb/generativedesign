package com.tomstoneberg.processing.custom;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PVector;

public class PerlinMountain extends Template
{

   float pOff = 0;
   float stepSize = 1.5f;

   PVector prev = new PVector(0, 400 +  map(noise(pOff), 0, 1, -50, 50));

   @Override
   public void settings()
   {
      size(800, 400);
      smooth();
   }

   @Override
   public void setup()
   {
      noStroke();
      colorMode(HSB);
   }

   @Override
   public void doDraw()
   {
      int r = (int)random(360);
      background(r, 30, 80);
//      background(127, 114, 92);
      drawMountain(r,200, 50, 60);
      drawMountain(r,250, 80, 50);
      drawMountain(r,300, 100, 40);

      noLoop();
   }

   private void drawMountain(int r, int yStart, int range, int bright)
   {
      fill(r, 30, bright);
      float x = 0;
      float y = yStart;
      beginShape();
      curveVertex(x, y);

      while(x <= width)
      {
         float offset = map(noise(pOff), 0, 1, -range, range);
         curveVertex(x + stepSize, yStart + offset);
         x += stepSize;
         y = yStart + offset;
         pOff += 0.01;
      }

      curveVertex(width, height);
      curveVertex(0, height);
      curveVertex(0, 400);

      endShape();
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
      if(key == ' ') loop();
   }

   public static void main(String[] args)
   {
      PApplet.main(PerlinMountain.class.getCanonicalName());
   }
}
