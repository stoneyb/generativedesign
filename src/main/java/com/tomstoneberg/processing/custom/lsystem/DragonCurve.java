package com.tomstoneberg.processing.custom.lsystem;

public class DragonCurve extends LSystem
{
   public DragonCurve(int generations)
   {
      this.axiom = "F";
      this.productions.put("F", "F+G+");
      this.productions.put("G", "-F-G");

      this.generations = generations;
      this.theta = 90f;
   }
}
