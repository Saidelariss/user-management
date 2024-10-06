package com.userManagement.repository;

import com.userManagement.criteria.UserCriteria;
import com.userManagement.domain.UserEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity,String>, JpaSpecificationExecutor<UserEntity> {
    List<UserEntity> findAll();
    Page<UserEntity> findAll(Pageable pageable);
    default Page<UserEntity> findAllUsersByCriteria(UserCriteria criteria, Pageable pageable){
        Specification<UserEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getUsername() != null) {
                Predicate username = builder.equal(root.get("username"), criteria.getUsername());
                predicates.add(username);
            }
            if(criteria.getEmail() != null) {
                Predicate email = builder.equal(root.get("email"), criteria.getEmail());
                predicates.add(email);
            }
            if(criteria.getRole() != null){
                Predicate role = builder.equal(root.get("role"), criteria.getRole());
                predicates.add(role);
            }
            if(criteria.getSearch() != null){
                Predicate username = builder.like(root.get("username"), "%" + criteria.getSearch()+"%");
                Predicate email = builder.like(root.get("email"), "%" +criteria.getSearch()+"%");
                predicates.add(builder.or(username,email));
            }
            return builder.and(predicates.toArray(new Predicate[]{}));
        };
            return findAll(specification,pageable);
    }

}
