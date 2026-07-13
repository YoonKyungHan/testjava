<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 부모 fileUpload('ahan_sm') 에서 넘어온 유형
    String fileType = request.getParameter("fileType");
    if (fileType == null) {
        fileType = "";
    }
%>
<!--
    레이어팝업 내용 페이지 (iframe 아님)
    부모에서 ajax/load 로 이 JSP를 레이어 div에 넣음
    확인 시 이 페이지에서 부모로 값 전달
-->
<div class="file-modal-inner">
    <h3>파일 업로드</h3>
    <p>유형: <strong><%= fileType %></strong> (다중 선택)</p>

    <div>
        <input type="file" id="uploadFile" name="uploadFile" multiple="multiple" />
    </div>

    <div style="margin-top:16px; text-align:right;">
        <button type="button" id="btnCancel">취소</button>
        <button type="button" id="btnSend">확인</button>
    </div>
</div>

<script type="text/javascript">
(function() {
    var fileType = "<%= fileType %>";

    // 확인 → 이 페이지에서 부모로 값 넘김
    $("#btnSend").off("click").on("click", function() {

        var fileList = $("#uploadFile")[0].files;

        if (!fileList || fileList.length === 0) {
            alert("파일을 선택하세요.");
            return;
        }

        // FileList → 배열
        var fileArr = [];
        for (var i = 0; i < fileList.length; i++) {
            fileArr.push(fileList[i]);
        }

        // 부모 함수 호출: type + 파일배열
        if (typeof setFileData === "function") {
            setFileData(fileType, fileArr);
        } else {
            alert("부모 setFileData 함수가 없습니다.");
        }
    });

    // 취소
    $("#btnCancel").off("click").on("click", function() {
        if (typeof closeFileModal === "function") {
            closeFileModal();
        }
    });
})();
</script>
