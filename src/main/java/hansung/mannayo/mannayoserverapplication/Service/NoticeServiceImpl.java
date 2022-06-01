package hansung.mannayo.mannayoserverapplication.Service;


import hansung.mannayo.mannayoserverapplication.Model.Entity.Notice;
import hansung.mannayo.mannayoserverapplication.Model.Type.NoticeType;
import hansung.mannayo.mannayoserverapplication.Repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    NoticeRepository noticeRepository;

    @Override
    public Boolean insert(String receiver, String sender, String title, String contents, NoticeType noticeType) {
        Notice notice = Notice.builder()
                .receiver(receiver)
                .sender(sender)
                .type(noticeType)
                .title(title)
                .contents(contents)
                .build();

        Notice save = noticeRepository.save(notice);
        if(isEmpty(save)) {
            return false;
        }

        return true;
    }

    @Override
    public Optional<List<Notice>> findNoticeByReceiverId(String receiver) {
        return noticeRepository.findByReceiver(receiver);
    }
}
