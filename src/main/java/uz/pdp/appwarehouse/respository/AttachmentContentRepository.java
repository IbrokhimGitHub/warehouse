package uz.pdp.appwarehouse.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.AttachmentContent;

import java.util.List;

public interface AttachmentContentRepository extends JpaRepository <AttachmentContent,Integer> {

}
