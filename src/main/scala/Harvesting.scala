import scala.io.Source

object Harvesting extends App {
  case class Fruits(fruit: String, date: String, price: Double)

  val filename = "/Users/e.shah/Downloads/pricing.txt"
  val delimiter = "\\s+"

  def readPriceFile(filename: String): List[Fruits] = {
    val file = Source.fromFile(filename)
    val lines = file.getLines().toList //prints all one line List(fdp,fdp,...)
    file.close()

    lines.flatMap { line =>
      val fields = line.split(delimiter)
      Option(Fruits(fields(0), fields(1), fields(2).toDouble))
    } //only want one list of fruits

  }

  val f: List[Fruits] = readPriceFile(filename)
  val fruitByName: Map[String, List[Fruits]] = f.groupBy(_.fruit)

  val priceByFruit: Map[String, Double] = fruitByName.map {
    case (f, p) =>
      val total = p.map(_.price).sum
      (f, total)
  }

  val fruitByNameMonth = f.groupBy(f => (f.fruit, f.date.substring(5, 7)))

  val priceByFruitMonth: Map[(String, String), Double] = fruitByNameMonth.map {
    case (m, p) =>
      val total = p.map(_.price).sum
      (m, total)
  } //returns map with the total price for each fruit, each month

  val groupByMonth = priceByFruitMonth.groupBy {
    case (a, b) => a._2
  } //groups by month (has each fruits total by month)

  val maxByMonth = groupByMonth.mapValues(x => x.maxBy(_._2))
  val minByMonth = groupByMonth.mapValues(x => x.minBy(_._2))

  maxByMonth.foreach { case (month, fruit) =>
    println(s"Best earning fruit in $month: ${fruit._1._1} ")
  }
  println()

  minByMonth.foreach { case (month, fruit) =>
    println(s"Worst earning fruit in $month: ${fruit._1._1} ")
  }

  val maxOverall = priceByFruit.maxBy(_._2)
  val minOverall = priceByFruit.minBy(_._2)
  println()
  println(s"The best earning fruit overall is: ${maxOverall._1} with a total of ${maxOverall._2} dollars")
  println(s"The worst earning fruit overall is: ${minOverall._1} with a total of ${minOverall._2} dollars")
  println()


  case class Harvest(name: String, date: String, fruit: String, amt: Double)

  val filename2 = "/Users/e.shah/Downloads/harvesting.txt"

  def readHarvestFile(filename2: String) = {
    val file2 = Source.fromFile(filename2)
    val lines2 = file2.getLines().toList //prints all one line List(fdp,fdp,...)
    file2.close()

    lines2.flatMap { line =>
      val fields = line.split(delimiter)
      Option(Harvest(fields(0), fields(1), fields(2), fields(3).toDouble))
    } //only want one list of fruits
  }

  val h = readHarvestFile(filename2)

  val byNameMonth: Map[(String, String), List[Harvest]] = h.groupBy(h => (h.name, h.date.substring(5, 7)))

  val amtByNameMonth: Map[(String, String), Double] = byNameMonth.map {
    case (a, b) =>
      val total = b.map(_.amt).sum //extracts the price from each list
      (a, total)
  }

  val byMonth = amtByNameMonth.groupBy {
    case (a, price) => a._2
  }

  val bestGatherer = byMonth.mapValues(x => x.maxBy(_._2))

  bestGatherer.foreach {
    case (month, name) => println(s"Month: $month Best Gatherer: ${name._1._1}")
  }
  println()

  val byNameFruit: Map[(String, String), List[Harvest]] = h.groupBy(x => (x.name, x.fruit))

  val amtNameFruit: Map[(String, String), Double] = byNameFruit.map {
    case (a, b) =>
      val total = b.map(_.amt).sum
      (a, total)
  }

  val byFruit: Map[String, Map[(String, String), Double]] = amtNameFruit.groupBy {
    case (a, b) => a._2
  }

  val bestFruitGatherer = byFruit.mapValues(x => x.maxBy(_._2))

  bestFruitGatherer.foreach {
    case (fruit, name) => println(s"Fruit: $fruit, Best Gatherer: ${name._1._1}")
  }
  println()

  //pt 6
  def calculateIncomeByGatherer(fruits: List[Fruits], harvests: List[Harvest]): Map[String, Double] = {
    val fruitPrices: Map[(String, String), List[Fruits]] = fruits.groupBy(x => (x.fruit, x.date))

    val valuePrice: Map[(String, String), Double] = fruitPrices.map {
      case (a, b) => (a, b.last.price)
    }

    val harvByName = harvests.groupBy(_.name)

    val prices = harvByName.map {
      case (name, list) =>
        val income = list.map {
          i =>
            val cost = valuePrice.getOrElse((i.fruit, i.date), 0.0)
            i.amt * cost
        }
        (name, income)
    }

    prices.map {
      case (name, list) =>
        val totalPrice = list.sum
        (name, totalPrice)
    }
  }

  val incomeByGatherer = calculateIncomeByGatherer(f, h)

  val bestGathererOverall = incomeByGatherer.maxBy(_._2)

  println(s"The best earning gatherer overall is: ${bestGathererOverall._1} with a total of ${bestGathererOverall._2} dollars")
  println()

  def calculateIncomeByGathererMonth(fruits: List[Fruits], harvests: List[Harvest]): Map[String, Map[String, Double]] = {
    val fruitPrices: Map[(String, String), List[Fruits]] = fruits.groupBy(x => (x.fruit, x.date))

    val valuePrice: Map[(String, String), Double] = fruitPrices.map {
      case (a, b) => (a, b.last.price)
    }

    val harvByMonth = harvests.groupBy(h => h.date.substring(5, 7))

    harvByMonth.map {
      case (month, list) =>
        val byGatherer = list.groupBy(_.name).map {
          case (name, list2) =>
            val income = list2.map { harvest =>
              val cost = valuePrice.getOrElse((harvest.fruit, harvest.date), 0.0)
              harvest.amt * cost
            }.sum
            (name, income)
        }
        (month, byGatherer)
    }
  }

  val incomeByGathererMonth = calculateIncomeByGathererMonth(f, h)

  incomeByGathererMonth.foreach {
    case (month, gathererMap) =>
      val bestGatherer = gathererMap.maxBy(_._2)
      println(s"Month: $month, Best Earning Gatherer: ${bestGatherer._1} with a total of ${bestGatherer._2} dollars")
  }

}
