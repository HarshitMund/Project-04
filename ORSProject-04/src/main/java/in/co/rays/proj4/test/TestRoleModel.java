package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.protobuf.TextFormat.ParseException;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.RoleModel;

public class TestRoleModel {

	public static void main(String[] args) throws ParseException, ApplicationException, DuplicateRecordException {

		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByName();
//		testSearch();
//		testList();

	}

	public static void testAdd() throws ParseException, DuplicateRecordException {

		try {
			RoleBean bean = new RoleBean();
			RoleModel model = new RoleModel();

			bean.setName("FACULTY");
			bean.setDescription("FACULTY");
			bean.setCreatedBy("Root");
			bean.setModifiedBy("Root");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			long id = model.add(bean);
			System.out.println("Record added at ID: " + id);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws ParseException, DuplicateRecordException {

		try {
			RoleBean bean = new RoleBean();
			RoleModel model = new RoleModel();

			bean.setId(4);
			bean.setName("Student");
			bean.setDescription("Student");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
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

			bean.setId(2);

			model.delete(bean);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPk() throws ParseException {

		try {
			RoleModel model = new RoleModel();

			RoleBean bean = model.findByPk(1);

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByName() throws ParseException {

		try {
			RoleModel model = new RoleModel();

			RoleBean bean = model.findByName("Student");

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testSearch() throws ParseException {

		try {
			List<RoleBean> list = new ArrayList<RoleBean>();
			RoleModel model = new RoleModel();
			RoleBean bean = new RoleBean();

			list = model.search(bean, 1, 5);

			Iterator<RoleBean> it = list.iterator();
			while (it.hasNext()) {
				bean = it.next();

				System.out.print(bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getDescription());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getCreatedDatetime());
				System.out.println("\t" + bean.getModifiedDatetime());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	
	public static void testList() throws ParseException {

		try {
			List<RoleBean> list = new ArrayList<RoleBean>();
			RoleModel model = new RoleModel();
			RoleBean bean = new RoleBean();

			list = model.list();

			Iterator<RoleBean> it = list.iterator();
			while (it.hasNext()) {
				bean = it.next();

				System.out.print(bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getDescription());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getCreatedDatetime());
				System.out.println("\t" + bean.getModifiedDatetime());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
