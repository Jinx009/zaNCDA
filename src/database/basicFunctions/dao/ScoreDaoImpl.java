package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.Score;

@Repository("scoreDao")
public class ScoreDaoImpl extends BaseDaoImpl<Score> implements ScoreDao{

}
