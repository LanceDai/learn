import java.io.InputStream
import java.net.{URI, URL}

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, FsUrlStreamHandlerFactory, Path}
import org.apache.hadoop.io.IOUtils

object HadoopFileOperationApp {

  val fs: FileSystem = FileSystem.get(
    new URI("hdfs://hadoop-master:9000"),
    new Configuration(), "root")


  def uploadFile(srcPath: String, dstPath: String): Unit = {
    val src = new Path(srcPath)
    val dst = new Path(dstPath)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
    fs.copyFromLocalFile(src, dst)
  }

  def createFile() = {}

  def viewFile(filename: String): Unit = {
    var in = None: Option[InputStream]
    val filePath = PREFIX + filename
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

  def main(args: Array[String]): Unit = {

  }
}
