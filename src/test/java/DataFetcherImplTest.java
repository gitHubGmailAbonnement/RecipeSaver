import fetcher.DataFetcher;
import fetcher.DataFetcherImpl;
import fetcher.JsoupWrapper;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class DataFetcherImplTest {
    @Autowired
    DataFetcher classUnderTest;
    @Mock
    JsoupWrapper wrapper;
    @BeforeEach
    void setup()
    {
        classUnderTest = new DataFetcherImpl(wrapper);
    }
    @Test
    void fetchData() throws IOException {
        Document success = new Document("URL");
        when(wrapper.connect(any())).thenReturn(success);
        Document result = classUnderTest.fetchData("https://www.livewellbakeoften.com/scone-recipe/#recipe");
        assertNotNull(result);
    }

    @Test
    void fetchDataError() throws IOException {
        Document success = new Document("URL");
        when(wrapper.connect(any())).thenThrow(new IOException("Error when connecting to site"));
        Document result = classUnderTest.fetchData("https://www.livewellbakeoften.com/scone-recipe/#recipe");
        assertNull(result);
    }
}