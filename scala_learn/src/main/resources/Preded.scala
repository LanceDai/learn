package scala

object Predef extends scala.LowPriorityImplicits with scala.DeprecatedPredef {
  def classOf[T]: Predef.Class[T] = {
    /* compiled code */
  }

  type String = java.lang.String
  type Class[T] = java.lang.Class[T]
  type Function[-A, +B] = scala.Function1[A, B]
  type Map[A, +B] = scala.collection.immutable.Map[A, B]
  type Set[A] = scala.collection.immutable.Set[A]
  val Map: scala.collection.immutable.Map.type = {
    /* compiled code */
  }
  val Set: scala.collection.immutable.Set.type = {
    /* compiled code */
  }
  @scala.deprecated("use `scala.reflect.ClassTag` instead", "2.10.0")
  @scala.annotation.implicitNotFound("No ClassManifest available for ${T}.")
  type ClassManifest[T] = scala.reflect.ClassManifest[T]
  type OptManifest[T] = scala.reflect.OptManifest[T]
  @scala.annotation.implicitNotFound("No Manifest available for ${T}.")
  type Manifest[T] = scala.reflect.Manifest[T]
  @scala.deprecated("use `scala.reflect.ClassTag` instead", "2.10.0")
  val ClassManifest: scala.reflect.ClassManifestFactory.type = {
    /* compiled code */
  }
  val Manifest: scala.reflect.ManifestFactory.type = {
    /* compiled code */
  }
  val NoManifest: scala.reflect.NoManifest.type = {
    /* compiled code */
  }

  def manifest[T](implicit m: Predef.Manifest[T]): Predef.Manifest[T] = {
    /* compiled code */
  }

  @scala.deprecated("use scala.reflect.classTag[T] instead", "2.10.0")
  def classManifest[T](implicit m: Predef.ClassManifest[T]): Predef.ClassManifest[T] = {
    /* compiled code */
  }

  def optManifest[T](implicit m: Predef.OptManifest[T]): Predef.OptManifest[T] = {
    /* compiled code */
  }

  @scala.inline
  def identity[A](x: A): A = {
    /* compiled code */
  }

  @scala.inline
  def implicitly[T](implicit e: T): T = {
    /* compiled code */
  }

  @scala.inline
  def locally[T](x: T): T = {
    /* compiled code */
  }

  @scala.annotation.elidable(2000)
  def assert(assertion: scala.Boolean): scala.Unit = {
    /* compiled code */
  }

  @scala.inline
  @scala.annotation.elidable(2000)
  final def assert(assertion: scala.Boolean, message: => scala.Any): scala.Unit = {
    /* compiled code */
  }

  @scala.annotation.elidable(2000)
  def assume(assumption: scala.Boolean): scala.Unit = {
    /* compiled code */
  }

  @scala.inline
  @scala.annotation.elidable(2000)
  final def assume(assumption: scala.Boolean, message: => scala.Any): scala.Unit = {
    /* compiled code */
  }

  def require(requirement: scala.Boolean): scala.Unit = {
    /* compiled code */
  }

  @scala.inline
  final def require(requirement: scala.Boolean, message: => scala.Any): scala.Unit = {
    /* compiled code */
  }

  def ??? : scala.Nothing = {
    /* compiled code */
  }

  @scala.deprecated("use built-in tuple syntax or Tuple2 instead", "2.11.0")
  type Pair[+A, +B] = scala.Tuple2[A, B]

  @scala.deprecated("use built-in tuple syntax or Tuple2 instead", "2.11.0")
  object Pair extends scala.AnyRef {
    def apply[A, B](x: A, y: B): scala.Tuple2[A, B] = {
      /* compiled code */
    }

    def unapply[A, B](x: scala.Tuple2[A, B]): scala.Option[scala.Tuple2[A, B]] = {
      /* compiled code */
    }
  }

  @scala.deprecated("use built-in tuple syntax or Tuple3 instead", "2.11.0")
  type Triple[+A, +B, +C] = scala.Tuple3[A, B, C]

  @scala.deprecated("use built-in tuple syntax or Tuple3 instead", "2.11.0")
  object Triple extends scala.AnyRef {
    def apply[A, B, C](x: A, y: B, z: C): scala.Tuple3[A, B, C] = {
      /* compiled code */
    }

