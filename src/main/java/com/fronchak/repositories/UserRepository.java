package com.fronchak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fronchak.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT obj FROM User WHERE obj.userName =:userName")
	User findByUsername(@Param("userName") String userName);
}
