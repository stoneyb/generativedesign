package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PShape;

public class AnimatedBrushv2 extends Template
{

   int col = color(181, 157, 0, 100);
   float lineModuleSize = 0;
   float angle = 0;
   float angleSpeed = 1;
   PShape lineModule = null;

   int clickPosX = 0;
   int clickPosY = 0;

   @Override
   public void settings()
   {
      size(displayWidth, displayHeight);
   }

   @Override
   public void setup()
   {
      background(255);
      smooth();
      cursor(CROSS);
   }

   @Override
   public void doDraw()
   {
      int x = mouseX;
      int y = mouseY;

      if(mousePressed)
      {
         if(keyPressed && keyCode == SHIFT)
         {
            if(abs(clickPosX-x) > abs(clickPosY-y)) y = clickPosY;
            else x = clickPosX;
         }
      }

      strokeWeight(0.75f);
      noFill();
      stroke(col);
      pushMatrix();
      translate(x, y);
      rotate(radians(angle));
      if(lineModule != null)
      {
         shape(lineModule, 0, 0, lineModuleSize, lineModuleSize);
      } else
      {
         line(0, 0, lineModuleSize, lineModuleSize);
      }
      angle = angle + angleSpeed;
      popMatrix();
   }

   @Override
   public void mousePressed()
   {
      // create a new random color and line length
      lineModuleSize = random(50, 160);

      // remember click position
      clickPosX = mouseX;
      clickPosY = mouseY;
   }


   @Override
   public void keyReleased()
   {
      super.keyReleased();

      // reverse direction and mirrow angle
      if (key=='d' || key=='D')
      {
         angle = angle + 180;
         angleSpeed = angleSpeed * -1;
      }

      // r g b alpha
      if (key == ' ') col = color(random(255),random(255),random(255),random(80,150));

      //default colors from 1 to 4
      if (key == '1') col = color(181,157,0,100);
      if (key == '2') col = color(0,130,164,100);
      if (key == '3') col = color(87,35,129,100);
      if (key == '4') col = color(197,0,123,100);

      // load svg for line module
      if (key=='5') lineModule = null;
      if (key=='6') lineModule = loadShape("P_2_3_1_02_data/02.svg");
      if (key=='7') lineModule = loadShape("P_2_3_1_02_data/03.svg");
      if (key=='8') lineModule = loadShape("P_2_3_1_02_data/04.svg");
      if (key=='9') lineModule = loadShape("P_2_3_1_02_data/05.svg");
      if (lineModule != null)
      {
         lineModule.disableStyle();
      }
   }

   public static void main(String[] args)
   {
      PApplet.main(AnimatedBrushv2.class.getCanonicalName());
   }
}
