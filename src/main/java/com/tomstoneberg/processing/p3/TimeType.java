package com.tomstoneberg.processing.p3;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PFont;

public class TimeType extends Template
{
   String textTyped = "Type slow and fast!";

   float[] fontSizes = new float[textTyped.length()];
   float minFontSize = 15;
   float maxFontSize = 800;
   float newFontSize = 0;

   int pMillis;
   float maxTimeDelta = 5000.0f;

   float spacing = 2;
   float tracking = 0;
   PFont font;

   @Override
   public void settings()
   {
      size(800, 800);
   }

   @Override
   public void setup()
   {
      surface.setResizable(true);
      font = createFont("Arial", 10);

      smooth();
      noCursor();

      // init fontSizes
      for (int i = 0; i < textTyped.length(); i++)
      {
         fontSizes[i] = minFontSize;
      }

      pMillis = millis();
   }

   @Override
   public void doDraw()
   {
      background(255);
      textAlign(LEFT);
      fill(0);
      noStroke();

      spacing = map(mouseY, 0, height, 0, 120);
      translate(0, 200 + spacing);

      float x = 0, y = 0, fontSize = 20;

      for (int i = 0; i < textTyped.length(); i++)
      {
         // get fontszie for the actual letter from the array
         fontSize = fontSizes[i];
         textFont(font, fontSize);
         char letter = textTyped.charAt(i);
         float letterWidth = textWidth(letter) + tracking;

         if (x + letterWidth > width)
         {
            // new line
            x = 0;
            y += spacing;
         }

         // draw letter
         text(letter, x, y);
         x += letterWidth;
      }

      // blinking cursor after text
      float timeDelta = millis() - pMillis;
      newFontSize = map(timeDelta, 0, maxTimeDelta, minFontSize, maxFontSize);
      newFontSize = min(newFontSize, maxFontSize);

//      fill(200, 30, 40);
//      if (frameCount / 10 % 2 == 0) fill(255);
//      rect(x, y, newFontSize / 2, newFontSize / 20);

   }

   @Override
   public void keyPressed()
   {
      if (key != CODED)
      {
         switch (key)
         {
            case DELETE:
            case BACKSPACE:
               if (textTyped.length() > 0)
               {
                  textTyped = textTyped.substring(0, max(0, textTyped.length() - 1));
                  fontSizes = shorten(fontSizes);
               }
               break;
            // disable those keys
            case TAB:
            case ENTER:
            case RETURN:
            case ESC:
               break;
            default:
               textTyped += key;
               fontSizes = append(fontSizes, newFontSize);
         }

         // reset timer
         pMillis = millis();
      }
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
      if (keyCode == CONTROL) saveFrame(getFilename("png"));
   }

   public static void main(String[] args)
   {
      PApplet.main(TimeType.class.getCanonicalName());
   }
}
