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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import org.springframework.security.access.AccessDeniedException;
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

import com.whr.dms.config.Config;
import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.ClickType;
import com.whr.dms.models.TDepartment;
import com.whr.dms.models.TNotice;
import com.whr.dms.models.TNoticeAttachment;
import com.whr.dms.models.TNoticeType;
import com.whr.dms.models.TNotice_TDepartment;
import com.whr.dms.models.TUser;
import com.whr.dms.security.RoleType;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.ClickCountService;
import com.whr.dms.service.DepartmentManager;
import com.whr.dms.service.NoticeService;
import com.whr.dms.service.NoticeTypeService;
import com.whr.dms.service.UserManager;
import com.whr.dms.web.ajax.ContentRangeHeader;
import com.whr.dms.web.ajax.DojoSort;
import com.whr.dms.web.ajax.JsonResponse;
import com.whr.dms.web.ajax.PageableRange;

@Controller
public class NoticeController {
	
	private static Logger log = LoggerFactory.getLogger(NoticeController.class);
	
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
		TUser u = um.findTUserByLoginName(username);
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

	@Resource
	Config cfg;
	
	@Resource
	ClickCountService ccs;
	/**
	 * 通知列表页面
	 * @return
	 */
	@RequestMapping(value = "/notice/noticeMana")
	public String noticeListPage(Model m) {
		List<TNoticeType> noticetypelist = nts.getAllList(new Long(1)) ;
		m.addAttribute("noticetypeList",noticetypelist);
		TUser u = getUser();
		m.addAttribute("departmentId", u.getDepartment().getId());
		return "notice/noticeList";
	}

