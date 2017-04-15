package com.tomstoneberg.processing.p4;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PImage;

public class BasicImage extends Template
{

   PImage img;

   @Override
   public void settings()
   {
      size(650, 450);
      pixelDensity(displayDensity());
   }

   @Override
   public void setup()
   {
      img = loadImage("P_4_0_01_data/image.jpg");
   }

   @Override
   public void doDraw()
   {
      float tileCountX = mouseX / 3 + 1;
      float tileCountY = mouseY / 3 + 1;
      float stepX = width / tileCountX;
      float stepY = height / tileCountY;
      for(float y = 0; y < height; y += stepY)
      {
         for(float x = 0; x < width; x += stepX)
         {
            image(img, x, y, stepX, stepY);
         }
      }
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
   }

   public static void main(String[] args)
   {
      PApplet.main(BasicImage.class.getCanonicalName());
   }
}
