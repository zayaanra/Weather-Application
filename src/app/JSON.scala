package app

import play.api.libs.json.{JsValue, Json}
import scalaj.http.Http


object JSON {
  /* Go to OpenWeather with API key and retrieve JSON data, parse it to retrieve data as a Tuple(String, String) */
  def weatherData(city: String, state: String): (String, String) = {
    val url: String = "http://api.openweathermap.org/data/2.5/weather?q=" +
      city + "," + state + "}&appid=" + Config.api_key
    val response = Http(url).asString.body
    val parsed: JsValue = Json.parse(response)
    getJSON(parsed)
  }

  /* Retrieve JSON data and convert to Tuple(String, String) */
  def getJSON(parsed: JsValue): (String, String) = {
    val main = (parsed \ "main").as[Map[String, JsValue]]
    (main.filter(_._1 == "feels_like").head._2.toString(), main.filter(_._1 == "temp").head._2.toString())
  }
}
