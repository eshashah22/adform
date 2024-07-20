import scala.io.Source

object ThiefData extends App {

  val filename = "/Users/e.shah/Downloads/thief.txt"
  val delimiter = ","
  val file = Source.fromFile(filename)
  for (line <- file.getLines()) {
    val fields = line.split(delimiter).map(_.trim)
    val lines = fields.mkString("")
    val ans = calcRemote(lines, 0)
    println(ans)


  }
  file.close()


  def calcRemote(num: String, accum: Int): Int = {
    if (num == "") accum
    else {
      if (num.head == '1') calcRemote(num.tail, accum)
      else {
        val newNum = flip(num.tail)
        calcRemote(newNum, 1 + accum)
      }
    }
  }

  def flip(n: String): String = {
    n.map {
      case '1' => '0'
      case '0' => '1'
    }
  }
  // println(flip("0101"))
  // println(calcRemote("01111",0))
  // println(calcRemote("101001", 0))
  // println(calcRemote("1", 0))
  // println(calcRemote("0", 0))
  // println(calcRemote("10", 0))
  // println(calcRemote("11", 0))
  //println(calcRemote("010",0))
}







