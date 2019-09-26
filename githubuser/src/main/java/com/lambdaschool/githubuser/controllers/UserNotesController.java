package com.lambdaschool.githubuser.controllers;

import com.lambdaschool.githubuser.models.User;
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
@RequestMapping("/usernotes")
public class UserNotesController
{
    private static final Logger logger = LoggerFactory.getLogger(UserNotesController.class);

    @Autowired
    UserNotesService usernotesService;

    @GetMapping(value = "/usernotes",
            produces = {"application/json"})
    public ResponseEntity<?> listAllUserNotes(HttpServletRequest request)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<UserNotes> allQuotes = usernotesService.findAll();
        return new ResponseEntity<>(allQuotes, HttpStatus.OK);
    }


    @GetMapping(value = "/usernotes/{usernotesId}",
            produces = {"application/json"})
    public ResponseEntity<?> getUserEmailById(HttpServletRequest request,
                                              @PathVariable
                                                      Long usernotesId)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        UserNotes un = usernotesService.findUsernotesById(usernotesId);
        return new ResponseEntity<>(un, HttpStatus.OK);
    }


    @GetMapping(value = "/usernotes/{userName}",
            produces = {"application/json"})
    public ResponseEntity<?> findNotesByUserName(HttpServletRequest request,
                                                 @PathVariable
                                                         String userName)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<UserNotes> theUsernotes = usernotesService.findByUserNotes(userName);
        return new ResponseEntity<>(theUsernotes, HttpStatus.OK);
    }


    @PostMapping(value = "/usernotes")
    public ResponseEntity<?> addNewNote(HttpServletRequest request, @Valid
    @RequestBody
            UserNotes newUserNotes) throws URISyntaxException
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        newUserNotes = usernotesService.save(newUserNotes);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUseremailURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{usernotesId}")
                .buildAndExpand(newUserNotes.getUsernotesid())
                .toUri();
        responseHeaders.setLocation(newUseremailURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping("/usernotes/{usernotesid}")
    public ResponseEntity<?> deleteNoteById(HttpServletRequest request,
                                             @PathVariable
                                                     long usernotesid)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        usernotesService.delete(usernotesid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
