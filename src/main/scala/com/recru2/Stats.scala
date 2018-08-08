package com.recru2

import scala.collection.immutable.ListMap

class Stats(t: Int, v: Double,val n: Int,val rs: Double,val minV: Double,val maxV: Double,val inWindMes: Map[Int,Double]) {
  override def toString: String = {
    f"$t\t\t$v%.5f\t\t$n\t\t$rs%.5f\t\t$minV%.5f\t\t$maxV%.5f"
  }
}

object Stats{
  val T = 60
  def apply(t: Int, v: Double, s: Option[Stats]): Stats = {
    if (s.isEmpty){
      new Stats(t,v,1,v,v,v, ListMap(t -> v))
    } else {
      val updInWindMes = s.get.inWindMes.filter(_._1 > t-T) + (t -> v)
      new Stats(t,v,updInWindMes.size,updInWindMes.foldLeft(0.0)((acc,v) => acc + v._2),updInWindMes.values.min,updInWindMes.values.max,updInWindMes)
    }
  }
}
