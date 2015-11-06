package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.WechatCache;

@Repository("wechatCacheDao")
public class WechatCacheDaoImpl extends BaseDaoImpl<WechatCache> implements WechatCacheDao {
	private StringBuffer buffer;
	
	@SuppressWarnings("unchecked")
	public WechatCache getByValue(String cacheName, String appId) {
		buffer = new StringBuffer();
		
		buffer.append(" FROM WechatCache  ");
		buffer.append(" WHERE ");
		buffer.append(" cacheName = '");
		buffer.append(cacheName);
		buffer.append("' ");
		buffer.append(" AND ");
		buffer.append(" appId ='");
		buffer.append(appId);
		buffer.append("'  ");
		
		Query query = em.createQuery(buffer.toString());
		List<WechatCache> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	
	public StringBuffer getBuffer() {
		return buffer;
	}
	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}
}
