package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CourseModel;

public class TestCourseModel {

	public static CourseModel model = new CourseModel();

	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {

//		testAdd();
		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByName();
//		testSearch();

	}

	public static void testAdd() throws ApplicationException, DuplicateRecordException {

		CourseBean bean = new CourseBean();

		bean.setName("BTech");
		bean.setDescription("Coumpter");
		bean.setDuration("4 Year");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		long id = model.add(bean);
		System.out.println("Record inserted at ID: " + id);
	}

	public static void testUpdate() throws ApplicationException, DuplicateRecordException {

		CourseBean bean = new CourseBean();

		bean.setId(3);
		bean.setName("BTech");
		bean.setDescription("Mechanical");
		bean.setDuration("4 Year");
		bean.setCreatedBy("System");
		bean.setModifiedBy("System");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);
	}

	public static void testDelete() throws ApplicationException {

		CourseBean bean = new CourseBean();

		bean.setId(3);

		model.delete(bean);
	}

	public static void testFindByPk() throws ApplicationException {

		CourseBean bean = model.findByPk(1);

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDuration());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
	}

	public static void testFindByName() throws ApplicationException {

		CourseBean bean = model.findByName("BTech");

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDuration());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
	}

	public static void testSearch() throws ApplicationException {

		CourseBean bean = new CourseBean();
		List<CourseBean> list = new ArrayList<CourseBean>();

		list = model.search(bean, 1, 10);
		Iterator<CourseBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}
}
