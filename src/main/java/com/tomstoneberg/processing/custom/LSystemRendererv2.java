package com.tomstoneberg.processing.custom;

import com.tomstoneberg.processing.Template;
import com.tomstoneberg.processing.custom.lsystem.AbopF;
import com.tomstoneberg.processing.custom.lsystem.LSystem;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.tomstoneberg.processing.custom.LSystemRendererv2.Direction.EAST;

public class LSystemRendererv2 extends Template
{
   int generations = 0;
   private LSystem lSystem = newSystem(generations);

   float centerX = width / 2;
   float centerY = height / 2;
   float offsetX, offsetY;

   enum Direction {
      NORTH, EAST, SOUTH, WEST;

      public static Direction getNextDirection(Direction direction)
      {
         switch (direction)
         {
            case NORTH:
               return EAST;
            case EAST:
               return SOUTH;
            case SOUTH:
               return WEST;
            case WEST:
               return NORTH;
         }
         throw new RuntimeException();
      }

      public static Direction getPreviousDirection(Direction direction)
      {
         switch (direction)
         {
            case NORTH:
               return WEST;
            case EAST:
               return NORTH;
            case SOUTH:
               return EAST;
            case WEST:
               return SOUTH;
         }
         throw new RuntimeException();
      }
   }

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
      strokeWeight(0.5f);

      if(mousePressed)
      {
         centerX = mouseX - offsetX;
         centerY = mouseY - offsetY;
      }
      translate(centerX, centerY);

      //System.out.println(lSystem.getProductionResult());

      PVector initialPoint = new PVector(0, 0);
      PVector currentPoint = initialPoint;
      Direction direction = EAST;

      Set<LinePoints> lines = new HashSet<>();

      for(char c : lSystem.getProductionResult().toCharArray())
      {
         stroke(0, 100);
         switch(c)
         {
            case 'F':
               PVector point = getPoint(currentPoint, direction);
               LinePoints line = new LinePoints(currentPoint.x, currentPoint.y, point.x, point.y);
               lines.add(line);
               currentPoint = point;
               break;
            case 'f':
               currentPoint = getPoint(currentPoint, direction);
               break;
            case '-':
               direction = Direction.getPreviousDirection(direction);
               break;
            case '+':
               direction = Direction.getNextDirection(direction);
               break;
         }
      }
      
      // Close the loop, add final line
      LinePoints finalLine = new LinePoints(currentPoint.x, currentPoint.y, initialPoint.x, initialPoint.y);
      lines.add(finalLine);

      for(LinePoints line: lines)
      {
         line(line.x1, line.y1, line.x2, line.y2);
      }
   }

   private PVector getPoint(PVector point, Direction direction)
   {
      float x = point.x;
      float y = point.y;
      switch(direction)
      {
         case NORTH:
            y -= 10;
            break;
         case EAST:
            x += 10;
            break;
         case SOUTH:
            y += 10;
            break;
         case WEST:
            x -= 10;
            break;
      }

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
