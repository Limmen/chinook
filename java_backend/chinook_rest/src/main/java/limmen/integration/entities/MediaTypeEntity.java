package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * POJO that represents a MediaType entity from the chinook database
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class MediaTypeEntity {
    @NotNull
    private int mediaTypeId;
    @Size(max = 120)
    private String name;

    /**
     * Class constructor. Initializes an entity class.
     *
     * @param mediaTypeId id of the mediatype, unique.
     * @param name name of the mediatype.
     */
    public MediaTypeEntity(int mediaTypeId, String name) {
        this.mediaTypeId = mediaTypeId;
        this.name = name;
    }

    public MediaTypeEntity(){}

    public int getMediaTypeId() {
        return mediaTypeId;
    }

    public void setMediaTypeId(int mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    public String getName() {
        return name;
    }
}
