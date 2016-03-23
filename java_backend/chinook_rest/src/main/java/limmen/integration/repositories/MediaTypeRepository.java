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
 * @author Kim Hammar on 2016-03-22.
 */
@Repository
public class MediaTypeRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbc;

    public MediaTypeEntity getMediaType(int mediaTypeId) {
        return jdbc.queryForObject("SELECT * FROM \"MediaType\" WHERE \"MediaTypeId\"=?", MediaTypeMapper, mediaTypeId);
    }

    public List<MediaTypeEntity> getAllMediaTypes(){
        log.info("getAllMediaTypes from Database");
        return jdbc.query("SELECT * FROM \"MediaType\";", MediaTypeMapper);
    }

    private static final RowMapper<MediaTypeEntity> MediaTypeMapper = new RowMapper<MediaTypeEntity>() {
        public MediaTypeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            MediaTypeEntity mediaTypeEntity = new MediaTypeEntity(rs.getInt("MediaTypeId"), rs.getString("Name"));
            return mediaTypeEntity;
        }
    };
}
