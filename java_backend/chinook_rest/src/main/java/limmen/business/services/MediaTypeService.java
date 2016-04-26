package limmen.business.services;

import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.MediaTypeFilter;
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
     * Method to get all mediaTypes (filtered).
     *
     * @param mediaTypeFilter properties to filter the list of mediaTypes on
     * @return list of filtered mediaTypes
     */
    public List<MediaTypeEntity> getAllMediaTypes(MediaTypeFilter mediaTypeFilter) throws SortException;
    
    /**
     * Method to get a mediatype with a specified id.
     *
     * @param mediaTypeId id of the mediatype.
     * @return Mediatype with the specified id.
     */
    public MediaTypeEntity getMediaType(int mediaTypeId);

    /**
     * Method to create a new mediaType.
     *
     * @param mediaType data of the mediaType to create
     * @return the created  mediaType
     */
    public MediaTypeEntity createNewMediaType(MediaTypeEntity mediaType);

    /**
     * Method to update a mediaType.
     *
     * @param mediaType mediaType to update
     * @return updated mediaType
     */
    public MediaTypeEntity updateMediaType(MediaTypeEntity mediaType);

    /**
     * Method to update the list of mediaTypes
     *
     * @param mediaTypes data to update mediaTypes list with
     * @return new list of mediaTypes
     */
    public List<MediaTypeEntity> updateMediaTypes(List<MediaTypeEntity> mediaTypes);

    /**
     * Method to delete a mediaType.
     *
     * @param mediaTypeId id of the mediaType to delete
     * @return the deleted mediaType.
     */
    public MediaTypeEntity deleteMediaType(int mediaTypeId);
}
