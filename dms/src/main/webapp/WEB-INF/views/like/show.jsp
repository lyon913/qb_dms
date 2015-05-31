<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>

共有${result.total }人表态
<table align="center">
	<tr>
		<c:forEach items="${result.counts }" var="c" varStatus="s">
			<td width="100px">
				<table>
					<tr height="50px">
						<td height="50px" align="center">
							${c.count}
								<dms:likeBar index="${s.index }" percent="${c.percent}"/>
						</td>
					</tr>
					<tr>
						<td align="center"><img src="${ctx }upload/images/like/${c.picture}"
							width="41" height="41"></td>
					</tr>
					<tr>
						<td align="center"><a href="${ctx }/like/vote/${type }/${fk}/${c.optionId}">${c.optionTitle }</a>
						</td>
					</tr>
				</table>
			</td>
		</c:forEach>
	</tr>
</table>