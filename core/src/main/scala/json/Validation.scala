package json

import com.github.andyglow.json.Value._
import com.github.andyglow.json.Value


final case class ValidationDef[S, V](validation: Validation[S, V], value: V, json: Value)

sealed abstract class Validation[S, V]()(implicit conv: V => Value) extends Product {

  def name: String = productPrefix

  def :=(x: V): ValidationDef[S, V] = ValidationDef(this, x, x)
}

object Validation {

  final case object `multipleOf` extends Validation[Number, Int]()(num.apply)

  final case object `maximum` extends Validation[Number, Number]()(num.apply)

  final case object `minimum` extends Validation[Number, Number]()(num.apply)

  final case object `exclusiveMaximum` extends Validation[Number, Number]()(num.apply)

  final case object `exclusiveMinimum` extends Validation[Number, Number]()(num.apply)

  final case object `maxLength` extends Validation[String, Int]()(num.apply)

  final case object `minLength` extends Validation[String, Int]()(num.apply)

  final case object `pattern` extends Validation[String, String]()(str.apply)

  final case object `maxItems` extends Validation[Iterable[_], Int]()(num.apply)

  final case object `minItems` extends Validation[Iterable[_], Int]()(num.apply)

  final case object `uniqueItems` extends Validation[Iterable[_], Boolean]()(bool.apply)

  final case object `maxProperties` extends Validation[Map[String, _], Int]()(num.apply)

  final case object `minProperties` extends Validation[Map[String, _], Int]()(num.apply)

  final case object `patternProperties` extends Validation[Map[String, _], String]()(str.apply)
}


