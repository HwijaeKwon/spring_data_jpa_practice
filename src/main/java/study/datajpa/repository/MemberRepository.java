package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    //findByUsername을 찾을 떄 먼저 Entity.findByUsername을 찾아본다. findByUsername을 찾는다
    //@Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    List<Member> findListByUsername(String username);
    Member findMemberByUsername(String username);
    Optional<Member> findOptionalByUsername(String username);

    //JPA는 간단한 sorting과 paging을 제공한다
    //개발자는 중요한 쿼리에만 집중하면 된다
    //단, 페이징 최적화나 복잡한 sorting을 해야할 경우에는 query를 짜야한다
    //Page<Member> findByAge(int age, Pageable pageable);

    //데이터 수가 많아지면 count 쿼리로 인해 성능이 떨어질 수 있다. 이를 최적화하기 위해 countQuery를 따로 설정할 수 있다
    //ex) left join인 경우 countQuery에서 굳이 join을 할 필요가 없다
    @Query(value = "select m from Member m left join m.team t",
            countQuery = "select count(m) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true) //executeUpdate로 동작하려면 반드시 넣어줘야 한다
    @Query("update Member m set m.age = age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);
}
