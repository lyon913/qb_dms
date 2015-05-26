package com.whr.dms.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TLikeDao;
import com.whr.dms.dao.TLikeOptionDao;
import com.whr.dms.dao.TLikeRecordDao;
import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.LikeRecordCount;
import com.whr.dms.models.TLike;
import com.whr.dms.models.TLikeOption;
import com.whr.dms.models.TLikeRecord;
import com.whr.dms.utils.UploadUtils;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private TLikeDao lDao;
	
	@Autowired
	private TLikeOptionDao oDao;
	
	@Autowired
	private TLikeRecordDao rDao;
	
	@Override
	public void addLike(TLike like, long suggId) {
		// TODO Auto-generated method stub
		lDao.save(like);
		lDao.updateLikeIdOnTSuggestion(suggId, like.getId());
	}

	@Override
	public void like(long likeId, long[] optionId, long userId, String userName)
			throws ParameterCheckException {
		// TODO Auto-generated method stub
		TLike v = lDao.findOne(likeId);
		if(v == null) {
			throw new ParameterCheckException("未找到点赞信息！");
		}
		//选项为空
		if(optionId == null || optionId.length == 0) {
			throw new ParameterCheckException("点赞选项为空！");
		}
		
		//判断用户是否已经投票
		if(rDao.countByUserId(likeId,userId)>0) {
			throw new ParameterCheckException("您已经点过赞，请勿重复点赞！");
		}

		for(long oId : optionId) {
			TLikeRecord r = new TLikeRecord();
			r.setLikeId(likeId);
			r.setOptionId(oId);
			r.setUserId(userId);
			r.setUserName(userName);
			rDao.save(r);
		}
	}

	@Override
	public TLike findById(long likeId) {
		// TODO Auto-generated method stub
		return lDao.findOne(likeId);
	}

	@Override
	public List<LikeRecordCount> getLikeCount(long likeId)
			throws ParameterCheckException {
		// TODO Auto-generated method stub
		TLike like = lDao.findOne(likeId);
		if(like == null) {
			throw new ParameterCheckException("未找到点赞主表ID！");
		}
		
		List<TLikeOption> ops = oDao.findAll();
		List<LikeRecordCount> cList = new ArrayList<LikeRecordCount>();
		for(TLikeOption op : ops) {
			LikeRecordCount c = new LikeRecordCount();
			c.setLikeId(likeId);
			c.setOptionId(op.getId());
			c.setOptionTitle(op.getTitle());
			c.setCount(rDao.countByOptionId(likeId,op.getId()));
			cList.add(c);
		}
		return cList;
		
	}

	@Override
	public List<TLikeOption> findAll() {
		// TODO Auto-generated method stub
		List<TLikeOption> olist = oDao.findAll();
		return olist;
	}

	@Transactional
	@Override
	public void addLikeOption(TLikeOption opt, InputStream pic, String uploadFolder) throws IOException {
		String path = UploadUtils.saveUploadFile(pic, uploadFolder);
		
		opt.setPicture(path);
		oDao.save(opt);
		
	}

	@Override
	public TLikeOption getOptionById(long optionId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}