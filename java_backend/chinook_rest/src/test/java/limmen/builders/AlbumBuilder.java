package limmen.builders;

import limmen.integration.entities.Album;

/**
 * @author Kim Hammar on 2016-03-24.
 */
public class AlbumBuilder {
    public static int DEFAULT_ID = 1;
    public static int DEFAULT_ARTIST_ID = 1;
    public static String DEFAULT_TITLE = "John Doe";

    private int albumId = DEFAULT_ID;
    private int artistId = DEFAULT_ARTIST_ID;
    private String title = DEFAULT_TITLE;

    private AlbumBuilder(){}

    public static AlbumBuilder aAlbum(){
        return new AlbumBuilder();
    }

    public AlbumBuilder withId(int albumId){
        this.albumId = albumId;
        return this;
    }
    public AlbumBuilder withArtistId(int artistId){
        this.artistId = artistId;
        return this;
    }
    public AlbumBuilder withTitle(String title){
        this.title = title;
        return this;
    }

    public Album build(){
        return new Album(albumId, title, artistId);
    }

}
