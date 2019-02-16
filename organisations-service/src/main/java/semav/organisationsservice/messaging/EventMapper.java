package semav.organisationsservice.messaging;

import java.sql.ResultSet;
import java.sql.SQLException;
import semav.organisationsservice.entity.Event;

import org.springframework.jdbc.core.RowMapper;

public class EventMapper implements RowMapper<Event> { 
   @Override
   public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
      Event event = new Event();
      event.setId(rs.getInt("id"));
      event.setData(rs.getString("data"));
      event.setStatus(rs.getInt("status"));
      return event;
   }
 }