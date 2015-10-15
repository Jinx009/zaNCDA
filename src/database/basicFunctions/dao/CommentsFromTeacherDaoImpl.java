package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.NbCommentsFromTeacher;


@Repository("commentsFromTeacherDao")
public class CommentsFromTeacherDaoImpl extends BaseDaoImpl<NbCommentsFromTeacher> implements CommentsFromTeacherDao {

}