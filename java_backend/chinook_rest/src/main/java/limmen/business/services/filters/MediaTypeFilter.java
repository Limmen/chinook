package limmen.business.services.filters;

import limmen.integration.entities.MediaTypeEntity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for implementing filtering and sorting functionality for the MediaTypeEntity-Resource.
 *
 * @author Kim Hammar on 2016-04-25.
 */
public class MediaTypeFilter {

    private String sort;
    private String mediaTypeId;
    private String name;

    /**
     * Default constructor
     */
    public MediaTypeFilter() {
    }

    /**
     * Method to filter a list of mediaTypeEntitys.
     *
     * @param mediaTypeEntitys list to filter
     * @return filtered list
     */
    public List<MediaTypeEntity> filter(List<MediaTypeEntity> mediaTypeEntitys) {
        if (name != null)
            mediaTypeEntitys = mediaTypeEntitys.stream().filter(mediaTypeEntity -> {if(mediaTypeEntity.getName() != null)
                return mediaTypeEntity.getName().equals(name); else return false;}).collect(Collectors.toList());
        if (mediaTypeId != null)
            mediaTypeEntitys = mediaTypeEntitys.stream().filter(mediaTypeEntity -> mediaTypeEntity.getMediaTypeId() == Integer.parseInt(mediaTypeId)).collect(Collectors.toList());
        return mediaTypeEntitys;
    }

    /**
     * Method to sort a list of mediaTypeEntitys.
     *
     * @param mediaTypeEntitys list to sort
     * @return sorted list
     */
    public List<MediaTypeEntity> sort(List<MediaTypeEntity> mediaTypeEntitys) {
        if(sort == null)
            return mediaTypeEntitys;
        String order = sort.substring(0, 1);
        String property = sort.substring(1, sort.length());
        Comparator<MediaTypeEntity> comparator = null;
        if (property.equals("mediaTypeId")) {
            comparator = (mediaTypeEntity1, mediaTypeEntity2) ->
            {
                if (mediaTypeEntity1.getMediaTypeId() > mediaTypeEntity2.getMediaTypeId())
                    return 1;
                else if (mediaTypeEntity1.getMediaTypeId() < mediaTypeEntity2.getMediaTypeId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("name")) {
            comparator = (mediaTypeEntity1, mediaTypeEntity2) ->
            {
                if(mediaTypeEntity1.getName() == null && mediaTypeEntity2.getName() == null)
                    return 0;
                if(mediaTypeEntity1.getName() == null)
                    return -1;
                if(mediaTypeEntity2.getName() == null)
                    return 1;
                if (mediaTypeEntity1.getName().compareTo(mediaTypeEntity2.getName()) > 0)
                    return 1;
                else if (mediaTypeEntity1.getName().compareTo(mediaTypeEntity2.getName()) < 0)
                    return -1;
                return 0;
            };
        }
        Collections.sort(mediaTypeEntitys, comparator);
        if (order.equals("-")) {
            Collections.reverse(mediaTypeEntitys);
        }
        return mediaTypeEntitys;
    }


    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setMediaTypeId(String mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    public String getMediaTypeId() {
        return mediaTypeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
