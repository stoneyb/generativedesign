package com.tomstoneberg.processing.custom.lsystem;

public class SierpinskiGasket extends LSystem
{
   public SierpinskiGasket(int generations)
   {
      this.axiom = "B";
      this.productions.put("A", "B+A+B");
      this.productions.put("B", "A-B-A");

      this.generations = generations;
      this.theta = 60f;

      this.closed = false;
   }
}
