package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import com.google.protobuf.TextFormat.ParseException;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.RoleModel;

public class TestRoleModel {

	public static void main(String[] args) throws ParseException {
		
//		testAdd();
//		testUpdate();
		testDelete();

	}
	
	public static void testAdd() throws ParseException {
		
		try {
			RoleBean bean = new RoleBean();
			RoleModel model = new RoleModel();
			
			bean.setName("Hardik");
			bean.setDescription("Student");
			bean.setCreatedBy("System");
			bean.setModifiedBy("System");
			bean.setCreatedDatatime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			
			long id = model.add(bean);
			System.out.println("Record added at ID: " + id);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	
	public static void testUpdate() throws ParseException {
		
		try {
			RoleBean bean = new RoleBean();
			RoleModel model = new RoleModel();
			
			bean.setId(1);
			bean.setName("Siddhanta");
			bean.setDescription("Faculty");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatatime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			
			model.update(bean);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	
	public static void testDelete() throws ParseException {
		
		try {
			RoleBean bean = new RoleBean();
			RoleModel model = new RoleModel();
			
			bean.setId(1);
			
			model.delete(bean);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
