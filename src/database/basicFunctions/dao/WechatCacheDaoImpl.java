package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.NbWechatCache;

@Repository("wechatCacheDao")
public class WechatCacheDaoImpl extends BaseDaoImpl<NbWechatCache> implements WechatCacheDao {

	@SuppressWarnings("unchecked")
	public NbWechatCache getByValue(String cacheName, String appId) {
		String hql = " from NbWechatCache where cacheName = '"+cacheName+"' and appId = '"+appId+"' ";
		Query query = em.createQuery(hql);
		List<NbWechatCache> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
