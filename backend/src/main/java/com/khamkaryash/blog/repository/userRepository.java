package com.khamkaryash.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khamkaryash.blog.models.userModel;

public interface userRepository extends JpaRepository<userModel,Long> {
	

}
