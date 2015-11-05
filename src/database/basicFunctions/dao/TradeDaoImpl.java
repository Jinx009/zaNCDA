package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.Trade;

@Repository("tradeDao")
public class TradeDaoImpl extends BaseDaoImpl<Trade> implements TradeDao{

}
