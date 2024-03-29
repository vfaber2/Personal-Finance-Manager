package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<List<CategoryEntity>> findCategoriesByDescription(String description);

    Optional<List<CategoryEntity>> findCategoriesByName(String name);

}
