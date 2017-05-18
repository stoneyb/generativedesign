package com.tomstoneberg.processing.p4;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PImage;

public class ImageFeedbackv2 extends Template
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
      int y1 = (int) random(0, height);

      int x2 = round(x1 + random(-10, 10));
      int y2 = round(y1 + random(-10, 10));

      int w = 150;
      int h = 50;

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
      PApplet.main(ImageFeedbackv2.class.getCanonicalName());
   }
}
