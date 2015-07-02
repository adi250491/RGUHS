<!DOCTYPE html>
<%@page import="com.dexpert.feecollection.main.users.LoginBean"%>
<html lang="en">
<head>
<%
	//checking session
	LoginBean loginUser = new LoginBean();
	loginUser = (LoginBean) session.getAttribute("loginUserBean");

	if (loginUser == null) {
		response.sendRedirect("Login.jsp");

		return;

	}
	String usercookie = null;
	String sessionID = null;
	String dispchar = "display:none";
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {

	if (cookie.getName().equals("user"))
		usercookie = cookie.getValue();
	if (cookie.getName().equals("JSESSIONID"))
		sessionID = cookie.getValue();
		}
	} else {
		sessionID = session.getId();
	}
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta charset="utf-8">

<title>Fee Collection Portal - Fee Form</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">

<!-- The styles -->
<link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">

<link href="css/charisma-app.css" rel="stylesheet">
<link href='bower_components/fullcalendar/dist/fullcalendar.css'
	rel='stylesheet'>
<link href='bower_components/fullcalendar/dist/fullcalendar.print.css'
	rel='stylesheet' media='print'>
<link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>
<link href='bower_components/colorbox/example3/colorbox.css'
	rel='stylesheet'>
<link href='bower_components/responsive-tables/responsive-tables.css'
	rel='stylesheet'>
<link
	href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>
<link href='css/jquery.noty.css' rel='stylesheet'>
<link href='css/noty_theme_default.css' rel='stylesheet'>
<link href='css/elfinder.min.css' rel='stylesheet'>
<link href='css/elfinder.theme.css' rel='stylesheet'>
<link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
<link href='css/uploadify.css' rel='stylesheet'>
<link href='css/animate.min.css' rel='stylesheet'>
<link href="bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- jQuery -->
<script src="bower_components/jquery/jquery.min.js"></script>

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon" href="img/favicon.ico">

</head>

