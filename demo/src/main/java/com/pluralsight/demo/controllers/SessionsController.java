package com.pluralsight.demo.controllers;

import com.pluralsight.demo.models.Session;
import com.pluralsight.demo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//returns data in JSON and probably is defined restful in other ways too
//returns a 200 by default
@RequestMapping("/api/v1/sessions")
//this specifies that all requests to that url are sent to this controller

public class SessionsController {

    @Autowired
    private SessionRepository sessionRepository;
    //spring will create an instance of session repo and put it onto the class

    @GetMapping
    public List<Session> list () {
        return sessionRepository.findAll();
        //spring will go get this data, turn it to Jackson which is a serialisation library,
        //then sessions turned to json and returned
    }

    @GetMapping
    @RequestMapping("{id}")
    //needs both as you've given it two instructions - get mapping sent to the address above,
    //and what to do if there's an extra /{id} on the end of it
    //you know your ids are Long types so use that
    public Session get(@PathVariable Long id) {
        return sessionRepository.getOne(id);
    }

    @PostMapping
    //no need for request mapping, anything posted to parent path at top will go here.
    @ResponseStatus(HttpStatus.CREATED)
    //this sends a bespoke http response (201 in this case, for Created)
    public Session create(@RequestBody final Session session){
        //spring will take all the attributes in a JSON payload and put them in an Session object
        return sessionRepository.saveAndFlush(session);
        //save and flush - saves to this repo but doesn't get commited to database until you flush it. do both.
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    //there is no @delete mapping, you have to do it like this.
    public void delete(@PathVariable Long id){
        //ought to check before deleting - might need to deal with cascading data. you could get an error.
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    //nb Put will replace all attributes/ expects them all to be passed in. patch would take only what needed changing
    public Session update(@PathVariable Long id, @RequestBody Session session){
        // add validation to check all attributes are passed in, otherwise return a 400 (bad payload)
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        //takes existing session and copies incoming data onto it. third param allows you to ignore things you don't want to copy over
        return sessionRepository.saveAndFlush(existingSession);
    }

}
