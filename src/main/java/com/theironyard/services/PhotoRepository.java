package com.theironyard.services;

import com.theironyard.entities.Photo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jonathandavidblack on 6/28/16.
 */
public interface PhotoRepository extends CrudRepository<Photo, Integer> {
}
