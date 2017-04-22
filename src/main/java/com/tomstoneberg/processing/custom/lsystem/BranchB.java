package com.tomstoneberg.processing.custom.lsystem;

public class BranchB extends LSystem
{
   public BranchB(int generations)
   {
      this.axiom = "F";
      this.productions.put("F", "F[+F]F[-F][F]");

      this.generations = generations;
      this.theta = 20f;
   }
}
