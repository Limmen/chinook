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
        log.debug("getMediaTypeEntity {} from Database", mediaTypeId);
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


    /**
     * Method to update the database with a new mediaTypeEntity.
     *
     * @param mediaTypeEntity mediaTypeEntity to insert
     * @return the inserted mediaTypeEntity
     */
    public MediaTypeEntity createNewMediaType(MediaTypeEntity mediaTypeEntity) {
        log.info("Update Database with new MediaTypeEntity. mediaTypeId: {}, name: {}", mediaTypeEntity.getMediaTypeId(), mediaTypeEntity.getName());
        jdbc.update("INSERT INTO \"MediaType\" (\"MediaTypeId\", \"Name\") VALUES (?, ?);", mediaTypeEntity.getMediaTypeId(), mediaTypeEntity.getName());
        return mediaTypeEntity;
    }

    /**
     * Method to query the database for the maximum id of all mediaTypeEntity
     *
     * @return maxmum id
     */
    public int getMaxId() {
        log.debug("get max Id of mediaTypeEntity");
        return jdbc.queryForObject("SELECT COALESCE(MAX(\"MediaTypeId\"),0) FROM \"MediaType\";", maxIdMapper);
    }

    /**
     * Method to update the database with new data for a certain mediaTypeEntity.
     *
     * @param mediaTypeEntity data to update
     * @return updated mediaTypeEntity
     */
    public MediaTypeEntity updateMediaType(MediaTypeEntity mediaTypeEntity) {
        log.debug("update mediaTypeEntity {}", mediaTypeEntity.getMediaTypeId());
        jdbc.update("UPDATE \"MediaType\" SET \"Name\" = ? WHERE \"MediaTypeId\" = ?;", mediaTypeEntity.getName(), mediaTypeEntity.getMediaTypeId());
        return mediaTypeEntity;
    }

    /**
     * Method to delete mediaTypeEntity from the database.
     *
     * @param mediaTypeId id of the mediaTypeEntity to delete
     */
    public void deleteMediaType(int mediaTypeId) {
        log.debug("delete mediaTypeEntity {}", mediaTypeId);
        jdbc.update("DELETE FROM \"MediaType\" WHERE \"MediaTypeId\" = ?;", mediaTypeId);
    }

    /**
     * Method to delete all mediaTypeEntity from the database.
     */
    public void deleteMediaTypes() {
        log.debug("delete all mediaTypeEntity");
        jdbc.update("DELETE  * FROM \"MediaType\";");
    }
    
    private static final RowMapper<MediaTypeEntity> mediaTypeMapper = new RowMapper<MediaTypeEntity>() {
        public MediaTypeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            MediaTypeEntity mediaTypeEntity = new MediaTypeEntity(rs.getInt("MediaTypeId"), rs.getString("Name"));
            return mediaTypeEntity;
        }
    };

    private static final RowMapper<Integer> maxIdMapper = new RowMapper<Integer>() {
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt(1);
            return id;
        }
    };
}
