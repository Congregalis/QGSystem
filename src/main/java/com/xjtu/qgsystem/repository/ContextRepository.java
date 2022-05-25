package com.xjtu.qgsystem.repository;

import com.xjtu.qgsystem.entity.Context;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ContextRepository extends JpaRepository<Context, Long> {

    public Optional<Context> findById(Long id);

    public List<Context> findByTitle(String title);

    public Page<Context> findBySubjectAndLanguage(String subject, String language, Pageable pageable);


}
