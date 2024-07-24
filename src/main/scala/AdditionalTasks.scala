object AdditionalTasks extends App {

  //Q1
  def getSize[T](l: List[T]): Int = {
    l.foldLeft(0)((counter, _) => counter + 1)
  }

  //  def middle[T](list: List[T]): Option[T] = {
  //    def middleAux[T](list: List[T], middle: Int, index: Int): Option[T] = {
  //      if (list.isEmpty) None
  //      else if (getSize(list) == 1) Some(list.head)
  //      else {
  //        if (index == middle) Some(list.head)
  //        else middleAux(list.tail, middle, index + 1)
  //      }
  //    }
  //
  //    val size = getSize(list) / 2
  //    val middleEle = if (size % 2 == 0) size else size - 1
  //    middleAux(list, middleEle, 0)
  //  }

  def middle[T](list: List[T]): Option[T] = {
    def middleRec(remaining: List[T]): Option[T] = remaining match {
      case (m :: Nil) => Some(m) //one element
      case (m :: _ :: Nil) => Some(m) //two elements
      case (_ :: _) => middleRec(remaining.tail.init)
      case _ => None
    }

    middleRec(list)
  }

  val list1 = middle(List(1, 2, 3, 4, 5)) //Some(3)
  val list2 = middle(List(1, 2, 3, 4, 5, 6)) //Some(3)
  val list3 = middle(List(1)) // Some(1)
  val list4 = middle(List()) // None
  val list5 = middle(List(1, 2)) // Some(1)

  println(list1)
  println(list2)
  println(list3)
  println(list4)
  println(list5)
  println()

  //Q2

  var charArray: Array[Char] = Array()
  var findLetterMap: Map[Int, Char] = Map()
  var findOccurMap: Map[Char, Int] = Map()

  def Initialize(array: Array[Char]): Unit = {
    charArray = array
    findLetterMap = charArray.zipWithIndex.map(_.swap).toList.toMap
    // println(findLetterMap)
    findOccurMap = charArray.groupBy(x => x).mapValues(_.size).toMap
    println(findOccurMap)
  }

  def FindOccurence(input: Char): Int = {
    findOccurMap.getOrElse(input, 0)
  }

  def FindLetter(input: Int): Char = {
    findLetterMap.getOrElse(input, 0)
  }

  val myList = Array('a', 'b', 'a', 'c', 'c', 'a')
  Initialize(myList)
  val x = FindOccurence('a')
  println("Occurence: " + x)
  val y = FindLetter(4)
  println("Character: " + y)
}
