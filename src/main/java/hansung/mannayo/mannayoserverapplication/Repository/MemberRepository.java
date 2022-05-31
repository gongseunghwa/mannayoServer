package hansung.mannayo.mannayoserverapplication.Repository;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByRealNameAndNickName(String realName, String nickName);

    Member findByRealNameAndPhoneNumber(String realName, String phoneNumber);


    Optional<Member> findByEmail(String email);



    Member findByRealNameAndEmail(String realName, String email);

    Optional<Member> findByNickName(String nickName);

    @Query("select m.imageAddress from Member m where m.id = :writerId")
    Optional<String> findImageAddress(@Param("writerId") Long writerId);

    @Query("select m.token from Member m")
    Optional<List<String>> findToken();
}
