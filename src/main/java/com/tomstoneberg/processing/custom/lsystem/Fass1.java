package com.tomstoneberg.processing.custom.lsystem;

/**
 *  The curves included in Figure 1.11 belong to the class of FASS
 * construction curves (an acronym for space-filling, self-avoiding,
 * simple and selfsimilar)
*/
public class Fass1 extends LSystem
{
   public Fass1(int generations)
   {
      this.axiom = "A";
      this.productions.put("A", "A+B++B-A--AA-B+");
      this.productions.put("B", "-A+BB++B+A--A-B");

      this.generations = generations;
      this.theta = 60f;

      this.closed = false;
   }
}
