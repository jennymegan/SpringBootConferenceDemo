package com.pluralsight.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sessions") //this is because the table name is sessions, and the class is Session. slightly different.
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//this is to fix a jackson issue, hibernate adds stub methods to entities which relate to other ones. this doesn't work

public class Session
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //that bit up there is assigning the postgres generated sequence as the id for these objects. otherwise you could assign the id to a unique thing like isbn for book.
    private Long session_id;
    private String session_name;
    private String session_description;
    private Integer session_length;
    //nb you would only need to do the @Column and ( name = "column name") if you wanted attributes to be a different name or case to the ones in the db.
    //as these are the same exact ones as in the db, we don not need to.


    @ManyToMany
    @JoinTable(
            name="session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private List<Speaker> speakers;
    //here you decide that this Session class is going to be the one that defines what exists as a union table in the db.
    //ie speakers can speak at many sessions, and one session can have many speakers.
    //this could have been defined in speakers as a list of sessions, it's arbitrary
    //the code in the jointable annotation is a laguage agnostic way of communicating with database that has a join table.

    public Session(){
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public void setSession_length(Integer session_length) {
        this.session_length = session_length;
    }
}
