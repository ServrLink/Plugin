package link.servr.servrlink.core.api.responses;

public class RetrievedID {

    /**
     * True if the request to the registry was successful
     */
    public boolean success;

    /**
     * The user's Discord ID.
     * null if the user is not registered
     */
    public Long id;

    public RetrievedID(boolean success, Long id) {
        this.success = success;
        this.id = id;
    }
}
