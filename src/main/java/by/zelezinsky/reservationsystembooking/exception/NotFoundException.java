package by.zelezinsky.reservationsystembooking.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entityName, String id) {
        super(entityName.concat(" have not found, id: ").concat(id));
    }
}
