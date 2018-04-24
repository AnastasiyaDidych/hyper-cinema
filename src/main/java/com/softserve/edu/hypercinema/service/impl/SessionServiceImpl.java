package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.entity.SessionEntity;
import com.softserve.edu.hypercinema.repository.SessionRepository;
import com.softserve.edu.hypercinema.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public SessionEntity getSession(Long id) {
        return sessionRepository.findById(id).orElse(null);
    }

    @Override
    public void createSession(SessionEntity sessionEntity) {
        sessionRepository.save(sessionEntity);
    }

    @Override
    public void deleteSession(Long id) {
            sessionRepository.deleteById(id);
    }

    @Override
    public void updateSession(SessionEntity sessionEntity) {
            sessionRepository.save(sessionEntity);
    }

    @Override
    public List<SessionEntity> getSessions() {
        return sessionRepository.findAll();
    }

}

