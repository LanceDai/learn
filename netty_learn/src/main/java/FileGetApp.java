import io.netty.util.CharsetUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author LanceDai
 * @date 2019/6/8 09:29
 * @description *
 */
public class FileGetApp {
    public static void main(String[] args) throws IOException {
        String fileName = "软件工程-3.doc";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        File inFile = new File("D:\\WorkSpace\\JavaWorkSpace\\learn\\netty_learn\\src\\main\\resources\\source.html");
        Document document = Jsoup.parse(inFile, CharsetUtil.UTF_8.name());
//        System.out.println("document.body().toString() = " + document.body().toString());
        Elements elements = document.select(".list-group-item > .row");
        int count = 1;
        Elements res = new Elements();
        for (Element element : elements) {
            if (count == 1) {
                res.add(element);
            }
            count ^= 1;
        }
        FileWriter fileWriter = new FileWriter(file, true);
        res.forEach(element -> {
            Elements divs = element.select("div");
            String inputStr = divs.stream()
                    .map(Element::ownText)
                    .flatMap((Function<String, Stream<String>>) Stream::of)
                    .collect(Collectors.joining());
            System.out.println("inputStr = " + inputStr);
            try {
                fileWriter.write(inputStr + '\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fileWriter.flush();
        fileWriter.close();
    }
}
