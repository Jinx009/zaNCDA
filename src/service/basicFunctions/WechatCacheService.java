package service.basicFunctions;

import database.models.NbWechatCache;

public interface WechatCacheService {

	public void updateWechatCache(NbWechatCache nbWechatCache);
	
	public NbWechatCache getByValue(String cacheName,String appId);
	
}
