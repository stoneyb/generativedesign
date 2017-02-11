package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PFont;

public class Text extends Template
{
   float x = 0, y = 0;
   float stepSize = 5.0f;

   PFont font;
   String letters = "The quick brown fox jumps over the lazy dog";
   int fontSizeMin = 3;
   float angleDistortion = 0.0f;

   int counter = 0;


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

      x = mouseX;
      y = mouseY;

      font = createFont("American Typewriter", 10);
      textFont(font, fontSizeMin);
      textAlign(LEFT);
      fill(0);
   }

   @Override
   public void doDraw()
   {
      if(mousePressed)
      {
         float d = dist(x, y, mouseX, mouseY);
         textFont(font, fontSizeMin + d / 2);
         char newLetter = letters.charAt(counter);
         stepSize = textWidth(newLetter);

         if(d > stepSize)
         {
            float angle = atan2(mouseY - y, mouseX - x);

            pushMatrix();
            translate(x, y);
            rotate(angle + random(angleDistortion));
            text(newLetter, 0, 0);
            popMatrix();

            counter++;
            if(counter > letters.length() - 1) counter = 0;

            x = x + cos(angle) * stepSize;
            y = y + sin(angle) * stepSize;
         }
      }
   }

   @Override
   public void mousePressed()
   {
      x = mouseX;
      y = mouseY;
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
      // angleDistortion ctrls arrowkeys up/down
      if (keyCode == UP) angleDistortion += 0.1;
      if (keyCode == DOWN) angleDistortion -= 0.1;
   }

   public static void main(String[] args)
   {
      PApplet.main(Text.class.getCanonicalName());
   }
}
