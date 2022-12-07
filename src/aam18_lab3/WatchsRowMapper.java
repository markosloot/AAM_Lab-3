package aam18_lab3;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class WatchsRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        PersonResultSetExtractor extractor = new PersonResultSetExtractor();
        return extractor.extractData(rs);
    }
    
    class PersonResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            Watchs watchs = new Watchs();
            watchs.setId(rs.getInt("id"));
            watchs.setBrand(rs.getString("Brand"));
            watchs.setMechanism(rs.getString("Mechanism"));
            return watchs;
        }
    }
}
