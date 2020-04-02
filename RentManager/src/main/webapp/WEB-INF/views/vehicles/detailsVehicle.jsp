<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		<!-- Left side column. contains the logo and sidebar -->
		<%@ include file="/WEB-INF/views/common/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Main content -->
			<section class="content">

				<div class="row">
					<div class="col-md-3">

						<!-- Profile Image -->
						<div class="box box-primary">
							<div class="box-body box-profile">
								<h3 class="profile-username text-center">${ constructeur } ${nb_places }
									${modele }</h3>

								<ul class="list-group list-group-unbordered">
									<li class="list-group-item"><b>Reservation(s)</b> <a
										class="pull-right">2</a></li>
									<li class="list-group-item"><b>Voiture(s)</b> <a
										class="pull-right">3</a></li>
								</ul>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
					<div class="col-md-9">
						<div class="nav-tabs-custom">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#rents" data-toggle="tab">Reservations</a></li>
								<li><a href="#cars" data-toggle="tab">Voitures</a></li>
							</ul>
							<div class="tab-content">
								<div class="active tab-pane" id="rents">
									<div class="box-body no-padding">
										<table class="table table-striped">

											<tr>
												<th style="width: 10px">#</th>
												<th>Marque</th>
												<th>Modele</th>
												<th>Nombre de places</th>
												<th>Action</th>
											</tr>
											<tr>
												<c:forEach items="${ vehicles }" var="vehicle">
													<td>${vehicle.id}.</td>
													<td>${vehicle.constructeur}</td>
													<td>${vehicle.modele}</td>
													<td>${vehicle.nb_places}</td>
													<td><a class="btn btn-primary"
														href="${pageContext.request.contextPath}/cars/details?id=${vehicle.id}">
															<i class="fa fa-play"></i>
													</a> <a class="btn btn-success"
														href="${pageContext.request.contextPath}/cars/modify?id=${vehicle.id}">
															<i class="fa fa-edit"></i>
													</a> <a class="btn btn-danger"
														href="${pageContext.request.contextPath}/cars/delete?id=${vehicle.id}">
															<i class="fa fa-trash"></i>
													</a></td>
												</c:forEach>
											</tr>

										</table>
									</div>
								</div>
								<!-- /.tab-pane -->
								<div class="tab-pane" id="cars">
									<!-- /.box-header -->
									<div class="box-body no-padding">
										<table class="table table-striped">
											<tr>
												<th style="width: 10px">#</th>
												<th>Modele</th>
												<th>Constructeur</th>
												<th style=>Nombre de places</th>
											</tr>
											<c:forEach var="i" begin="0" end="10" step="2">
												<tr>
													<td>3.</td>
													<td>Megane</td>
													<td>Renault</td>
													<td>5</td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</div>
								<!-- /.tab-pane -->
							</div>
							<!-- /.tab-content -->
						</div>
						<!-- /.nav-tabs-custom -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->

			</section>
			<!-- /.content -->
		</div>

		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="/WEB-INF/views/common/js_imports.jsp"%>
</body>
</html>