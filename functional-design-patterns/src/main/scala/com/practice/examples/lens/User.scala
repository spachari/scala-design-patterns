package com.practice.examples.lens

case class Country(name: String, code: String)
case class City(name: String, country: Country)
case class Address(number: Int, street: String, city: City)
case class Company(name: String, address: Address)
case class User(name: String, company: Company, address: Address)


case class Person (name : String, country: Country)

object VerboseExample {
  def main(args: Array[String]): Unit = {
    val uk = Country("United Kingdom", "uk")
    val london = City("London", uk)
    val buckinghamPalace = Address(1, "Buckingham Palace Road", london)
    val castleBuilders = Company("Castle Builders", buckinghamPalace)
    val switzerland = Country("Switzerland", "CH")
    val geneva = City("geneva", switzerland)
    val genevaAddress = Address(1, "Geneva Lake", geneva)
    val ivan = User("Ivan", castleBuilders, genevaAddress)
    System.out.println(ivan)
    System.out.println("Capitalize UK code...")

    val ivanFixed = ivan.copy(
      company = ivan.company.copy(
        address = ivan.company.address.copy(
          city = ivan.company.address.city.copy(
            country = ivan.company.address.city.country.copy (
              code = ivan.company.address.city.country.code.toUpperCase
            )
          )
        )
      )
    )

    println(ivanFixed)

    val srinivas = Person("Srinivas", uk)
    val copyTest = srinivas.copy(
      country = srinivas.country.copy(
        code = srinivas.country.code.toUpperCase
      )
    )
    println(copyTest)
  }
}

object UserBadExample {

  case class Country(var name: String, var code: String)
  case class City(var name: String, var country: Country)
  case class Address(var number: Int, var street: String, var city: City)
  case class Company(var name: String, var address: Address)
  case class User(var name: String, var company: Company, var address: Address)

  def main(args: Array[String]): Unit = {
    val uk = Country("United Kingdom", "uk")
    val london = City("London", uk)
    val buckinghamPalace = Address(1, "Buckingham Palace Road", london)
    val castleBuilders = Company("Castle Builders", buckinghamPalace)
    val switzerland = Country("Switzerland", "CH")
    val geneva = City("geneva", switzerland)
    val genevaAddress = Address(1, "Geneva Lake", geneva)
    val ivan = User("Ivan", castleBuilders, genevaAddress)
    System.out.println(ivan)
    System.out.println("Capitalize UK code...")
    ivan.company.address.city.country.code = ivan.company.address.city.country.code.toUpperCase
    System.out.println(ivan)
  }
}

import scalaz.Lens

object User {
  val userCompany : Lens[User, Company] = Lens.lensu[User, Company](
    (u, company) => u.copy(company = company), _.company
  )
  /*
  They create actual lenses so that for an object of the A type, the calls get and set a value of the B type.
  There is nothing special about them really, and they simply look like boilerplate code.
   */

  val userAddress : Lens[User, Address] = Lens.lensu[User, Address](
    (u, address) => u.copy(address = address), _.address
  )

  val companyAddress : Lens[Company, Address] = Lens.lensu[Company, Address](
    (c, address) => c.copy(address = address), _.address
  )

  val addressCity : Lens[Address, City] = Lens.lensu[Address, City](
    (address, city) => address.copy(city = city), _.city
  )

  val cityCountry : Lens[City, Country]= Lens.lensu[City, Country](
    (city, country) => city.copy(country = country), _.country
  )

  val countryCode : Lens[Country, String] = Lens.lensu[Country, String](
    (country, code) => country.copy(code = code), _.code
  )


  val userCompanyCountryCode = userCompany >=> companyAddress >=> addressCity >=> cityCountry >=> countryCode
}

object LensExample {
  def main(args: Array[String]): Unit = {
    val uk : Country = Country("United Kingdom", "uk")
    val london : City = City("London", uk)
    val buckinghamPalace : Address = Address(1, "Buckingham Palace Road", london)
    val castleBuilders : Company = Company("Castle Builders", buckinghamPalace)
    val switzerland = Country("Switzerland", "CH")
    val geneva = City("geneva", switzerland)
    val genevaAddress = Address(1, "Geneva Lake", geneva)
    val ivan = User("Ivan", castleBuilders, genevaAddress)
    val ivanFixed = User.userCompanyCountryCode.mod(_.toUpperCase, ivan)


    println(ivanFixed)
  }
}

