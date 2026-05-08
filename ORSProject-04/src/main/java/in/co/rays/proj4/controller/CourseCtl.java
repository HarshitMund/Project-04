package in.co.rays.proj4.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet("/CourseCtl")
public class CourseCtl extends BaseCtl {

	@Override
	protected String getView() {
		return ORSView.COURSE_CTL;
	}

}
