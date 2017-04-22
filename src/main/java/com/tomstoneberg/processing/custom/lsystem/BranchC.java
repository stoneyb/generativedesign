package com.tomstoneberg.processing.custom.lsystem;

public class BranchC extends LSystem
{
   public BranchC(int generations)
   {
      this.axiom = "F";
      this.productions.put("F", "FF-[-F+F+F]+[+F-F-F]");

      this.generations = generations;
      this.theta = 22.5f;
   }
}
