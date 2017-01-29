package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class FormMorphv2 extends Template
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
   int mode = 0;

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
      centerY = height / 2;
      float angle = radians(360 / (float)formResolution);
      for(int i = 0; i < formResolution; i++)
      {
         x[i] = cos(angle * i) * initRadius;
         y[i] = sin(angle * i) * initRadius;
      }

      stroke(55, 21, 74, 50);
      background(255);
   }

   @Override
   public void doDraw()
   {
      // floating towards mouse position
      if(mouseX != 0 || mouseY != 0)
      {
         centerX += (mouseX - centerX) * 0.1;
         centerY += (mouseY - centerY) * 0.1;
      }

      // calculate new points
      for(int i = 0; i < formResolution; i++)
      {
         x[i] += random(-stepSize, stepSize);
         y[i] += random(-stepSize, stepSize);
         // ellipse(x[i] + centerX, y[i] + centerY, 5, 5);
      }

      strokeWeight(0.75f);
      if(filled) fill(random(255));
      else noFill();

      if(mode == 0)
      {
         beginShape();
         // start controlpoint
         curveVertex(x[formResolution-1] + centerX, y[formResolution -1] + centerY);
         // only these points are drawn
         for(int i = 0; i < formResolution; i++)
         {
            curveVertex(x[i] + centerX, y[i] + centerY);
         }
         curveVertex(x[0] + centerX, y[0] + centerY);

         // end controlpoint
         curveVertex(x[1] + centerX, y[1] + centerY);
         endShape();
      }

      if(mode == 1)
      {
         beginShape();
         // start controlpoint
         curveVertex(x[0]+centerX, y[0]+centerY);

         // only these points are drawn
         for (int i=0; i<formResolution; i++)
         {
            curveVertex(x[i]+centerX, y[i]+centerY);
         }

         // end controlpoint
         curveVertex(x[formResolution-1]+centerX, y[formResolution-1]+centerY);
         endShape();
      }
   }

   @Override
   public void mousePressed()
   {
      // init forms on mouse position
      centerX = mouseX;
      centerY = mouseY;

      // circle
      if(mode == 0)
      {
         centerX = mouseX;
         centerY = mouseY;
         float angle = radians(360 / (float)formResolution);
         float radius = initRadius * random(0.5f, 1.0f);
         for (int i=0; i<formResolution; i++)
         {
            x[i] = cos(angle*i) * radius;
            y[i] = sin(angle*i) * radius;
         }
      }

      // line
      if (mode == 1)
      {
         centerX = mouseX;
         centerY = mouseY;
         float radius = initRadius*4;
         float angle = 0;

         float x1 = cos(angle) * radius;
         float y1 = sin(angle) * radius;
         float x2 = cos(angle-PI) * radius;
         float y2 = sin(angle-PI) * radius;
         for(int i=0; i<formResolution; i++)
         {
            x[i] = lerp(x1, x2, i / (float)formResolution);
            y[i] = lerp(y1, y2, i / (float)formResolution);
         }
      }
   }

   @Override
   public void keyPressed()
   {
      if (keyCode == UP) stepSize++;
      if (keyCode == DOWN) stepSize--;
      stepSize = max(stepSize, 1);
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();

      if (key == '1') filled = false;
      if (key == '2') filled = true;
      if (key == '3') mode = 0;
      if (key == '4') mode = 1;

      // switch draw loop on/off
      if (key == 'f' || key == 'F') freeze = !freeze;
      if (freeze) noLoop();
      else loop();
   }

   public static void main(String[] args)
   {
      PApplet.main(FormMorphv2.class.getCanonicalName());
   }
}
