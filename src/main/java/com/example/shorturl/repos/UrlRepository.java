package com.example.shorturl.repos;

import com.example.shorturl.entity.Url;
import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
}
