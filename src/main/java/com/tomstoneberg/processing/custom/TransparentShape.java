package com.tomstoneberg.processing.custom;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

import java.util.Arrays;
import java.util.List;

public class TransparentShape extends Template
{
   private int transparency = 50;
   private Integer orange = color(246, 81, 29, transparency);
   private Integer yellow = color(255, 180, 0, transparency);
   private Integer blue = color(0, 166, 237, transparency);
   private Integer green = color(127, 184, 0, transparency);
   private List<Integer> colors = Arrays.asList(orange, yellow, blue, green);

   @Override
   public void settings()
   {
      size(800, 800);
   }

   @Override
   public void setup()
   {
      background(255);
      noStroke();
   }

   @Override
   public void doDraw()
   {
      float x = random(0, 800);
      float y = random(0, 800);
      float size = random(10,50);
      fill(colors.get((int)Math.ceil(random(0,3))));
      float choose = random(0, 1);
      if(choose > 0.66)
      {
         rect(x, y, size, size);
      } else if(choose > 0.33)
      {
         triangle(x, y, x + size / 2, y - size, x + size, y);
      } else
      {
         ellipse(x, y, size, size);
      }
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
   }

   public static void main(String[] args)
   {
      PApplet.main(TransparentShape.class.getCanonicalName());
   }
}