    def unapply[A, B, C](x: scala.Tuple3[A, B, C]): scala.Option[scala.Tuple3[A, B, C]] = {
      /* compiled code */
    }
  }

  implicit final class ArrowAssoc[A](self: A) extends scala.AnyVal {
    @scala.inline
    def ->[B](y: B): scala.Tuple2[A, B] = {
      /* compiled code */
    }

    def →[B](y: B): scala.Tuple2[A, B] = {
      /* compiled code */
    }
  }

  implicit final class Ensuring[A](self: A) extends scala.AnyVal {
    def ensuring(cond: scala.Boolean): A = {
      /* compiled code */
    }

    def ensuring(cond: scala.Boolean, msg: => scala.Any): A = {
      /* compiled code */
    }

    def ensuring(cond: scala.Function1[A, scala.Boolean]): A = {
      /* compiled code */
    }

    def ensuring(cond: scala.Function1[A, scala.Boolean], msg: => scala.Any): A = {
      /* compiled code */
    }
  }

  implicit final class StringFormat[A](self: A) extends scala.AnyVal {
    @scala.inline
    def formatted(fmtstr: Predef.String): Predef.String = {
      /* compiled code */
    }
  }

  implicit final class any2stringadd[A](self: A) extends scala.AnyVal {
    def +(other: Predef.String): Predef.String = {
      /* compiled code */
    }
  }

  implicit final class RichException(self: scala.Throwable) extends scala.AnyVal {
    @scala.deprecated("use Throwable#getStackTrace", "2.11.0")
    def getStackTraceString: _root_.scala.Predef.String = {
      /* compiled code */
    }
  }

  implicit final class SeqCharSequence(@scala.deprecated("will be made private", "2.12.0") val __sequenceOfChars: scala.collection.IndexedSeq[scala.Char]) extends java.lang.Object with java.lang.CharSequence {
    def length(): scala.Int = {
      /* compiled code */
    }

    def charAt(index: scala.Int): scala.Char = {
      /* compiled code */
    }

    def subSequence(start: scala.Int, end: scala.Int): java.lang.CharSequence = {
      /* compiled code */
    }

    override def toString(): _root_.scala.Predef.String = {
      /* compiled code */
    }
  }

  implicit final class ArrayCharSequence(@scala.deprecated("will be made private", "2.12.0") val __arrayOfChars: scala.Array[scala.Char]) extends java.lang.Object with java.lang.CharSequence {
    def length(): scala.Int = {
      /* compiled code */
    }

    def charAt(index: scala.Int): scala.Char = {
      /* compiled code */
    }

    def subSequence(start: scala.Int, end: scala.Int): java.lang.CharSequence = {
      /* compiled code */
    }

    override def toString(): _root_.scala.Predef.String = {
      /* compiled code */
    }
  }

  implicit val StringCanBuildFrom: scala.collection.generic.CanBuildFrom[Predef.String, scala.Char, Predef.String] = {
    /* compiled code */
  }

  @scala.inline
  implicit def augmentString(x: Predef.String): scala.collection.immutable.StringOps = {
    /* compiled code */
  }

  @scala.inline
  implicit def unaugmentString(x: scala.collection.immutable.StringOps): Predef.String = {
    /* compiled code */
  }

  def print(x: scala.Any): scala.Unit = {
    /* compiled code */
  }

  def println(): scala.Unit = {
    /* compiled code */
  }

  def println(x: scala.Any): scala.Unit = {
    /* compiled code */
  }

  def printf(text: Predef.String, xs: scala.Any*): scala.Unit = {
    /* compiled code */
  }

  implicit def tuple2ToZippedOps[T1, T2](x: scala.Tuple2[T1, T2]): scala.runtime.Tuple2Zipped.Ops[T1, T2] = {
    /* compiled code */
  }

  implicit def tuple3ToZippedOps[T1, T2, T3](x: scala.Tuple3[T1, T2, T3]): scala.runtime.Tuple3Zipped.Ops[T1, T2, T3] = {
    /* compiled code */
  }

  implicit def genericArrayOps[T](xs: scala.Array[T]): scala.collection.mutable.ArrayOps[T] = {
    /* compiled code */
  }

  implicit def booleanArrayOps(xs: scala.Array[scala.Boolean]): scala.collection.mutable.ArrayOps.ofBoolean = {
    /* compiled code */
  }

