package example


import org.scalajs.dom.html

import scala.io.Source
import scala.scalajs.js
import org.scalajs.dom

import scala.scalajs.js.JSConverters._


import scala.scalajs.js.annotation.{JSExport, JSName}


object ScalaJSExample extends js.JSApp {
  def main(): Unit = {
    //dom.document.getElementById("page-wrapper").asInstanceOf[html.Div].innerHTML = "GoT"
  }
  @JSExport
  def chartBar(canvas: html.Canvas): Unit = {
    val ctx = canvas.getContext("2d")
    canvas.width = canvas.parentElement.clientWidth * 95 / 100
    canvas.height = canvas.parentElement.clientHeight
    val t = new JSChart(ctx).Bar(ChartData(Seq("A", "B", "C"), Seq(ChartDataset(Seq(1, 2, 3), "Data1"))))
    t.build
  }
  @JSExport
  def chartLine(canvas: html.Canvas): Unit = {
    val ctx = canvas.getContext("2d")
    canvas.width = canvas.parentElement.clientWidth * 95 / 100
    canvas.height = canvas.parentElement.clientHeight
    val t = new JSChart(ctx).Line(ChartData(Seq("A", "B", "C", "D", "E"), Seq(ChartDataset(Seq(1, 2, 3, 2, 0.5, 4), "Data1"))))
    t.build
  }

  trait ChartDataset extends js.Object {
    def label: String = js.native
    def fillColor: String = js.native
    def strokeColor: String = js.native
    def data: js.Array[Double] = js.native
  }


//  var gradient = ctx.createLinearGradient(0, 0, 0, 400);
//  gradient.addColorStop(0, 'rgba(151,187,205,0.7)');
//  gradient.addColorStop(1, 'rgba(151,187,205,0)');
  object ChartDataset {
    def apply(data: Seq[Double], label: String, fillColor: String = "#eaf1f5", strokeColor: String = "#97bbcd", pointHighlightFill : String = "#fff"): ChartDataset = {
      js.Dynamic.literal(
        data = data.toJSArray,
        label = label,
        fillColor = fillColor,
        strokeColor = strokeColor,
          pointHighlightFill = pointHighlightFill
      ).asInstanceOf[ChartDataset]
    }
  }

  trait ChartData extends js.Object {
    def labels: js.Array[String] = js.native
    def datasets: js.Array[ChartDataset] = js.native
  }

  object ChartData {
    def apply(labels: Seq[String], datasets: Seq[ChartDataset]): ChartData = {
      js.Dynamic.literal(
        labels = labels.toJSArray,
        datasets = datasets.toJSArray
      ).asInstanceOf[ChartData]
    }
  }

  // define a class to access the Chart.js component
  @JSName("Chart")
  class JSChart(ctx: js.Dynamic) extends js.Object {
    // create different kinds of charts
    def Line(data: ChartData): js.Dynamic = js.native
    def Bar(data: ChartData): js.Dynamic = js.native
  }
}
