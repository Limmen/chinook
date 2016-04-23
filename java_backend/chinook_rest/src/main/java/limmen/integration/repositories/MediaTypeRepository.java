package limmen.integration.repositories;

import limmen.integration.entities.MediaTypeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * CRUD-repository for the "MediaType" table in the chinook database.
 *
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class MediaTypeRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * Method to query the database for a mediatype with a certain id.
     *
     * @param mediaTypeId id of the mediatype.
     * @return MediaType with the specified id.
     */
    public MediaTypeEntity getMediaType(int mediaTypeId) {
        log.debug("getMediaTypeEntity from Database");
        return jdbc.queryForObject("SELECT * FROM \"MediaType\" WHERE \"MediaTypeId\"=?", mediaTypeMapper, mediaTypeId);
    }

    /**
     * Method to query the database for all mediatypes.
     *
     * @return list of mediatypes.
     */
    public List<MediaTypeEntity> getAllMediaTypes(){
        log.debug("getAllMediaTypes from Database");
        return jdbc.query("SELECT * FROM \"MediaType\";", mediaTypeMapper);
    }

    private static final RowMapper<MediaTypeEntity> mediaTypeMapper = new RowMapper<MediaTypeEntity>() {
        public MediaTypeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            MediaTypeEntity mediaTypeEntity = new MediaTypeEntity(rs.getInt("MediaTypeId"), rs.getString("Name"));
            return mediaTypeEntity;
        }
    };
}
