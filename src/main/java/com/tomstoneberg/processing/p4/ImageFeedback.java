package com.tomstoneberg.processing.p4;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PImage;

public class ImageFeedback extends Template
{
   PImage img;

   @Override
   public void settings()
   {
      size(1024, 780);
      pixelDensity(displayDensity());
   }

   @Override
   public void setup()
   {
      background(255);
      img = loadImage("P_4_1_2_01_data/pic.png");
      image(img, 0, 100);
   }

   @Override
   public void doDraw()
   {
      int x1 = (int) random(0, width);
      int y1 = 0;

      int x2 = round(x1 + random(-7, 7));
      int y2 = round(random(-5, 5));

      int w = (int) random(35, 50);
      int h = height;

      copy(x1, y1, w, h, x2, y2, w, h);
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
      if(keyCode == DELETE || keyCode == BACKSPACE)
      {
         background(255);
         image(img, 0, 100);
      }
   }

   public static void main(String[] args)
   {
      PApplet.main(ImageFeedback.class.getCanonicalName());
   }
}
