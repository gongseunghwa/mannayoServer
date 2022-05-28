package hansung.mannayo.mannayoserverapplication.Repository;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Board;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<List<Board>> findByMemberOrderByCreatedDateDesc(Long id); //이름으로 검색할 때 사용하기 위한 함수(내림차순)

    Optional<List<Board>> findByMember_NickName(String nickName);

    Optional<List<Board>> findByMember_Id(Long id);

    Optional<List<Board>> findByType(BoardType boardType);

    // findAll  order by desc -> findAll(Sort.by(Sort.Direction.DESC, "id")로 할 예정
}
