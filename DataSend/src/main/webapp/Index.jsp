<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<style>
			.form-control{
				border-radius:5px;
				width:96%;
				padding-left:10px;
				height:20px;
			}
			.label{
				text-align:left; 
				margin-bottom:15px;
			}
			.form{
				margin:0 auto;
				width:28%;
				border:1px solid none;
				padding:10px;
				margin-top:80px;
				background-color:white;
				border-radius:10px;
				box-shadow:10px red;
			}
			input[type=text]:focus {
				background-color: lightgrey	;
			}
			.btn{
				background:#0066A2;
				color:white;
				border-color:#0066A2;
				height:30px;
				width:100px;
				font:bold 15px arial,sans-serif;
				text-shadow:none;
				border-radius:10px;				
			}
			.btn1{
				background:#0066A2;
				color:white;
				border-color:#0066A2;
				width:100px;
				font:bold 15px arial,sans-serif;
				text-shadow:none;
				border-radius:10px;				
			}
		</style>
		<title>99kart.com</title>
	</head>
	<body style="background-color:#ecf0f4; width:100%">
		<div class="form">
			<div style="text-align:center;">
				<div class="form__btn" style="margin-top:20px;">
					
				</div>
				<h1>Share your file!</h1>
				<form action="uploadServlet" method="post" enctype="multipart/form-data">
					<div class="label">
                        <label style="font-size:18px;margin-right:50px;">Username</label>
                        <input type="text" name="name" class="form-control">
                    </div>
					<div class="label">
                        <label style="font-size:18px;margin-right:24px;">Email Address</label>
                        <input type="email" name="email" class="form-control">
                    </div>
					<div style="margin-bottom:20px;text-align:left;">
                        <label style="font-size:18px;margin-right:45px;">Upload File</label>
                        <input type="file" name="myfile" class="form-control" style="margin-top:5px;margin-left:-7px;">
                    </div>
					<div style="margin-bottom:10px;">
						<button type="submit" name="submit" class="btn">Submit</button>
					</div>
					<div style="display:inline">
						<a href="DataFetch.jsp" style="float:left;margin-top:5px;">Click here</a>
						<p style="margin-right:215px;">to fetch data...</p>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
