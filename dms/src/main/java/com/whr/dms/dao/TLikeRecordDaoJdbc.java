package com.whr.dms.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.whr.dms.models.LikeRecordCount;

@Component
public class TLikeRecordDaoJdbc {

	@PersistenceContext
	private EntityManager em;
	
	public List<LikeRecordCount> getResult(long likeId) {
		String sql = "select o.id,o.title,o.picture,count(r.id) from TlikeOption o left join TLikeRecord r on(o.id = r.optionid and r.likeid=:likeId ) group by o.id,o.title order by o.id";
		Query q = em.createNativeQuery(sql);
		q.setParameter("likeId", likeId);
		List<Object[]> result = q.getResultList();
		
		List<LikeRecordCount> countList = new ArrayList<LikeRecordCount>();
		for(Object[] record : result) {
			LikeRecordCount rc = new LikeRecordCount(((BigInteger)record[0]).longValue(), (String)record[1],(String)record[2],((BigInteger)record[3]).longValue());
			countList.add(rc);
		}
		return countList;
	}
}
