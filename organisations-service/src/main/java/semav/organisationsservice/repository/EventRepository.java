package semav.organisationsservice.repository;

import org.springframework.data.repository.CrudRepository;
import semav.organisationsservice.entity.Event;

public interface EventRepository extends CrudRepository<Event, Integer> {

}

