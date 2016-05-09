case class Person(firstName: String, lastName: String)

val hickey = Person("Rich", "Hickey")
val odersky = Person("Martin", "Odersky")
val wadler = Person("Philip", "Wadler")

val persons = List(hickey,odersky,wadler)

val mapped = persons.map(_.firstName)
val filtered = mapped.filter(_.length > 4)
val reduced = filtered.foldLeft(0)((acc: Int,name: String) => acc + name.length)

def countTotalLengthOffirstNamesLongerThanFourCharacters(persons: List[Person]): Int = {
  val firstNames = persons.map(_.firstName)
  val longerThan4 = mapped.filter(_.length > 4)
  val totalLength = filtered.foldLeft(0)((acc: Int,name: String) => acc + name.length)
  totalLength
}

/* 
From the command line:
$ scala
scala> :load scala-example.sc
 */
