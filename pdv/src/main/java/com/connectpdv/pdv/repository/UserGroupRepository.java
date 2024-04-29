package com.connectpdv.pdv.repository;

import com.util.commons.entity.User;
import com.util.commons.entity.GroupsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserGroupRepository extends JpaRepository<GroupsUser, Long> {
    @Query("SELECT ug FROM GroupsUser ug WHERE :user MEMBER OF ug.users")
    List<GroupsUser> findByUser(@Param("user") User user);
}
