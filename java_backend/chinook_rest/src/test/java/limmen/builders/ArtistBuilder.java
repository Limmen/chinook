package limmen.builders;

import limmen.integration.entities.Artist;

/**
 * @author Kim Hammar on 2016-03-24.
 */
public class ArtistBuilder {
    public static int DEFAULT_ID = 1;
    public static String DEFAULT_NAME = "John Doe";

    private int artistId = DEFAULT_ID;
    private String name = DEFAULT_NAME;

    private ArtistBuilder(){}

    public static ArtistBuilder aArtist(){
        return new ArtistBuilder();
    }

    public ArtistBuilder withId(int artistId){
        this.artistId = artistId;
        return this;
    }

    public ArtistBuilder withName(String name){
        this.name = name;
        return this;
    }

    public Artist build(){
        return new Artist(artistId, name);
    }

}
