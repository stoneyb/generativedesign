package com.tomstoneberg.processing.p3;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PFont;

public class TextAnalysisv4 extends Template
{

   PFont font;
   String[] lines;
   String joinedText;

   String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ,.;:!? ";
   int[] counters = new int[alphabet.length()];
   boolean[] drawLetters = new boolean[alphabet.length()];

   float charSize;
   int charColor = 0;
   int posX = 20;
   int posY = 50;

   boolean drawGreyLines = false;
   boolean drawColoredLines = true;
   boolean drawText = true;

   @Override
   public void settings()
   {
      size(1200, 800);
      pixelDensity(displayDensity());
   }

   @Override
   public void setup()
   {
      lines = loadStrings("P_3_1_3_03_data/preamble.txt");
      joinedText = join(lines, "");
      font = createFont("Courier", 10);

      for (int i = 0; i < alphabet.length(); i++)
      {
         drawLetters[i] = true;
      }
      countCharacters();
   }

   @Override
   public void doDraw()
   {
      colorMode(HSB, 360, 100, 100, 100);
      textFont(font);
      background(360);
      noStroke();
      fill(0);
      smooth();

      translate(50, 0);

      posX = 0;
      posY = 200;
      float[] sortPositionsX = new float[alphabet.length()];
      float[] oldPositionsX = new float[alphabet.length()];
      float[] oldPositionsY = new float[alphabet.length()];
      float oldX = 0;
      float oldY = 0;

      // draw counters
      if (mouseX >= width - 50)
      {
         textSize(10);
         for (int i = 0; i < alphabet.length(); i++)
         {
            textAlign(LEFT);
            text(alphabet.charAt(i), -15, i * 20 + 40);
            textAlign(RIGHT);
            text(counters[i], -20, i * 20 + 40);
         }
      }

         textAlign(LEFT);
         textSize(18);

         for (int i = 0; i < joinedText.length(); i++)
         {
            String s = str(joinedText.charAt(i)).toUpperCase();
            char uppercaseChar = s.charAt(0);
            int index = alphabet.indexOf(uppercaseChar);
            if (index < 0) continue;

            float m = map(mouseX, 50, width - 50, 0, 1);
            m = constrain(m, 0, 1);

            float sortX = sortPositionsX[index];
            float interX = lerp(posX, sortX, m);

            float sortY = index * 20 + 40;
            float interY = lerp(posY, sortY, m);

            if (drawLetters[index])
            {
               if (drawGreyLines)
               {
                  if (oldX != 0 && oldY != 0)
                  {
                     stroke(0, 10);
                     line(oldX, oldY, interX, interY);
                  }
                  oldX = interX;
                  oldY = interY;
               }

               if (drawColoredLines)
               {
                  if (oldPositionsX[index] != 0 && oldPositionsY[index] != 0)
                  {
                     stroke(index * 10, 80, 60, 50);
                     line(oldPositionsX[index], oldPositionsY[index], interX, interY);
                  }
                  oldPositionsX[index] = interX;
                  oldPositionsY[index] = interY;
               }

               if (drawText)
               {
                  text(joinedText.charAt(i), interX, interY);
               }
            } else
            {
               oldX = 0;
               oldY = 0;
            }

            sortPositionsX[index] += textWidth(joinedText.charAt(i));
            posX += textWidth(joinedText.charAt(i));
            if (posX >= 200 && uppercaseChar == ' ')
            {
               posY += 40;
               posX = 0;
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
   public void keyReleased()
   {
      super.keyReleased();
      if (key == '1') drawGreyLines = !drawGreyLines;
      if (key == '2') drawColoredLines = !drawColoredLines;
      if (key == '3') drawText = !drawText;

      if (key == '4') {
         for (int i = 0; i < alphabet.length(); i++) {
            drawLetters[i] = false;
         }
      }
      if (key == '5') {
         for (int i = 0; i < alphabet.length(); i++) {
            drawLetters[i] = true;
         }
      }
      String s = str(key).toUpperCase();
      char uppercaseKey = s.charAt(0);
      int index = alphabet.indexOf(uppercaseKey);
      if (index >= 0) {
         drawLetters[index] = !drawLetters[index];
      }
   }

   public static void main(String[] args)
   {
      PApplet.main(TextAnalysisv4.class.getCanonicalName());
   }
}
