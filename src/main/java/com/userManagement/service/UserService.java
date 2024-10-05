package com.userManagement.service;

import com.userManagement.criteria.UserCriteria;
import com.userManagement.domain.UserEntity;
import com.userManagement.repository.UserRepository;
import com.userManagement.specifications.UserSpecification;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserEntity> findUsers(UserCriteria criteria){

        Specification<UserEntity> specification = Specification
                                                    .where(UserSpecification.hasUsername(criteria.getUsername()))
                                                    .and(UserSpecification.hasEmail(criteria.getEmail()));
        return repository.findAll(specification);
    }

    public List<UserEntity> findUserByEmailAndUsername(String keyword){
        Specification<UserEntity> specification = Specification.where(UserSpecification.hasKeyword(keyword));
        return repository.findAll(specification);
    }
}
