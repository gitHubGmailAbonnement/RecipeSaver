package fetcher;

public class WebData<T> {

    private T data;

    public WebData(T data)
    {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
