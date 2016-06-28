package com.theironyard.services;

import com.theironyard.entities.Photo;
import com.theironyard.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jonathandavidblack on 6/28/16.
 */
public interface PhotoRepository extends CrudRepository<Photo, Integer> {
    public Iterable<Photo> findByRecipient(User recipient);

   // @Query("DELETE from photos WHERE timestamp > NOW - 10")


    //delete from events where timestamp < NOW()

}
