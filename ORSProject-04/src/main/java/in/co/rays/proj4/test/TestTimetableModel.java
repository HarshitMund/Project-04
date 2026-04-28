package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import in.co.rays.proj4.bean.TimetableBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.TimetableModel;

public class TestTimetableModel {

	public static TimetableModel model = new TimetableModel();

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testCheckByCourseName();
//		testCheckBySubjectName();
//		testCheckBySemester();
//		testCheckByExamTime();
		testSearch();
	}

	public static void testAdd() throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		TimetableBean bean = new TimetableBean();

		bean.setSemester("1st");
		bean.setDescription("Mid Term");
		bean.setExamDate(sdf.parse("2026-05-01"));
		bean.setExamTime("10:00 AM");

		bean.setCourseId(1);
		bean.setSubjectId(1);

		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
		bean.setModifiedDatetime(new Timestamp(System.currentTimeMillis()));

		long id = model.add(bean);
		System.out.println("Inserted ID: " + id);
	}

	public static void testUpdate() throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		TimetableBean bean = new TimetableBean();

		bean.setId(1);
		bean.setSemester("2nd");
		bean.setDescription("Final Exam");
		bean.setExamDate(sdf.parse("2026-06-01"));
		bean.setExamTime("2:00 PM");

		bean.setCourseId(1);
		bean.setSubjectId(1);

		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
		bean.setModifiedDatetime(new Timestamp(System.currentTimeMillis()));

		model.update(bean);

		System.out.println("Record updated");
	}

	public static void testDelete() throws ApplicationException {

		TimetableBean bean = new TimetableBean();
		bean.setId(1); // existing ID

		model.delete(bean);

		System.out.println("Record deleted");
	}

	public static void printBean(TimetableBean bean) {

		if (bean == null) {
			System.out.println("No record found");
			return;
		}

		System.out.print(bean.getId());
		System.out.print("\t" + bean.getSemester());
		System.out.print("\t" + bean.getDescription());
		System.out.print("\t" + bean.getExamDate());
		System.out.print("\t" + bean.getExamTime());
		System.out.print("\t" + bean.getCourseId());
		System.out.print("\t" + bean.getCourseName());
		System.out.print("\t" + bean.getSubjectId());
		System.out.print("\t" + bean.getSubjectName());
		System.out.print("\t" + bean.getCreatedBy());
		System.out.print("\t" + bean.getModifiedBy());
		System.out.print("\t" + bean.getCreatedDatetime());
		System.out.println("\t" + bean.getModifiedDatetime());
	}

	public static void testFindByPk() throws ApplicationException {

		TimetableBean bean = model.findByPk(1);
		printBean(bean);
	}

	public static void testCheckByCourseName() throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		TimetableBean bean = model.checkByCourseName(1L, new java.sql.Date(sdf.parse("2026-05-01").getTime()));

		printBean(bean);
	}

	public static void testCheckBySubjectName() throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		TimetableBean bean = model.checkBySubjectName(1L, 1L, new java.sql.Date(sdf.parse("2026-05-01").getTime()));

		printBean(bean);
	}

	public static void testCheckBySemester() throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		TimetableBean bean = model.checkBySemester(1L, 1L, "1st", new java.sql.Date(sdf.parse("2026-05-01").getTime()));

		printBean(bean);
	}

	public static void testCheckByExamTime() throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		TimetableBean bean = model.checkByExamTime(1L, 1L, "1st", new java.sql.Date(sdf.parse("2026-05-01").getTime()),
				"10:00 AM", "Mid Term");

		printBean(bean);
	}

	public static void testSearch() throws ApplicationException {

		TimetableBean bean = new TimetableBean();

//		bean.setSemester("1st");
//		bean.setCourseName("BTech");

		List<TimetableBean> list = model.search(bean, 1, 10);

		for (TimetableBean b : list) {
			printBean(b);
		}
	}
}