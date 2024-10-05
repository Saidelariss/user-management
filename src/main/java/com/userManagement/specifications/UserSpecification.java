package com.userManagement.specifications;

import com.userManagement.domain.UserEntity;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import jakarta.persistence.criteria.*;

public class UserSpecification {
    public static Specification<UserEntity> hasUsername(String username){
        return (Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) ->{
            if(username == null || username.isEmpty() ){
                return criteriaBuilder.conjunction();
            } else return criteriaBuilder.equal(root.get("username"),username);
        };
    }

    public static Specification<UserEntity> hasEmail(String email){
       return (root, query, criteriaBuilder) -> {
           if(email == null || email.isEmpty()){
               return criteriaBuilder.conjunction();
           } else return criteriaBuilder.equal(root.get("email"),email);
       };
    }

    public static Specification<UserEntity> hasKeyword(String keyword){
        return (root, query, criteriaBuilder) -> {
            if(keyword == null || keyword.isEmpty()){
                return criteriaBuilder.conjunction();
            } else{
                Predicate byUsername = criteriaBuilder.like(root.get("username"), "%" + keyword + "%");
                Predicate byEmail = criteriaBuilder.like(root.get("email"), "%" + keyword + "%");
                return criteriaBuilder.or(byUsername,byEmail);
            }
        };
    }
}
