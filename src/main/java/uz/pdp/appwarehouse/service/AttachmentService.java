package uz.pdp.appwarehouse.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.AttachmentContent;
import uz.pdp.appwarehouse.result.Result;
import uz.pdp.appwarehouse.respository.AttachmentContentRepository;
import uz.pdp.appwarehouse.respository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request)  {
        Iterator<String> fileNames = request.getFileNames();
        List<MultipartFile> files = request.getFiles(fileNames.next());
        String returnerStr="";
        boolean bool=false;
        for (MultipartFile multipartFile : files) {


            if (multipartFile != null) {
                String originalFilename = multipartFile.getOriginalFilename();
                long size = multipartFile.getSize();
                String contentType = multipartFile.getContentType();
                Attachment attachment = new Attachment();
                attachment.setContentType(contentType);
                attachment.setName(originalFilename);
                attachment.setSize(size);
                Attachment savedAttachment = attachmentRepository.save(attachment);

                AttachmentContent attachmentContent = new AttachmentContent();
                attachmentContent.setAttachment(savedAttachment);
                attachmentContent.setBytes(multipartFile.getBytes());
                attachmentContentRepository.save(attachmentContent);
                returnerStr+="file saved. ID : " + savedAttachment.getId();
                bool=true;
            }
        }
        if (bool) {
            return new Result(returnerStr,true);
        }else {
            return new Result("something went wrong, file not saved",false);
        }


    }
//    public void downloadFile(Integer id, HttpServletResponse response){
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//        if (optionalAttachment.isPresent()) {
//            Attachment attachment = optionalAttachment.get();
//            List<AttachmentContent> attachmentContentByAttachment_id = attachmentContentRepository.getAttachmentContentByAttachment_Id(attachment.getId());
//
//        }
//    }



}
