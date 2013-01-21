=libSBOLs

Scala API for the SBOL standard.

This library provides an implementation of the SBOL 1.1 data model in Scala, Java and as an abstraction over both. The
package structure is arranged so that each API has its own package defining the interoperability layer, and then has
two sub-packages, `j` and `s` for the Java and Scala bindings, respectively. Java clients are strongly encouraged to
work directly with the classes and interfaces exposed through the `.j` implementation packages.

==Interoperability by design

The interoperability layer makes extensive use of Scala's type-class design pattern for abstracting data representation.
Let's consider a simple `Person` datatype with a `name` and `age` property. The type-class representation of this is:

```scala
package person;

trait AsPerson[P] {
  def name(p: P): String
  def age(p: P): Int
}
```

Or, equivalently in Java:

```java
package person;

public interface AsPerson<P> {
  public String name(P p);
  public int age(P p);
}
```

This will then be suplimented by Scala and Java object-oriented representations of the same data in nested `s` and `j`
packages. The `s` package defines a trait, an implementation of that trait as a case class and an implicit
implementation of the type-class using the trait:

```scala
package person
package s

trait Person {
  def name: String
  def age: Int
}

object Person {
  implicit object personAsPerson extends AsPerson[Person] {
    def name(p: P) = p.name
    def age(p: P) = p.age
  }
}

case class PersonImpl(val name: String, val age: String) extends Person
```

The Java package is similar, but provides an ideomatic Java-bean data model.

```java
package person.j;

import person.*

public interface Person {
  public String getName();
  public void setName(String name);

  pubic int getAge();
  public void setAge(int age);
}

public class PersonImpl implements Person {
  private String name;
  private int age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  pubic int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
```

Actually, for the sake of our sanity, the `j` packages are also maintained in scala, but this doesn't impact upon the
API seen from Java, and also provides an implicit object to present the beans as the corresponding type-class.