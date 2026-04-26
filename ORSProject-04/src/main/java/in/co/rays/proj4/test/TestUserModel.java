package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.UserModel;

public class TestUserModel {

	public static UserModel model = new UserModel();

	public static void main(String[] args) throws ParseException, DuplicateRecordException {

		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByName();
//		testAuthenticate();
//		testSearch();

	}

	public static void testAdd() throws ParseException, DuplicateRecordException {

		try {
			UserBean bean = new UserBean();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			bean.setFirstName("Harshit");
			bean.setLastName("Mund");
			bean.setLogin("harshit@example.com");
			bean.setPassword("12345");
			bean.setDob(sdf.parse("2000-02-02"));
			bean.setMobileNo("9876543210");
			bean.setRoleId(1);
			bean.setGender("Male");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
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
			UserBean bean = new UserBean();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			bean.setId(1);
			bean.setFirstName("Hardik");
			bean.setLastName("Mund");
			bean.setLogin("hardikt@example.com");
			bean.setPassword("12345");
			bean.setDob(sdf.parse("1999-02-02"));
			bean.setMobileNo("9123456789");
			bean.setRoleId(1);
			bean.setGender("Male");
			bean.setCreatedBy("System");
			bean.setModifiedBy("System");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			model.update(bean);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() {

		try {
			UserBean bean = new UserBean();

			bean.setId(1);

			model.delete(bean);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPk() {

		try {
			UserBean bean = model.findByPk(1);

			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByName() {

		try {
			UserBean bean = model.findByLogin("harshit@example.com");

			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testAuthenticate() {

		try {
			UserBean bean = model.authenticate("harshit@example.com", "12345");

			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
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
			UserBean bean = new UserBean();
			List<UserBean> list = new ArrayList<UserBean>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			bean.setDob(sdf.parse("2000-02-02"));

			list = model.search(bean, 1, 5);
			Iterator<UserBean> it = list.iterator();
			while (it.hasNext()) {
				bean = it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				System.out.println(bean.getLogin());
				System.out.println(bean.getPassword());
				System.out.println(bean.getDob());
				System.out.println(bean.getMobileNo());
				System.out.println(bean.getRoleId());
				System.out.println(bean.getGender());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
