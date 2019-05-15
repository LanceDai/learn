import org.apache.hadoop.conf.{Configuration, Configured}
import org.apache.hadoop.util.{Tool, ToolRunner}

class ConfigurationPrinter extends Configured with Tool {
  Configuration.addDefaultResource("hdfs-default.xml")
  Configuration.addDefaultResource("hdfs-site.xml")
  Configuration.addDefaultResource("yarn-default.xml")
  Configuration.addDefaultResource("yarn-site.xml")
  Configuration.addDefaultResource("mapred-default.xml")
  Configuration.addDefaultResource("mapred-site.xml")

  override def run(strings: Array[String]): Int = {
    val conf = getConf
    conf.forEach(entry => {
      printf("%s=%s\n", entry.getKey, entry.getValue)
    })
    0
  }
}

object ConfigurationPrinter {
  def main(args: Array[String]): Unit = {
    val exitCode = ToolRunner.run(new ConfigurationPrinter, args)
    System.exit(exitCode)
  }
}
