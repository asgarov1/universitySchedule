package com.asgarov.university.schedule.dao;

import com.asgarov.university.schedule.domain.Room;
import com.asgarov.university.schedule.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDao extends AbstractDao<Long, Room> {

    @Override
    public Room findById(Long id) {
        try(Session session = HibernateUtil.getHibernateSession()){
            return session.get(Room.class, id);
        }
    }

    @Override
    protected String from() {
        return "from Room";
    }
}
