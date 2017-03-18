package com.tomstoneberg.processing.custom.lsystem;

public class AbopD extends LSystem
{
   public AbopD(int generations)
   {
      this.axiom = "F-F-F-F";
      this.productions.put("F", "FF-F--F-F");

      this.generations = generations;
      this.theta = 90f;
   }

   public AbopD()
   {
      this(2);
   }
}
