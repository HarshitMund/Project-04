package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.SubjectModel;

public class TestSubjectModel {

	public static SubjectModel model = new SubjectModel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void testAdd() throws ApplicationException, DuplicateRecordException {
		
		SubjectBean bean = new SubjectBean();
		
		bean.setName("css");
		bean.setDescription("programingL");
		bean.setCourseId(3);
		bean.setCourseName("ccssn");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		long pk = model.add(bean);
	}

}
