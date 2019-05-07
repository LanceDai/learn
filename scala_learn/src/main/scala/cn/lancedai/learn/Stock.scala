package cn.lancedai.learn


// a poorly written class
//class Stock(var symbol: String, var company: String, var price: BigDecimal, var volumn: Long) {
//  var html: String = _
//
//  def buildUrl(stockSymbol: String): String = {
//    "..."
//  }
//
//  def getUrlContent(url: String): String = {
//    "..."
//  }
//
//
//  def setPriceFromHtml(html:String): Unit ={
//    this.price = BigDecimal.apply(0)
//  }
//
//
//}

case class Stock(symbol: String, company: String)

case class StockInstance(symbol: String,
                         datetime: String,
                         price: BigDecimal,
                         volume: Long)

object NetworkUtils {
  def getUrlContent(url: String): String = {
    "..."
  }
}

object StockUtils {
  def buildUrl(stockSymbol: String): String = {
    "..."
  }

  def getPrice(symbol: String, html: String): String = {
    "..."
  }

  def getVolume(symbol: String, html: String): String = {
    "..."
  }

  def getHigh(symbol: String, html: String): String = {
    "..."
  }

  def getLow(symbol: String, html: String): String = {
    "..."
  }
}

object DateUtils {
  def currentDate: String = {
    "..."
  }

  def currentTime: String = {
    "..."
  }
}

object StockTest extends App {
  val stock = Stock("AAPL", "Apple")
  val url = StockUtils.buildUrl(stock.symbol)
  val html = NetworkUtils.getUrlContent(url)

  val price = StockUtils.getPrice(stock.symbol, html)
  val volume = StockUtils.getVolume(stock.symbol, html)
  val high = StockUtils.getHigh(stock.symbol, html)
  val low = StockUtils.getLow(stock.symbol, html)
  val date = DateUtils.currentDate
  val stockInstance = StockInstance(stock.symbol, date, BigDecimal(price), Long(volume))
}


