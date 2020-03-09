//Functor is something that will abstract some specific computations.

/*
In Scala, a functor is a class that has a  map method and conforms to a few laws. Let's call them functor laws.

The map method for a functor of the F[T] type takes a function from T to Y as a parameter and returns a F[Y] as a result.

Functors also obey some functor laws:

Identity: Whenever the identity function is mapped over some data, it doesn't change it, in other words, map(x)(i => i) == x.

Composition: Multiple maps must compose together. It should make no difference if we do this operation: map(map(x)(i => y(i)))(i => z(i)) or map(x)(i => z(y(i))).

The map function preserves the structure of the data, for example, it does not add or remove elements, change their order, and so on. It just changes the representation.
 */