package com.tomstoneberg.processing.custom.lsystem;

public class AbopC extends LSystem
{
   public AbopC(int generations)
   {
      this.axiom = "F-F-F-F";
      this.productions.put("F", "FF-F+F-F-FF");

      this.generations = generations;
      this.theta = 90f;
   }

   public AbopC()
   {
      this(2);
   }
}
