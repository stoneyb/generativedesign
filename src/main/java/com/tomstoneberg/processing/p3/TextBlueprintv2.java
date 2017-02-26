package com.tomstoneberg.processing.p3;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PShape;

public class TextBlueprintv2 extends Template
{

   PFont font;
   String textTyped = "Lprem Ipsum is simply dummy text of the printing and typesetting industry.\n" +
         "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,\n" +
         "when an unknown printer took a galley of type and scrambled it to make a type specimen book.\n" +
         "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\n" +
         "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and\n" +
         "more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

   PShape shapeSpace, shapeSpace2, shapePeriod, shapeComma, shapeExclamationmark;
   PShape shapeQuestionmark, shapeReturn,  icon1, icon2, icon3, icon4, icon5;

   int centerX = 0, centerY = 0, offsetX = 0, offsetY = 0;
   float zoom = 0.75f;

   int[] palette =
      {
         color(253, 195, 0), color(0), color(0, 158, 224), color(99, 33, 129),
         color(121, 156, 19), color(226, 0, 26), color(224, 134, 178)
      };
   int actColorIndex = 0;

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

      font = createFont("P_3_1_2_02_data/miso-bold.ttf", 10);
      shapeSpace = loadShape("P_3_1_2_02_data/space.svg");
      shapeSpace2 = loadShape("P_3_1_2_02_data/space2.svg");
      shapePeriod = loadShape("P_3_1_2_02_data/period.svg");
      shapeComma = loadShape("P_3_1_2_02_data/comma.svg");
      shapeExclamationmark = loadShape("P_3_1_2_02_data/exclamationmark.svg");
      shapeQuestionmark = loadShape("P_3_1_2_02_data/questionmark.svg");
      shapeReturn = loadShape("P_3_1_2_02_data/return.svg");
      icon1 = loadShape("P_3_1_2_02_data/icon1.svg");
      icon2 = loadShape("P_3_1_2_02_data/icon2.svg");
      icon3 = loadShape("P_3_1_2_02_data/icon3.svg");
      icon4 = loadShape("P_3_1_2_02_data/icon4.svg");
      icon5 = loadShape("P_3_1_2_02_data/icon5.svg");

      shapeSpace.disableStyle();
      shapeSpace2.disableStyle();
      shapePeriod.disableStyle();
      shapeComma.disableStyle();
      shapeExclamationmark.disableStyle();
      shapeQuestionmark.disableStyle();
      shapeReturn.disableStyle();

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

      translate(centerX, centerY);
      scale(zoom);

      pushMatrix();

      randomSeed(0);

      actColorIndex = 0;
      fill(palette[actColorIndex]);
      rect(0, -25, 10, 35);

      for(int i = 0; i < textTyped.length(); i++)
      {
         textFont(font, 25);
         char letter = textTyped.charAt(i);
         float letterWidth = textWidth(letter);

         switch(letter)
         {
            case ' ':
               int dir = floor(random(0, 5));
               if(dir == 0)
               {
                  shape(shapeSpace, 0, 0);
                  translate(1.9f, 0);
                  rotate(PI / 4);
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
               rotate(PI/4);
               break;

            case '.':
               shape(shapePeriod, 0, 0);
               translate(56, -54);
               rotate(-PI/2);
               break;

            case '!':
               shape(shapeExclamationmark, 0, 0);
               translate(42, -17.4f);
               rotate(-PI/4);
               break;

            case '?':
               shape(shapeQuestionmark, 0, 0);
               translate(42, -18);
               rotate(-PI/4);
               break;

            case '\n':
               rect(0, -25, 10, 35);
               popMatrix();
               pushMatrix();
               translate(random(-300, 300), random(-300, 300));
               rotate(floor(random(8)) * PI / 4);
               actColorIndex = (actColorIndex + 1) % palette.length;
               fill(palette[actColorIndex]);
               rect(0, -25, 10, 35);
               break;

            case 'o': // Station big
               rect(0,0-15,letterWidth+1,15);
               pushStyle();
               fill(0);
               String station = textTyped.substring(i-10,i-1);
               station = station.toLowerCase();
               station = station.replaceAll(" ", "");
               station = station.substring(0,1).toUpperCase() + station.substring(1,station.length()-1);
               text(station,-10,40);

               ellipse(-5,-7,33,33);
               fill(255);
               ellipse(-5,-7,25,25);
               popStyle();
               translate(letterWidth, 0);
               break;

            case 'a': // Station small left
               rect(0,0-15,letterWidth+1,25);
               rect(0,0-15,letterWidth+1,15);
               translate(letterWidth, 0);
               break;

            case 'u': // Station small right
               rect(0,0-25,letterWidth+1,25);
               rect(0,0-15,letterWidth+1,15);
               translate(letterWidth, 0);
               break;


            case ':': // icon
               shape(icon1,0,-60,30,30);
               break;

            case '+': // icon
               shape(icon2,0,-60,35,30);
               break;

            case '-': // icon
               shape(icon3,0,-60,30,30);
               break;

            case 'x': // icon
               shape(icon4,0,-60,30,30);
               break;

            case 'z': // icon
               shape(icon5,0,-60,30,30);
               break;

            default: // all others
               //text(letter, 0, 0);

               rect(0,0-15,letterWidth+1,15);
               translate(letterWidth, 0);
               //  rotate(-0.05);

         }
      }

      popMatrix();
   }

   @Override
   public void mousePressed()
   {
      offsetX = mouseX - centerX;
      offsetY = mouseY - centerY;
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
   }

   public static void main(String[] args)
   {
      PApplet.main(TextBlueprintv2.class.getCanonicalName());
   }
}
