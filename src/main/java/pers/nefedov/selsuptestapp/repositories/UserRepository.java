package pers.nefedov.selsuptestapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import pers.nefedov.selsuptestapp.models.User;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin(@NonNull String login);

    List<User> findByNameLikeIgnoreCase(String name);

    List<User> findByDateOfBirthGreaterThan(Date dateOfBirth);

    @Transactional
    @Modifying
    @Query("update User u set u.accountBalance = ?1")
    void updateAccountBalanceByAccountBalance(User user, double accountBalance);
}
