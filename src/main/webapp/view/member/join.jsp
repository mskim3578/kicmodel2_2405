<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html >
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 화면 샘플 - Bootstrap</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
body {
	min-height: 100vh;
	background: -webkit-gradient(linear, left bottom, right top, from(#92b5db),
		to(#1d466c));
	background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
}

.input-form {
	max-width: 680px;
	margin-top: 80px;
	padding: 32px;
	background: #fff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
}
</style>
</head> 
<script>
function chkform(f) {
	alert(f.pass.value==f.pass2.value)
	alert("aaaa")
	if (f.pass.value!=f.pass2.value){
		f.focus()
		return false
	}
	return true
}
function win_upload() {
	let op = "width=500, height=150, left=50, top=150";
	open("${pageContext.request.contextPath}/member/pictureimgForm", "", op);
}
</script>
<body>
	<div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<h4 class="mb-3  text-center">회원가입</h4>
				<form class="validation-form" novalidate  name="joinform"
				    action="joinPro"   method="post"   onsubmit="return chkform(this)" >
				    <input type="hidden"  name="picture">
					<div class="row">
					   <div class="col-md-3 mb-3">
							<label for="id">사진</label> <img src=""  width="100px"  height="120px" id="pic">
							<div   class="btn btn-primary  btn-block" onclick="win_upload()">사진업로드</div>
						</div>
						<div class="col-md-9 mb-3">
						<div class="row">
						<div class="col-md-6 mb-3">
							<label for="id">아이디</label> <input type="text"
								class="form-control" id="id" placeholder="아이디" value="" required name="id" >
							<div class="invalid-feedback">아이디을 입력해주세요.</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">이름</label> <input type="text"   name="name"
								class="form-control" id="name" placeholder="" value=""   
								required>
							<div class="invalid-feedback">이름을 입력해주세요.</div>
						</div>
					</div></div></div>
					
						<div class="row">
						<div class="col-md-6 mb-3">
							<label for="pass">비밀번호</label> <input type="password"    name="pass"
								class="form-control" id="pass" placeholder="아이디" value="" required >
							<div class="invalid-feedback">비밀번호을 입력해주세요.</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="pass2">비밀번호확인</label> <input type="password"    name="pass2"
								class="form-control" id="pass2" placeholder="" value=""  
								required>
							<div class="invalid-feedback">비밀번호확인을 입력해주세요.</div>
						</div>
					</div>
					
						<div class="row">
						<div class="col-md-6 mb-3">
							<label for="gender">남자</label> <input type="radio"    name="gender"
								 id="gender"  value="1" required  >
						
						</div>
						<div class="col-md-6 mb-3">
							<label for="gender">여자</label> <input type="radio"    name="gender"
								 id="gender" placeholder="" value="2"  
								required>
							
						</div>
					</div>
					
					<div class="mb-3">
						<label for="email">이메일</label> <input type="email"     name="email"
							class="form-control" id="email" placeholder="you@example.com"
							required>
						<div class="invalid-feedback">이메일을 입력해주세요.</div>
					</div>
					<div class="mb-3">
						<label for="tel">전화번호</label> <input type="text"    name="tel"
							class="form-control" id="tel" placeholder="전화번호"   
							required>
						<div class="invalid-feedback">전화번호를 입력해주세요.</div>
					</div>	
					
					
					
					
				
					<button class="btn btn-primary btn-lg btn-block" type="submit">가입
						완료</button>
				</form>
			</div>
		</div>
		
	</div>
	<script> window.addEventListener('load', () => { const forms = document.getElementsByClassName('validation-form'); Array.prototype.filter.call(forms, (form) => { form.addEventListener('submit', function (event) { if (form.checkValidity() === false) { event.preventDefault(); event.stopPropagation(); } form.classList.add('was-validated'); }, false); }); }, false); </script>
</body>
</html>