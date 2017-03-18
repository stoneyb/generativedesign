package com.tomstoneberg.processing.custom.lsystem;

public class AbopA extends LSystem
{
   public AbopA(int generations)
   {
      this.axiom = "F-F-F-F";
      this.productions.put("F", "FF-F-F-F-F-F+F");

      this.generations = generations;
      this.theta = 90f;
   }

   public AbopA()
   {
      this(2);
   }
}
