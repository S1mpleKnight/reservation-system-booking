package by.zelezinsky.reservationsystembooking.exception;

import java.util.List;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entityName, String id) {
        super(entityName.concat(" have not found, id: ").concat(id));
    }

    public NotFoundException(String entityName, List<String> ids) {
        super(entityName.concat(" have not found, ids: ").concat(String.join(" ", ids)));
    }
}
