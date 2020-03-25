

/*
Let's imagine that we have a method that can read and print the contents of a file. If we have two different libraries that
allow us to read a file, in order to use our method, we will have to make sure the methods that read the file somehow become
the same type.

One way would be by wrapping them in a class that implements a specific interface.

Provided that in both the libraries the read method has the same signature, which could easily happen, Scala can use duck typing instead,
and this way it will minimize the extra work we will have to do.

Duck typing is a term that comes from dynamic languages and it allows us to treat different types of
objects in a similar manner based on a common method they have.

Another name for duck typing is structural typing.


Overusing duck typing can negatively affect code quality and application performance. You should not avoid creating common interfaces
in favor of duck typing. It should be really only used in cases when we cannot implement a common interface between different types.
The argument about limiting the use of duck typing is further enhanced by the fact that, under the hood, they use reflection, which
is slower and negatively impacts performance.
 */