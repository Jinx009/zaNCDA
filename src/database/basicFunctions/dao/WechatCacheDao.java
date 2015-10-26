package database.basicFunctions.dao;

import database.common.BaseDao;
import database.models.NbWechatCache;

public interface WechatCacheDao extends BaseDao<NbWechatCache>{

	public NbWechatCache getByValue(String cacheName, String appId);

}
