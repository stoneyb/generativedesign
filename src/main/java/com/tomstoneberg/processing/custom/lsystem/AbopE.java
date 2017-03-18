package com.tomstoneberg.processing.custom.lsystem;

public class AbopE extends LSystem
{
   public AbopE(int generations)
   {
      this.axiom = "F-F-F-F";
      this.productions.put("F", "F-FF--F-F");

      this.generations = generations;
      this.theta = 90f;
   }

   public AbopE()
   {
      this(2);
   }
}
