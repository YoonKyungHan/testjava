<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 수정</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

    <h2>회원 수정</h2>

    <!--
        form name = VO 필드명 = MyBatis #{필드명}
        member_id 기준 UPDATE
    -->
    <!-- 수정은 기존 po 필요 -->
    <form id="memberForm">
        <div>
            <label>고유키(po)</label>
            <input type="text" name="po" />
        </div>
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
            <button type="button" id="btnUpdate">수정</button>
        </div>
    </form>

<script type="text/javascript">
$(document).ready(function() {

    $("#btnUpdate").on("click", function() {

        $.ajax({
            url: "/member/update.do",
            type: "POST",
            data: $("#memberForm").serialize()
        })
        .done(function(result) {
            alert("수정 완료");
        })
        .fail(function(xhr) {
            alert("수정 실패: " + xhr.status);
        });

    });

});
</script>

</body>
</html>
