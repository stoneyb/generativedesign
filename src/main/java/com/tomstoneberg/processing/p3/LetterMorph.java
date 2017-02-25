package com.tomstoneberg.processing.p3;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PFont;

public class LetterMorph extends Template
{

   PFont font;
   String letter = "A";

   @Override
   public void settings()
   {
      size(800, 800);
   }

   @Override
   public void setup()
   {
      background(255);
      fill(0);

      font = createFont("Arial", 12);
      textFont(font);
      textAlign(CENTER);
   }

   @Override
   public void doDraw()
   {

   }

   @Override
   public void mouseMoved()
   {
      background(255);
      textSize((mouseX - width) * 5 + 1);
      text(letter, width / 2, mouseY);
   }

   @Override
   public void mouseDragged()
   {
      textSize((mouseX - width / 2) * 5 + 1);
      text(letter, width / 2, mouseY);
   }


   @Override
   public void keyReleased()
   {
      if (keyCode == CONTROL) saveFrame(getFilename("png"));

      if (key != CODED && (int)key > 32) letter = str(key);
   }

   public static void main(String[] args)
   {
      PApplet.main(LetterMorph.class.getCanonicalName());
   }
}
