package com.example;

import com.example.entity.Memo;
import com.example.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testInsert(){
        System.out.println("=================================");
        System.out.println(memoRepository.getClass().getName());
        System.out.println("=================================");
    }
/*
    @Test
    public void testInsertDummies(){
        // 100개의 새로운 Memo객체를 생성하고
        IntStream.rangeClosed(1,100).forEach(i -> {
            Memo memo =  Memo.builder().memoText("Memo..." + i).build();
            // 저장한다
            memoRepository.save(memo);
        });
    }
*/
    //조회
    @Test
    public void testSelect(){
        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);
        System.out.println("======================");
        if (result.isPresent()){
            Memo memo = result.get();
            System.out.println(memo);
        }
        System.out.println("======================");
    }

    //수정
    @Test
    public void testUpdate(){
        Memo memo = Memo.builder().mno(1L).memoText("Update Text......").build();
        System.out.println("1---------------------");
        memoRepository.save(memo);
        System.out.println("2---------------------");
    }

    //삭제
    @Test
    public void testDelete(){
        Long mno = 5L;
        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault(){

        Sort sort = Sort.by("mno").descending();
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort.and(sort2);

        Pageable pageable = PageRequest.of(0, 10, sort);
        Pageable pageable2 = PageRequest.of(0,10, sortAll);
        Page<Memo> result = memoRepository.findAll(pageable);
        Page<Memo> result2 = memoRepository.findAll(pageable2);

        System.out.println(result);

        result.get().forEach(memo -> {
            System.out.println(memo);
        });

        System.out.println(result2);
        result2.get().forEach(memo -> {
            System.out.println(memo);
        });
    }
}
