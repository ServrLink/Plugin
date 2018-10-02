package link.servr.servrlink.core.api;

public interface Callback<T> {

    void run(T param);

    void onError(Exception ex);
}
