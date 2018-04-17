package com.softserve.edu.hypercinema.service;


import com.softserve.edu.hypercinema.entity.SessionEntity;

import java.util.List;

public interface SessionService {

    SessionEntity getSession(Long id);

   // SessionEntity getSession(Principal principal);

    void createSession(SessionEntity sessionEntity);

    void deleteSession(Long id);

    void updateSession(SessionEntity sessionEntity);

   // void updateSession(SessionEntity sessionEntity,Principal principal);

    List<SessionEntity> getAll();


}
