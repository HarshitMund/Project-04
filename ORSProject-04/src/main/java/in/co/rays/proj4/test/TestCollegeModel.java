package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.CollegeModel;

public class TestCollegeModel {

	public static CollegeModel model = new CollegeModel();

	public static void main(String[] args) throws ParseException {

//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByName();
//		testSearch();
		testList();

	}

	public static void testAdd() throws ParseException {

		try {
			CollegeBean bean = new CollegeBean();

			bean.setName("C.V.Raman");
			bean.setAddress("Tomanda");
			bean.setCity("Bhubaneswar");
			bean.setState("Odisha");
			bean.setPhoneNo("9876543210");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatatime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			long id = model.add(bean);
			System.out.println("Rocord add at ID: " + id);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws ParseException {

		try {
			CollegeBean bean = new CollegeBean();

			bean.setId(1);
			bean.setName("KITT");
			bean.setAddress("Patia");
			bean.setCity("Bhubaneswar");
			bean.setState("Odisha");
			bean.setPhoneNo("9123456789");
			bean.setCreatedBy("System");
			bean.setModifiedBy("System");
			bean.setCreatedDatatime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			model.update(bean);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() throws ParseException {

		try {
			CollegeBean bean = new CollegeBean();

			bean.setId(2);

			model.delete(bean);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPk() throws ParseException {

		try {
			CollegeBean bean = model.findByPk(1);

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getCity());
			System.out.println(bean.getState());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatatime());
			System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByName() throws ParseException {

		try {
			CollegeBean bean = model.findByName("C.V.Raman");

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getCity());
			System.out.println(bean.getState());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatatime());
			System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testSearch() throws ParseException {

		try {
			CollegeBean bean = new CollegeBean();
			List<CollegeBean> list = new ArrayList<CollegeBean>();

//			bean.setId(1);
//			bean.setName("KITT");
			bean.setCity("Bhubaneswar");
			
			list = model.search(bean, 1, 5);
			Iterator<CollegeBean> it = list.iterator();

			while (it.hasNext()) {
				bean = it.next();

				System.out.print(bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getAddress());
				System.out.print("\t" + bean.getCity());
				System.out.print("\t" + bean.getState());
				System.out.print("\t" + bean.getPhoneNo());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getCreatedDatatime());
				System.out.println("\t" + bean.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	
	public static void testList() throws ParseException {

		try {
			CollegeBean bean = null;
			List<CollegeBean> list = new ArrayList<CollegeBean>();

			list = model.list();
			Iterator<CollegeBean> it = list.iterator();

			while (it.hasNext()) {
				bean = it.next();

				System.out.print(bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getAddress());
				System.out.print("\t" + bean.getCity());
				System.out.print("\t" + bean.getState());
				System.out.print("\t" + bean.getPhoneNo());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getCreatedDatatime());
				System.out.println("\t" + bean.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
