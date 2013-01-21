package uk.co.turingatemyhamster.sbols
package util

import java.net.URI

/**
 * A resource of type `T` or a URI that identifies it.
 *
 * @tparam T  the type of the resource
 * @author Matthew Pocock
 */
sealed trait ReferenceOrInstance[T]

/**
 * The case where we have a URI.
 *
 * @param uri the URI that identifies a resource of type `T`
 * @tparam T  the type of the resource
 *
 * @author Matthew pocock
 */
case class Reference[T](uri: URI) extends ReferenceOrInstance[T]

/**
 * The case where we have an instance.
 *
 * @param t   the resource of type `T`
 * @tparam T  the type of the resource
 */
case class Instance[T](t: T) extends ReferenceOrInstance[T]

/**
 * Utilities for working with references and instances.
 *
 * @author Matthew Pocock
 */
object ReferenceOrInstance {
  /** Automatically wrap up a URI as a reference.
    *
    * @param uri  the URI to wrap
    * @tparam T   the type of the resource
    * @return     a `Reference[T]` wrapping `uri`
    */
  implicit def reference[T](uri: URI): Reference[T] = Reference(uri)

  /** Automatically wrap up a resource as a reference.
    *
    * @param t    the reference to wrap
    * @tparam T   the type of the resource
    * @return     an `Instance[T]` wrapping `t`
    */
  implicit def instance[T](t: T): Instance[T] = Instance(t)

  /** Automatically dereference an instance to the wrapped instance.
    *
    * @param inst the `Instance[T]` to dereference
    * @tparam T   the type of the resource
    * @return     the wrapped instance
    */
  implicit def dereference[T](inst: Instance[T]): T = inst.t

  /** Automatically attempt to dereference a reference.
    * The details of how URIs are resolved to objects is encapsulated by the implicit `lookup` function. In different
    * contexts and at different times, a `URI` may resolve to no or different instances.
    *
    * @param ref    the `Reference[T]` to resolve
    * @param lookup an implicit lookup function
    * @tparam T     the type of the resource
    * @return       `Some[T]` containing an instance found by resolving the reference if resolution succeeded, `None`
    *        otherwise
    */
  implicit def dereference[T](ref: Reference[T])(implicit lookup: URI => Option[T]): Option[T] = lookup(ref.uri)

  /** Automatically attempt to dereference a reference or instance.
    * In the case of `roi` being an instance, the wrapped instance is returned. In the case of `roi` being a reference,
    * the implicit `lookup` function is used to attempt to resolve the reference.
    *
    * @param roi    the `ReferenceOrInstance[T]` to resolve
    * @param lookup an implicit lookup function
    * @tparam T     the type of the resource
    * @return       `Some[T]` containing an instance if dereferencing succeeded, `None` otherwise
    */
  implicit def dereference[T](roi: ReferenceOrInstance[T])(implicit lookup: URI => Option[T]): Option[T] = roi match {
    case inst : Instance[T] => Some(dereference(inst))
    case ref : Reference[T] => dereference(ref)
  }
}