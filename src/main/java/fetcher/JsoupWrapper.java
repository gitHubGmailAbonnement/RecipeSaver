package fetcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupWrapper {
    public Document connect(String url, int timeout) throws IOException {
        return Jsoup.connect(url).timeout(timeout).get();
    }
}
