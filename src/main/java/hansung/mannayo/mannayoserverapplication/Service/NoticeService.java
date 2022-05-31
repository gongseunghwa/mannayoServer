package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Notice;
import hansung.mannayo.mannayoserverapplication.Model.Type.NoticeType;

import java.util.List;
import java.util.Optional;

public interface NoticeService {

    Boolean insert(String receiver, String sender, String title, String contents, NoticeType noticeType);

    Optional<List<Notice>> findNoticeByReceiverId(String receiver);
}
