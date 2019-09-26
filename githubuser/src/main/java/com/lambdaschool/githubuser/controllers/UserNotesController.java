package com.lambdaschool.githubuser.controllers;

import com.lambdaschool.githubuser.models.UserNotes;
import com.lambdaschool.githubuser.services.UserNotesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
//@RequestMapping("/users")
public class UserNotesController
{
    private static final Logger logger = LoggerFactory.getLogger(UserNotesController.class);

    @Autowired
    UserNotesService userNotesService;

    @GetMapping(value = "/usersnotes/usernotes",
                produces = {"application/json"})
    public ResponseEntity<?> listAllUsernotes(HttpServletRequest request)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<UserNotes> allQuotes = userNotesService.findAll();
        return new ResponseEntity<>(allQuotes, HttpStatus.OK);
    }


    @GetMapping(value = "/usernotes/id/{id}",
                produces = {"application/json"})
    public ResponseEntity<?> getUserNotesById(HttpServletRequest request,
                                      @PathVariable
                                              Long id)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        UserNotes ue = userNotesService.findUserNotesById(id);
        return new ResponseEntity<>(ue, HttpStatus.OK);
    }


    @GetMapping(value = "/usernotes/{username}",
                produces = {"application/json"})
    public ResponseEntity<?> findNoteByUserName(HttpServletRequest request,
                                                 @PathVariable
                                                         String username)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<UserNotes> theUserNotes = userNotesService.findByUserName(username);
        return new ResponseEntity<>(theUserNotes, HttpStatus.OK);
    }


    @PostMapping(value = "/addusernotes")
    public ResponseEntity<?> addNewNote(HttpServletRequest request, @Valid
    @RequestBody
            UserNotes newUserNotes) throws URISyntaxException
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        newUserNotes = userNotesService.save(newUserNotes);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUseremailURI = ServletUriComponentsBuilder.fromCurrentRequest()
                                                     .path("/{usernotesId}")
                                                     .buildAndExpand(newUserNotes.getUsernotesid())
                                                     .toUri();
        responseHeaders.setLocation(newUseremailURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping("/usernotes/{id}")
    public ResponseEntity<?> deleteNoteById(HttpServletRequest request,
                                             @PathVariable
                                                     long id)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userNotesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
