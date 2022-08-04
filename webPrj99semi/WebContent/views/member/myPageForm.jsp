<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>충희네 회원가입</title>
<style>
	main table{
		margin: 0 auto;
	}
	main{
		background-color: black;
		color: white;
	}
	#pwdFormOuter{
		display: flex;
		justify-content: center;
	}
	#pwdFormOuter td:last-child{
		display: flex;
		justify-content: left;
	}
</style>
</head>
<body>
	<%@include file="/views/common/header.jsp" %>
	
	<main>
		<h1 align="center">마이페이지</h1>
		<form action="/semi/member/myPage" method="post">
		<input type="hidden" value="<%=loginMember.getNo()%>" name="memberNo">
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input type="text" name="memberId" maxlength="10" value="<%=loginMember.getId()%>" required  readonly="readonly"></td>
				</tr>
					<td>* 이름</td>
					<td><input type="text" name="memberName" maxlength="3" value="<%=loginMember.getName() %>" required></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="te" name="memberPhone" placeholder="- 없이 입력" value="<%=loginMember.getPhone()%>"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="memberEmail"  value="<%=loginMember.getEmail()%>"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="memberAddr" value="<%=loginMember.getAddr()%>" ></td>
				</tr>
				<tr>
					<td>관심분야</td>
					<td>
						<input type="checkbox" name="interest" value="game" id="game">
						<label for="game">게임</label>
						<input type="checkbox" name="interest" value="workout" id="workout">
						<label for="workout">운동</label>
						<input type="checkbox" name="interest" value="cook" id="cook">
						<label for="cook">요리</label>
						<br>
						<input type="checkbox" name="interest" value="music" id="music">
						<label for="music">음악</label>
						<input type="checkbox" name="interest" value="book" id="book">
						<label for="book">독서</label>
						<input type="checkbox" name="interest" value="draw" id="draw">
						<label for="draw">그림</label>
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="정보변경" id="" class="btn btn-primary"></td>
					<td><input type="button" value="비밀번호변경" id="" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#pwdChange">
						<input type="button" value="회원탈퇴" id="" onclick="test()" class="btn btn-danger">
					</td>
				</tr>				
			</table>
		</form>
	</main>
	<!-- The Modal -->
	<div class="modal" id="pwdChange">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">Modal Heading</h4>
	        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body">
	       	<div id="pwdFormOuter">
	       		<form action="<%=contextPath%>/member/pwd" method="post">
	       		<input type="hidden" name="memberId" value="<%=loginMember.getId()%>">
				<table>
					<tr>
						<td>기존 비밀번호</td>
						<td><input type="password" name="memberPwd"></td>
					</tr>
					<tr>
						<td>신규 비밀번호</td>
						<td><input type="password" name="memberPwdNew"></td>
					</tr>
					<tr>
						<td>신규 비밀번호 확인</td>
						<td><input type="password" name="memberPwdNew2"></td>
					</tr>
					<tr>
						<td></td>
						<td colspan="2"><input type="submit" value="변경하기" onclick="return checkPwd();"></td>
					</tr>												
				</table>
			</form>
	       	</div>
	      </div>
	    </div>
	  </div>
	</div>
	<script>
		$(function(){
			
			const interest = '<%=loginMember.getInterest()%>'; 
			
			console.log(interest);
			
			//체크박스 가져오기
			$('input[name=interest]').each(function(){
				var result = interest.search(this.value);
				console.log(result);
				if(result != -1) {
				this.checked = true;
				}
				
				var result2 = interest.indexOf(this.value);
				console.log(result2);
				if(result2 != -1) {
					this.checked = true;
				}
			});
		})
	</script>
	<script>
	function checkPwd() {
		isSame = $('input[name=memberPwdNew]').val() == $('input[name=memberPwdNew2]').val();
		if(isSame == true) {
			return true;
		}else {
			alert("신규 비밀번호가 일치하지 않습니다.");
			return false;
			
		}
	}
	</script>
	
	
	
	
	<script>
	function test() {
        if (!confirm("회원 탈퇴를 진행하시겠습니까?")) {
            alert("취소(아니오)를 누르셨습니다.");
        } else {
            alert("확인(예)을 누르셨습니다.");
            location.href='/semi/member/quit';
        }
    }
	</script>
</body>
</html>
