package com.vmware.talentboost.demo.repository;

import com.vmware.talentboost.demo.entity.Meme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemeRepository extends JpaRepository<Meme, Integer> {
    List<Meme> findByUrl(String url);
    List<Meme> findByDescription(String description);
    List<Meme> findByDatePosted(Date datePosted);
    List<Meme> findByDescriptionIgnoreCaseContaining(String description);
}
