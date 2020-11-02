package com.CSCI4050.TermProject.CovidWebsite.repository;

import java.util.Optional;

import com.CSCI4050.TermProject.CovidWebsite.entities.AccountEntity;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

    Optional<AccountEntity> findById(Long Id);

    AccountEntity findByUserName(String userName);

    AccountEntity findByEmail(String email);

}