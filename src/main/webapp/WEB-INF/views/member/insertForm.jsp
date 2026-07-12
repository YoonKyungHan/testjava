<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 등록</title>
    <!-- jQuery (프로젝트 CDN/경로에 맞게 변경) -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

    <h2>회원 등록</h2>

    <!--
        form name = VO 필드명 = MyBatis #{필드명}
        jQuery serialize() 로 값 전송
    -->
    <!-- po 는 화면에서 안 보냄. Service 에서 pp2026-001 형태로 채번 -->
    <form id="memberForm">
        <div>
            <label>아이디</label>
            <input type="text" name="member_id" />
        </div>
        <div>
            <label>이름</label>
            <input type="text" name="member_name" />
        </div>
        <div>
            <label>이메일</label>
            <input type="text" name="email" />
        </div>
        <div>
            <label>연락처</label>
            <input type="text" name="phone" />
        </div>
        <div>
            <label>나이</label>
            <input type="text" name="age" />
        </div>
        <div>
            <label>성별</label>
            <select name="gender">
                <option value="M">남</option>
                <option value="F">여</option>
            </select>
        </div>
        <div>
            <label>주소</label>
            <input type="text" name="addr" />
        </div>
        <div>
            <label>부서코드</label>
            <input type="text" name="dept_cd" />
        </div>
        <div>
            <label>사용여부</label>
            <select name="use_yn">
                <option value="Y">Y</option>
                <option value="N">N</option>
            </select>
        </div>
        <div>
            <label>가입일</label>
            <input type="text" name="join_dt" placeholder="yyyy-MM-dd" />
        </div>
        <div>
            <button type="button" id="btnInsert">등록</button>
        </div>
    </form>

<script type="text/javascript">
$(document).ready(function() {

    $("#btnInsert").on("click", function() {

        $.ajax({
            url: "/member/insert.do",
            type: "POST",
            data: $("#memberForm").serialize()
        })
        .done(function(result) {
            // 성공
            alert("등록 완료");
        })
        .fail(function(xhr) {
            // 실패 - xhr 로 상태 확인
            alert("등록 실패: " + xhr.status);
        });

    });

});
</script>

</body>
</html>
