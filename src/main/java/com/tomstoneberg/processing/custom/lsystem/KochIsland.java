package com.tomstoneberg.processing.custom.lsystem;

public class KochIsland extends LSystem
{
   public KochIsland(int generations)
   {
      this.axiom = "F-F-F-F";
      this.productions.put("F", "F-F+F+FF-F-F+F");

      this.generations = generations;
      this.theta = 90f;
   }

   public KochIsland()
   {
      this(2);
   }
}
