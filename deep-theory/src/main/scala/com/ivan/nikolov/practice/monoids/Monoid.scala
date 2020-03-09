package com.ivan.nikolov.practice.monoids

trait Monoid[A] {
  def op(a :A, b : A) : A
  def id : A
}
