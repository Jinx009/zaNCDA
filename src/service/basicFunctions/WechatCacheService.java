package service.basicFunctions;

import database.models.WechatCache;

public interface WechatCacheService {

	public void updateWechatCache(WechatCache nbWechatCache);
	
	public WechatCache getByValue(String cacheName,String appId);
	
}
