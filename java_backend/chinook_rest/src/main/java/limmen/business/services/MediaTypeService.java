package limmen.business.services;

import limmen.integration.entities.MediaTypeEntity;

import java.util.List;

/**
 * @author Kim Hammar on 2016-03-22.
 */
public interface MediaTypeService {

    public List<MediaTypeEntity> getAllMediaTypes();
    public MediaTypeEntity getMediaType(int mediaTypeId);
}
