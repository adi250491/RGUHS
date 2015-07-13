<!DOCTYPE html>
<%@page import="com.dexpert.feecollection.main.users.LoginBean"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
<head>
<%-- <%
	//checking session
	LoginBean loginUser = new LoginBean();
	loginUser = (LoginBean) session.getAttribute("loginUserBean"); String profile=(String)session.getAttribute("sesProfile");

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

	if (cookie.getName().equals("loginUser"))
		usercookie = cookie.getValue();
	if (cookie.getName().equals("JSESSIONID"))
		sessionID = cookie.getValue();
		}
	} else {
		sessionID = session.getId();
	}
%> --%>
<meta charset="utf-8">
<title>Fee Collection Portal - Student Detail for Quick Pay</title>
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

			<div id="content" class="col-lg-10 col-sm-10">
				<!-- content starts -->
				<div></div>



				<div class="row">
					<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well">
								<h2>
									<i class="glyphicon glyphicon-info-sign"></i> Applicant Detail
								</h2>

								<div class="box-icon">

									<a href="#" class="btn btn-minimize btn-round btn-default"><i
										class="glyphicon glyphicon-chevron-down"></i></a>

								</div>
							</div>
							<div class="box-content row">
								<div class="col-lg-12 col-md-12 animated fadeIn">

									<table class="table table-condensed">
										<thead>


										</thead>
										<tbody>
											<tr>

												<td><strong>Enrollment NUmber</strong></td>
												<td><div id="the-basics" class="has-success">
														<s:property value="appBean1.enrollmentNumber" />

													</div></td>

											</tr>

										</tbody>
									</table>


								</div>


							</div>
						</div>
					</div>


				</div>
				<form action="submitingParameter" method="post">
					<div class="row">
						<div class="box col-md-12">
							<div class="box-inner">
								<div class="box-header well">
									<h2>
										<i class="glyphicon glyphicon-info-sign"></i>Applicant Fees
										Collection
									</h2>

									<div class="box-icon">

										<a href="#" class="btn btn-minimize btn-round btn-default"><i
											class="glyphicon glyphicon-chevron-down"></i></a>

									</div>
								</div>
								<div class="box-content row">
									<div class="col-lg-12 col-md-12 animated fadeIn">

										<table class="table table-condensed">
											<thead>


											</thead>
											<tbody>
												<tr>

													<td><strong>Type of Service</strong></td>
													<td>

														<div class="box-content">
															<div class="control-group">
																<div class="controls">


																	<select required="required" data-rel="chosen"
																		name="feeCollectionBean.service_type"
																		style="width: 400px;">
																		<option value="">--Select from Option--</option>
																		<option value="ELIGIBILITY_CERTIFICATE">ELIGIBILITY
																			CERTIFICATE</option>
																		<option value="ELIGIBILITY_ERTIFICATE_RENEWAL">ELIGIBILITY
																			CERTIFICATE-RENEWAL</option>
																		<option value="CHANGE OF NAME AND INITIAL EXPANSION">CHANGE
																			OF NAME & INITIAL EXPANSION</option>
																		<option value="MIGRATION CERTIFICATE">MIGRATION
																			CERTIFICATE</option>
																		<option value="MIGRATION TRANSFER">MIGRATION
																			TRANSFER - From 1st to 2nd year</option>
																		<option value="NOC for internship transfer">NOC
																			(for internship transfer)</option>
																		<option value="CONSOLIDATED MARKS CARD LAMINATED">CONSOLIDATED
																			MARKS CARD (LAMINATED)</option>
																		<option value="NAME CORRECTION IN MARKS CARD">NAME
																			CORRECTION IN MARKS CARD</option>
																		<option value="PDC">PDC</option>
																		<option value="Rank Certificate">RANK
																			CERTIFICATE</option>
																		<option value="Rank Certificate - Duplicate">RANK
																			CERTIFICATE-DUPLICATE</option>
																		<option value="Medal/Prize Certificate">MEDAL/PRIZE
																			CERTIFICATE</option>
																		<option value="Medal/Prize Certificate-Duplicate">MEDAL/PRIZE
																			CERTIFICATE-Duplicate</option>
																		<option value="DUPLICATE DEGREE CERTIFICATE">DUPLICATE
																			DEGREE CERTIFICATE</option>
																		<option value="PGET Superspeciality">PGET
																			Superspeciality</option>
																		<option value="PGET Fee">PGET Fee</option>
																		<option value="PGET Application">PGET
																			Application</option>
																		<option value="Verification Only">VERIFICATION
																			ONLY</option>
																		<option
																			value="Verification and certification of Copys">VERIFICATION
																			AND CERTIFICATION OF COPY'S</option>
																		<option
																			value="Certified copy of the Syllabus/Orinance">CERTIFIED
																			COPY OF THE SYLLABUS/ORINANCE</option>
																		<option value="Duplicate Marks Card">DUPLICATE
																			MARKS CARD</option>
																		<option value="Provisional Pass Certificate">PROVISIONAL
																			PASS CERTIFICATE</option>
																		<option value="Degree Certificate">DEGREE
																			CERTIFICATE</option>
																		<option value="Certificate Course">CERTIFICATE
																			COURSE</option>
																		<option value="Name Correction in Degree Certificate">NAME
																			CORRECTION IN DEGREE CERTIFICATE</option>
																		<option value="Change of Title of Thesis-PhD">CHANGE
																			OF TITLE OF THESIS-PHD</option>
																		<option value="Re-registration-Ph.D">RE-REGISTRATION-PH.D</option>
																		<option value="Extension of Registration">EXTENSION
																			OF REGISTRATION</option>
																		<option value="Pre Examination">PRE
																			EXAMINATION</option>
																		<option value="Registration Fee">REGISTRATION
																			FEE</option>
																		<option
																			value="Final Synopsis Submission and Final Ph.D. Examination">FINAL
																			SYNOPSIS SUBMISSION AND FINAL PH.D. EXAMINATION</option>
																		<option value="Dissertation Fee">DISSERTATION
																			FEE</option>
																		<option value="Dissertation Fee-Resubmission">DISSERTATION
																			FEE-RESUBMISSION</option>
																		<option
																			value="Credential Verification/Official Transcript/Attestation of the documents fee">CREDENTIAL
																			VERIFICATION / OFFICIAL TRANSCRIPT/ ATTESTATION OF
																			THE DOCUMENTS FEE</option>
																		<option
																			value="Medium of Instruction in English Certification">MEDIUM
																			OF INSTRUCTION IN ENGLISH CERTIFICATION</option>
																		<option
																			value="Malpractice lapses committee - Penalty fee-Rs. 2500">MALPRACTICE
																			LAPSES COMMITTEE - PENALTY FEE-RS. 2500</option>
																		<option
																			value="Malpractice lapses committee - Penalty fee-Rs. 5000">MALPRACTICE
																			LAPSES COMMITTEE - PENALTY FEE-RS. 5000</option>
																		<option
																			value="Malpractice lapses committee - Penalty fee-Rs. 7500">MALPRACTICE
																			LAPSES COMMITTEE - PENALTY FEE-RS. 7500</option>
																		<option
																			value="Malpractice lapses committee - Penalty fee-Rs. 10000">MALPRACTICE
																			LAPSES COMMITTEE - PENALTY FEE-RS. 10000</option>
																		<option
																			value="Malpractice lapses committee - Penalty fee-Rs. 12500">MALPRACTICE
																			LAPSES COMMITTEE - PENALTY FEE-RS. 12500</option>
																		<option
																			value="Malpractice lapses committee - Penalty fee-Rs. 15000">MALPRACTICE
																			LAPSES COMMITTEE - PENALTY FEE-RS. 15000</option>
																		<option
																			value="Malpractice lapses committee - Penalty fee-Rs. 17500">MALPRACTICE
																			LAPSES COMMITTEE - PENALTY FEE-RS. 17500</option>
																		<option
																			value="Malpractice lapses committee - Penalty fee-Rs. 20000">MALPRACTICE
																			LAPSES COMMITTEE - PENALTY FEE-RS. 20000</option>
																		<option
																			value="Malpractice lapses committee - Penalty fee-Rs. 25000">MALPRACTICE
																			LAPSES COMMITTEE - PENALTY FEE-RS. 25000</option>
																		<option value="Re-totaling for 1 subject">RE-TOTALING
																			FOR 1 SUBJECT</option>
																		<option value="Re-totaling for 2 subject">RE-TOTALING
																			FOR 2 SUBJECT</option>
																		<option value="Re-totaling for 3 subject">RE-TOTALING
																			FOR 3 SUBJECT</option>
																		<option value="Re-totaling for 4 subject">RE-TOTALING
																			FOR 4 SUBJECT</option>
																		<option value="Re-totaling for 5 subject">RE-TOTALING
																			FOR 5 SUBJECT</option>
																		<option
																			value="Professional Misconduct Committee fine fees-Rs. 5000">PROFESSIONAL
																			MISCONDUCT COMMITTEE FINE FEES-RS. 5000</option>
																		<option
																			value="Professional Misconduct Committee fine fees-Rs. 10000">PROFESSIONAL
																			MISCONDUCT COMMITTEE FINE FEES-RS. 10000</option>
																		<option
																			value="Professional Misconduct Committee fine fees-Rs. 20000">PROFESSIONAL
																			MISCONDUCT COMMITTEE FINE FEES-RS. 20000</option>
																		<option
																			value="Professional Misconduct Committee fine fees-Rs. 30000">PROFESSIONAL
																			MISCONDUCT COMMITTEE FINE FEES-RS. 30000</option>
																		<option
																			value="Professional Misconduct Committee fine fees-Rs. 50000">PROFESSIONAL
																			MISCONDUCT COMMITTEE FINE FEES-RS. 50000</option>
																		<option
																			value="Professional Misconduct Committee fine fees-Rs. 100000">PROFESSIONAL
																			MISCONDUCT COMMITTEE FINE FEES-RS. 100000</option>


																	</select>


																</div>
															</div>



														</div>
													</td>

												</tr>

												<tr>
													<td><strong>Nationality/Origin</strong></td>
													<td>

														<div class="box-content">
															<div class="control-group">
																<div class="controls">
																	<select data-rel="chosen"
																		name="feeCollectionBean.nationality"
																		style="width: 400px;">
																		<option value="">--Select Option--</option>

																		<option value="INDIAN">INDIAN</option>
																		<option value="NRI/SAARC">NRI/SAARC</option>
																		<option value="FOREIGN">FOREIGN</option>

																	</select>

																</div>
															</div>
														</div>
													</td>
												</tr>
												<tr>
													<td><strong>Faculty</strong></td>
													<td>

														<div class="box-content">
															<div class="control-group">
																<div class="controls">
																	<select data-rel="chosen"
																		name="feeCollectionBean.faculty" style="width: 400px;">
																		<option value="">--Select Option--</option>

																		<option value="MEDICAL">MEDICAL</option>
																		<option value="DENTAL">DENTAL</option>
																		<option value="NURSING,PHYSIOTHERAPY,PHARMACY">NURSING,PHYSIOTHERAPY,PHARMACY</option>
																		<option value="AYUSH,AYURVEDA,HOMEOPATHY,UNANI,YOGA">AYUSH,AYURVEDA,HOMEOPATHY,UNANI,YOGA</option>
																		<option value="PARAMEDICAL AND Others">PARAMEDICAL
																			AND Others</option>

																	</select>

																</div>
															</div>
														</div>
													</td>
												</tr>

												<tr>
													<td><strong>Course</strong></td>
													<td>

														<div class="box-content">
															<div class="control-group">
																<div class="controls">
																	<select data-rel="chosen"
																		name="feeCollectionBean.course" style="width: 400px;">
																		<option value="">--Select Option--</option>

																		<option value="PG">PG</option>
																		<option value="UG">UG</option>
																		<option value="PG DIPLOMA">PG DIPLOMA</option>
																		<option value="PhD.">PHD</option>

																	</select>

																</div>
															</div>
														</div>
													</td>
												</tr>


												<tr>
													<td><strong>Total Fees</strong></td>
													<td>

														<div class="box-content">
															<div class="control-group">
																<div class="controls">

																	<div>
																		<s:iterator value="collectionBeanList">

																			<s:property value="fee" />
																		</s:iterator>


																	</div>

																</div>
															</div>
														</div>
													</td>
												</tr>

											</tbody>
										</table>



									</div>


								</div>
							</div>
						</div>
						<div class="col-md-12">
							<button type="submit" class="btn btn-success">Proceed</button>

							<button onclick="window.close()" class="btn btn-info">Close
							</button>

						</div>

					</div>
					<!--/row-->
				</form>
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
			window.opener.location.reload(true);
			window.close();

		}
	</script>
</body>
</html>
