object AdditionalTasks extends App {

  //Q1
  def getSize[T](l: List[T], accum: Int): Int = {
    if (l.isEmpty) accum
    else {
      getSize(l.tail, 1 + accum)
    }
  }

  def middle[T](list: List[T]): Option[T] = {
    def middleAux[T](list: List[T], middle: Int, index: Int): Option[T] = {
      if (list.isEmpty) None
      else if (getSize(list, 0) == 1) Some(list.head)
      else {
        if (index == middle) Some(list.head)
        else middleAux(list.tail, middle, index + 1)
      }
    }

    val size = getSize(list, 0) / 2
    val middleEle = if (size % 2 == 0) size else size - 1
    middleAux(list, middleEle, 0)
  }


  val list1 = middle(List(1, 4, 3, 2, 5)) //Some(3)
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

  def Initialize(array: Array[Char]): Unit = {
    charArray = array
  }

  def FindOccurence(input: Char): Int = {
    def FindOccurenceAux(list: Array[Char], input: Char, accum: Int): Int = {
      if (list.isEmpty) accum
      else {
        if (list.head == input) FindOccurenceAux(list.tail, input, 1 + accum)
        else FindOccurenceAux(list.tail, input, accum)
      }
    }

    FindOccurenceAux(charArray, input, 0)
  }

  def FindLetter(input: Int): Char = {
    def FindLetterAux(list: Array[Char], input: Int, counter: Int): Char = {
      if (list.isEmpty) {
        throw new NoSuchElementException("List is empty.")
      }
      if (input == counter) list.head
      else {
        FindLetterAux(list.tail, input, 1 + counter)
      }
    }

    FindLetterAux(charArray, input, 0)
  }

  val myList = Array('a', 'b', 'a', 'c', 'c', 'a')
  charArray = myList
  val x = FindOccurence('a')
  println("Occurence: " + x)
  val y = FindLetter(2)
  println("Character: " + y)
}



