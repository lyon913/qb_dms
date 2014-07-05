package com.whr.dms.web.controller;

import java.awt.image.BufferedImage;
import java.beans.PropertyEditorSupport;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.whr.dms.models.TNotice;
import com.whr.dms.models.TNoticeAttachment;
import com.whr.dms.models.TNoticeType;
import com.whr.dms.models.TUser;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.DepartmentManager;
import com.whr.dms.service.NoticeService;
import com.whr.dms.service.NoticeTypeService;
import com.whr.dms.service.UserManager;
import com.whr.dms.web.ajax.ContentRangeHeader;
import com.whr.dms.web.ajax.DojoSort;
import com.whr.dms.web.ajax.JsonResponse;
import com.whr.dms.web.ajax.PageableRange;

@Controller
public class PublicNewsController {
	private static Logger log = LoggerFactory.getLogger(PublicNewsController.class);
	
	/**
	 * 表单绑定
	 * 
	 * @param binder
	 */
	@InitBinder
	public void binder(WebDataBinder binder) {

		/**
		 * Date类字段转换
		 */
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				try {
					if (text != null && !"".equals(text))
						setValue(df.parse(text));
				} catch (ParseException e) {
					throw new ValidationException("无效日期字符");
				}
			}

			@Override
			public String getAsText() {
				Date date = (Date) getValue();
				return date == null ? "" : df.format(getValue());
			}

		});

	}

	public TUser getUser(){
		String username = SecurityUtil.getCurrentUserDetials().getUsername();
		TUser u = um.getTUserByLoginName(username);
		return u;
	}
	@Resource
	NoticeService ns;
	

	@Resource
	UserManager um;
	
	@Resource
	DepartmentManager dm;
	

	@Resource
	DepartmentManager ds;
	
	@Resource
	NoticeTypeService nts;

	/**
	 * 院务政务公开信息列表页面
	 * @return
	 */
	@RequestMapping("/publicnews/pubMana")
	public String publicnewsListPage(Model m) {
		List<TNoticeType> noticetypelist = nts.getAllList(new Long(2)) ;
		m.addAttribute("publicnewstypeList",noticetypelist);
		
		return "publicNews/publicNewsList";
	}

	/**
	 * 已发布院务政务列表JSON
	 * @param sort
	 * @param range
	 * @return
	 */
	@RequestMapping(value = "/publicnews/list/{parentId}")
	public ResponseEntity<List<TNotice>> publicnewsList(@RequestParam(required = false)String sort,@RequestHeader("Range") String range,@PathVariable long parentId) {
		PageableRange pr = new PageableRange(range,new DojoSort(sort).toSpringSort());
		Page<TNotice> page = ns.getPublishedNotices(pr,new Long(2));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", new ContentRangeHeader(pr.getOffset(), page.getNumberOfElements(), page.getTotalElements()).toString());
		
		return new ResponseEntity<List<TNotice>>(page.getContent(), headers, HttpStatus.OK);
	}
	
	/**
	 * 全部院务政务列表JSON
	 * @param sort
	 * @param range
	 * @return
	 */
	@RequestMapping(value = "/publicnews/allList/{parentId}")
	public ResponseEntity<List<TNotice>> allPublicNewsList(@RequestParam(required = false)String sort,@RequestHeader("Range") String range,@PathVariable long parentId) {
		PageableRange pr = new PageableRange(range,new DojoSort(sort).toSpringSort());
		Page<TNotice> page = ns.getAllNotices(pr,parentId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", new ContentRangeHeader(pr.getOffset(), page.getNumberOfElements(), page.getTotalElements()).toString());
		
		return new ResponseEntity<List<TNotice>>(page.getContent(), headers, HttpStatus.OK);
	}
	
	@RequestMapping("/publicnews/manage/{parentId}")
	public String publicNewsManagePage(@PathVariable long parentId,Model m){
		List<TNoticeType> noticetypelist = nts.getAllList(parentId) ;
		m.addAttribute("publicnewstypeList",noticetypelist);
		return "publicNews/publicNewsManage";
	}
	
	
	/**
	 * 新建院务政务公开信息页面
	 * @return
	 */
	@RequestMapping(value = "/publicnews/create")
	public String publicNewsCreatePage(Model m){
		TUser u = getUser();
		m.addAttribute("user", u);
		List<TNoticeType> noticetypelist = nts.getAllList(new Long(2)) ;
		m.addAttribute("publicnewstypeList",noticetypelist);
		return "publicNews/createPublicNews";
	}
	
	/**
	 * 保存院务政务公开信息
	 * @param notice
	 * @return
	 */
	@RequestMapping(value="/publicnews",method = RequestMethod.POST)
	public @ResponseBody JsonResponse savePublicNews(TNotice notice){
		try{
			TNotice n = new TNotice();
			if(notice.getId()!=null){
				n = ns.getById(notice.getId());
			}
			n.setAuthor(notice.getAuthor());
			n.setPublishDate(new Date());
			n.setParentNoticeTypeId(new Long(2)); //院务政务
			n.setContent(notice.getContent());
			n.setNoticetypeId(notice.getNoticetypeId());
			n.setPublished(notice.isPublished());
			n.setTitle(notice.getTitle());
			
			ns.saveNotice(n);
			return new JsonResponse(true, null, n.getId());
		} catch(Exception e){
			log.error(e.getMessage(), e);
			return new JsonResponse(false, e.getMessage(), null);
		}
	}
	
	/**
	 * 通知修改
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/publicnews/edit/{id}")
	public String prepareEdit(@PathVariable long id,Model m){
		TNotice n = ns.getById(id);
		m.addAttribute("publicnews", n);	
		String username = SecurityUtil.getCurrentUserDetials().getUsername();
		TUser u = um.getTUserByLoginName(username);
		m.addAttribute("user", u);
		List<TNoticeType> noticetypelist = nts.getAllList(new Long(2)) ;
		m.addAttribute("publicnewstypeList",noticetypelist);
		return "publicNews/createPublicNews";
	}
	
	
	/**
	 * 根据条件查找通知
	 * @param key
	 * @param departmentId
	 * @param noticetypeId
	 * @param noticeDate
	 * @param published  0为未发布，1为发布，%为全部
	 * @return
	 */
	@RequestMapping(value = "/publicnews/searchAll/{parentId}")
	public @ResponseBody ResponseEntity<List<TNotice>> searchAll(@RequestParam(required = false)String sort,@RequestHeader("Range") String range,
			String key,Long publicnewstypeId,Date publishDate,Boolean published,@PathVariable long parentId) {
		PageableRange pr = new PageableRange(range,new DojoSort(sort).toSpringSort());
		Page<TNotice> page= ns.searchAllPublicNews(key, publicnewstypeId, publishDate, parentId,published,pr);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", new ContentRangeHeader(pr.getOffset(), page.getNumberOfElements(), page.getTotalElements()).toString());
		return new ResponseEntity<List<TNotice>>(page.getContent(), headers, HttpStatus.OK);
	}
	
	/**
	 * 切换通知发布状态
	 * @return
	 */
	@RequestMapping("/publicnews/switchState/{id}")
	public @ResponseBody JsonResponse switchPublishState(@PathVariable long id){
		try{
			ns.setPublished(id, !ns.getById(id).isPublished());
			return new JsonResponse(true, null, null);
		}catch(Exception e){
			return new JsonResponse(false, e.getMessage(), null);
		}
	}
	
	/**
	 * 切换通知发布状态
	 * @return
	 */
	@RequestMapping("/publicnews/del/{id}")
	public @ResponseBody JsonResponse deletePubliceNews(@PathVariable long id){
		try{
			ns.deleteNotice(id);
			return new JsonResponse(true, null, null);
		}catch(Exception e){
			return new JsonResponse(false, e.getMessage(), null);
		}
	}
	
	@RequestMapping("/publicnews/{id}")
	public String publicNewsRead(@PathVariable long id,Model m){
		TNotice n = ns.getById(id);
		m.addAttribute("publicnews", n);
		return "publicNews/readPublicNews";
	}
	

	
	
	@RequestMapping("/publicnews/upload")
	public @ResponseBody String noticeImageUpload(String filename, MultipartFile uploadedfile, HttpServletRequest request) throws Exception{
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + "upload\\images\\";
		File saveFile = new File(uploadPath + UUID.randomUUID());
		if(!saveFile.exists()){
			saveFile.createNewFile();
		}

		BufferedOutputStream os;

		os = new BufferedOutputStream(new FileOutputStream(saveFile));

		InputStream is = null;

		is = uploadedfile.getInputStream();
		//read buffer
		byte[] buffer = new byte[1024];
		while(is.read(buffer) > -1){
			os.write(buffer);
		}
		
		is.close();
		os.close();

		BufferedImage image = ImageIO.read(saveFile);


		
		return "<textarea>{file:'" + saveFile.getName() + "'," +
				"name:'" + FilenameUtils.getName(uploadedfile.getOriginalFilename()) + "'," +
				"width:" + image.getWidth() + ",height:" + image.getHeight() + "," +
				"type:'" + FilenameUtils.getExtension(uploadedfile.getOriginalFilename())+ "'," +
				"size:" + uploadedfile.getSize() + "," +
				"additionalParams:[]}</textarea>";
	}
	
	@RequestMapping("/publicnews/uploadAttachment")
	public @ResponseBody ResponseEntity<String> uploadAttachment(long publicnewsId, MultipartFile uploadedfile, HttpServletRequest request)throws Exception{
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + "upload\\publicnews\\attachment\\";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/html; charset=utf-8");
		
		if(uploadedfile.getSize() > 105000000){
			return new ResponseEntity<String>("<textarea>{success:false,message:'文件大小超过限制。'}</textarea>", headers, HttpStatus.OK);
		}
		
		try {
			TNoticeAttachment attach = new TNoticeAttachment();
			attach.setNoticeId(publicnewsId);
			attach.setName(FilenameUtils.getName(uploadedfile.getOriginalFilename()));
			attach.setSize(uploadedfile.getSize());
			//save
			ns.addAttachment(attach, uploadedfile.getInputStream(), uploadPath);
			return new ResponseEntity<String>("<textarea>{success:true}</textarea>", headers, HttpStatus.OK);
		} catch (Exception e) {
			String responseBody = "<textarea>{success:false,message:'" + e.getMessage() + "'}</textarea>";
			return new ResponseEntity<String>(responseBody, headers, HttpStatus.OK);
		}
	}
	
	@RequestMapping("/publicnews/attachmentList")
	public @ResponseBody List<TNoticeAttachment> attachmentList(long publicnewsId){
		return ns.getNoticeAttachments(publicnewsId);
	}

	@RequestMapping("/publicnews/download/{attachmentId}")
	public void download(@PathVariable long attachmentId,HttpServletRequest request,HttpServletResponse response){
		
		try {
			TNoticeAttachment file = ns.downLoadNoticeAttachment(attachmentId);
		    response.reset();  
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file.getName(), "UTF-8") + "\"");  
		    response.addHeader("Content-Length", "" + file.getSize());  
		    response.setContentType("application/octet-stream;charset=UTF-8");  
		    OutputStream out = new BufferedOutputStream(response.getOutputStream());
		    String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload\\publicnews\\attachment\\";
		    FileInputStream in = new FileInputStream(new File(uploadDir + file.getPath()));
		    IOUtils.copy(in, out);
		    out.flush();
		    out.close();
		    in.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping("/publicnews/deleteAttach/{attachmentId}")
	public @ResponseBody JsonResponse deleteAttachment(@PathVariable long attachmentId){
		try{
			ns.deleteAttachment(attachmentId);
			return new JsonResponse(true, null, null);
		}catch(Exception e){
			return new JsonResponse(false, e.getMessage(), null);
		}
	}

}
