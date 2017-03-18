package com.tomstoneberg.processing.p3;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PFont;

public class TextAnalysisv2 extends Template
{
   private PFont font;
   private String[] lines;
   String joinedText;

   String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜß,.;:!? ";
   int[] counters = new int[alphabet.length()];
   boolean[] drawLetters = new boolean[alphabet.length()];

   float charSize;
   int charColor = 0;
   int posX = 20;
   int posY = 50;

   boolean drawLines = false;
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
      String[] lines = loadStrings("P_3_1_3_01_data/preamble.txt");
      joinedText = join(lines, " ");
      font = createFont("Courier", 10);

      for(int i = 0; i < alphabet.length(); i++)
      {
         drawLetters[i] = true;
      }

      countCharacters();
   }

   @Override
   public void doDraw()
   {
      textFont(font);
      background(255);
      noStroke();
      smooth();

      posX = 20;
      posY = 200;
      float oldX = 0;
      float oldY = 0;

      // go through all characters in the text to draw them
      for(int i = 0; i < joinedText.length(); i++)
      {
         // again, find the index of the current letter in the alphabet
         String s = str(joinedText.charAt(i)).toUpperCase();
         char uppercaseChar = s.charAt(0);
         int index = alphabet.indexOf(uppercaseChar);
         if(index < 0) continue;

         fill(87, 35, 129);
         textSize(18);

         float sortY = index * 20 + 40;
         float m = map(mouseX, 50, width - 50, 0, 1);
         m = constrain(m, 0, 1);
         float interY = lerp(posY, sortY, m);

         if(drawLetters[index])
         {
            if(drawLines)
            {
               if(oldX != 0 && oldY != 0)
               {
                  stroke(181, 157, 0, 100);
                  line(oldX, oldY, posX, interY);
               }
               oldX = posX;
               oldY = interY;
            }

            if(drawText)
            {
               text(joinedText.charAt(i), posX, interY);
            }
         } else
         {
            oldX = 0;
            oldY = 0;
         }

         posX += textWidth(joinedText.charAt(i));
         if(posX >= width - 200 && uppercaseChar == ' ')
         {
            posY += 40;
            posX = 20;
         }
      }
   }

   private void countCharacters()
   {
      for(int i = 0; i < joinedText.length(); i++)
      {
         int idx = alphabet.indexOf(joinedText.substring(i, i+1).toUpperCase());
         if(idx >= 0)
         {
            counters[idx]++;
         }


      }
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
      if (key == '1') drawLines = !drawLines;
      if (key == '2') drawText = !drawText;
   }

   public static void main(String[] args)
   {
      PApplet.main(TextAnalysisv2.class.getCanonicalName());
   }
}
