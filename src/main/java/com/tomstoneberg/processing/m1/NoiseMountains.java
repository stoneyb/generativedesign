package com.tomstoneberg.processing.m1;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;

public class NoiseMountains extends Template
{
   // ------ mesh ------
   int tileCount = 50;
   int zScale = 150;

   // ------ noise ------
   int noiseXRange = 10;
   int noiseYRange = 10;
   int octaves = 4;
   float falloff = 0.5f;

   // ------ mesh coloring ------
   int midColor, topColor, bottomColor;
   int strokeColor;
   float threshold = 0.30f;

   // ------ mouse interaction ------
   int offsetX = 0, offsetY = 0, clickX = 0, clickY = 0, zoom = -280;
   float rotationX = 0, rotationZ = 0, targetRotationX = -PI/3, targetRotationZ = 0, clickRotationX, clickRotationZ;

   // ------ image output ------
   int qualityFactor = 4;
   TileSaver tiler;
   boolean showStroke = true;

   @Override
   public void settings()
   {
      size(800, 800, P3D);
      pixelDensity(displayDensity());
   }

   @Override
   public void setup()
   {
      colorMode(HSB, 360, 100, 100);
      tiler = new TileSaver(this);
      cursor(CROSS);

      // colors
      topColor = color(0, 0, 100);
      midColor = color(191, 99, 63);
      bottomColor = color(0, 0, 0);

      strokeColor = color(0, 0, 0);
   }

   @Override
   public void doDraw()
   {
      if (tiler==null) return;
      tiler.pre();

      if (showStroke) stroke(strokeColor);
      else noStroke();

      background(0, 0, 100);
      lights();


      // ------ set view ------
      pushMatrix();
      translate(width*0.5f, height*0.5f, zoom);

      if (mousePressed && mouseButton==RIGHT) {
         offsetX = mouseX-clickX;
         offsetY = mouseY-clickY;
         targetRotationX = min(max(clickRotationX + offsetY/(float)width * TWO_PI, -HALF_PI), HALF_PI);
         targetRotationZ = clickRotationZ + offsetX/(float)height * TWO_PI;
      }
      rotationX += (targetRotationX-rotationX)*0.25;
      rotationZ += (targetRotationZ-rotationZ)*0.25;
      rotateX(-rotationX);
      rotateZ(-rotationZ);


      // ------ mesh noise ------
      if (mousePressed && mouseButton==LEFT) {
         noiseXRange = mouseX/10;
         noiseYRange = mouseY/10;
      }

      noiseDetail(octaves, falloff);
      float noiseYMax = 0;

      float tileSizeY = (float)height/tileCount;
      float noiseStepY = (float)noiseYRange/tileCount;

      for (int meshY=0; meshY<=tileCount; meshY++) {
         beginShape(TRIANGLE_STRIP);
         for (int meshX=0; meshX<=tileCount; meshX++) {

            float x = map(meshX, 0, tileCount, -width/2, width/2);
            float y = map(meshY, 0, tileCount, -height/2, height/2);

            float noiseX = map(meshX, 0, tileCount, 0, noiseXRange);
            float noiseY = map(meshY, 0, tileCount, 0, noiseYRange);
            float z1 = noise(noiseX, noiseY);
            float z2 = noise(noiseX, noiseY+noiseStepY);

            noiseYMax = max(noiseYMax, z1);
            int interColor;
            colorMode(RGB);
            if (z1 <= threshold) {
               float amount = map(z1, 0, threshold, 0.15f, 1);
               interColor = lerpColor(bottomColor, midColor, amount);
            }
            else {
               float amount = map(z1, threshold, noiseYMax, 0, 1);
               interColor = lerpColor(midColor, topColor, amount);
            }
            colorMode(HSB, 360, 100, 100);
            fill(interColor);

            vertex(x, y, z1*zScale);
            vertex(x, y+tileSizeY, z2*zScale);
         }
         endShape();
      }
      popMatrix();

      tiler.post();
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
   }

   public static void main(String[] args)
   {
      PApplet.main(NoiseMountains.class.getCanonicalName());
   }
}
