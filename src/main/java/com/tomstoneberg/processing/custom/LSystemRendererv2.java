package com.tomstoneberg.processing.custom;

import com.tomstoneberg.processing.Template;
import com.tomstoneberg.processing.custom.lsystem.*;
import com.tomstoneberg.processing.p2.IntelligentAgent;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LSystemRendererv2 extends Template
{
   private int generations = 0;
   private LSystem lSystem = newSystem(generations);

   private static float LINE_LENGTH = 10.0f;
   private static float LINE_WEIGHT = 0.5f;

   private float centerX = width / 2;
   private float centerY = height / 2;
   private float offsetX, offsetY;

   private float angle = 0;

   private static LSystem newSystem(int generations)
   {
      return new BranchC(generations);
   }

   @Override
   public void settings()
   {
      size(600, 600);
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
      angle = 180;
      background(255);
      strokeWeight(LINE_WEIGHT);
      strokeCap(SQUARE);

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

      Stack<State> states = new Stack<>();

      for(char c : lSystem.getProductionResult().toCharArray())
      {
         stroke(0, 100);
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
            case '[':
               states.push(new State(currentPoint, angle));
               break;
            case ']':
               State state = states.pop();
               currentPoint = state.currentPoint;
               angle = state.angle;
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

   private static class State
   {
      PVector currentPoint;
      float angle;

      public State(PVector currentPoint, float angle)
      {
         this.currentPoint = currentPoint;
         this.angle = angle;
      }
   }

   public static void main(String[] args)
   {
      PApplet.main(LSystemRendererv2.class.getCanonicalName());
   }
}
