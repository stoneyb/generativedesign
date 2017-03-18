package com.tomstoneberg.processing.custom.lsystem;

public class AbopF extends LSystem
{
   public AbopF(int generations)
   {
      this.axiom = "F-F-F-F";
      this.productions.put("F", "F-F+F-F-F");

      this.generations = generations;
      this.theta = 90f;
   }

   public AbopF()
   {
      this(2);
   }
}
