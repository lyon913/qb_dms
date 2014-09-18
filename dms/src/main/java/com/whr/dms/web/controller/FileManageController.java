package com.whr.dms.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.whr.dms.config.Config;
import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TFile;
import com.whr.dms.models.TFolder;
import com.whr.dms.models.TUser;
import com.whr.dms.security.RoleType;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.DepartmentManager;
import com.whr.dms.service.FileService;
import com.whr.dms.service.UserManager;
import com.whr.dms.web.ajax.ContentRangeHeader;
import com.whr.dms.web.ajax.DojoSort;
import com.whr.dms.web.ajax.JsonResponse;
import com.whr.dms.web.ajax.PageableRange;
import com.whr.dms.web.form.FolderTreeNode;

@Controller
public class FileManageController {
	@Autowired
	FileService fs;
	
	@Autowired
	UserManager um;
	
	@Autowired
	DepartmentManager dm;
	
	@Autowired
	Config cfg;
	
	public TUser getUser(){
		String username = SecurityUtil.getCurrentUserDetials().getUsername();
		TUser u = um.findTUserByLoginName(username);
		return u;
	}

	/**
	 * 文件共享页面
	 * @param m
	 * @return
	 */
	@RequestMapping("/files")
	public String filePage(Model m) {
		//check permissions
		String username = SecurityUtil.getCurrentUserDetials().getUsername();
		TUser u = um.findTUserByLoginName(username);
		m.addAttribute("departList", dm.getAllDepartments());
		m.addAttribute("user", u);
		
		
		m.addAttribute("isManager", SecurityUtil.hasRole(RoleType.ROLE_FILE_MANAGER));
		return "files/files";
	}

	/**
	 * 子文件夹列表（树形）
	 * @param id
	 * @return
	 */
	@RequestMapping("/folder/list/{id}")
	public @ResponseBody
	FolderTreeNode listFolders(@PathVariable long id) {
		FolderTreeNode fullNode = new FolderTreeNode();
		fullNode.setChildrenTFolder(fs.getSubDirs(id));
		fullNode.setId(id);
		return fullNode;
	}

	/**
	 * 创建文件夹
	 * @param parentId
	 * @param name
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "/folder/new", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse createFolder(long parentId, String name, String description) {
		TFolder folder = new TFolder();
		folder.setParentId(parentId);
		folder.setName(name);
		folder.setDescription(description);

		try {
			fs.createFolder(folder);
			return new JsonResponse(true, null, null);
		} catch (ParameterCheckException e) {
			return new JsonResponse(false, e.getMessage(), null);
		}
	}

	/**
	 * 删除文件夹
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/folder/{id}/delete")
	public @ResponseBody
	JsonResponse deleteFolder(@PathVariable long id) {
		try {
			fs.deleteFolder(id);
			return new JsonResponse(true, null, null);
			
		} catch (ParameterCheckException e) {
			
			return new JsonResponse(false, e.getMessage(), null);
		}
	}

	/**
	 * 上传文件
	 * @param parentId
	 * @param sharedDepartmentId
	 * @param file
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/files/upload", method = RequestMethod.POST)
	public ResponseEntity<String> upload(long parentId, long[] sharedDepartmentId, MultipartFile file, HttpServletRequest request) throws UnsupportedEncodingException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/html; charset=utf-8");
		
		if(file.getSize() > 105000000){
			return new ResponseEntity<String>("<textarea>{success:false,message:'文件大小超过限制。'}</textarea>", headers, HttpStatus.OK);
		}
		TUser u = getUser();
		TFile tfile = new TFile();
		tfile.setParentId(parentId);
		tfile.setName(FilenameUtils.getName(file.getOriginalFilename()));
		tfile.setSize(file.getSize());
        tfile.setAuthor(u.getName());
        tfile.setAuthorDepart(u.getDepartment().getName());
        tfile.setAuthorId(u.getId());

		
		try {
			Set<Long> dpIds = new HashSet<Long>();
			for (int i = 0; i < sharedDepartmentId.length; i++) {
				dpIds.add(sharedDepartmentId[i]);
			}
			String savePath = request.getSession().getServletContext().getRealPath("/") + "upload\\files\\";
			fs.uploadFile(tfile, file.getInputStream(),savePath, dpIds);
			return new ResponseEntity<String>("<textarea>{success:true}</textarea>", headers, HttpStatus.OK);
		} catch (Exception e) {
			String responseBody = "<textarea>{success:false,message:'" + e.getMessage() + "'}</textarea>";
			return new ResponseEntity<String>(responseBody, headers, HttpStatus.OK);
		}

	}
	
	/**
	 * 文件夹下的文件列表
	 * @param sort
	 * @param range
	 * @param folderId
	 * @return
	 */
	@RequestMapping(value = "/files/list/{folderId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<TFile>> listFiles(@RequestParam(required = false)String sort,@RequestHeader("Range") String range,@PathVariable long folderId) {
		PageableRange pr = new PageableRange(range,new DojoSort(sort).toSpringSort());
		Page<TFile> page = fs.listFiles(folderId,pr);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", new ContentRangeHeader(pr.getOffset(), page.getNumberOfElements(), page.getTotalElements()).toString());
		return new ResponseEntity<List<TFile>>(page.getContent(), headers, HttpStatus.OK);
	}
	
