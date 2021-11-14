package com.xjtu.qgsystem.repository;

import com.xjtu.qgsystem.entity.Context;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContextRepository extends JpaRepository<Context, Long> {

    public Optional<Context> findById(Long id);

    public List<Context> findByTitle(String title);

}
