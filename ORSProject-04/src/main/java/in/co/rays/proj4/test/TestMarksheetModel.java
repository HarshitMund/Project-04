package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.MarksheetModel;

public class TestMarksheetModel {

	public static MarksheetModel model = new MarksheetModel();

	public static void main(String[] args) throws ApplicationException, ParseException, DuplicateRecordException {

		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByRollNo();
//		testSearch();
//		testGetMeritList();

	}

	public static void testAdd() throws ApplicationException, DuplicateRecordException {

		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo("102");
		bean.setStudentId(3);
		bean.setPhysics(54);
		bean.setChemistry(65);
		bean.setMaths(43);
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		long id = model.add(bean);
		System.out.println("Rocord add at ID: " + id);
	}

	public static void testUpdate() throws ParseException, ApplicationException, DuplicateRecordException {

		MarksheetBean bean = new MarksheetBean();

		bean.setId(2);
		bean.setRollNo("102");
		bean.setStudentId(3);
		bean.setPhysics(22);
		bean.setChemistry(34);
		bean.setMaths(33);
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);
	}

	public static void testDelete() throws ParseException, ApplicationException {

		MarksheetBean bean = new MarksheetBean();

		bean.setId(3);

		model.delete(bean);
	}

	public static void testFindByPk() throws ApplicationException {

		MarksheetBean bean = model.findByPk(1);

		System.out.println(bean.getId());
		System.out.println(bean.getRollNo());
		System.out.println(bean.getStudentId());
		System.out.println(bean.getName());
		System.out.println(bean.getPhysics());
		System.out.println(bean.getChemistry());
		System.out.println(bean.getMaths());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	public static void testFindByRollNo() throws ApplicationException {

		MarksheetBean bean = model.findByRollNo("102");

		System.out.println(bean.getId());
		System.out.println(bean.getRollNo());
		System.out.println(bean.getStudentId());
		System.out.println(bean.getName());
		System.out.println(bean.getPhysics());
		System.out.println(bean.getChemistry());
		System.out.println(bean.getMaths());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	public static void testSearch() throws ApplicationException, ParseException {

		MarksheetBean bean = new MarksheetBean();
		List<MarksheetBean> list = new ArrayList<MarksheetBean>();

		list = model.search(bean, 1, 5);
		Iterator<MarksheetBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getRollNo());
			System.out.print("\t" + bean.getStudentId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getPhysics());
			System.out.print("\t" + bean.getChemistry());
			System.out.print("\t" + bean.getMaths());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}

	}

	public static void testGetMeritList() throws ApplicationException, ParseException {

		MarksheetBean bean = new MarksheetBean();
		List<MarksheetBean> list = new ArrayList<MarksheetBean>();

		list = model.getMeritList(1, 5);
		Iterator<MarksheetBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getRollNo());
			System.out.print("\t" + bean.getStudentId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getPhysics());
			System.out.print("\t" + bean.getChemistry());
			System.out.print("\t" + bean.getMaths());
		}

	}

}
