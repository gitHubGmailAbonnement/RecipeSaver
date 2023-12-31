package fetcher;

public interface DataFetcher<T> {

T fetchData(String url);
}
