package com.example.twodatabase.db1.repository;

import com.example.twodatabase.db1.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface UserRepository extends JpaRepository<UserEntity, Serializable> {
}
