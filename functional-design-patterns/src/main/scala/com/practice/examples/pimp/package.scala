package com.practice.examples.pimp

import com.practice.examples.pimp.model.Person

package object pimp {

  implicit class StringExtensions(val i : String) extends AnyVal {
    def isAllUpperClass() : Boolean = {
      !(0 until i.length).exists {
        case index => i.charAt(index).isLower
      }
    }
  }

  implicit class PersonExtensions(val seq : List[Person]) {
    def saveToDatabase()  = {
      seq.foreach{
        case x => println(x.name + "is now saved")
      }
    }
  }

}
