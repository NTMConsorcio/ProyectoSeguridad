package com.ntm.appseguridad.config;

import com.ntm.appseguridad.audit.Revision;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomRevisionListener implements RevisionListener {

    public void newRevision(Object revisionEntity) {
        final Revision revision = (Revision) revisionEntity;
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        revision.setUser(user);
    }
}
