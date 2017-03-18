package com.tomstoneberg.processing.custom.lsystem;

public class AbopB extends LSystem
{
   public AbopB(int generations)
   {
      this.axiom = "F-F-F-F";
      this.productions.put("F", "FF-F-F-F-FF");

      this.generations = generations;
      this.theta = 90f;
   }

   public AbopB()
   {
      this(2);
   }
}
