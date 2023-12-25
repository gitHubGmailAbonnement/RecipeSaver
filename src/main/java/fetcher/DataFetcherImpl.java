package fetcher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class DataFetcherImpl implements DataFetcher{
    private static Logger LOGGER = Logger.getLogger(String.valueOf(DataFetcherImpl.class));
    @Value("${data.fetcher.timeout}")
    private int timeout;
    private JsoupWrapper wrapper;
    public DataFetcherImpl(JsoupWrapper wrapper)
    {
        this.wrapper = wrapper;
    }
    @Override
    public <T> T fetchData(String url) {
        try {
        WebData<T> data= new WebData(wrapper.connect(url));
        return data.getData();
        } catch (IOException e) {
            LOGGER.severe("An error occured while trying to fetch HTML data for URL: "+url);
        }
        return null;
    }
}
