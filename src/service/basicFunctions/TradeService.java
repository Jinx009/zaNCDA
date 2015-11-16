package service.basicFunctions;

import java.util.List;

import database.models.Trade;

public interface TradeService {

	public List<Trade> findList();

	public Trade find(Integer tradeId);
	
}
