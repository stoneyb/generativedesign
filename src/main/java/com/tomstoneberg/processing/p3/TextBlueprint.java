package com.tomstoneberg.processing.p3;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PShape;

public class TextBlueprint extends Template
{
   PFont font;
   String textTyped = "";

   PShape shapeSpace, shapeSpace2, shapePeriod, shapeComma;
   PShape shapeQuestionmark, shapeExclamationmark, shapeReturn;

   int centerX = 0, centerY = 0, offsetX = 0, offsetY = 0;
   float zoom = 0.75f;

   int actRandomSeed = 6;

   @Override
   public void settings()
   {
      size(800, 800);
   }

   @Override
   public void setup()
   {
      surface.setResizable(true);

      centerX = width / 2;
      centerY = height / 2;

      font = createFont("P_3_1_2_01_data/miso-bold.tff", 10);

      shapeSpace = loadShape("P_3_1_2_01_data/space.svg");
      shapeSpace2 = loadShape("P_3_1_2_01_data/space2.svg");
      shapePeriod = loadShape("P_3_1_2_01_data/period.svg");
      shapeComma = loadShape("P_3_1_2_01_data/comma.svg");
      shapeExclamationmark = loadShape("P_3_1_2_01_data/exclamationmark.svg");
      shapeQuestionmark = loadShape("P_3_1_2_01_data/questionmark.svg");
      shapeReturn = loadShape("P_3_1_2_01_data/return.svg");

      cursor(HAND);
   }


   @Override
   public void doDraw()
   {
      background(255);
      smooth();
      noStroke();
      textAlign(LEFT);

      if(mousePressed)
      {
         centerX = mouseX - offsetX;
         centerY = mouseY - offsetY;
      }

      // always produce the same sequence of random numbers
      randomSeed(actRandomSeed);

      translate(centerX, centerY);
      scale(zoom);

      for(int i = 0; i < textTyped.length(); i++)
      {
         float fontSize = 25;
         textFont(font, fontSize);
         char letter = textTyped.charAt(i);
         float letterWidth = textWidth(letter);

         // letter rule table
         switch(letter)
         {
            case ' ':
               int dir = floor(random(0, 2));
               if(dir == 0)
               {
                  shape(shapeSpace, 0, 0);
                  translate(1.9f, 0);
                  rotate(-PI / 4);
               }
               if(dir == 1)
               {
                  shape(shapeSpace2, 0, 0);
                  translate(13, -5);
                  rotate(-PI / 4);
               }
               break;

            case ',':
               shape(shapeComma, 0, 0);
               translate(34, 15);
               rotate(PI / 4);
               break;

            case '.':
               shape(shapePeriod, 0, 0);
               translate(56, -54);
               rotate(-PI / 2);
               break;

            case '!':
               shape(shapeExclamationmark, 0, 0);
               translate(42, -17.4f);
               rotate(-PI / 4);
               break;

            case '?':
               shape(shapeQuestionmark, 0, 0);
               translate(42, -18);
               rotate(-PI / 4);
               break;

            case '\n':
               shape(shapeReturn, 0, 0);
               translate(0, 10);
               rotate(PI);
               break;

            default:
               fill(0);
               text(letter, 0, 0);
               translate(letterWidth, 0);

         }
      }
   }

   @Override
   public void mousePressed()
   {
      offsetX = mouseX-centerX;
      offsetY = mouseY-centerY;
   }

   @Override
   public void keyPressed()
   {
      if (key != CODED) {
         switch(key) {
            case DELETE:
            case BACKSPACE:
               textTyped = textTyped.substring(0,max(0,textTyped.length()-1));
               break;
            case TAB:
               break;
            case ENTER:
            case RETURN:
               // enable linebreaks
               textTyped += "\n";
               break;
            case ESC:
               break;
            default:
               textTyped += key;
         }
      }

      // zoom
      if (keyCode == UP) zoom += 0.05;
      if (keyCode == DOWN) zoom -= 0.05;
   }

   @Override
   public void keyReleased()
   {
      if (keyCode == CONTROL) saveFrame(getFilename("png"));
      if (keyCode == ALT) actRandomSeed++;
   }

   public static void main(String[] args)
   {
      PApplet.main(TextBlueprint.class.getCanonicalName());
   }
}
