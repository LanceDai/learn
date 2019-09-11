import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSTest {
    public static void main(String[] args) {
        try {
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://localhost:9000");
            conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
            FileSystem fs = FileSystem.get(conf);
            String fileName = "/hadoop/input/entrypoint.sh";
            Path path = new Path(fileName);
            FSDataInputStream inputStream = fs.open(path);
            byte[] buff = new byte[1024];
            int length;
            while ((length = inputStream.read(buff)) != -1) {
                System.out.println(
                        new String(buff, 0, length));
            }
            System.out.println(
                    fs.getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
