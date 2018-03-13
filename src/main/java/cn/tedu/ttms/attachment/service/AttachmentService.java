package cn.tedu.ttms.attachment.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachment.entity.Attachment;


public interface AttachmentService {
	void saveObject(String title,MultipartFile mFile);
	public List<Attachment> findObjects();
	/**根据id查找某个对象*/
	Attachment findObjectById(Integer id);
	
}
