package cn.tedu.ttms.attachment.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;

import cn.tedu.ttms.attachment.dao.AttachmentDao;
import cn.tedu.ttms.attachment.entity.Attachment;
import cn.tedu.ttms.attachment.service.AttachmentService;
import cn.tedu.ttms.common.exception.ServiceException;

@Service
public class AttachmentServiceImpl implements AttachmentService{
	/**
	 * 实现文件上传
	 * 1）将文件存储到服务器
	 * 2）将文件信息存储到数据库
	 */
	@Autowired
	private AttachmentDao attachmentDao; 
	@Override
	public void saveObject(String title, MultipartFile mFile) {
		//1.验证参数的有效性
		if(StringUtils.isEmpty(title))
			throw new ServiceException("title不能为空");
		if(mFile == null)
			throw new ServiceException("请选择上传文件");
		if(mFile.isEmpty())
			throw new ServiceException("不允许上传空文件");
		//2.判定文件是否已上传（根据摘要信息）
		//2.1）根据mFile内容生成摘要信息	
		String digest = null;//摘要字符串
		try {
			byte[] bytes = mFile.getBytes();
			digest = DigestUtils.md5DigestAsHex(bytes);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException("文件上传失败");
		}		
		//2.2）根据摘要信息查询文件
		int count = attachmentDao.getRowCountByDigest(digest);
		//2.3）根据查询的结果判定文件是否已上传
		if(count > 0)
			throw new ServiceException("文件已上传");
		//3.假如文件不在则上传文件
		File rootDir = new File("d:/uploads");
		//创建日期文件夹名
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
		String dateDir = sdf.format(new Date());
		File fileDir = new File(rootDir + dateDir);
		if(!fileDir.exists())
			fileDir.mkdirs();//如果文件目录不存在则创建文件目录
		File dest = new File(fileDir,mFile.getOriginalFilename());//
		try {
			mFile.transferTo(dest);//
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException("文件上传失败");
		}
		//4.将文件信息保存到数据库
		Attachment a = new Attachment();
		a.setTitle(title);
		a.setFileName(mFile.getOriginalFilename());
		a.setFileDisgest(digest);
		a.setFilePath(dest.getPath());
		a.setContentType(mFile.getContentType());
		int rows = attachmentDao.insertObject(a);
		//5.验证保存结果
		if(rows <= 0)
			throw new ServiceException("数据保存失败");
		
	}
	
    /**获得所有附件信息*/
    @Override
    public List<Attachment> findObjects() {
    	return attachmentDao.findObjects();
    }
    
    @Override
    public Attachment findObjectById(Integer id) {
    	//1.判定参数有效性
    	if(id==null)
    	throw new ServiceException("id的值不能为空");
    	//2.执行查询操作
    	Attachment a=attachmentDao.findObjectById(id);
    	//3.对查询结果进行业务判定
    	if(a==null)
    	throw new ServiceException("没找到对应记录");
    	return a;
    }
	
}
