package com.SwimcoachPlatform.coach.repository;

import com.SwimcoachPlatform.coach.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
