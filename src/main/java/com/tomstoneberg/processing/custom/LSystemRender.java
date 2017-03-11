package com.tomstoneberg.processing.custom;

import com.tomstoneberg.processing.Template;
import com.tomstoneberg.processing.custom.lsystem.AbopC;
import com.tomstoneberg.processing.custom.lsystem.LSystem;
import processing.core.PApplet;

public class LSystemRender extends Template
{
   int generations = 0;
   private LSystem lSystem = new AbopC(generations);

   float centerX = width / 2;
   float centerY = height / 2;
   float offsetX, offsetY;

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
      float lineLength = 10f;

      if(mousePressed)
      {
         centerX = mouseX - offsetX;
         centerY = mouseY - offsetY;
      }
      translate(centerX, centerY );

      pushMatrix();

      System.out.println(lSystem.getProductionResult());

      for(char c : lSystem.getProductionResult().toCharArray())
      {
         stroke(0);
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
         lSystem = new AbopC(generations);
      }

      if (keyCode == DOWN)
      {
         generations--;
         lSystem = new AbopC(generations);
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
