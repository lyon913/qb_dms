package com.whr.dms.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.whr.dms.config.Config;
import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TFolder;
import com.whr.dms.models.TSoftware;
import com.whr.dms.models.TUser;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.SoftwareService;
import com.whr.dms.service.UserManager;
import com.whr.dms.web.ajax.ContentRangeHeader;
import com.whr.dms.web.ajax.DojoSort;
import com.whr.dms.web.ajax.JsonResponse;
import com.whr.dms.web.ajax.PageableRange;
import com.whr.dms.web.form.FolderTreeNode;

@Controller
public class SoftwareController {
	@Autowired
	SoftwareService sservice;
	@Resource
	Config cfg;
	@Resource
	UserManager um;
	
	public TUser getUser(){
		String username = SecurityUtil.getCurrentUserDetials().getUsername();
		TUser u = um.getTUserByLoginName(username);
		return u;
	}
	
	@RequestMapping(value="/software/softwareMana")
	public String tolist(){
		return "software/softwareManage";
	}
//	@RequestMapping(value="/software/softwareList")
//	public ResponseEntity<List<TSoftware>> softwareLists(@RequestParam(required = false)String sort,@RequestHeader("Range") String range) {
//		PageableRange pr = new PageableRange(range,new DojoSort(sort).toSpringSort());
//		Page<TSoftware> page = sservice.getSoftware(pr);
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Range", new ContentRangeHeader(pr.getOffset(), page.getNumberOfElements(), page.getTotalElements()).toString());
//		
//		return new ResponseEntity<List<TSoftware>>(page.getContent(), headers, HttpStatus.OK);
//	}
	
	@RequestMapping(value = "/software/softwareList/{folderId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<TSoftware>> listFiles(@RequestParam(required = false)String sort,@RequestHeader("Range") String range,@PathVariable long folderId) {
		PageableRange pr = new PageableRange(range,new DojoSort(sort).toSpringSort());
		Page<TSoftware> page = sservice.listSoftware(folderId,pr);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", new ContentRangeHeader(pr.getOffset(), page.getNumberOfElements(), page.getTotalElements()).toString());
		return new ResponseEntity<List<TSoftware>>(page.getContent(), headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/software/upload", method = RequestMethod.POST)
	public ResponseEntity<String> upload(long parentId, MultipartFile file, HttpServletRequest request) throws UnsupportedEncodingException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/html; charset=utf-8");
		
		if(file.getSize() > 105000000){
			return new ResponseEntity<String>("<textarea>{success:false,message:'文件大小超过限制。'}</textarea>", headers, HttpStatus.OK);
		}
		TUser u = getUser();
		TSoftware software = new TSoftware();
		software.setParentId(parentId);
		software.setName(FilenameUtils.getName(file.getOriginalFilename()));
		software.setSize(file.getSize());
        software.setAuthor(u.getDepartment().getName());

		
		try {
			String savePath = request.getSession().getServletContext().getRealPath("/") + "upload\\softwares\\";
			sservice.uploadFile(software, file.getInputStream(),savePath);
			return new ResponseEntity<String>("<textarea>{success:true}</textarea>", headers, HttpStatus.OK);
		} catch (Exception e) {
			String responseBody = "<textarea>{success:false,message:'" + e.getMessage() + "'}</textarea>";
			return new ResponseEntity<String>(responseBody, headers, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/software/list")
	public String listSoftware() {
		return "software/softwareDownloadList";
	}
	
	@RequestMapping("/software/download/{softwareId}")
	public void download(@PathVariable long softwareId,HttpServletRequest request,HttpServletResponse response){
		
		try {
			TSoftware file = sservice.downLoadSoftware(softwareId);
		    response.reset();  
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file.getName(), "UTF-8") + "\"");  
		    response.addHeader("Content-Length", "" + file.getSize());  
		    response.setContentType("application/octet-stream;charset=UTF-8");  
		    OutputStream out = new BufferedOutputStream(response.getOutputStream());
		    String savePath = request.getSession().getServletContext().getRealPath("/") + "upload\\softwares\\";
		    FileInputStream in = new FileInputStream(new File(savePath + file.getFilePath()));
		    IOUtils.copy(in, out);
		    out.flush();
		    out.close();
		    in.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(value = "/software/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody JsonResponse deleteSoftware(@PathVariable long id,HttpServletRequest request){
		
		try {
			String savePath = request.getSession().getServletContext().getRealPath("/") + "upload\\softwares\\";
			sservice.deleteSoftware(id,savePath);
			return new JsonResponse(true, null, null);
		} catch (Exception e) {
			return new JsonResponse(false, e.getMessage(), null);
		}
	}
	
	/**
	 * 在全部软件中查找
	 * @param sort
	 * @param range
	 * @return
	 */
	@RequestMapping(value = "/software/searchAll")
	public @ResponseBody ResponseEntity<List<TSoftware>> searchAll(@RequestParam(required = false)String sort,@RequestHeader("Range") String range,String nameKey) {
		PageableRange pr = new PageableRange(range,new DojoSort(sort).toSpringSort());
		Page<TSoftware> page = sservice.search(nameKey,pr);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", new ContentRangeHeader(pr.getOffset(), page.getNumberOfElements(), page.getTotalElements()).toString());
		return new ResponseEntity<List<TSoftware>>(page.getContent(), headers, HttpStatus.OK);

	}
	
	@RequestMapping("/softwarefolder/list/{id}")
	public @ResponseBody
	FolderTreeNode listFolders(@PathVariable long id) {
		FolderTreeNode fullNode = new FolderTreeNode(sservice.getSubDirs(id));
		fullNode.setId(id);
		return fullNode;
	}

	@RequestMapping(value = "/softwarefolder", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse createFolder(long parentId, String name, String description) {
		TFolder folder = new TFolder();
		folder.setParentId(parentId);
		folder.setName(name);
		folder.setDescription(description);

		try {
			sservice.createFolder(folder);
			return new JsonResponse(true, null, null);
		} catch (ParameterCheckException e) {
			return new JsonResponse(false, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/softwarefolder/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	JsonResponse deleteFolder(@PathVariable long id) {
		try {
			sservice.deleteFolder(id);
			return new JsonResponse(true, null, null);
			
		} catch (ParameterCheckException e) {
			
			return new JsonResponse(false, e.getMessage(), null);
		}
	}

}
