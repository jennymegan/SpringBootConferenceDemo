package com.pluralsight.demo.repositories;

import com.pluralsight.demo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
// Session is our data type and Long is the primary key type
    //now have find update save delete etc all set up and usable.
}
