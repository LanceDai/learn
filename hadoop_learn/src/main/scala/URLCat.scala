import java.io.InputStream
import java.net.URL

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory
import org.apache.hadoop.io.IOUtils

object URLCat {
  URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory())

  def main(args: Array[String]): Unit = {
    var in = None: Option[InputStream]
    val filePath = "hdfs://localhost:9000/output/part-r-00000"
    try {
      in = Some(new URL(filePath).openStream())
      assert(in.isDefined)
      IOUtils.copyBytes(in.get, System.out, 4096, false)
    }
    catch {
      case e: Exception => e.printStackTrace()
    } finally {
      IOUtils.closeStream(in.get)
    }
  }
}
