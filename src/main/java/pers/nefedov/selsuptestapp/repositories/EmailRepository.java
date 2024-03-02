package pers.nefedov.selsuptestapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.nefedov.selsuptestapp.models.Email;

public interface EmailRepository extends JpaRepository<Email, String> {
}