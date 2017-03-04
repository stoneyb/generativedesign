package com.tomstoneberg.processing.custom;

public class LSystem
{
//   private String axiom = "F-F-F-F";
//   private String rule = "F+FF-FF-F-F+F+FF-F-F+F+FF+FF-F";
   private String axiom = "F-F-F-F-F";
   private String rule = "F-F++F+F-F-F";
   private int generations = 3;
   private float theta = 72.0f;

   public LSystem()
   {

   }

   public String getAxiom()
   {
      return axiom;
   }

   public void setAxiom(String axiom)
   {
      this.axiom = axiom;
   }

   public String getRule()
   {
      return rule;
   }

   public void setRule(String rule)
   {
      this.rule = rule;
   }

   public int getGenerations()
   {
      return generations;
   }

   public void setGenerations(int generations)
   {
      this.generations = generations;
   }

   public float getTheta()
   {
      return theta;
   }

   public void setTheta(float theta)
   {
      this.theta = theta;
   }
}
