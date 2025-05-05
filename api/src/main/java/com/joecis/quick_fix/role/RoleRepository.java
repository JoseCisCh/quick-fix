package com.joecis.quick_fix.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
    /**
     * Returns the role filtered by the role name
     *
     * @param name the name of the role to fetch.
     * @return Role object.
     */
    public Role findByName(String name);
}
