package com.tomstoneberg.processing.p3;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PFont;

public class TextAnalysisv3 extends Template
{
   PFont font;
   String[] lines;
   String joinedText;

   String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ,.;:!? ";
   int[] counters = new int[alphabet.length()];

   int posX, posY;
   int tracking = 29;

   int actRandomSeed = 0;

   boolean drawAlpha = true;
   boolean drawLines = true;
   boolean drawEllispses = true;
   boolean drawText = false;

   @Override
   public void settings()
   {
      size(1400, 800);
      pixelDensity(displayDensity());
   }

   @Override
   public void setup()
   {
      lines = loadStrings("P_3_1_3_03_data/preamble.txt");
      joinedText = join(lines, "");
      font = createFont("Courier", 10);
      countCharacters();
   }

   @Override
   public void doDraw()
   {
      colorMode(HSB, 360, 100, 100, 100);
      textFont(font);
      background(360);
      noStroke();
      smooth();
      textSize(20);
      posX = 80;
      posY = 300;
      randomSeed(actRandomSeed);

      // draw lines and ellipses
      for (int i = 0; i < joinedText.length(); i++)
      {
         String s = str(joinedText.charAt(i)).toUpperCase();
         char uppercaseChar = s.charAt(0);
         int index = alphabet.indexOf(uppercaseChar);
         if (index < 0) continue;

         float charAlpha = 100;
         if (drawAlpha) charAlpha = counters[index];

         float my = map(mouseY, 50, height - 50, 0, 1);
         float charSize = counters[index] * my * 3;
         float mx = map(mouseX, 50, width - 50, 0, 1);
         mx = constrain(mx, 0, 1);
         float lineLength = charSize;
         float lineAngle = random(-PI, PI) * mx - HALF_PI;
         float newPosX = lineLength * cos(lineAngle);
         float newPosY = lineLength * sin(lineAngle);

         pushMatrix();
         translate(posX, posY);
         stroke(273, 73, 51, charAlpha);
         if (drawLines) line(0, 0, newPosX, newPosY);
         noStroke();
         fill(52, 100, 71, charAlpha);
         if (drawEllispses) ellipse(0, 0, charSize / 10, charSize / 10);
         popMatrix();

         posX += textWidth(joinedText.charAt(i));
         if (posX >= width && uppercaseChar == ' ')
         {
            posY += (int) (tracking * my + 30);
            posX = 80;
         }
      }

      // draw letters
      if (drawText)
      {
         posX = 80;
         posY = 300;
         randomSeed(actRandomSeed);

         for (int i = 0; i < joinedText.length(); i++)
         {
            // again, find the index of the current letter in the alphabet
            String s = str(joinedText.charAt(i)).toUpperCase();
            char uppercaseChar = s.charAt(0);
            int index = alphabet.indexOf(uppercaseChar);
            if (index < 0) continue;

            // ------ calculate parameters ------
            float charAlpha = 100;
            if (drawAlpha) charAlpha = counters[index];

            float my = map(mouseY, 50, height - 50, 0, 1);
            my = constrain(my, 0, 1);
            float charSize = counters[index] * my * 3;

            float mx = map(mouseX, 50, width - 50, 0, 1);
            mx = constrain(mx, 0, 1);
            float lineLength = charSize;
            float lineAngle = random(-PI, PI) * mx - HALF_PI;
            float newPosX = lineLength * cos(lineAngle);
            float newPosY = lineLength * sin(lineAngle);

            // ------ draw elements ------
            pushMatrix();
            translate(posX, posY);
            fill(0, charAlpha);
            text(joinedText.charAt(i), newPosX, newPosY);
            popMatrix();

            posX += textWidth(joinedText.charAt(i));
            if (posX >= width - 200 && uppercaseChar == ' ')
            {
               posY += (int) (tracking * my + 30);
               posX = 80;
            }
         }
      }
   }

   private void countCharacters()
   {
      for (int i = 0; i < joinedText.length(); i++)
      {
         // get one char from the text, convert it to a string and turn it to uppercase
         String s = str(joinedText.charAt(i)).toUpperCase();
         // convert it back to a char
         char uppercaseChar = s.charAt(0);
         // get the position of this char inside the alphabet string
         int index = alphabet.indexOf(uppercaseChar);
         // increase the respective counter
         if (index >= 0) counters[index]++;
      }
   }

   @Override
   public void mousePressed()
   {
      actRandomSeed = (int) random(100000);
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
      if (key == '1') drawAlpha = !drawAlpha;
      if (key == '2') drawLines = !drawLines;
      if (key == '3') drawEllispses = !drawEllispses;
      if (key == '4') drawText = !drawText;
   }

   public static void main(String[] args)
   {
      PApplet.main(TextAnalysisv3.class.getCanonicalName());
   }
}
