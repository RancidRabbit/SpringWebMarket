package ru.gb.Ex.webApp.converter;

import org.springframework.stereotype.Component;
import ru.gb.Ex.webApp.dto.UserDTOreq;
import ru.gb.Ex.webApp.entities.User;

/* В дальнейшем реализовать на MapStruct */
@Component
public class UserConverter {

    public UserDTOreq entityToDto(User user) {
         return new UserDTOreq(user.getId(), user.getUsername(), user.getEmail());
    }

}
