import java.io.{ByteArrayOutputStream, File, IOException}
import java.lang

import org.apache.avro.Schema
import org.apache.avro.generic.{GenericData, GenericDatumWriter, GenericRecord}
import org.apache.avro.io.{DatumWriter, EncoderFactory}
import org.apache.avro.mapred.{AvroKey, AvroValue}
import org.apache.avro.mapreduce.{AvroJob, AvroKeyOutputFormat}
import org.apache.hadoop.conf.Configured
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{LongWritable, NullWritable, Text}
import org.apache.hadoop.mapreduce.lib.input.{FileInputFormat, TextInputFormat}
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.mapreduce.{Job, Mapper, Reducer}
import org.apache.hadoop.util.{Tool, ToolRunner}

/**
  * @author LanceDai 
  * @date 2019/5/17 11:15 
  * @description * 
  */
object AvroApp extends App {
  val parser: Schema.Parser = new Schema.Parser
  val schema: Schema = parser.parse(getClass.getResourceAsStream("StringPair2.avsc"))

  val datum: GenericRecord = new GenericData.Record(schema)
  datum.put("left", "L")
  datum.put("right", "R")

  val out: ByteArrayOutputStream = new ByteArrayOutputStream()
  val writer: DatumWriter[GenericRecord] = new GenericDatumWriter[GenericRecord](schema)

  val encoder = EncoderFactory.get().binaryEncoder(out, null)

  writer.write(datum, encoder)
  encoder.flush()
  out.close()
}

class AvroGenericMaxTemperature extends Configured with Tool {
  private val SCHEMA: Schema = new Schema.Parser()
    .parse(new File(
      getClass.getResource("WeatherRecord.avsc").toURI))

  class MaxTemperatureMapper extends Mapper[LongWritable, Text, AvroKey[Integer], AvroValue[GenericRecord]] {
    private val parser = new NcdcRecordParser
    private val record = new GenericData.Record(SCHEMA)

    @throws[IOException]
    @throws[InterruptedException]
    override protected def map(key: LongWritable, value: Text,
                               context: Mapper[LongWritable, Text,
                                 AvroKey[Integer],
                                 AvroValue[GenericRecord]]#Context): Unit = {
      parser.parse(value.toString)
      if (parser.isValidTemperature) {
        record.put("year", parser.getYearInt)
        record.put("temperature", parser.getAirTemperature)
        record.put("stationId", parser.getStationId)
        context.write(new AvroKey[Integer](parser.getYearInt),
          new AvroValue[GenericRecord](record))
      }
    }
  }

  class MaxTemperatureReducer extends Reducer[AvroKey[Integer],
    AvroValue[GenericRecord], AvroKey[GenericRecord], NullWritable] {

    @throws[IOException]
    @throws[InterruptedException]
    override protected def reduce(key: AvroKey[Integer],
                                  values: lang.Iterable[AvroValue[GenericRecord]],
                                  context: Reducer[AvroKey[Integer],
                                    AvroValue[GenericRecord],
                                    AvroKey[GenericRecord], NullWritable]#Context): Unit = {
      var max: GenericRecord = null
      values.forEach(value => {
        val record = value.datum()
        if (max == null ||
          record.get("temperature").asInstanceOf[Integer]
            > max.get("temperature").asInstanceOf[Integer])
          max = newWeatherRecord(record)
      })
      context.write(new AvroKey[GenericRecord](max), NullWritable.get)
    }

    private def newWeatherRecord(value: GenericRecord): GenericData.Record = {
      val record = new GenericData.Record(SCHEMA)
      record.put("year", value.get("year"))
      record.put("temperature", value.get("temperature"))
      record.put("stationId", value.get("stationId"))
      record
    }
  }

  @throws[Exception]
  override def run(args: Array[String]): Int = {
    if (args.length != 2) {
      System.err.printf("Usage: %s [generic options] <input> <output>\n", getClass.getSimpleName)
      ToolRunner.printGenericCommandUsage(System.err)
      return -1
    }
    val job = Job.getInstance(getConf, "Max temperature")
    job.setJarByClass(getClass)
    //    job.getConfiguration.setBoolean(Job.MAPREDUCE_JOB_USER_CLASSPATH_FIRST, true)
    FileInputFormat.addInputPath(job, new Path(args(0)))
    FileOutputFormat.setOutputPath(job, new Path(args(1)))
    AvroJob.setMapOutputKeySchema(job, Schema.create(Schema.Type.INT))
    AvroJob.setMapOutputValueSchema(job, SCHEMA)
    AvroJob.setOutputKeySchema(job, SCHEMA)
    job.setInputFormatClass(classOf[TextInputFormat])
    job.setOutputFormatClass(classOf[AvroKeyOutputFormat[_]])
    job.setMapperClass(classOf[AvroGenericMaxTemperature#MaxTemperatureMapper])
    job.setReducerClass(classOf[AvroGenericMaxTemperature#MaxTemperatureReducer])
    if (job.waitForCompletion(true)) 0
    else 1
  }

  @throws[Exception]
  def main(args: Array[String]): Unit = {
    val exitCode = ToolRunner.run(new AvroGenericMaxTemperature, args)
    System.exit(exitCode)
  }
}

import java.text._
import java.util.Date
import org.apache.hadoop.io.Text


object NcdcRecordParser {
  private val MISSING_TEMPERATURE = 9999
  private val DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmm")
}

class NcdcRecordParser {
  private var stationId: String = _
  private var observationDateString: String = _
  private var year: String = _
  private var airTemperatureString: String = _
  private var airTemperature = 0
  private var airTemperatureMalformed = false
  private var quality: String = _

  def parse(record: String): Unit = {
    stationId = record.substring(4, 10) + "-" + record.substring(10, 15)
    observationDateString = record.substring(15, 27)
    year = record.substring(15, 19)
    airTemperatureMalformed = false
    // Remove leading plus sign as parseInt doesn't like them (pre-Java 7)
    if (record.charAt(87) == '+') {
      airTemperatureString = record.substring(88, 92)
      airTemperature = airTemperatureString.toInt
    }
    else if (record.charAt(87) == '-') {
      airTemperatureString = record.substring(87, 92)
      airTemperature = airTemperatureString.toInt
    }
    else airTemperatureMalformed = true
    airTemperature = airTemperatureString.toInt
    quality = record.substring(92, 93)
  }

  def parse(record: Text): Unit = {
    parse(record.toString)
  }

  def isValidTemperature: Boolean = !airTemperatureMalformed && airTemperature != NcdcRecordParser.MISSING_TEMPERATURE && quality.matches("[01459]")

  def isMalformedTemperature: Boolean = airTemperatureMalformed

  def isMissingTemperature: Boolean = airTemperature == NcdcRecordParser.MISSING_TEMPERATURE

  def getStationId: String = stationId

  def getObservationDate: Date = try {
    System.out.println(observationDateString)
    NcdcRecordParser.DATE_FORMAT.parse(observationDateString)
  } catch {
    case e: ParseException =>
      throw new IllegalArgumentException(e)
  }

  def getYear: String = year

  def getYearInt: Int = year.toInt

  def getAirTemperature: Int = airTemperature

  def getAirTemperatureString: String = airTemperatureString

  def getQuality: String = quality
}

