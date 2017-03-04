package com.tomstoneberg.processing.custom;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class LSystemRender extends Template
{

   LSystem lSystem = new LSystem();

   @Override
   public void settings()
   {
      size(800, 800);
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
      float lineLength = 15f;
      translate(width / 2, height / 2);

      String start = lSystem.getAxiom();

      for(int i = 0; i < lSystem.getGenerations(); i++)
      {
         start = start.replace("F", lSystem.getRule());
      }

      System.out.println(start);

      for(char c : start.toCharArray())
      {
         switch(c)
         {
            case 'F':
               line(0, 0, lineLength, 0);
               translate(lineLength, 0);
               break;
            case '-':
               rotate(radians(-lSystem.getTheta()));
               break;
            case '+':
               rotate(radians(lSystem.getTheta()));
               break;
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
      PApplet.main(LSystemRender.class.getCanonicalName());
   }
}
