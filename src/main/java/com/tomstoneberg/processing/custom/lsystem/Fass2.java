package com.tomstoneberg.processing.custom.lsystem;

public class Fass2 extends LSystem
{
   public Fass2(int generations)
   {
      this.axiom = "-B";
      this.productions.put("A", "AA-B-B+A+A-B-BA+B+AAB-A+B+AA+B-AB-B-A+A+BB-");
      this.productions.put("B", "+AA-B-B+A+AB+A-BB-A-B+ABB-A-BA+A+B-B-A+A+BB");

      this.generations = generations;
      this.theta = 90f;

      this.closed = false;
   }
}