<body>
	<!-- topbar starts -->

	<!-- topbar ends -->
	<div class="ch-container">
		<div class="row">


			<noscript>
				<div class="alert alert-block col-md-12">
					<h4 class="alert-heading">Warning!</h4>

					<p>
						You need to have <a href="http://en.wikipedia.org/wiki/JavaScript"
							target="_blank">JavaScript</a> enabled to use this site.
					</p>
				</div>
			</noscript>

			<div id="content" class="col-lg-12 col-sm-12">
				<!-- content starts -->
				<div></div>


				<div class="row">
					<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well">
								<h2>
									<i class="glyphicon glyphicon-info-sign"></i> Set Fee Values
									for Admission Fee
								</h2>

								<div class="box-icon">

									<a href="#" class="btn btn-minimize btn-round btn-default"><i
										class="glyphicon"></i></a>

								</div>
							</div>
							<div class="box-content row">
								<div class="col-lg-12 col-md-12 animated fadeIn">


									<table class="table table-condensed">

										<thead>
											<tr>
												<th></th>
												<s:iterator value="HeaderList">

													<th><s:property /></th>

												</s:iterator>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="BodyList">
												<tr>
													<s:iterator status="incr">
														<td><s:if test="%{#incr.index==0}">
																<input value='<s:property />' name="id" hidden="hidden">
															</s:if> <s:else>
																<s:property />
															</s:else></td>


													</s:iterator>
													<td><input type="text" placeholder="Enter Fee Value">
														<button>Remove</button></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>


									<!-- <table class="table table-condensed">
										<thead>

											<tr>
												<th>Case</th>
												<th>Faculty</th>
												<th>Course Type</th>
												<th>Course Duration</th>
												<th>Student Nationality</th>
												<th>Student Category</th>
												<th>Amount/College</th>
												<th>Amount/Student</th>
												<th>Actions</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>Engineering</td>
												<td>Professional</td>
												<td>4</td>
												<td>Indian</td>
												<td>Open</td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><a class="btn btn-danger btn-sm"
													 href="#"> <i
														class="glyphicon glyphicon-zoom-in icon-white"></i> Remove Case
												</a></td>

											</tr>
											<tr>
												<td>2</td>
												<td>Engineering</td>
												<td>Professional</td>
												<td>4</td>
												<td>Indian</td>
												<td>SC</td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><a class="btn btn-danger btn-sm"
													 href="#"> <i
														class="glyphicon glyphicon-zoom-in icon-white"></i> Remove Case
												</a></td>

											</tr>
											<tr>
												<td>2</td>
												<td>Engineering</td>
												<td>Professional</td>
												<td>4</td>
												<td>Indian</td>
												<td>ST</td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><a class="btn btn-danger btn-sm"
													 href="#"> <i
														class="glyphicon glyphicon-zoom-in icon-white"></i> Remove Case
												</a></td>

											</tr>
											<tr>
												<td>3</td>
												<td>Engineering</td>
												<td>Professional</td>
												<td>4</td>
												<td>Indian</td>
												<td>NT</td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><a class="btn btn-danger btn-sm"
													 href="#"> <i
														class="glyphicon glyphicon-zoom-in icon-white"></i> Remove Case
												</a></td>

											</tr>
											<tr>
												<td>4</td>
												<td>Engineering</td>
												<td>Professional</td>
												<td>4</td>
												<td>Indian</td>
												<td>Open</td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><a class="btn btn-danger btn-sm"
													 href="#"> <i
														class="glyphicon glyphicon-zoom-in icon-white"></i> Remove Case
												</a></td>

											</tr>
											<tr>
												<td>5</td>
												<td>Engineering</td>
												<td>Professional</td>
												<td>4</td>
												<td>NRI</td>
												<td>NA</td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><a class="btn btn-danger btn-sm"
													 href="#"> <i
														class="glyphicon glyphicon-zoom-in icon-white"></i> Remove Case
												</a></td>

											</tr>
											<tr>
												<td>6</td>
												<td>Medical</td>
												<td>Non Professional</td>
												<td>4</td>
												<td>Indian</td>
												<td>Open</td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><a class="btn btn-danger btn-sm"
													 href="#"> <i
														class="glyphicon glyphicon-zoom-in icon-white"></i> Remove Case
												</a></td>

											</tr>
											<tr>
												<td>7</td>
												<td>Medical</td>
												<td>Professional</td>
												<td>4</td>
												<td>Indian</td>
												<td>Open</td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><a class="btn btn-danger btn-sm"
													 href="#"> <i
														class="glyphicon glyphicon-zoom-in icon-white"></i> Remove Case
												</a></td>

											</tr>
											<tr>
												<td>8</td>
												<td>Medical</td>
												<td>Professional</td>
												<td>4</td>
												<td>Indian</td>
												<td>SC</td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><a class="btn btn-danger btn-sm"
													 href="#"> <i
														class="glyphicon glyphicon-zoom-in icon-white"></i> Remove Case
												</a></td>

											</tr>
											<tr>
												<td>9</td>
												<td>Medical</td>
												<td>Professional</td>
												<td>4</td>
												<td>Indian</td>
												<td>ST</td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><input type="number" placeholder="Enter Amount"></td>
												<td><a class="btn btn-danger btn-sm"
													 href="#"> <i
														class="glyphicon glyphicon-zoom-in icon-white"></i> Remove Case
												</a></td>

											</tr>


										</tbody>
									</table>
 -->
								</div>


							</div>
						</div>
					</div>
					<div class="col-md-12">
						<button class="btn btn-success">Save Fee Details</button>

						<button onclick="window.close()" class="btn btn-info">Close
						</button>

					</div>

				</div>

				<!--/row-->

				<!--/row-->
				<!-- content ends -->
			</div>
			<!--/#content.col-md-0-->
		</div>
		<!--/fluid-row-->



		<hr>

		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">

			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>Settings</h3>
					</div>
					<div class="modal-body">
						<p>Here settings can be configured...</p>
					</div>
					<div class="modal-footer">
						<a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
						<a href="#" class="btn btn-primary" data-dismiss="modal">Save
							changes</a>
					</div>
				</div>
			</div>
		</div>

		<!-- <footer class="row">
			<p class="col-md-9 col-sm-9 col-xs-12 copyright">
				&copy; <a href="http://dexpertsystems.com" target="_blank">Dexpert
					Systems Pvt. Ltd</a>
			</p>

			<p class="col-md-3 col-sm-3 col-xs-12 powered-by">
				Powered by: <a href="http://dexpertsystems.com">Dexpert</a>
			</p>
		</footer> -->

	</div>
	<!--/.fluid-container-->

	<!-- external javascript -->

	<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- library for cookie management -->
	<script src="js/jquery.cookie.js"></script>
	<!-- calender plugin -->
	<script src='bower_components/moment/min/moment.min.js'></script>
	<script src='bower_components/fullcalendar/dist/fullcalendar.min.js'></script>

	<!-- data table plugin -->
	<script src='js/jquery.dataTables.min.js'></script>

	<!-- select or dropdown enhancer -->
	<script src="bower_components/chosen/chosen.jquery.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="bower_components/colorbox/jquery.colorbox-min.js"></script>
	<!-- notification plugin -->
	<script src="js/jquery.noty.js"></script>
	<!-- library for making tables responsive -->
	<script src="bower_components/responsive-tables/responsive-tables.js"></script>
	<!-- tour plugin -->
	<script
		src="bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
	<!-- star rating plugin -->
	<script src="js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="js/charisma.js"></script>

	<script>
		function OpenSummaryInParent() {
			window.opener.location = "College-Payment-Summary.html";
			window.close();

		}
	</script>
</body>
</html>
