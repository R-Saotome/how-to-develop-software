package controllers

import javax.inject.{Inject, Singleton}
import models.{Progress, ProgressRepository}
import play.api.libs.json.{JsError, Json}
import play.api.mvc.{AnyContent, MessagesAbstractController, MessagesControllerComponents, Request}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProgressesController @Inject()(pr: ProgressRepository, cc: MessagesControllerComponents)(implicit
                                                                                               ec: ExecutionContext)
  extends MessagesAbstractController(cc) {
  def get() = Action.async { implicit request: Request[AnyContent] =>
    Future {
      val results = pr.find
      Ok(Json.obj("data" -> Json.toJson(results)))
    }(ec)
  }

  def add() = Action.async(parse.json) { implicit request =>
    Future {
      val progressResult = request.body.validate[Progress]
      progressResult.fold(
        errors => BadRequest(JsError.toJson(errors)),
        progress => {
          val generatedId: Option[Long] = progress.save(pr)
          Created(Json.obj("data" -> Json.toJson(progress.copy(id = generatedId))))
        }
      )
    }(ec)
  }

  def update(updateId: Long) = Action.async(parse.json) { implicit request =>
    Future {
      val progressResult = request.body.validate[Progress]
      progressResult.fold(
        errors => BadRequest(JsError.toJson(errors)),
        progress => {
          progress.copy(id = Some(updateId)).edit(pr)
          NoContent
        }
      )
    }(ec)
  }

  def remove(deleteId: Long) = Action.async(parse.json) { implicit request =>
    Future {
      pr.remove(deleteId)
      NoContent
    }(ec)
  }
}