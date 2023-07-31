package com.userloginapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.userloginapp.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	User findByEmail(String email);

}
