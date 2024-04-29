package com.util.commons.entity;

import com.util.commons.abstraction.AbstractEntity;
import com.util.commons.annotation.ExcludedCoverage;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = false)
@ExcludedCoverage
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "group_user")
public class GroupsUser extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_group",
            joinColumns = @JoinColumn(name = "group_user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "permission_group",
            joinColumns = @JoinColumn(name = "group_user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;
}