	/**
	 * 已发布通知列表JSON
	 * @param sort
	 * @param range
	 * @return
	 */
	@RequestMapping(value = "/notice/list/{parentId}")
	public ResponseEntity<List<TNotice>> noticeList(@RequestParam(required = false)String sort,@RequestHeader("Range") String range,@PathVariable Long parentId) {
		PageableRange pr = new PageableRange(range,new DojoSort(sort).toSpringSort());
		TUser u = getUser();
		long departmentId = u.getDepartment().getId();
		Page<TNotice> page = ns.getPublishedNoticesByDepartment(pr, departmentId,parentId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", new ContentRangeHeader(pr.getOffset(), page.getNumberOfElements(), page.getTotalElements()).toString());
		
		return new ResponseEntity<List<TNotice>>(page.getContent(), headers, HttpStatus.OK);
	}
	
	/**
	 * 全部通知列表JSON
	 * @param sort
	 * @param range
	 * @return
	 */
	@RequestMapping(value = "/notice/allList/{parentId}")
	public ResponseEntity<List<TNotice>> allNoticeList(@RequestParam(required = false)String sort,@RequestHeader("Range") String range,@PathVariable Long parentId) {
		PageableRange pr = new PageableRange(range,new DojoSort(sort).toSpringSort());
		Page<TNotice> page = ns.getAllNotices(pr,parentId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", new ContentRangeHeader(pr.getOffset(), page.getNumberOfElements(), page.getTotalElements()).toString());
		
		return new ResponseEntity<List<TNotice>>(page.getContent(), headers, HttpStatus.OK);
	}
	
	@RequestMapping("/notice/manage/{parentId}")
	public String noticeManagePage(@PathVariable long parentId,Model m){
		List<TDepartment> departmentlist = ds.getAllDepartments();
		List<TNoticeType> noticetypelist = nts.getAllList(parentId) ;
		m.addAttribute("departmentList", departmentlist);
		m.addAttribute("noticetypeList",noticetypelist);
		return "notice/noticeManage";
	}
	
	
	/**
	 * 新建通知页面
	 * @return
	 */
	@RequestMapping(value = "/notice/create")
	public String noticeCreatePage(Model m){
		TUser u = getUser();
		m.addAttribute("departList", dm.getAllDepartments());
		m.addAttribute("user", u);
		List<TNoticeType> noticetypelist = nts.getAllList(new Long(1)) ;
		m.addAttribute("noticetypeList",noticetypelist);
		return "notice/createNotice";
	}
	
	/**
	 * 保存通知
	 * @param notice
	 * @return
	 */
	@RequestMapping(value="/notice",method = RequestMethod.POST)
	public @ResponseBody JsonResponse saveNotice(TNotice notice, long[] sharedDepartmentId){
		try{
			Set<Long> departIds = new HashSet<Long>();
			for (int i = 0; i < sharedDepartmentId.length; i++) {
				departIds.add(sharedDepartmentId[i]);
			}
			TNotice n = new TNotice();
			if(notice.getId()!=null){
				n = ns.getById(notice.getId());
			}
			
			n.setAuthor(notice.getAuthor());
			n.setPublishDate(new Date());
			n.setParentNoticeTypeId(new Long(1)); //通知
			
			String c = notice.getContent();
			if(c != null) {
				c = c.replace("\n", "");
				c = c.replace("\r", "");
				notice.setContent(c);
			}
			n.setContent(notice.getContent());
			n.setNoticetypeId(notice.getNoticetypeId());
			n.setNoticeDate(notice.getNoticeDate());
			n.setPublished(notice.isPublished());
			n.setTitle(notice.getTitle());
			n.setEmergencyState(notice.isEmergencyState());
			ns.saveNotice(n,departIds);
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
	 * @throws ParameterCheckException 
	 */
	@RequestMapping(value="/notice/edit/{id}")
	public String prepareEdit(@PathVariable long id,Model m) throws ParameterCheckException{
		TNotice n = ns.getById(id);
		
		if (n == null) {
			throw new ParameterCheckException("未找到此记录");
		}

		if (!SecurityUtil.isMe(n.getLastUpdaterLoginName())
				&& !SecurityUtil.hasRole(RoleType.ROLE_ADMIN)) {
			throw new AccessDeniedException("只有作者本人或者管理员才能删除");
		}
		
		m.addAttribute("notice", n);
		
		List<TNotice_TDepartment> ndList = ns.findNoticeDepartment(id);
		List<Long> checkedDepartment = new ArrayList<Long>();
		for(TNotice_TDepartment nd : ndList){
			checkedDepartment.add(nd.getDepartmentId());
		}
		m.addAttribute("checkedDepartment", checkedDepartment);
		
		List<TNoticeType> noticetypelist = nts.getAllList(new Long(1)) ;
		m.addAttribute("noticetypeList",noticetypelist);
		
		String username = SecurityUtil.getCurrentUserDetials().getUsername();
		TUser u = um.findTUserByLoginName(username);
		m.addAttribute("departList", dm.getAllDepartments());
		m.addAttribute("user", u);
		return "notice/createNotice";
	}
	
	
	/**
	 * 根据条件查找通知
	 * @param key
	 * @param departmentId
	 * @param noticetypeId
	 * @param noticeDate
	 * @param published  0为未发布，1为发布，2为全部
	 * @return
	 */
	@RequestMapping(value = "/notice/searchAll/{parentId}")
	public @ResponseBody ResponseEntity<List<TNotice>> searchAll(@RequestParam(required = false)String sort,@RequestHeader("Range") String range,
			String key,long departmentId,long noticetypeId,Date noticeDate,long published,@PathVariable long parentId) {
		PageableRange pr = new PageableRange(range,new DojoSort(sort).toSpringSort());
		Page<TNotice> page = null;
		if(departmentId==0){
			if(noticetypeId==0){
				if(noticeDate==null){
					page = ns.searchAllNotices(key,published,parentId,pr);
				}else if(noticeDate!=null){
					page = ns.searchAllNoticesByNoticedate(key,noticeDate,published,parentId,pr);
				}
			}else if(noticetypeId>0){
				if(noticeDate==null){
					page = ns.searchAllNoticesByNoticetype(key, noticetypeId,published,parentId,pr);
				}
				else if(noticeDate!=null){
					page = ns.searchAllNoticesByTypeAndDate(key,noticetypeId,noticeDate,published,parentId,pr);
				}
			}
		}else if(departmentId>0){
			if(noticetypeId==0){
				if(noticeDate==null){
					page = ns.searchAllNoticesByDepartmentId(key,departmentId,published,parentId,pr);
				}else if(noticeDate!=null){
					page = ns.searchAllNoticesByDepartmentAndDate(key,departmentId,noticeDate,published,parentId,pr);
				}
			}else if(noticetypeId>0){
				if(noticeDate==null){
					page = ns.searchAllNoticesByDepartmentAndType(key,departmentId,noticetypeId,published,parentId,pr);
				}else if(noticeDate!=null){
					page = ns.searchAllNotices(key,departmentId,noticetypeId,noticeDate,published,parentId,pr);
				}
			}
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", new ContentRangeHeader(pr.getOffset(), page.getNumberOfElements(), page.getTotalElements()).toString());
		return new ResponseEntity<List<TNotice>>(page.getContent(), headers, HttpStatus.OK);
	}
	
	/**
	 * 切换通知发布状态
	 * @return
	 */
	@RequestMapping("/notice/switchState/{id}")
	public @ResponseBody JsonResponse switchPublishState(@PathVariable long id){
		try{
			ns.setPublished(id, !ns.getById(id).isPublished());
			return new JsonResponse(true, null, null);
		}catch(Exception e){
			return new JsonResponse(false, e.getMessage(), null);
		}
	}
	
	/**
	 *删除通知 
	 * @return
	 * @throws ParameterCheckException 
	 */
	@RequestMapping("/notice/del/{id}")
	public @ResponseBody JsonResponse deleteNotice(@PathVariable long id) throws ParameterCheckException{
		


		try{
			TNotice n = ns.getById(id);
			if (n == null) {
				throw new ParameterCheckException("未找到此记录");
			}

			if (!SecurityUtil.isMe(n.getLastUpdaterLoginName())
					&& !SecurityUtil.hasRole(RoleType.ROLE_ADMIN)) {
				throw new AccessDeniedException("只有作者本人或者管理员才能删除");
			}
			ns.deleteNotice(id);
			return new JsonResponse(true, null, null);
		}catch(Exception e){
			return new JsonResponse(false, e.getMessage(), null);
		}
	}
	
	@RequestMapping("/notice/{id}")
	public String noticeRead(@PathVariable long id,Model m){
		TNotice n = ns.getById(id);
		m.addAttribute("notice", n);
		Long counts = ccs.saveClickCount(ClickType.TNotice,id);//增加点击次数
		m.addAttribute("counts",counts);
		return "notice/readNotice";
	}
	
	@RequestMapping("/notice/upload")
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
	
	@RequestMapping("/notice/uploadAttachment")
	public @ResponseBody ResponseEntity<String> uploadAttachment(long noticeId, MultipartFile uploadedfile, HttpServletRequest request)throws Exception{
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + "upload\\notice\\attachment\\";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/html; charset=utf-8");
		
		if(uploadedfile.getSize() > 105000000){
			return new ResponseEntity<String>("<textarea>{success:false,message:'文件大小超过限制。'}</textarea>", headers, HttpStatus.OK);
		}
		
		try {
			TNoticeAttachment attach = new TNoticeAttachment();
			attach.setNoticeId(noticeId);
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
	
	@RequestMapping("/notice/attachmentList")
	public @ResponseBody List<TNoticeAttachment> attachmentList(long noticeId){
		return ns.getNoticeAttachments(noticeId);
	}

	@RequestMapping("/notice/download/{attachmentId}")
	public void download(@PathVariable long attachmentId,HttpServletRequest request,HttpServletResponse response){
		
		try {
			TNoticeAttachment file = ns.downLoadNoticeAttachment(attachmentId);
		    response.reset();  
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file.getName(), "UTF-8") + "\"");  
		    response.addHeader("Content-Length", "" + file.getSize());  
		    response.setContentType("application/octet-stream;charset=UTF-8");  
		    OutputStream out = new BufferedOutputStream(response.getOutputStream());
		    String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload\\notice\\attachment\\";
		    FileInputStream in = new FileInputStream(new File(uploadDir + file.getPath()));
		    IOUtils.copy(in, out);
		    out.flush();
		    out.close();
		    in.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping("/notice/deleteAttach/{attachmentId}")
	public @ResponseBody JsonResponse deleteAttachment(@PathVariable long attachmentId){
		try{
			ns.deleteAttachment(attachmentId);
			return new JsonResponse(true, null, null);
		}catch(Exception e){
			return new JsonResponse(false, e.getMessage(), null);
		}
	}
	
	/**
	 * 切换通知紧急状态
	 * @return
	 */
	@RequestMapping("/notice/switchEmergencyState/{id}")
	public @ResponseBody JsonResponse switchEmergencyState(@PathVariable long id){
		try{
			ns.setEmergencyState(id, !ns.getById(id).isEmergencyState());
			return new JsonResponse(true, null, null);
		}catch(Exception e){
			return new JsonResponse(false, e.getMessage(), null);
		}
	}
}
