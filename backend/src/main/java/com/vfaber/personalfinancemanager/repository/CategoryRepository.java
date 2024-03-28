package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<List<Category>> findCategoriesByDescription(String description);

    Optional<List<Category>> findCategoriesByName(String Name);

}
