package com.tomstoneberg.processing.custom.lsystem;

public class BranchD extends LSystem
{
   public BranchD(int generations)
   {
      this.axiom = "X";
      this.productions.put("X", "F[+X]F[-X]+X");
      this.productions.put("F", "FF");

      this.generations = generations;
      this.theta = 20f;
   }
}
