<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="dms"%>
<style>
html,body{
	height: auto;
	width:auto;
	overflow: auto;
}
</style>
<div>
<a href="${ctx }vote/new?suggId=${suggId}" class="btn-normal">
	<span class="dijitIconAdd"></span>
	发起投票
</a>
</div>