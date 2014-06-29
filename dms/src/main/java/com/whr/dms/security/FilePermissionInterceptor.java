//package com.whr.dms.security;
//
//import javax.annotation.Resource;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import com.whr.dms.files.service.FileService;
//
//@Component
//@Aspect
//public class FilePermissionInterceptor {
//
//	@Resource
//	private FileService fs;
//	
//	//@Before("execution(* com.whr.dms.files.service..downLoadFile(..))")
//	public void checkFilePermissions(){
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username = "";
//		if (principal instanceof UserDetails) {
//		  username = ((UserDetails)principal).getUsername();
//		} else {
//		  username = principal.toString();
//		}
//		if(!fs.hasAccessPermission(username, 1)){
//			throw new AccessDeniedException("你无权访问此文件。");
//		}
//	}
//
//}
