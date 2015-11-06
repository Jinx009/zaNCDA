package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.AreaDao;
import database.models.Area;

@Service("areaService")
public class AreaServiceImpl  implements AreaService{

	private StringBuffer buffer;
	
	@Autowired
	private AreaDao araAreaDao;
	
	public List<Area> getByParentId(Integer id) {
		buffer = new StringBuffer();
		
		buffer.append(" FROM Area  ");
		buffer.append(" WHERE ");
		buffer.append(" parentId = '");
		buffer.append(id);
		buffer.append("' ");
		
		return araAreaDao.getByHql(buffer.toString());
	}

	
	
	
	public StringBuffer getBuffer() {
		return buffer;
	}
	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}
}
