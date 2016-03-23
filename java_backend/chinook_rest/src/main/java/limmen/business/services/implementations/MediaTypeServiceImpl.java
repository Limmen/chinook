package limmen.business.services.implementations;

import limmen.business.services.MediaTypeService;
import limmen.integration.entities.MediaTypeEntity;
import limmen.integration.repositories.MediaTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
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
    public List<MediaTypeEntity> getAllMediaTypes() {
        return mediaTypeRepository.getAllMediaTypes();
    }

    @Override
    public MediaTypeEntity getMediaType(int mediaTypeId) {
        return mediaTypeRepository.getMediaType(mediaTypeId);
    }
}
