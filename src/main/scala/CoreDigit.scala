import scala.io.Source
import scala.annotation.tailrec
import scala.language.postfixOps

object CoreDigit extends App {

  val filename = "/Users/e.shah/Downloads/CoreData.txt"
  val delimiter = ","
  val file = Source.fromFile(filename)
  for (line <- file.getLines()) {
    val fields = line.split(delimiter).map(_.trim)
    val lines = fields.mkString("")
    val ans = coreDigit(lines)
    println(s"The core digit is: $ans")


  }
  file.close()

  def coreDigit(nums: String): Int = {

    //splits the string into two numbers
    val parts = nums.split(" ")
    val n1 = parts(0)
    val n2 = parts(1).toInt

    val digits = n1.map(_.asDigit).toList

    val coreDig = digits.foldLeft(0)(_ + _)
    val ans = coreDig * n2

    @tailrec
    def calcCoreDig(numb: Int): Int = {
      if (numb < 10) numb
      else calcCoreDig(numb.toString.map(_.asDigit).sum)
    }

    calcCoreDig(ans)
  }

  //println("Core Digit is: " + coreDigit("9785 4")) //5

}
