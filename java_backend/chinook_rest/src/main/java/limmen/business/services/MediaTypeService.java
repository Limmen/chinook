package limmen.business.services;

import limmen.integration.entities.MediaTypeEntity;

import java.util.List;

/**
 * Interface for a service that provides CRUD services for mediatype-data.
 *
 * @author Kim Hammar on 2016-03-22.
 */
public interface MediaTypeService {

    /**
     * Method to get all mediatypes.
     *
     * @return list of mediatypes.
     */
    public List<MediaTypeEntity> getAllMediaTypes();

    /**
     * Method to get a mediatype with a specified id.
     *
     * @param mediaTypeId id of the mediatype.
     * @return Mediatype with the specified id.
     */
    public MediaTypeEntity getMediaType(int mediaTypeId);
}
