package com.carmelcop.cms.repository;

import com.carmelcop.cms.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

    List<Member> findAllByActiveEquals(boolean active);

    List<Member> findAllByDeceasedEquals(boolean deceased);

}
