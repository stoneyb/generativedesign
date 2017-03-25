package com.tomstoneberg.processing.custom;

public class LinePoints
{
   public float x1, x2, y1, y2;

   public LinePoints(float x1, float y1, float x2, float y2)
   {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
   }

   @Override
   public boolean equals(Object other)
   {
      return other instanceof LinePoints &&
            this.x1 == ((LinePoints)other).x1 &&
            this.x2 == ((LinePoints)other).x2 &&
            this.y1 == ((LinePoints)other).y1 &&
            this.y2 == ((LinePoints)other).y2;
   }

   @Override
   public int hashCode()
   {
      int result = 1;
      result = 31 * result + Float.floatToIntBits(x1);
      result = 31 * result + Float.floatToIntBits(y1);
      result = 31 * result + Float.floatToIntBits(x2);
      result = 31 * result + Float.floatToIntBits(y2);
      return result;
   }

   @Override
   public String toString()
   {
      return "LinePoints{" +
            "x1=" + x1 +
            ", x2=" + x2 +
            ", y1=" + y1 +
            ", y2=" + y2 +
            '}';
   }

}
