package models

import anorm.SqlParser.get
import anorm.{RowParser, ~}
import play.api.libs.json.JsonNaming.SnakeCase
import play.api.libs.json.{Format, Json, JsonConfiguration}

object Company {
  implicit val config = JsonConfiguration(SnakeCase)
  implicit val format: Format[Company] = Json.format[Company]
}

object SimpleCompany {
  implicit val config = JsonConfiguration(SnakeCase)
  implicit val format: Format[SimpleCompany] = Json.format[SimpleCompany]

  implicit val companyParser: RowParser[Option[SimpleCompany]] =
    get[Option[Long]]("company.id") ~
      get[Option[String]]("company.name") map {
      case id ~ name => {
        name match {
          case Some(x) => Some(SimpleCompany(id, x))
          case _ => None
        }
      }
    }
}

case class Company(id: Option[Long] = None,
                   name: String,
                   field: Option[String],
                   address: Option[String],
                   tel: Option[String],
                   fax: Option[String],
                   email: Option[String],
                   url: Option[String],
                   correspondence: Option[SimpleUser]) {
  def save(cr: CompanyRepository): Option[Long] = {
    cr.add(this)
  }

  def edit(cr: CompanyRepository) = {
    cr.update(this)
  }

  def remove = {}

}

case class SimpleCompany(id: Option[Long] = None, name: String)
