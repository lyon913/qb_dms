package com.whr.dms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TVoteDao;
import com.whr.dms.dao.TVoteOptionDao;
import com.whr.dms.dao.TVoteRecordDao;
import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TVote;
import com.whr.dms.models.TVoteOption;
import com.whr.dms.models.TVoteRecord;
import com.whr.dms.models.VoteRecordCount;
import com.whr.dms.models.VoteResult;

@Service
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	private TVoteDao vDao;
	
	@Autowired
	private TVoteOptionDao oDao;
	
	@Autowired
	private TVoteRecordDao rDao;

	@Transactional
	@Override
	public void addVote(TVote vote, long suggId) {
		vDao.save(vote);
		vDao.updateVoteIdOnTSuggestion(suggId, vote.getId());
	}

	@Transactional
	@Override
	public void removeVote(long voteId) {
		vDao.delete(voteId);
		vDao.deleteVoteIdOnTSuggestion(voteId);;
	}

//	@Override
//	public void saveVoteOption(long voteId, TVoteOption option) {
//		TVote v = vDao.findOne(voteId);
//		option.setVote(v);
//		
//		oDao.save(option);
//	}
//
//	@Override
//	public void removeVoteOption(long optionId) {
//		oDao.delete(optionId);
//	}

	@Transactional
	@Override
	public void vote(long voteId, long[] optionId, long userId) throws ParameterCheckException {
		TVote v = vDao.findOne(voteId);
		if(v == null) {
			throw new ParameterCheckException("未找到投票信息");
		}
		//选项为空
		if(optionId == null || optionId.length == 0) {
			throw new ParameterCheckException("投票选项为空");
		}
		//多选时判断选项是否超出最大值
		if(v.getIsMulti() && optionId.length > v.getMaxVotes()) {
			throw new ParameterCheckException("选择的投票项目超出最大值");
		}
		
		//单选时判断选项是否大于1
		if(!v.getIsMulti() && optionId.length >1) {
			throw new ParameterCheckException("选择的投票项目超出最大值");
		}
		
		//判断用户是否已经投票
		if(rDao.countByUserId(voteId,userId)>0) {
			throw new ParameterCheckException("你已经过投票，请勿重复投票");
		}
		Calendar end = Calendar.getInstance();
		end.setTime(v.getEndDate());
		end.set(Calendar.HOUR, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		if(new Date().after(end.getTime())) {
			throw new ParameterCheckException("投票已经截止，不能计入结果");
		}
		for(long oId : optionId) {
			TVoteRecord r = new TVoteRecord();
			r.setVoteId(voteId);
			r.setOptionId(oId);
			r.setUserId(userId);
			rDao.save(r);
		}
		
	}

	@Override
	public TVote findById(long voteId) {
		return vDao.findOne(voteId);
	}


	@Transactional(readOnly = true)
	@Override
	public VoteResult getVoteResult(long voteId) throws ParameterCheckException {
		TVote vote = vDao.findOne(voteId);
		if(vote == null) {
			throw new ParameterCheckException("未找到投票ID");
		}
		VoteResult result = new VoteResult();
		result.setVoteId(voteId);
		result.setTitle(vote.getTitle());
		result.setTotal(rDao.countByVoteId(voteId));
		
		List<TVoteOption> ops = vote.getOptions();
		List<VoteRecordCount> cList = new ArrayList<VoteRecordCount>();
		for(TVoteOption op : ops) {
			VoteRecordCount c = new VoteRecordCount();
			c.setOptionId(op.getId());
			c.setOptionTitle(op.getTitle());
			c.setCount(rDao.countByOptionId(op.getId()));
			if(result.getTotal() == 0) {
				c.setPercent(0);
			}else {
				double p = (double)c.getCount()/result.getTotal();
				c.setPercent(p*100);
			}
			
			cList.add(c);
		}
		result.setCounts(cList);
		return result;
	}

}
