package co.com.pragma.api.user.helper;

import co.com.pragma.api.user.dto.request.RegisterUserRequest;
import co.com.pragma.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User map(RegisterUserRequest request) {
        return new User(request.name(), request.lastName(), request.documentNumber(), request.phoneNumber(),
                request.email(), request.baseSalary(), request.roleId());
    }
}
