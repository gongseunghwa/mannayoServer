package hansung.mannayo.mannayoserverapplication.Repository;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByRealNameAndNickName(String realName, String nickName);

    Member findByRealNameAndPhoneNumber(String realName, String phoneNumber);


    Optional<Member> findByEmail(String eamil);



    Member findByRealNameAndEmail(String realName, String email);

}
