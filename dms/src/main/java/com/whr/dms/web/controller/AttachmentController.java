package com.whr.dms.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.AttachmentType;
import com.whr.dms.models.TAttachment;
import com.whr.dms.security.RoleType;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.AttachmentService;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {

	@Autowired
	AttachmentService ats;

	@RequestMapping(value = "/{foreignId}/{attachmentType}/addAttachment", method = RequestMethod.POST)
	public String addAttachment(@PathVariable long foreignId,
			@PathVariable AttachmentType attachmentType,
			MultipartFile uploadedfile, HttpServletRequest request)
			throws Exception {
		String uploadPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "upload\\attachment\\";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/html; charset=utf-8");

		TAttachment attach = new TAttachment();
		attach.setForeignTable(attachmentType);
		attach.setForeignKey(foreignId);
		attach.setName(FilenameUtils.getName(uploadedfile.getOriginalFilename()));
		attach.setSize(uploadedfile.getSize());

		// save
		ats.addAttachment(attach, uploadedfile.getInputStream(), uploadPath);

		return "redirect:/attachment/{foreignId}/{attachmentType}/attachmentList";
	}

	@RequestMapping(value = "/{foreignId}/{attachmentType}/attachmentList", method = RequestMethod.GET)
	public String attachmentList(@PathVariable long foreignId,
			@PathVariable AttachmentType attachmentType, Model m) {
		List<TAttachment> alist = ats
				.listAttachments(foreignId, attachmentType);
		m.addAttribute("attachmentList", alist);
		m.addAttribute("foreignId", foreignId);
		m.addAttribute("attachmentType", attachmentType);
		return "/attachment/attachmentList";
	}

	@RequestMapping("/download/{id}")
	public void download(@PathVariable long id, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			TAttachment file = ats.downloadAttachment(id);
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ URLEncoder.encode(file.getName(), "UTF-8") + "\"");
			response.addHeader("Content-Length", "" + file.getSize());
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream out = new BufferedOutputStream(
					response.getOutputStream());
			String uploadDir = request.getSession().getServletContext()
					.getRealPath("/")
					+ "upload\\attachment\\";
			FileInputStream in = new FileInputStream(new File(uploadDir
					+ file.getPath()));
			IOUtils.copy(in, out);
			out.flush();
			out.close();
			in.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@RequestMapping("/{id}/del")
	public String deleteAttachment(@PathVariable long id) throws ParameterCheckException {
		TAttachment a = ats.getAttachment(id);
		if (a == null) {
			throw new ParameterCheckException("未找到此记录");
		}

		if (!SecurityUtil.isMe(a.getLastUpdaterLoginName())
				&& !SecurityUtil.hasRole(RoleType.ROLE_ADMIN)) {
			throw new AccessDeniedException("只有作者本人或者管理员才能删除");
		}

		Long foreignId = a.getForeignKey();
		AttachmentType attachmentType = a.getForeignTable();
		ats.deleteAttachment(id);
		return "redirect:/attachment/"+foreignId+"/"+attachmentType+"/attachmentList";
	}
}
