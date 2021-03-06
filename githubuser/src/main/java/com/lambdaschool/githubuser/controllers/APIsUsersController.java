package com.lambdaschool.githubuser.controllers;

import com.lambdaschool.githubuser.models.APIOpenLibrary;
import com.lambdaschool.githubuser.models.GithubUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/users")
public class APIsUsersController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);
    private RestTemplate restTemplate = new RestTemplate();

    // taken from https://openlibrary.org/dev/docs/api/books
    // returns a list of books - you can include multiple ISBNs in a single request
    // This API returns a map instead of the standard list
    //
    // localhost:2019/otherapis/openlibrary/0982477562

    //"https://api.github.com/users/{user}"

    @GetMapping(value = "/users/{user}",
                produces = {"application/json"})
    public ResponseEntity<?> listUser(HttpServletRequest request,
                                                @PathVariable
                                                        String user)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

//        String requestURL = "https://api.github.com/users/" + "User:" + user + "&format=json";
        String requestURL = "https://api.github.com/users/" + user;

        System.out.println(requestURL);
        ParameterizedTypeReference<GithubUser> responseType = new ParameterizedTypeReference<>()
        {
        };
        ResponseEntity<GithubUser> responseEntity = restTemplate.exchange(requestURL, HttpMethod.GET, null, responseType);

        GithubUser userdata = responseEntity.getBody();

//        System.out.println(userdata);
        return new ResponseEntity<>(userdata, HttpStatus.OK);
    }
}
