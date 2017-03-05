package com.tomstoneberg.processing.custom;

import com.tomstoneberg.processing.Template;
import com.tomstoneberg.processing.custom.lsystem.KochIsland;
import com.tomstoneberg.processing.custom.lsystem.LSystem;
import processing.core.PApplet;

public class LSystemRender extends Template
{
   int generations = 0;
   private LSystem lSystem = new KochIsland(generations);

   @Override
   public void settings()
   {
      size(1600, 1600);
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
      translate(width / 2, height / 2);

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

      noLoop();

   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
      if (keyCode == UP)
      {
         generations++;
         lSystem = new KochIsland(generations);
      }

      if (keyCode == DOWN)
      {
         generations--;
         lSystem = new KochIsland(generations);
      }

      loop();
   }

   public static void main(String[] args)
   {
      PApplet.main(LSystemRender.class.getCanonicalName());
   }
}
