package fetcher;

import com.recipe.fetcher.DataFetcher;
import com.recipe.fetcher.DataFetcherDocumentImpl;
import com.recipe.fetcher.JsoupWrapper;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataFetcherImplTest {
    @Autowired
    DataFetcher<Document> classUnderTest;

    @Mock
    JsoupWrapper wrapper;
    @BeforeEach
    void setup()
    {
        classUnderTest = new DataFetcherDocumentImpl(10, wrapper);

    }
    @Test
    void fetchData() throws IOException {
        Document success = new Document("URL");
        when(wrapper.connect(any(), anyInt())).thenReturn(success);
        Document result = classUnderTest.fetchData("https://www.livewellbakeoften.com/scone-recipe/#recipe");
        assertNotNull(result);
    }

    @Test
    void fetchDataError() throws IOException {
        when(wrapper.connect(any(), anyInt())).thenThrow(new IOException("Error when connecting to site"));
       Document result =  classUnderTest.fetchData("https://www.livewellbakeoften.com/scone-recipe/#recipe");
        assertNull(result);
    }
}