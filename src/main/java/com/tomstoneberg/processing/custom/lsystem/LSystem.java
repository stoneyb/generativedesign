package com.tomstoneberg.processing.custom.lsystem;

import java.util.HashMap;
import java.util.Map;

public abstract class LSystem
{
   String axiom;
   int generations;
   float theta;
   Map<String, String> productions = new HashMap<>();
   boolean closed = true;

   public String getProductionResult()
   {
      if(productions.isEmpty() || generations == 0)
      {
         return this.axiom;
      }

      String prev = this.axiom;
      String result = "";
      for(int i = 0; i < this.generations; i++)
      {
         for(String s : prev.split(""))
         {
            if(productions.containsKey(s))
            {
               result += productions.get(s);
            } else
            {
               result += s;
            }
         }
         prev = result;

         if(i != generations - 1) result = "";
      }
      return result;
   }

   public float getTheta()
   {
      return theta;
   }

   public boolean isClosed()
   {
      return closed;
   }

}
