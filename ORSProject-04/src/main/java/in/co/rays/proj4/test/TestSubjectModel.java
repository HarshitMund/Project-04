package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.SubjectModel;

public class TestSubjectModel {

	public static SubjectModel model = new SubjectModel();

	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {

//		testAdd();
		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByName();
//		testSearch();

	}

	public static void testAdd() throws ApplicationException, DuplicateRecordException {

		SubjectBean bean = new SubjectBean();

		bean.setName("css");
		bean.setDescription("programingL");
		bean.setCourseId(2);
		bean.setCourseName("ccssn");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		long pk = model.add(bean);
		System.out.println("Record added at ID: " + pk);
	}

	public static void testUpdate() throws ApplicationException, DuplicateRecordException {

		SubjectBean bean = new SubjectBean();

		bean.setId(1);
		bean.setName("JAVA");
		bean.setDescription("programing language");
		bean.setCourseId(2);
		bean.setCourseName("java coperate");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	public static void testDelete() throws ApplicationException {

		SubjectBean bean = new SubjectBean();

		bean.setId(1);

		model.delete(bean);

	}

	public static void testFindByPk() throws ApplicationException {

		SubjectBean bean = model.findByPk(1);

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getCourseId());
		System.out.println(bean.getCourseName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	public static void testFindByName() throws ApplicationException {

		SubjectBean bean = model.findByName("css");

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getCourseId());
		System.out.println(bean.getCourseName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	public static void testSearch() throws ApplicationException {

		List<SubjectBean> list = new ArrayList<SubjectBean>();
		SubjectBean bean = new SubjectBean();

		bean.setName("JAVA");

		list = model.search(bean, 1, 5);
		Iterator<SubjectBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("---------------------------");
		}
	}

	public static void testList() throws ApplicationException {

		List<SubjectBean> list = new ArrayList<SubjectBean>();
		SubjectBean bean = new SubjectBean();

		list = model.list();
		Iterator<SubjectBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("---------------------------");
		}
	}

}
