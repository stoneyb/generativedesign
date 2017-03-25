package com.tomstoneberg.processing.custom.lsystem;

public class SierpinskiGasket extends LSystem
{
   public SierpinskiGasket(int generations)
   {
      this.axiom = "YF";
      this.productions.put("X", "YF+X+YF");
      this.productions.put("Y", "FX-Y-FX");

      this.generations = generations;
      this.theta = 60f;
   }
}
