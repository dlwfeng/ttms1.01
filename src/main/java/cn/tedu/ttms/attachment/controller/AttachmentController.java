package cn.tedu.ttms.attachment.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachment.entity.Attachment;
import cn.tedu.ttms.attachment.service.AttachmentService;
import cn.tedu.ttms.common.web.JsonResult;

@Controller
@RequestMapping("/attachment/")
public class AttachmentController {
	@Autowired
	private AttachmentService attachmentService;
	//private Logger log = Logger.getLogger(ProjectController.class.getName());
	@RequestMapping("attachmentUI")
	public String listUI(){
		return "attachment/attachment";
	}
	@RequestMapping("doUpload")
	@ResponseBody
	public JsonResult doUpload(	String title,MultipartFile mFile) throws IOException{
		//String name=mFile.getOriginalFilename();
		//System.out.println("name="+name);
		//File dest = new File("d:\\"+name);
		//将文件内容赋值到dest对象
		//mFile.transferTo(dest);
		attachmentService.saveObject(title, mFile);
		return new JsonResult("上传 Ok");
	}
	
	/**获得所有的附件信息*/
    @RequestMapping("doFindObjects")
    @ResponseBody
    public JsonResult doFindObjects(){
    	List<Attachment> list = attachmentService.findObjects();
    	return new JsonResult(list);
    } 
    
    @RequestMapping("doDownload")
    @ResponseBody
    public byte[] doDownload(Integer id,HttpServletResponse response)throws IOException{
    	//1.根据id执行查找操作
    	Attachment a = attachmentService.findObjectById(id);
    	//2.设置下载内容类型以及响应头(固定格式)
    	response.setContentType("appliction/octet-stream");
    	String fileName = URLEncoder.encode(a.getFileName(),"utf-8");
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		//3.获得指定文件的路径对象(java.nio.Path)
        Path path=Paths.get(a.getFilePath());
        //4.读取path路径对应的文件,并返回字节数组
    	return Files.readAllBytes(path);
    }

}