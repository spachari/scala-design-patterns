/*
Purpose of Monads

Let's call them computation builders, as this is exactly what they are used for. This gives the ordinary developer much more
understanding about when and where to use monad's computation builder chain operations in some way, which are then performed.
 */

//Monads are functors that have the unit and flatMap methods and follow the monad rules.

//So, what does the preceding definition mean? First of all, it means that monads follow all the rules we previously defined about
// functors. Additionally, they take things further and add support for two more methods.

//1. the flatMap method

/*
flatMap just maps and then flattens, as shown here:

def flatMap[T](f : T => Monad[T]) : Monad[T] = flatten(map(f))

Just FYI, look at the flatten signature

def flatten(l : F[F[T]]) : M[T] (or F[T])

 */

//Examples

val some = Some(10)
val none = None

val list = List(some, none).flatten

/*
 It is pretty simple—it takes a value of the T type and turns it into a monad of the T type.
 This is nothing more than a single argument constructor or just a factory method.

  In Scala, this can be expressed using a companion object with an apply method as well.
  As long as it does the right thing, the implementation doesn't really matter.
 */
//def unit[T](x : T) : M[T]

val list1 = List.apply(1,2,3,4)

//The connection between map, flatMap, Unit

// In above example we saw that flatMap can be defined using map and flatten.
// We can think of map in a different way (refer to the pseudo code)

//def map[T](f : T => T) : M[T] = flatMap { x => f(unit(x)) }

//here assumption is flatMap will just return the same value

list.flatMap(x => Some(x))

/*



Depending on what kind of monads we implement, it could sometimes be easier to implement map first (usually,
if we build collection-like monads) and then flatMap based on it and flatten, while other times it could be easier to first implement
flatMap instead. As long as the monad laws are satisfied, it shouldn't matter which approach we take.
 */

// map and flatMap give us something extra—the possibility of using our classes in for comprehensions.

class ListWrapper(list : List[Int]) {

  def unit(x : List[Int]) : ListWrapper = ListWrapper.apply(x)

  def map[B](f : Int => B) : List[B] = list.map(f)

  def flatMap[B](f : Int => List[B]) : List[B] = list.flatMap(f)

}

object ListWrapper {
  def apply(x : List[Int]) = new ListWrapper(x)
}


//Monad laws

//Identity law: Doing map over the identity function doesn't change the data—
// map(x)(i => i) == x. Flat mapping over the unit function also keeps the data the same—x.flatMap(i => unit(i)) == x.
// The latter basically says that flatMap undoes unit. Using the connection between map, flatMap, and
// unit we defined earlier, we can derive one of these two rules from the other and vice versa.
// The unit method can be thought of as the zero element in monoids.

//The unit law: From the definition of unit, we can also say this: unit(x).flatMap { y => f(y) } == f(x).
// From this, we will get unit(x).map { y => f(x) } == unit(f(x)). This gives us some interesting connections
// between all the methods.

//Composition: Multiple maps must be composed together. It should make no difference if we do
// x.map(i => y(i)).map(i => z(i)) or x.map(i => z(y(i))). Moreover, multiple flatMap calls must also compose,
// making the following true:

//x.flatMap(i => y(i)).flatMap(i => z(i)) == x.flatMap(i => y(i).flatMap(j => z(j))).



/*
Monads, similarly to monoids, also have a zero element. Some real-world examples of monadic zeros are Nil in the
Scala List and the None option. However, here we can also have multiple zero elements, which are represented by an
algebraic datatype with a constructor parameter to which we can pass different values. In order to be complete, we
might not have zeros at all if there is no such concept for the monads we are modeling. In any case, the zero monad
represents some kind of emptiness and follows some extra laws:

Zero identity: This one is pretty straightforward. It says that no matter what function we apply to a zero monad,
it is still going to be zero—zero.flatMap(i => f(i)) == zero and zero.map(i => f(i)) == zero. Zero shouldn't be confused
with unit, as they are different and the latter doesn't represent emptiness.

Reverse zero: This is straightforward as well. Basically, if we replace everything with zero, our final result will
also be zero—x.flatMap(i => zero) == zero.

Commutativity : Monads can have a concept of addition, whether it is concatenation or something else. In any case, this
kind of operation when done with the zero monad will be commutative, for example, x plus zero == zero plus x == x.
 */

/*
Monads and side effects

When presenting the composition law, we kind of assumed that an operation has no side effects. We said the following:
x.map(i => y(i)).map(i => z(i)) == x.map(i => z(y(i))).

However, let's now think about what would happen if y or z cause some side effects. On the left-hand side, we first
run all ys and then all zs. On the right-hand side, however, we interleave them, doing y and z all the time. Now, if an
operation causes a side effect, it would mean that the two might end up producing different results. That's why developers
should prefer using the left-hand side version, especially when there might be side effects such as IO.
 */

/*
We have discussed the monad laws. For those who have more experience with Scala, monads might seem pretty close to the
collection classes, and the rules we defined previously might seem logical. However, we are pointing out once more that it
is not necessary for a monad to be a collection, and it is important that these rules are followed in order to be able to
call an algebraic data structure a monad.
 */