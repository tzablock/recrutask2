package com.recru2

import scala.io.Source

object Start {
  def main(args: Array[String]): Unit = {
    val inPath = args match {
      case Array(p) => p
      case _ => throw new RuntimeException("Path to input file should be provided.")
    }
    new Analyzer().run(inPath)
  }
}
class Analyzer{
  def run(inPath: String): Unit ={
    val inSource =  Source.fromFile(inPath)//Files.lines(Paths.get(inPath))
    println("T         \t\tV      \t\tN\t\tRS   \t\tMinV  \t\tMaxV\n------------------------------------------------------------------")
    var prevSt:Option[Stats] = None
    inSource.getLines()
            .map(
              _.split("\t")
            ).map(
              tv =>
                   {
                     val currStats = Stats(tv(0).toInt,tv(1).toDouble,prevSt)
                     prevSt = Some(currStats)
                     currStats
                   }
            ).foreach(println)
  }
}
