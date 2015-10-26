package service.basicFunctions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.WechatCacheDao;
import database.models.NbWechatCache;

@Service("wechatCacheService")
public class WechatCacheServiceImp implements WechatCacheService{

	@Autowired
	private WechatCacheDao wechatCacheDao;
	
	public void updateWechatCache(NbWechatCache nbWechatCache) {
		wechatCacheDao.update(nbWechatCache);
	}

	public NbWechatCache getByValue(String cacheName, String appId) {
		return wechatCacheDao.getByValue(cacheName,appId);
	}

}
