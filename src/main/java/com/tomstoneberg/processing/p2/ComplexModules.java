package com.tomstoneberg.processing.p2;

import com.tomstoneberg.processing.Template;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PShape;

public class ComplexModules extends Template
{

   PFont font;
   PShape[] modules;

   float tileSize = 30;
   int gridResolutionX, gridResolutionY;
   char[][] tiles;

   boolean drawGrid = true;
   boolean debugMode = false;

   @Override
   public void settings()
   {
      size(displayWidth, displayHeight);
   }

   @Override
   public void setup()
   {
      smooth();
      cursor(CROSS);
      font = createFont("sans-serif", 8);
      textFont(font, 8);
      textAlign(CENTER);

      gridResolutionX = round(width / tileSize) + 2;
      gridResolutionY = round(height / tileSize) + 2;
      tiles = new char[gridResolutionX][gridResolutionY];
      initTiles();

      // load svg modules
      modules = new PShape[16];
      for(int i = 0; i < modules.length; i++)
      {
         modules[i] = loadShape("P_2_3_6_01_data/" + nf(i, 2) + ".svg");
      }
   }

   @Override
   public void doDraw()
   {
      background(255);
      if(mousePressed && (mouseButton == LEFT))
      {
         setTile();
      }
      if(mousePressed && (mouseButton == RIGHT))
      {
         unsetTile();
      }

      if(drawGrid) drawGrid();
      drawModules();
   }

   private void initTiles()
   {
      for(int gridY = 0; gridY < gridResolutionY; gridY++)
      {
         for(int gridX = 0; gridX < gridResolutionX; gridX++)
         {
            tiles[gridX][gridY] = '0';
         }
      }
   }

   private void setTile()
   {
      // convert mouse position to grid coordinates
      int gridX = floor((float) mouseX / tileSize) + 1;
      gridX = constrain(gridX, 1, gridResolutionX - 2);
      int gridY = floor((float) mouseY / tileSize) + 1;
      gridY = constrain(gridY, 1, gridResolutionY - 2);
      tiles[gridX][gridY] = '1';
   }

   private void unsetTile()
   {
      int gridX = floor((float) mouseX / tileSize) + 1;
      gridX = constrain(gridX, 1, gridResolutionX - 2);
      int gridY = floor((float) mouseY / tileSize) + 1;
      gridY = constrain(gridY, 1, gridResolutionY - 2);
      tiles[gridX][gridY] = '0';
   }

   private void drawGrid()
   {
      rectMode(CENTER);
      for(int gridY = 0; gridY < gridResolutionY; gridY++)
      {
         for(int gridX = 0; gridX < gridResolutionX; gridX++)
         {
            float posX = tileSize * gridX - tileSize / 2;
            float posY = tileSize * gridY - tileSize / 2;
            strokeWeight(0.15f);
            fill(255);
            if(debugMode)
            {
               if(tiles[gridX][gridY] == '1') fill(220);
            }
            rect(posX, posY, tileSize, tileSize);
         }
      }
   }

   private void drawModules()
   {
      shapeMode(CENTER);
      for(int gridY = 0; gridY < gridResolutionY; gridY++)
      {
         for (int gridX = 0; gridX < gridResolutionX; gridX++)
         {
            // use only active tiles
            if(tiles[gridX][gridY] == '1')
            {
               String east = str(tiles[gridX + 1][gridY]);
               String south = str(tiles[gridX][gridY + 1]);
               String west = str(tiles[gridX - 1][gridY]);
               String north = str(tiles[gridX][gridY - 1]);
               // create a binary string to a deciaml value from 0-15
               String binaryResult = north + west + south + east;
               int decimalResult = unbinary(binaryResult);

               float posX = tileSize * gridX - tileSize / 2;
               float posY = tileSize * gridY - tileSize / 2;

               shape(modules[decimalResult], posX, posY, tileSize, tileSize);

               if(debugMode)
               {
                  fill(150);
                  text(decimalResult + "\n" + binaryResult, posX, posY);
               }
            }
         }
      }
   }

   @Override
   public void keyReleased()
   {
      super.keyReleased();
      if (key == DELETE || key == BACKSPACE) initTiles();
      if (key == 'g' || key == 'G') drawGrid = !drawGrid;
      if (key == 'd' || key == 'D') debugMode = !debugMode;
   }

   public static void main(String[] args)
   {
      PApplet.main(ComplexModules.class.getCanonicalName());
   }
}
