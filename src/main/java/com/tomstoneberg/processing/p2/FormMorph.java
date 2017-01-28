package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class FormMorph extends Template
{
   int formResolution = 15;
   int stepSize = 2;
   float distortionFactor = 1;
   float initRadius = 150;
   float centerX, centerY;
   float[] x = new float[formResolution];
   float[] y = new float[formResolution];

   boolean filled = false;
   boolean freeze = false;

   @Override
   public void settings()
   {
      size(displayWidth, displayHeight);
   }

   @Override
   public void setup()
   {
      smooth();

      // init form
      centerX = width / 2;
      centerY = width / 2;
      float angle = radians(360 / (float)formResolution);
      for(int i = 0; i < formResolution; i++)
      {
         x[i] = cos(angle * i) * initRadius;
         y[i] = sin(angle * i) * initRadius;
      }
      stroke(0, 50);
      background(255);

   }

   @Override
   public void doDraw()
   {
      // floating toward mouse position
      if(mouseX != 0 || mouseY != 0)
      {
         centerX += (mouseX - centerX) * 0.01;
         centerY += (mouseY - centerY) * 0.01;
      }

      // calculate new points
      for(int i = 0; i < formResolution; i++)
      {
         x[i] += random(-stepSize, stepSize);
         y[i] += random(-stepSize, stepSize);
      }

      strokeWeight(0.75f);
      if(filled) fill(random(255));
      else noFill();

      beginShape();

      // start controlpoint
      curveVertex(x[formResolution - 1] + centerX, y[formResolution - 1] + centerY);
      // only these points are drawn
      for(int i = 0; i < formResolution; i ++)
      {
         curveVertex(x[i] + centerX, y[i] + centerY);
      }
      curveVertex(x[0] + centerX, y[0] + centerY);

      //end controlpoint
      curveVertex(x[1] + centerX, y[1] + centerY);
      endShape();
   }

   @Override
   public void mousePressed()
   {
      // init form on mouse position
      centerX = mouseX;
      centerY = mouseY;
      float angle = radians(360 / (float)formResolution);
      float radius = initRadius * random(0.5f, 1.0f);
      for(int i = 0; i < formResolution; i++)
      {
         x[i] = cos(angle * i) * radius;
         y[i] = sin(angle * i) * radius;
      }
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
      if (key == '1') filled = false;
      if (key == '2') filled = true;

      // switch draw loop on/off
      if (key == 'f' || key == 'F') freeze = !freeze;
      if (freeze) noLoop();
      else loop();
   }

   public static void main(String[] args)
   {
      PApplet.main(FormMorph.class.getCanonicalName());
   }
}
