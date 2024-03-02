package pers.nefedov.selsuptestapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import pers.nefedov.selsuptestapp.models.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin(@NonNull String login);
}