package com.tomstoneberg.processing.custom;

import com.tomstoneberg.processing.Template;
import com.tomstoneberg.processing.custom.lsystem.Fass1;
import com.tomstoneberg.processing.custom.lsystem.Fass2;
import com.tomstoneberg.processing.custom.lsystem.LSystem;
import com.tomstoneberg.processing.custom.lsystem.SierpinskiGasket;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.HashSet;
import java.util.Set;

public class LSystemRendererv2 extends Template
{
   private int generations = 0;
   private LSystem lSystem = newSystem(generations);
   private float LINE_LENGTH = 5.0f;

   private float centerX = width / 2;
   private float centerY = height / 2;
   private float offsetX, offsetY;

   private float angle = 0;

   private static LSystem newSystem(int generations)
   {
      return new Fass2(generations);
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
      angle = 0;
      background(0);
      strokeWeight(0.5f);

      if(mousePressed)
      {
         centerX = mouseX - offsetX;
         centerY = mouseY - offsetY;
      }
      translate(centerX, centerY);

      System.out.println(lSystem.getProductionResult());

      PVector initialPoint = new PVector(0, 0);
      PVector currentPoint = initialPoint;

      Set<LinePoints> lines = new HashSet<>();

      for(char c : lSystem.getProductionResult().toCharArray())
      {
         stroke(255, 100);
         switch(c)
         {
            case 'F':
            case 'G':
            case 'A':
            case 'B':
               PVector point = getPoint(currentPoint);
               LinePoints line = new LinePoints(currentPoint.x, currentPoint.y, point.x, point.y);
               lines.add(line);
               currentPoint = point;
               break;
            case 'f':
               currentPoint = getPoint(currentPoint);
               break;
            case '-':
               angle = (angle - lSystem.getTheta()) % 360;
               break;
            case '+':
               angle = (angle + lSystem.getTheta()) % 360;
               break;
         }
      }

      System.out.print(lines);

      if(lSystem.isClosed())
      {
         // Close the loop, add final line
         LinePoints finalLine = new LinePoints(currentPoint.x, currentPoint.y, initialPoint.x, initialPoint.y);
         lines.add(finalLine);
      }

      for(LinePoints line: lines)
      {
         line(line.x1, line.y1, line.x2, line.y2);
      }
   }

   private PVector getPoint(PVector point)
   {
      float x = point.x + (int)(sin(radians(angle)) * LINE_LENGTH);
      float y = point.y + (int)(cos(radians(angle)) * LINE_LENGTH);

      return new PVector(x, y);
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
      PApplet.main(LSystemRendererv2.class.getCanonicalName());
   }
}
