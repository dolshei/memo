package com.example.repository;

import com.example.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {

}
