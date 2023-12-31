package fetcher;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class DataFetcherImpl<T> implements DataFetcher<T>{
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DataFetcherImpl.class));
    @Value("${data.fetcher.timeout}")
    private int timeout;
    private final JsoupWrapper wrapper;
    public DataFetcherImpl(JsoupWrapper wrapper)
    {
        this.wrapper = wrapper;
    }
    @Override
    @SuppressWarnings("unchecked")
    public T fetchData(String url) {
        try {
            return (T) wrapper.connect(url, timeout);

        } catch (IOException e) {
            LOGGER.severe("An error occured while trying to fetch HTML data for URL: "+url);
        }
        return null;
    }
}
