package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.StudentModel;

public class TestStudentModel {

	public static StudentModel model = new StudentModel();

	public static void main(String[] args) throws ParseException, ApplicationException {

//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByEmail();
		testSearch();

	}

	public static void testAdd() throws ParseException, ApplicationException {

		StudentBean bean = new StudentBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("Siddhanta");
		bean.setLastName("Hota");
		bean.setDob(sdf.parse("2000-02-02"));
		bean.setGender("Male");
		bean.setMobileNo("9876543210");
		bean.setEmail("sidd@gmail.com");
		bean.setCollegeId(1);
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		long id = model.add(bean);
		System.out.println("Rocord add at ID: " + id);
	}

	public static void testUpdate() throws ParseException, ApplicationException {

		StudentBean bean = new StudentBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(1);
		bean.setFirstName("Khushi");
		bean.setLastName("Hota");
		bean.setDob(sdf.parse("2000-02-02"));
		bean.setGender("Male");
		bean.setMobileNo("9876543210");
		bean.setEmail("khushi@gmail.com");
		bean.setCollegeId(2);
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);
	}

	public static void testDelete() throws ParseException, ApplicationException {

		StudentBean bean = new StudentBean();

		bean.setId(2);

		model.delete(bean);
	}

	public static void testFindByPk() throws ApplicationException {

		StudentBean bean = model.findByPk(1);

		System.out.println(bean.getId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getDob());
		System.out.println(bean.getGender());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getEmail());
		System.out.println(bean.getCollegeId());
		System.out.println(bean.getCollegeName());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	public static void testFindByEmail() throws ApplicationException {

		StudentBean bean = model.findByEmail("sidd@gmail.com");

		System.out.println(bean.getId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getDob());
		System.out.println(bean.getGender());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getEmail());
		System.out.println(bean.getCollegeId());
		System.out.println(bean.getCollegeName());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	public static void testSearch() throws ApplicationException, ParseException {

		StudentBean bean = new StudentBean();
		List<StudentBean> list = new ArrayList<StudentBean>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setDob(sdf.parse("2000-02-02"));

		list = model.search(bean, 1, 5);
		Iterator<StudentBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}
}
