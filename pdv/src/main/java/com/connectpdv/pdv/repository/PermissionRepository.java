package com.connectpdv.pdv.repository;

import com.util.commons.entity.Permission;
import com.util.commons.entity.GroupsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Query("SELECT p FROM Permission p WHERE :userGroup MEMBER OF p.userGroups")
    List<Permission> findByUserGroups(GroupsUser userGroup);
}
