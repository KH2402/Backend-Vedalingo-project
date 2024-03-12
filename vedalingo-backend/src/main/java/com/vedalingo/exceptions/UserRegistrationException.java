package  com.vedalingo.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationException extends RuntimeException {

    public UserRegistrationException(String message) {
        super(message);
    }
}