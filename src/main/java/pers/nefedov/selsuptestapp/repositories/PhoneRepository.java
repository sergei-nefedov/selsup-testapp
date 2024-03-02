package pers.nefedov.selsuptestapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import pers.nefedov.selsuptestapp.models.Phone;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, String> {
    List<Phone> findByUser_Login(@NonNull String login);
}