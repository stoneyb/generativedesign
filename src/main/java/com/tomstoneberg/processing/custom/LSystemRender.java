package com.tomstoneberg.processing.custom;

import com.tomstoneberg.processing.Template;
import com.tomstoneberg.processing.custom.lsystem.*;
import processing.core.PApplet;

public class LSystemRender extends Template
{
   int generations = 0;
   private LSystem lSystem = newSystem(generations);

   float centerX = width / 2;
   float centerY = height / 2;
   float offsetX, offsetY;

   private static LSystem newSystem(int generations)
   {
      return new AbopF(generations);
   }

   @Override
   public void settings()
   {
      size(800, 800);
      pixelDensity(displayDensity());
   }

   @Override
   public void setup()
   {
      noFill();
   }

   @Override
   public void doDraw()
   {
      background(255);
      float lineLength = 5f;
      strokeWeight(0.5f);

      if(mousePressed)
      {
         centerX = mouseX - offsetX;
         centerY = mouseY - offsetY;
      }
      translate(centerX, centerY );

      pushMatrix();

      System.out.println(lSystem.getProductionResult().length());

      for(char c : lSystem.getProductionResult().toCharArray())
      {
         stroke(0, 100);
         switch(c)
         {
            case 'F':
               line(0, 0, 0, lineLength);
               translate(0, lineLength);
               break;
            case 'f':
               translate(0, lineLength);
               break;
            case '-':
               rotate(-PI / 2);
               break;
            case '+':
               rotate(PI / 2);
               break;
         }
      }

      popMatrix();

   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
      if (keyCode == UP)
      {
         generations++;
         lSystem = newSystem(generations);
      }

      if (keyCode == DOWN)
      {
         generations--;
         lSystem = newSystem(generations);
      }
   }

   @Override
   public void mousePressed()
   {
      offsetX = mouseX - centerX;
      offsetY = mouseY - centerY;
   }

   public static void main(String[] args)
   {
      PApplet.main(LSystemRender.class.getCanonicalName());
   }
}
