package controllers

import play.api.mvc._
import play.twirl.api.Html

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def testt () = views.html.index()

  def home = Action {
    skeleton(views.html.home())
  }

  def chart = Action {
      skeleton(views.html.chart())
  }

  def dataCollection = Action {
    skeleton(views.html.data_collection())
  }


  def skeleton(content : Html) = {
    Ok(views.html.skeleton(content))
  }

}
