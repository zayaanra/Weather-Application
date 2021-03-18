package gui

import app.ButtonListener
import scalafx.geometry.Pos
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.image.{Image, ImageView}

object GUI extends JFXApp {
  /* Load image in */
  def load(url: String): ImageView = {
    val image: Image = new Image(url)
    new ImageView(image)
  }

  /* Create text box to take in user input */
  val searchBox: TextField = new TextField {
    editable = true
    style = "-fx-font: 18 ariel;"
  }

  val feelsLike, temp: TextField = new TextField {
    editable = false
    style = "-fx-font: 18 ariel;"
  }

  /* If the user enters input incorrectly, the TRY message will become visible */
  val TRY: Label = new Label {
    text =  "Re-enter in the correct format."
    visible = false
  }

  /* New Stage */
  this.stage = new PrimaryStage {
    title = "Weather Application"
    width = 800
    height = 600

    scene = new Scene(500, 500) {

      val button: Button = new Button {
        minWidth = 50; minHeight = 39.5
        style = "-fx-font: 14 ariel;"
        text = "Enter"
        onAction = new ButtonListener(searchBox, feelsLike, temp)
      }

      val input, fl, tp, WARNING: Label = new Label
      input.text = "CITY, STATE:"; input.layoutX = 10
      fl.text = "Feels Like:"
      tp.text = "Temperature (in Fahrenheit):"
      WARNING.text = "Please enter in the format: CITY-STATE (example: ATLANTA-GA)"

      /* Create horizontal box to hold label, search box, and "Try again" message */
      val horizontalBox: HBox = new HBox() {
        children = List(input, searchBox, TRY)
      }
      /* Create a vertical box to hold the WARNING, horizontal box, and other labels */
      val verticalBox: VBox = new VBox() {
        children = List(WARNING, horizontalBox, fl, feelsLike, tp, temp)
        layoutX = 250
        layoutY = 150
      }

      /* Set alignment */
      verticalBox.getChildren.add(button)
      verticalBox.setAlignment(Pos.CenterLeft)

      /* Contains horizontalBox and a textField */
      content = List(verticalBox)
    }
  }
}


