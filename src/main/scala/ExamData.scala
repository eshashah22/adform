import scala.io.Source
object ExamData extends App{

  val filename = "/Users/e.shah/Downloads/t1.txt"
  val delimiter = ","
  val file = Source.fromFile(filename)
  for (line <- file.getLines()) {
    val fields = line.split(delimiter).map(_.trim)
    val K = fields(0).toInt
    val L = fields(1).toInt
    val M = fields(2).toInt
    val time = K * L
    if(time < M) println("yes")
    else println("no")

  }
  file.close()
}
