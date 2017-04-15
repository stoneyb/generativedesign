package com.tomstoneberg.processing.p4;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PImage;

public class Cutout extends Template
{
   PImage img;

   int tileCountX = 4;
   int tileCountY = 4;
   int tileCount = tileCountX * tileCountY;
   PImage[] imageTiles = new PImage[tileCount];

   int tileWidth, tileHeight;

   int cropX;
   int cropY;

   boolean selectMode = true;
   boolean randomMode = false;

   @Override
   public void settings()
   {
      size(1600, 1200);
      pixelDensity(displayDensity());
   }

   @Override
   public void setup()
   {
      img = loadImage("P_4_1_1_01_data/image.jpg");
      image(img, 0, 0);
      noCursor();

      tileWidth = width / tileCountX;
      tileHeight = height / tileCountY;
   }

   @Override
   public void doDraw()
   {
      if (selectMode)
      {
         cropX = constrain(mouseX, 0, width - tileWidth);
         cropY = constrain(mouseY, 0, height - tileHeight);

         image(img, 0, 0);
         noFill();
         stroke(255);
         rect(cropX, cropY, tileWidth, tileHeight);
      } else
      {
         // reassemble
         int i = 0;
         for (int y = 0; y < tileCountY; y++)
         {
            for (int x = 0; x < tileCountX; x++)
            {
               image(imageTiles[i], x * tileWidth, y * tileHeight);
               i++;
            }
         }
      }
   }

   private void cropTiles()
   {
      tileWidth = width / tileCountY;
      tileHeight = height / tileCountX;
      tileCount = tileCountX * tileCountY;
      imageTiles = new PImage[tileCount];

      int i = 0;
      for (int gridY = 0; gridY < tileCountY; gridY++)
      {
         for (int gridX = 0; gridX < tileCountX; gridX++)
         {
            if (randomMode)
            {
               cropX = (int) random(mouseX - tileWidth / 2, mouseX + tileWidth / 2);
               cropY = (int) random(mouseY - tileHeight / 2, mouseY + tileHeight / 2);
            }
            cropX = constrain(cropX, 0, width - tileWidth);
            cropY = constrain(cropY, 0, height - tileHeight);
            imageTiles[i++] = img.get(cropX, cropY, tileWidth, tileHeight);
         }
      }
   }

   @Override
   public void mouseMoved()
   {
      selectMode = true;
   }

   @Override
   public void mouseReleased()
   {
      selectMode = false;
      cropTiles();
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
      if (key == 'r' || key == 'R')
      {
         randomMode = !randomMode;
         cropTiles();
      }

      if (key == '1')
      {
         tileCountY = 4;
         tileCountX = 4;
         cropTiles();
      }
      if (key == '2')
      {
         tileCountY = 10;
         tileCountX = 10;
         cropTiles();
      }
      if (key == '3')
      {
         tileCountY = 20;
         tileCountX = 20;
         cropTiles();
      }
   }

   public static void main(String[] args)
   {
      PApplet.main(Cutout.class.getCanonicalName());
   }
}
