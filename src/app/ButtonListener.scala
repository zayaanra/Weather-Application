package app

import GUI.GUI
import javafx.event.{ActionEvent, EventHandler}
import scalafx.scene.control.TextField

class ButtonListener(input: TextField, feelsLike: TextField, temp: TextField) extends EventHandler[ActionEvent] {
  /* Check if user inputted correctly, if not, turn "TRY AGAIN" text to visible. If the input is correct then we will retrieve the weather data */
  override def handle(event: ActionEvent): Unit = {
    if(input.text.value.toUpperCase.matches("[A-Za-z]{1,17}[-][A-Za-z]{2}")) {
      GUI.TRY.visible = false
      val (city, state): (String, String) = identify(input.text.value.toUpperCase)
      val (feels, tmp) = getWeather(city, state)

      feelsLike.text.value = convert(feels.toDouble).toString
      temp.text.value = convert(tmp.toDouble).toString
    }
    else {
      GUI.TRY.visible = true
    }
  }
  def getWeather(city: String, state: String): (String, String) = {
    JSON.weatherData(city, state)
  }

  /* Identify the user input */
  def identify(text: String): (String, String) = {
    val (city, state): (String, String) = text.splitAt(text.indexOf("-"))
    (city, state.replace("-", " "))
  }

  /* Convert Kelvin to Fahrenheit */
  def convert(k: Double): Double = {
    (k * (9.0/5.0)) - 459.67
  }
}
