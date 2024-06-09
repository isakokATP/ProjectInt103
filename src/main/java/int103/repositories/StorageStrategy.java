package int103.repositories;

import int103.exceptions.DatabaseException;
import int103.exceptions.NotFoundException;

public interface StorageStrategy extends StudentRepository, CourseRepository, RegistrationRepository {
}
