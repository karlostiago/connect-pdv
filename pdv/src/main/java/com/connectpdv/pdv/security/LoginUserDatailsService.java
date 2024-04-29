package com.connectpdv.pdv.security;

import com.connectpdv.pdv.repository.PermissionRepository;
import com.connectpdv.pdv.repository.UserGroupRepository;
import com.connectpdv.pdv.repository.UserRepository;
import com.util.commons.entity.Permission;
import com.util.commons.entity.User;
import com.util.commons.entity.GroupsUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class LoginUserDatailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;
    private final PermissionRepository permissionRepository;

    public LoginUserDatailsService(UserRepository userRepository, UserGroupRepository userGroupRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.userGroupRepository = userGroupRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usuario = userRepository.findByUserNameContaining(username);

        if (usuario == null)
            throw new UsernameNotFoundException("Usuário não encontrado!");

        return new UserSystem(usuario.getUserName(), usuario.getPassword(),
                authorities(usuario));
    }

    public Collection<? extends GrantedAuthority> authorities(User usuario) {
        return authorities(userGroupRepository.findByUser(usuario));
    }

    public Collection<? extends GrantedAuthority> authorities(List<GroupsUser> grupos) {
        Collection<GrantedAuthority> auths = new ArrayList<>();

        for (GroupsUser grupo : grupos) {
            List<Permission> lista = permissionRepository.findByUserGroups(grupo);

            for (Permission permissao : lista) {
                auths.add(new SimpleGrantedAuthority("ROLE_" + permissao.getName()));
            }
        }
        return auths;
    }

}
