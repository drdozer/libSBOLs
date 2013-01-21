=libSBOLs

Scala API for the SBOL standard.

This library provides an implementation of the SBOL 1.1 data model in Scala, Java and as an abstraction over both. The
package structure is arranged so that each API has its own package defining the interoperability layer, and then has
two sub-packages, `j` and `s` for the Java and Scala bindings, respectively. Java clients are strongly encouraged to
work directly with the classes and interfaces exposed through the `.j` implementation packages.
