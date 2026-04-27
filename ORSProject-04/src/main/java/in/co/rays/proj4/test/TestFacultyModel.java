package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.FacultyModel;

public class TestFacultyModel {

	public static FacultyModel model = new FacultyModel();

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByEmail();
		testSearch();
	}

	public static void testAdd() throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		FacultyBean bean = new FacultyBean();

		bean.setFirstname("Harshit");
		bean.setLastName("Mund");
		bean.setDob(sdf.parse("2000-02-02"));
		bean.setGender("Male");
		bean.setMobileNo("9999999999");
		bean.setEmail("harshit123@example.com");
		bean.setCollegeId(1);
		bean.setCourseId(1);
		bean.setSubjectId(1);
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
		bean.setModifiedDatetime(new Timestamp(System.currentTimeMillis()));

		long id = model.add(bean);
		System.out.println("Record inserted at ID: " + id);
	}

	public static void testUpdate() throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		FacultyBean bean = new FacultyBean();

		bean.setId(1);
		bean.setFirstname("Updated");
		bean.setLastName("Faculty");
		bean.setDob(sdf.parse("1995-05-05"));
		bean.setGender("Male");
		bean.setMobileNo("7777777777");
		bean.setEmail("updated@example.com");

		bean.setCollegeId(1);
		bean.setCourseId(1);
		bean.setSubjectId(1);

		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
		bean.setModifiedDatetime(new Timestamp(System.currentTimeMillis()));

		model.update(bean);

		System.out.println("Record updated successfully");
	}

	public static void testDelete() throws ApplicationException {

		FacultyBean bean = new FacultyBean();
		bean.setId(1);

		model.delete(bean);

		System.out.println("Record deleted successfully");
	}

	public static void testFindByPk() throws ApplicationException {

		FacultyBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getFirstname());
			System.out.println(bean.getLastName());
			System.out.println(bean.getEmail());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		} else {
			System.out.println("Record not found");
		}
	}

	public static void testFindByEmail() throws ApplicationException {

		FacultyBean bean = model.findByEmail("harshit123@example.com");

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getFirstname());
			System.out.println(bean.getLastName());
			System.out.println(bean.getEmail());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		} else {
			System.out.println("Record not found");
		}
	}

	public static void testSearch() throws ApplicationException {

		FacultyBean bean = new FacultyBean();
		List<FacultyBean> list = new ArrayList<FacultyBean>();

//		bean.setFirstname("Har");
		bean.setEmail("harshit");

		list = model.search(bean, 1, 10);

		Iterator<FacultyBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getFirstname());
			System.out.println(bean.getLastName());
			System.out.println(bean.getEmail());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("-----------------------------");
		}
	}
}