	/**
	 * 下载
	 * @param fileId
	 * @param request
	 * @param response
	 */
	@RequestMapping("/files/{fileId}")
	public void download(@PathVariable long fileId,HttpServletRequest request,HttpServletResponse response){
		FileInputStream in = null;
		OutputStream out = null;
		try {
			TFile file = fs.downLoadFile(fileId);
		    response.reset();  
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file.getName(), "UTF-8") + "\"");  
		    response.addHeader("Content-Length", "" + file.getSize());  
		    response.setContentType("application/octet-stream;charset=UTF-8");  

		    String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload\\files\\";
		    File f = new File(uploadDir + file.getFilePath());
		    if(!f.exists()){
		    	throw new RuntimeException("待下载的文件未找到。");
		    }
		    in = new FileInputStream(new File(uploadDir + file.getFilePath()));
		    out = new BufferedOutputStream(response.getOutputStream());
		    
		    IOUtils.copy(in, out);
		    out.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			//关闭文件流
			try{
				if(out != null){
					out.close();
				}
				if(in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * 删除文件
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/files/{id}/delete")
	public @ResponseBody JsonResponse deleteFile(@PathVariable long id,HttpServletRequest request){
		
		try {
			String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload\\files\\";
			fs.deleteFile(id,uploadDir);
			return new JsonResponse(true, null, null);
		} catch (Exception e) {
			return new JsonResponse(false, e.getMessage(), null);
		}
	}
	
	/**
	 * 文件查找
	 * @param sort
	 * @param range
	 * @param nameKey
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/files/search")
	public @ResponseBody ResponseEntity<List<TFile>> searchFiles(@RequestParam(required = false)String sort,@RequestHeader("Range") String range,String nameKey) throws UnsupportedEncodingException{
		PageableRange pr = new PageableRange(range,new DojoSort(sort).toSpringSort());
		Page<TFile> page = fs.search(nameKey,pr);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", new ContentRangeHeader(pr.getOffset(), page.getNumberOfElements(), page.getTotalElements()).toString());
		return new ResponseEntity<List<TFile>>(page.getContent(), headers, HttpStatus.OK);
	}
	
	/**
	 * 选择文件夹对话框
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/files/chooseFolder")
	public String chooseFolderDialog(Model m) {
		List<TFolder> list = fs.findAllFolders();
		FolderTreeNode n = new FolderTreeNode(list);
		List<FolderTreeNode> formated = n.loadFormatedList();
		m.addAttribute("folders",formated);
		return "files/chooseFolder";
	}
	
	/**
	 * 移动文件
	 * @param filesId
	 * @param folerId
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/files/move", method = RequestMethod.POST)
	public @ResponseBody JsonResponse moveFiles(long[] filesId, long folderId, Model m) {
		try {
			fs.moveFiles(filesId,folderId);
			return new JsonResponse(true, null, null);
		} catch (Exception e) {
			return new JsonResponse(false, e.getMessage(), null);
		}
	}

}
