package com.shop.aqua.repository;

import com.shop.aqua.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
	Member findByEmail(String email);

	List<Member> findAll();

	Member findByUsername(String username);

}
