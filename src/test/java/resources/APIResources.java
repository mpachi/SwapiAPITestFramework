package resources;

public enum APIResources {

    People3("people/3"),
    Starships9("starships/9/"),
    Species3("species/3"),
    Planets14("planets/14/");
    private String resource;
    APIResources(String endpoint){
     this.resource=endpoint;
    }

    public String getResource()
    {
        return resource;
    }

}
