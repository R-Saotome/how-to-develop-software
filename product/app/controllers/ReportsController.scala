package controllers

import javax.inject.Inject
import models.{Report, ReportRepository}
import play.api.libs.json.{JsError, Json}
import play.api.mvc.{AnyContent, MessagesAbstractController, MessagesControllerComponents, Request}

import scala.concurrent.{ExecutionContext, Future}

class ReportsController @Inject()(rr: ReportRepository, cc: MessagesControllerComponents)(implicit
                                                                                          ec: ExecutionContext)
  extends MessagesAbstractController(cc) {
  def get() = Action.async { implicit request: Request[AnyContent] =>
    Future {
      val results = rr.find
      Ok(Json.obj("data" -> Json.toJson(results)))
    }(ec)
  }

  def add() = Action.async(parse.json) { implicit request =>
    Future {
      val reportResult = request.body.validate[Report]
      reportResult.fold(
        errors => BadRequest(JsError.toJson(errors)),
        report => {
          val generatedId: Option[Long] = report.save(rr)
          Created(Json.obj("data" -> Json.toJson(report.copy(id = generatedId))))
        }
      )
    }(ec)
  }

  def update(updateId: Long) = Action.async(parse.json) { implicit request =>
    Future {
      val companyResult = request.body.validate[Report]
      companyResult.fold(
        errors => BadRequest(JsError.toJson(errors)),
        opportunity => {
          opportunity.copy(id = Some(updateId)).edit(rr)
          NoContent
        }
      )
    }
  }


  def remove(deleteId: Long) = Action.async(parse.json) { implicit request =>
    Future {
      rr.remove(deleteId)
      NoContent
    }(ec)
  }
}