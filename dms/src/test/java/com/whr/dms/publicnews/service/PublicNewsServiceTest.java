package com.whr.dms.publicnews.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.whr.dms.models.TNotice;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/root-context.xml")
public class PublicNewsServiceTest {
	@PersistenceContext
	EntityManager em;
	
	@Test
	public void testSearch(){
		TypedQuery<TNotice> q = em.createQuery("from TNotice where noticetypeId like :noticetypeId", TNotice.class);
		q.setParameter("noticetypeId", "");
		List<TNotice> list = q.getResultList();
		System.out.print(list.size());
	}
	
}
