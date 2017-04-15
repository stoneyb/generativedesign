package com.tomstoneberg.processing.custom;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class LineMountain extends Template
{
   private static int NUM_OF_POINTS = 300;
   private float pOff = 0;

   @Override
   public void settings()
   {
      size(800, 400);
      pixelDensity(displayDensity());
   }

   @Override
   public void setup()
   {
      background(0);
   }

   @Override
   public void doDraw()
   {
      background(0);
      stroke(255);
      noFill();
      int pointSize = 2;

      List<PVector> topPoints = new ArrayList<>();
      List<PVector> bottomPoints = new ArrayList<>();
      int startX = 0;
      float stepSize =  (float)(width) / NUM_OF_POINTS;
      for(int i = 0; i < NUM_OF_POINTS; i++)
      {
         float offset = map(noise(pOff), 0, 1, -25, 25);
         //ellipse(startX + (stepSize * i), 100 + offset, pointSize, pointSize);
         pOff += 0.5f;
         topPoints.add(new PVector(startX + (stepSize * i), 150 + offset));
      }

      for(int i = 0; i < NUM_OF_POINTS; i++)
      {
         float offset = map(noise(pOff), 0, 1, -10, 10);
         //ellipse(startX + (stepSize * i), 300 + offset, pointSize, pointSize);
         pOff += 0.05f;
         bottomPoints.add(new PVector(startX + (stepSize * i), 250 + offset));
      }

      for(int i = 0; i < 150; i++)
      {
         int topR = (int)random(0, 300);
         int botR = (int)random(topR - 50, topR + 50);
         if(botR < 0) botR = 0;
         if(botR >= 300) botR = 299;
         PVector topPoint = topPoints.get(topR);
         PVector bottomPoint = bottomPoints.get(botR);
         noFill();
         stroke(100, 150, 150, 30);
         line(topPoint.x, topPoint.y, bottomPoint.x, bottomPoint.y);
      }

      noLoop();
   }

   @Override
   public void mousePressed()
   {
      loop();
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
   }

   public static void main(String[] args)
   {
      PApplet.main(LineMountain.class.getCanonicalName());
   }
}
