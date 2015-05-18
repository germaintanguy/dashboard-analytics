package example

/**
 * Created by germaintanguy on 02/05/15.
 */
import org.scalajs.dom.raw.HTMLCanvasElement

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSName



//object Chart {
//
//  // avaiable chart styles
//  sealed trait ChartStyle
//
//  case object LineChart extends ChartStyle
//
//  case object BarChart extends ChartStyle
//
//  case class ChartProps(name: String, style: ChartStyle, data: ChartData, width: Int = 400, height: Int = 200)
//
//  class Backend(t: BackendScope[ChartProps, _])
//
//  val Chart = ReactComponentB[ChartProps]("Chart")
//    .render((P) => {
//    <.canvas(^.width := P.width, ^.height := P.height)
//  }).componentDidMount(scope => {
//    // access context of the canvas
//    val ctx = scope.getDOMNode().asInstanceOf[HTMLCanvasElement].getContext("2d")
//    // create the actual chart using the 3rd party component
//    scope.props.style match {
//      case LineChart => new JSChart(ctx).Line(scope.props.data)
//      case BarChart => new JSChart(ctx).Bar(scope.props.data)
//      case _ => throw new IllegalArgumentException
//    }
//  }).build
//
//  def apply(props: ChartProps) = Chart(props)
//}