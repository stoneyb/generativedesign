package com.tomstoneberg.processing.custom;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class Test extends Template
{
   @Override
   public void settings()
   {
      size(800, 800);
   }

   @Override
   public void setup()
   {

   }

   @Override
   public void doDraw()
   {
      translate(width / 2, height / 2);
      for(int i = 0; i < 10; i++)
      {
         line(0, 0, 0, 10);
         translate(0, 10);
         rotate(radians(90));
      }

   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
   }

   public static void main(String[] args)
   {
      PApplet.main(Test.class.getCanonicalName());
   }
}
