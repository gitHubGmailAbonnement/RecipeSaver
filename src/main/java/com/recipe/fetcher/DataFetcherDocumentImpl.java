package com.recipe.fetcher;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class DataFetcherDocumentImpl implements DataFetcher<Document>{
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DataFetcherDocumentImpl.class));

    private final int timeout;
    private final JsoupWrapper wrapper;

    public DataFetcherDocumentImpl( @Value("${data.fetcher.timeout}") int timeout, JsoupWrapper wrapper) {
        this.timeout = timeout;
        this.wrapper = wrapper;
    }

    @Override
    public Document fetchData(String url) {
        try {
            return wrapper.connect(url, timeout);

        } catch (IOException e) {
            LOGGER.severe("An error occured while trying to fetch HTML data for URL: "+url);
            return null;
        }

    }
}
