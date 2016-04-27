package limmen.business.services.implementations;

import limmen.business.services.MediaTypeService;
import limmen.business.services.exceptions.SortException;
import limmen.business.services.filters.MediaTypeFilter;
import limmen.integration.entities.MediaTypeEntity;
import limmen.integration.repositories.MediaTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the MediaTypeService interface, uses a repository for database interaction.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Service
public class MediaTypeServiceImpl implements MediaTypeService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final MediaTypeRepository mediaTypeRepository;

    @Inject
    public MediaTypeServiceImpl(final MediaTypeRepository mediaTypeRepository) {
        this.mediaTypeRepository = mediaTypeRepository;
    }
    @Override
    public List<MediaTypeEntity> getAllMediaTypes(MediaTypeFilter mediaTypeFilter) throws SortException {
        List<MediaTypeEntity> mediaTypes = getAllMediaTypes();
        mediaTypes = mediaTypeFilter.filter(mediaTypes);
        try {
            return mediaTypeFilter.sort(mediaTypes);
        } catch (Exception e) {
            throw new SortException("Invalid query string for sorting: " + mediaTypeFilter.getSort());
        }
    }

    @Override
    public List<MediaTypeEntity> getAllMediaTypes() {
        return mediaTypeRepository.getAllMediaTypes();
    }

    @Override
    public MediaTypeEntity getMediaType(int mediaTypeId) {
        return mediaTypeRepository.getMediaType(mediaTypeId);
    }

    @Override
    public MediaTypeEntity createNewMediaType(MediaTypeEntity mediaType) {
        mediaType.setMediaTypeId(mediaTypeRepository.getMaxId() + 1);
        return mediaTypeRepository.createNewMediaType(mediaType);
    }

    @Override
    public MediaTypeEntity updateMediaType(MediaTypeEntity mediaType) {
        return mediaTypeRepository.updateMediaType(mediaType);
    }

    @Override
    public List<MediaTypeEntity> updateMediaTypes(List<MediaTypeEntity> mediaTypes) {
        mediaTypeRepository.deleteMediaTypes();
        mediaTypes.forEach((mediaType) -> {
            createNewMediaType(mediaType);
        });
        return getAllMediaTypes();
    }

    @Override
    public MediaTypeEntity deleteMediaType(int mediaTypeId) {
        MediaTypeEntity mediaType = getMediaType(mediaTypeId);
        mediaTypeRepository.deleteMediaType(mediaTypeId);
        return mediaType;
    }
}
