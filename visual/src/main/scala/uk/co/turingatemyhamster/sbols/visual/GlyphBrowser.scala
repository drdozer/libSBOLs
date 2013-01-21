package uk.co.turingatemyhamster.sbols
package visual

import scalafx.Includes._

import scalafx.application.JFXApp
import scalafx.stage.Stage
import scalafx.scene.Scene
import javafx.scene.paint.Color
import scalafx.scene.control.{Label, ListView}
import scalafx.scene.control.cell.TextFieldListCell
import scalafx.util.StringConverter
import scalafx.scene.layout.FlowPane
import javafx.geometry.Orientation
import scalafx.beans.property.{ReadOnlyProperty, Property, ObjectProperty}

/**
 * Created with IntelliJ IDEA.
 * User: nmrp3
 * Date: 18/12/12
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
object GlyphBrowser extends JFXApp {
  stage = new Stage {
    title = "SBOL Visual Glyphs"
    width = 800
    height = 640

    scene = new Scene {

      val currentGlyph: ObjectProperty[Option[Glyph]] = ObjectProperty.apply(None)

      fill = Color.ANTIQUEWHITE
      content = new FlowPane {
        orientation = Orientation.HORIZONTAL
        content = List(
          new ListView(Glyph.glyphs) {
            cellFactory = TextFieldListCell.forListView(new StringConverter[Glyph] {
              def fromString(string: String) = ???
              def toString(g: Glyph) = g.name
            })
            currentGlyph <-- selectionModel().selectedItem using { g => Option(g) }
          },
          new FlowPane {
            orientation = Orientation.VERTICAL
            content = List(
              new Label {
                text <-- currentGlyph using { _ map (_.name) getOrElse "<select glyph>"  }
              },
              new FlowPane {
                currentGlyph.onChange { (_, _, og) => og match {
                  case None => content.clear()
                  case Some(g) =>
                    content.clear()
                    content.add(Glyph.glyphFor(g))
                }}
              }
            )
          }
        )
      }
    }
  }


  implicit class MappableProperty[A <: AnyRef](prop: Property[A, A]) {
    def <-- [B <: AnyRef](bp: ReadOnlyProperty[B, B]) = new {
      def using(f: B => A) = {
        prop.value = f(bp.value)
        bp.onChange { (_, _, newVal) => prop.value = f(newVal) }
      }
    }
  }
}
