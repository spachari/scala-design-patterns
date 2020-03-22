package com.practice.examples.cake

trait UserComponent {
  this : DaoComponent =>

  val userService : UserService
    class UserService() {
      def getAverageAgeOfusersInClass(classname : String) = {
        val peopleInClass = dao.getPeopleInClass(classname)
        val (ageSum, peopleCount) = peopleInClass.foldLeft((0,0)){
          case((sum, count) , person) => (sum + person.age , count + 1)
        }

        if (peopleCount != 0) {
          ageSum.toDouble / peopleCount.toDouble
        } else {
          0.0
        }
      }
    }

}

/*
In our UserComponent, we follow the same pattern we already know, but this time our dependency is on DaoComponent. We can
then have other components that depend on this component and on others as well. We haven't shown any example here in which a component
depends on multiple ones at the same time, but this is not hard to do at all. We just use the following:
 */

object Test extends App {
  val list = List(1,2,3)
  val sum = list.foldLeft(0){(acc, total) => acc + total}

  println(sum)
  val (total, count) = list.foldLeft((0,0)){
    case ((tot_acc, cou_acc), item) => (tot_acc + item, cou_acc + 1)
  }

  println(total/ count)
}