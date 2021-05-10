package com.pluralsight.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity(name = "speakers") //this is because the table name is sessions, and the class is Session. slightly different.
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Speaker
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long speaker_id;

    private String first_name;
    private String last_name;
    private String title;
    private String company;
    private String speaker_bio;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] speaker_photo;
    //storing binary data is tricky, byte array does it well
    //lob - large object. helps JPA deal with larger data
    //type helps hibernate deal with larger data.
    //there is some code in app properties file which is needed for the lob to work


    @ManyToMany(mappedBy = "speakers")
    @JsonIgnore
    //this is a Jackson library thing, it stops it iterating a bunch of times when it returns json, and making a deep nest
    //this is all because this class is related to sessions - so when you call one, you want to call the other. try it in postman and see
    private List<Session> sessions;
    //so here you pick up what was defined over in the speaker class (speakers table)


    public Speaker(){
    }

    public byte[] getSpeaker_photo() {
        return speaker_photo;
    }

    public void setSpeaker_photo(byte[] speaker_photo) {
        this.speaker_photo = speaker_photo;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public Long getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(Long speaker_id) {
        this.speaker_id = speaker_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSpeaker_bio() {
        return speaker_bio;
    }

    public void setSpeaker_bio(String speaker_bio) {
        this.speaker_bio = speaker_bio;
    }
}
