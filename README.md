
# Introduction  
This is the repository for the ServrLink public plugin that is installed on public Minecraft servers. It features a developer API that can be used to query a [Registry](https://github.com/ServrLink/Registry), as well as providing a /link command that provides players with instructions on how to link their accounts.  
  
# Javadocs  
You can view the Javadocs for the API [here](https://servrlink.github.io/Plugin/).  
  
# Example  
  
```java  
String baseURL = "https://go.servr.link"; // Base URL that points to a Registry instance  
ServrLinkAPI api = new ServrLinkAPI(baseURL); // Create an API instance  
  
Registered registered;  
boolean success;  
boolean isRegistered;  
try {  
  // Synchronously check if a Minecraft UUID is registered in the Registry  
  registered = api.isRegistered(minecraftUuid); // Checks whether a Minecraft UUID is registered in the Registry  
  success = registered.success; // Returns true if the request to the Registry was made successfully  
  isRegistered = registered.isRegistered; // Returns true if the UUID was found in the registry  
  
  // Synchronously check if a Discord ID is registered in the Registry  
  registered = api.isRegistered(discordId); // Checks whether a Discord ID is registered in the Registry  
  success = registered.success; // Returns true if the request to the Registry was made successfully  
  isRegistered = registered.isRegistered; // Returns true if the Discord ID was found in the registry  
  
  // Asynchronously check if a Minecraft UUID is registered in the Registry
  api.isRegistered(minecraftUuid, new Callback<Registered>() {  
    @Override  
    public void run(Registered registered) {  
      boolean success = registered.success; // Returns true if the request to the Registry was made successfully  
      boolean isRegistered = registered.isRegistered; // Returns true if the UUID was found in the registry  
    }  
  
    // Ran if any exceptions occur while processing the request  
    @Override  
    public void onError(Exception ex) {  
      ex.printStackTrace();  
    }  
  });  
  
  // Asynchronously check if a Discord ID is registered in the Registry  
  api.isRegistered(discordId, new Callback<Registered>() {  
    @Override  
    public void run(Registered registered) {  
      boolean success = registered.success; // Returns true if the request to the Registry was made successfully  
      boolean isRegistered = registered.isRegistered; // Returns true if the Discord ID was found in the registry  
    }  
  
     // Ran if any exceptions occur while processing the request  
    @Override  
    public void onError(Exception ex) {  
      ex.printStackTrace();  
    }  
  });  
  
  // Synchronously get a Discord ID by Minecraft UUID  
  RetrievedID retrievedID = api.getDiscordID(minecraftUuid);  
  success = retrievedID.success; // Returns true if the request to the Registry was made successfully  
  String id = retrievedID.id; // The associated Discord ID. A null value will be returned if the Minecraft UUID was not present in the registry
  
 // Synchronously get a Minecraft UUID by Discord ID  
  RetrievedUUID retrievedUUID = api.getUUID(discordId);  
  success = retrievedUUID.success; // Returns true if the request to the Registry was made successfully  
  UUID uuid = retrievedUUID.uuid; // The associated Minecraft UUID. A null value will be returned if the Discord ID was not present in the registry  
  
 // Asynchronously get a Discord ID by Minecraft UUID
   api.getDiscordID(minecraftUuid, new Callback<RetrievedID>() {  
     @Override  
     public void run(RetrievedID retrievedID) {  
       boolean success = retrievedID.success; // Returns true if the request to the Registry was made successfully  
       String id = retrievedID.id; // The associated Discord ID. A null value will be returned if the Minecraft UUID was not present in the registry
    }  
  
    // Ran if any exceptions occur while processing the request  
    @Override  
    public void onError(Exception ex) {  
      ex.printStackTrace();  
    }  
  });  
  
  // Asynchronously get a Minecraft UUID by Discord ID  
  api.getDiscordID(minecraftUuid, new Callback<RetrievedID>() {  
    @Override  
    public void run(RetrievedID retrievedID) {  
      boolean success = retrievedUUID.success; // Returns true if the request to the Registry was made successfully  
      UUID uuid = retrievedUUID.uuid; // The associated Minecraft UUID. A null value will be returned if the Discord ID was not present in the registry  
    }  
  
    // Ran if any exceptions occur while processing the request  
    @Override  
    public void onError(Exception ex) {  
      ex.printStackTrace();  
    }  
  });  
} catch(IOException ex) {  
    ex.printStackTrace();  
}  
```
