package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public class MediaTypeEntity {
    @NotNull
    private int mediaTypeId;
    @Size(max = 120)
    private String name;

    public MediaTypeEntity(int mediaTypeId, String name) {
        this.mediaTypeId = mediaTypeId;
        this.name = name;
    }

    public int getMediaTypeId() {
        return mediaTypeId;
    }

    public String getName() {
        return name;
    }
}
