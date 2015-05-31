package com.whr.dms.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TLikeDao;
import com.whr.dms.dao.TLikeOptionDao;
import com.whr.dms.dao.TLikeRecordDao;
import com.whr.dms.dao.TLikeRecordDaoJdbc;
import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.LikeRecordCount;
import com.whr.dms.models.LikeResult;
import com.whr.dms.models.TLike;
import com.whr.dms.models.TLikeOption;
import com.whr.dms.models.TLikeRecord;
import com.whr.dms.models.TUser;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.utils.UploadUtils;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private TLikeDao lDao;
	
	@Autowired
	private TLikeOptionDao oDao;
	
	@Autowired
	private TLikeRecordDao rDao;
	
	@Autowired
	private TLikeRecordDaoJdbc rDaoJdbc;
	
	@Transactional
	@Override
	public void like(String type, long fk, long optionId)
			throws ParameterCheckException {
		TLike like = lDao.findByTypeFk(fk);
		if (like == null) {
			like = new TLike();
			like.setFk(fk);

			lDao.save(like);
		}

		TLikeOption opt = oDao.findOne(optionId);
		// 选项为空
		if (opt == null) {
			throw new ParameterCheckException("选项不存在");
		}

		TUser user = SecurityUtil.getCurrentUser();

		// 判断用户是否已经投票
		if (rDao.countByUserId(like.getId(), user.getId()) > 0) {
			return ;
		}

		TLikeRecord r = new TLikeRecord();
		r.setLikeId(like.getId());
		r.setOptionId(optionId);
		r.setUserId(user.getId());
		r.setUserName(user.getName());
		rDao.save(r);

	}


	@Override
	public LikeResult getLikeCount(String type, long fk) throws ParameterCheckException {
		TLike like = lDao.findByTypeFk(fk);
		
		if(like == null) {
			like = new TLike();
			like.setId(-1L);
		}
		
		LikeResult result = new LikeResult();
		result.setLikeId(like.getId());
		
		List<LikeRecordCount> countList = rDaoJdbc.getResult(like.getId());
		long total = 0;
		for(LikeRecordCount count : countList) {
			total += count.getCount();
		}
		for(LikeRecordCount count : countList) {
			double percent = 0;
			if(total>0) {
				percent = (double)count.getCount()/total*100f;
			}
			count.setPercent(Math.floor(percent));
		}
		result.setTotal(total);
		result.setCounts(countList);
		return result;
		
	}

	@Override
	public List<TLikeOption> findAll() {
		List<TLikeOption> olist = oDao.findAll();
		return olist;
	}

	@Transactional
	@Override
	public void saveLikeOption(TLikeOption opt, InputStream pic, String uploadFolder) throws IOException {
		String path = UploadUtils.saveUploadFile(pic, uploadFolder);
		
		opt.setPicture(path);
		oDao.save(opt);
	}
	
	@Transactional
	@Override
	public void saveLikeOptionWithoutPicture(TLikeOption opt) {
		oDao.save(opt);
	}
	
	@Transactional
	@Override
	public void deleteOptionCascade(long optionId) {
		rDao.deleteByOptionId(optionId);
		oDao.delete(optionId);
	}

	@Override
	public TLikeOption findOptionById(long optionId) {
		return oDao.findOne(optionId);
	}
	

	
}