  implicit def byteArrayOps(xs: scala.Array[scala.Byte]): scala.collection.mutable.ArrayOps.ofByte = {
    /* compiled code */
  }

  implicit def charArrayOps(xs: scala.Array[scala.Char]): scala.collection.mutable.ArrayOps.ofChar = {
    /* compiled code */
  }

  implicit def doubleArrayOps(xs: scala.Array[scala.Double]): scala.collection.mutable.ArrayOps.ofDouble = {
    /* compiled code */
  }

  implicit def floatArrayOps(xs: scala.Array[scala.Float]): scala.collection.mutable.ArrayOps.ofFloat = {
    /* compiled code */
  }

  implicit def intArrayOps(xs: scala.Array[scala.Int]): scala.collection.mutable.ArrayOps.ofInt = {
    /* compiled code */
  }

  implicit def longArrayOps(xs: scala.Array[scala.Long]): scala.collection.mutable.ArrayOps.ofLong = {
    /* compiled code */
  }

  implicit def refArrayOps[T <: scala.AnyRef](xs: scala.Array[T]): scala.collection.mutable.ArrayOps.ofRef[T] = {
    /* compiled code */
  }

  implicit def shortArrayOps(xs: scala.Array[scala.Short]): scala.collection.mutable.ArrayOps.ofShort = {
    /* compiled code */
  }

  implicit def unitArrayOps(xs: scala.Array[scala.Unit]): scala.collection.mutable.ArrayOps.ofUnit = {
    /* compiled code */
  }

  implicit def byte2Byte(x: scala.Byte): java.lang.Byte = {
    /* compiled code */
  }

  implicit def short2Short(x: scala.Short): java.lang.Short = {
    /* compiled code */
  }

  implicit def char2Character(x: scala.Char): java.lang.Character = {
    /* compiled code */
  }

  implicit def int2Integer(x: scala.Int): java.lang.Integer = {
    /* compiled code */
  }

  implicit def long2Long(x: scala.Long): java.lang.Long = {
    /* compiled code */
  }

  implicit def float2Float(x: scala.Float): java.lang.Float = {
    /* compiled code */
  }

  implicit def double2Double(x: scala.Double): java.lang.Double = {
    /* compiled code */
  }

  implicit def boolean2Boolean(x: scala.Boolean): java.lang.Boolean = {
    /* compiled code */
  }

  implicit def Byte2byte(x: java.lang.Byte): scala.Byte = {
    /* compiled code */
  }

  implicit def Short2short(x: java.lang.Short): scala.Short = {
    /* compiled code */
  }

  implicit def Character2char(x: java.lang.Character): scala.Char = {
    /* compiled code */
  }

  implicit def Integer2int(x: java.lang.Integer): scala.Int = {
    /* compiled code */
  }

  implicit def Long2long(x: java.lang.Long): scala.Long = {
    /* compiled code */
  }

  implicit def Float2float(x: java.lang.Float): scala.Float = {
    /* compiled code */
  }

  implicit def Double2double(x: java.lang.Double): scala.Double = {
    /* compiled code */
  }

  implicit def Boolean2boolean(x: java.lang.Boolean): scala.Boolean = {
    /* compiled code */
  }

  @scala.annotation.implicitNotFound("Cannot prove that ${From} <:< ${To}.")
  sealed abstract class <:<[-From, +To]() extends scala.AnyRef with scala.Function1[From, To] with scala.Serializable {

  }

  implicit def $conforms[A]: Predef.<:<[A, A] = {
    /* compiled code */
  }

  @scala.deprecated("use `implicitly[T <:< U]` or `identity` instead.", "2.11.0")
  def conforms[A]: Predef.<:<[A, A] = {
    /* compiled code */
  }

  @scala.annotation.implicitNotFound("Cannot prove that ${From} =:= ${To}.")
  sealed abstract class =:=[From, To]() extends scala.AnyRef with scala.Function1[From, To] with scala.Serializable {

  }

  object =:= extends scala.AnyRef with scala.Serializable {
    implicit def tpEquals[A]: Predef.=:=[A, A] = {
      /* compiled code */
    }
  }

  class DummyImplicit() extends scala.AnyRef {
  }

  object DummyImplicit extends scala.AnyRef {
    implicit def dummyImplicit: Predef.DummyImplicit = {
      /* compiled code */
    }
  }

}
