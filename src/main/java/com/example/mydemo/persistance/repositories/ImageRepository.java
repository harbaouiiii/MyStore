package com.example.mydemo.persistance.repositories;

import com.example.mydemo.persistance.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    Optional<Image> findByName(String name);
    Set<Image> findByProducts_Id(UUID id);
}
