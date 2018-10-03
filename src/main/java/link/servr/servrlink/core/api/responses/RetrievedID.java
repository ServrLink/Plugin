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
    public String id;

    public RetrievedID(boolean success, String id) {
        this.success = success;
        this.id = id;
    }
}
