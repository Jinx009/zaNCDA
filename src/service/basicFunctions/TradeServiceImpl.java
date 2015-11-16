package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.TradeDao;
import database.models.Trade;

@Service("tradeService")
public class TradeServiceImpl  implements TradeService{

	@Autowired
	private TradeDao tradeDao;

	public List<Trade> findList() {
		return tradeDao.findAll();
	}

	public Trade find(Integer tradeId) {
		return tradeDao.find(tradeId);
	}
	
	
	
}
