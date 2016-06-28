package com.theironyard.controllers;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import com.theironyard.entities.Photo;
import com.theironyard.entities.User;
import com.theironyard.services.PhotoRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.callback.PasswordCallback;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by jonathandavidblack on 6/28/16.
 */
@RestController //specifically for json routes
public class IronGramRestController {
    @Autowired
    UserRepository users;

    @Autowired
    PhotoRepository photos;


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(@RequestBody User user, HttpSession session) throws Exception {
        User userFromDB = users.findFirstByName(user.getName());
        if (userFromDB == null) {
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(user.getPassword(), userFromDB.getPassword())) {
            throw new Exception("Wrong Password!");
        }
        session.setAttribute("username", user.getName());
        return user;
    }

    @RequestMapping(path = "/photos", method = RequestMethod.GET)
    public Iterable<Photo> getPhotos(HttpSession session) {

        String username = (String) session.getAttribute("username");
        User user = users.findFirstByName(username);
        Iterable<Photo> setPhotoTimeList = photos.findByRecipient(user);
        for(Photo ph : setPhotoTimeList) {
            if (ph.getTime() == null) {

                ph.setTime(LocalDateTime.now());
                photos.save(ph);
            }
        }

        Iterable<Photo> photoList = photos.findByRecipient(user);
        for (Photo p : photoList) {

            if(LocalDateTime.now().isAfter(p.getTime().plusSeconds(p.getDurationInSeconds()))) {

                Photo photoToDelete = photos.findOne(p.getId());
                File photoFileToDelete = new File("public/photos/" + photoToDelete.getFilename());
                photoFileToDelete.delete();
                photos.delete(p.getId());
            }
        }
        return photos.findByRecipient(user);

    }
}
  //  LocalDateTime ldt = LocalDateTime.now();
  //  Timestamp t = Timestamp.valueOf(ldt);
  //  LocalDateTime ldt2 = t.toLocalDateTime();

// info for timestamp and localdatetime conversions http://www.coderanch.com/t/651936/JDBC/databases/Convert-java-time-LocalDateTime-SE

//    AnonFile fileToDelete = files.findOne(files.findMinNonPermId());  //creating an object into which we insert the item(file) to delete
//    File fileOnDisk = new File("public/files/" + fileToDelete.getRealFileName());
//fileOnDisk.delete();