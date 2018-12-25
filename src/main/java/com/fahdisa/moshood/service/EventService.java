package com.fahdisa.moshood.service;

import com.fahdisa.moshood.entity.Event;
import com.fahdisa.moshood.entity.Guest;
import com.fahdisa.moshood.util.BaseResponse;
import com.fahdisa.moshood.util.Codes;
import com.fahdisa.moshood.util.Page;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Stateless
public class EventService {

    @PersistenceContext
    private EntityManager entityManager;

    public BaseResponse createEvent(Event event) {

        if (Objects.isNull(event)) {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid Event")
                    .build();
        } else {
            List<Guest> guests = event.getGuests();
            if (Objects.nonNull(guests) && (!guests.isEmpty())) {
                for (Guest guest : guests) {
                    guest.setId(null);
                    guest.setEvent(event);
                }
            }
event.setId(null);
            entityManager.persist(event);
            return new BaseResponse.Builder<>()
                    .setCode(Codes.SUCCESS)
                    .setDescription("Success")
                    .setData(event)
                    .build();
        }

    }

    public BaseResponse updateEvent(Event event) {
        if (Objects.isNull(event) || Objects.isNull(event.getId())) {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid Event")
                    .build();
        } else {
            List<Guest> guests = event.getGuests();
            if (Objects.nonNull(guests) && (!guests.isEmpty())) {
                for (Guest guest : guests) {
                    guest.setEvent(event);
                }
            }

            entityManager.merge(event);
            return new BaseResponse.Builder<>()
                    .setCode(Codes.SUCCESS)
                    .setDescription("Success")
                    .setData(event)
                    .build();
        }
    }

    public BaseResponse removeEvent(Long eventId){
        if(Objects.isNull(eventId)){
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid event id")
                    .build();
        }else{
            Event event = entityManager.find(Event.class, eventId);
            if(Objects.isNull(event)){
                return new BaseResponse.Builder<>()
                        .setCode(Codes.NO_RECORD)
                        .setDescription("Event not found")
                        .build();
            }else{
                entityManager.remove(event);
                return new BaseResponse.Builder<>()
                        .setCode(Codes.SUCCESS)
                        .setDescription("Success")
                        .setData(event)
                        .build();
            }
        }
    }

    public BaseResponse getEvent(Long eventId){
        if(Objects.isNull(eventId)){
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid event id")
                    .build();
        }else{
            Event event = entityManager.find(Event.class, eventId);
            if(Objects.isNull(event)){
                return new BaseResponse.Builder<>()
                        .setCode(Codes.NO_RECORD)
                        .setDescription("Event not found")
                        .build();
            }else{
                return new BaseResponse.Builder<>()
                        .setCode(Codes.SUCCESS)
                        .setDescription("Success")
                        .setData(event)
                        .build();
            }
        }
    }

    public BaseResponse getEvents(Long lastId, Integer page, Integer size){
        if(Objects.isNull(lastId) || (lastId < 1)){
            lastId = Page.DEFAULT_LAST_ID;
        }

        if(Objects.isNull(page) || (page < 0)){
            page = Page.DEFAULT_PAGE;
        }

        if(Objects.isNull(size) || (size < 1)){
            size = Page.DEFAULT_SIZE;
        }

        List<Event> events = entityManager
                .createQuery("SELECT e FROM Event e WHERE e.id < :id ORDER BY e.id desc",
                        Event.class).setParameter("id", lastId)
                .setFirstResult(page * size).setMaxResults(size)
                .getResultList();

        if(events.isEmpty()){
            return new BaseResponse.Builder<>()
                    .setCode(Codes.NO_RECORD)
                    .setDescription("No event found")
                    .build();
        }else{
            return new BaseResponse.Builder<>()
                    .setCode(Codes.SUCCESS)
                    .setDescription("Success")
                    .setData(events)
                    .build();
        }
    }

}
