<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kurly Approve</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<form id="ApproveForm" >

		pg_id : 	<select name="pg_id" class="form-control" id="pg_id">
                		<option value="kakao-pay">카카오페이</option>
						<option value="naver-pay">네이버페이</option>
						<option value="toss">토스</option>
						<option value="tosspayments">신용카드(토페)</option>
						<option value="chai">차이</option>
						<option value="payco">페이코</option>
						<option value="danal">다날</option>
						<option value="smilepay">스마일페이</option>
                	</select><br/>
		payment_id : <input type="text"  name="payment_id" class="form-control"  id="payment_id" ><br/>
		order_no : <input type="text"  name="order_no" class="form-control"  id="order_no" value = "20210513"><br/>		
		pg_transaction_id : <input type="text"  name="pg_transaction_id" class="form-control"  id="pg_transaction_id" ><br/>
		pg_token : <input type="text"  name="pg_token" class="form-control"  id="pg_token" value = "${pg_token}"><br/>
		pg_auth_no : <input type="text"  name="pg_auth_no" class="form-control"  id="pg_auth_no"><br/>
		pg_auth_token : <input type="text"  name="pg_auth_token" class="form-control"  id="pg_auth_token" ><br/>
				
	</form>
	<button onclick="paymentApprove()" id="btn-payment" class="btn btn-primary" >결제승인</button>
	
	<form method="get" action="/api/v1/payment/kakaopay_cancel">
		<button type="submit">카카오페이 결제 취소하러가기</button>
	</form>
	
	<script type="text/javascript">
	
	function paymentApprove() {
		
		var returnUrl;
		var pg_id = $("#pg_id").val();
		
			let data = {
					payment_id: $("#payment_id").val(),
					pg_transaction_id: $("#pg_transaction_id").val(), 
					pg_token: $("#pg_token").val(),
					order_no: $("#order_no").val(), 
					pg_auth_no: $("#pg_auth_no").val(), 
					pg_auth_token: $("#pg_auth_token").val(),
			};

			// ajax 호출시 default가 비동기 호출이다.
			$.ajax({ // 회원가입 수행 요청

				type: "POST",
				url: "/api/v1/payment/"+pg_id+"/approve/",
				//url: "/payment/1/create/",
				data: JSON.stringify(data), // json 문자열으로 변경 -> http body 데이터
				//data: JSON.stringify($("#CreateForm").serialize()), // json 문자열으로 변경 -> http body 데이터
				contentType: "application/json; charset=utf-8", //body테이터가 어떤 타입인지(MIME)
				dataType: 'json' // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열이다, (생긴게 json이라면) => javascript오브젝트로 변경해준다.

			}).done(function(response) { // 요청 결과 정상			
				console.log(response);
				//location.href = "/";
			}).fail(function(error) { // 요청 결과 실패
				alert(JSON.stringify(error))
			}); 
	}
	</script>
</body>
</html>