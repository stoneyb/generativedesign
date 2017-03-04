package com.tomstoneberg.processing.p3;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PFont;

public class TextAnalysis extends Template
{
   PFont font;
   String joinedText;

   String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜß,.;:!? ";
   int[] counters = new int[alphabet.length()];

   float charSize;
   int charColor;
   int posX, posY;

   boolean drawAlpha = false;

   

   @Override
   public void settings()
   {
      size(670, 800);
   }

   @Override
   public void setup()
   {
      String[] lines = loadStrings("P_3_1_3_01_data/preamble.txt");
      joinedText = join(lines, " ");
      font = createFont("Courier", 10);

      countCharacters();
   }

   private void countCharacters()
   {
      for(int i = 0; i < joinedText.length(); i++)
      {
         char c = joinedText.charAt(i);
         String s = str(c);
         s = s.toUpperCase();
         char uppercaseChar = s.charAt(0);
         int index = alphabet.indexOf(uppercaseChar);
         if(index >= 0) counters[index]++;
      }
   }

   @Override
   public void doDraw()
   {
      textFont(font);
      background(255);
      noStroke();
      smooth();

      posX = 20;
      posY = 40;

      for(int i = 0; i < joinedText.length(); i++)
      {
         String s = str(joinedText.charAt(i)).toUpperCase();
         char uppercaseChar = s.charAt(0);
         int index = alphabet.indexOf(uppercaseChar);
         if(index < 0) continue;

         if(drawAlpha) fill(0, 166, 237, counters[index] * 3);
         else fill(0, 166, 237);
         textSize(18);

         float sortY = index * 20 + 40;
         float m = map(mouseX, 50, width - 50, 0, 1);
         m = constrain(m, 0, 1);
         float interY = lerp(posY, sortY, m);

         text(joinedText.charAt(i), posX, interY);

         posX += textWidth(joinedText.charAt(i));
         if(posX >= width - 200 && uppercaseChar == ' ')
         {
            posY += 30;
            posX = 20;
         }
      }
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
   }

   public static void main(String[] args)
   {
      PApplet.main(TextAnalysis.class.getCanonicalName());
   }
}
