<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>부모 화면 - 유형별 파일 (레이어팝업)</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body { margin: 20px; font-family: sans-serif; }
        .type-row { margin: 10px 0; }
        button { padding: 6px 12px; cursor: pointer; margin-right: 6px; }

        #layerDim {
            display: none;
            position: fixed;
            left: 0; top: 0; right: 0; bottom: 0;
            background: rgba(0,0,0,0.4);
            z-index: 1000;
        }
        #layerPopup {
            display: none;
            position: fixed;
            left: 50%; top: 50%;
            transform: translate(-50%, -50%);
            width: 460px;
            background: #fff;
            border: 1px solid #333;
            padding: 16px;
            z-index: 1001;
        }
    </style>
</head>
<body>

    <h2>부모 화면 (레이어팝업)</h2>

    <!-- fileUpload('ahan_sm') → 레이어에 fileModal.jsp 로드 -->
    <div class="type-row">
        <button type="button" onclick="fileUpload('ahan_sm')">ahan_sm 파일</button>
        <span id="preview_ahan_sm">없음</span>
    </div>
    <div class="type-row">
        <button type="button" onclick="fileUpload('bhan_sm')">bhan_sm 파일</button>
        <span id="preview_bhan_sm">없음</span>
    </div>
    <div class="type-row">
        <button type="button" onclick="fileUpload('chan_sm')">chan_sm 파일</button>
        <span id="preview_chan_sm">없음</span>
    </div>
    <div class="type-row">
        <button type="button" onclick="fileUpload('dhan_sm')">dhan_sm 파일</button>
        <span id="preview_dhan_sm">없음</span>
    </div>
    <div class="type-row">
        <button type="button" onclick="fileUpload('ehan_sm')">ehan_sm 파일</button>
        <span id="preview_ehan_sm">없음</span>
    </div>

    <br/>
    <button type="button" id="btnUpload">서버로 전송 (5유형)</button>

    <!-- 레이어: 내용은 fileModal.jsp 를 불러옴 -->
    <div id="layerDim"></div>
    <div id="layerPopup">
        <div id="layerContent"></div>
    </div>

<script type="text/javascript">
// 유형별 파일 배열
var savedFiles = {
    ahan_sm: [],
    bhan_sm: [],
    chan_sm: [],
    dhan_sm: [],
    ehan_sm: []
};

/**
 * 유형만 넘겨 레이어 오픈
 * 예) fileUpload('ahan_sm')
 * → fileModal.jsp 를 레이어에 로드
 */
function fileUpload(type) {
    $("#layerContent").load("/file/fileModal.do?fileType=" + type, function() {
        $("#layerDim, #layerPopup").show();
    });
}

/**
 * fileModal.jsp 확인 버튼에서 호출
 * type + 파일배열을 부모 savedFiles 에 저장
 */
function setFileData(type, fileArr) {
    savedFiles[type] = fileArr;
    $("#preview_" + type).text(fileNames(fileArr));
    closeFileModal();
}

function closeFileModal() {
    $("#layerDim, #layerPopup").hide();
    $("#layerContent").empty();
}

$(document).ready(function() {

    $("#layerDim").on("click", function() {
        closeFileModal();
    });

    $("#btnUpload").on("click", function() {
        var formData = new FormData();

        appendFiles(formData, "ahan_sm", savedFiles.ahan_sm);
        appendFiles(formData, "bhan_sm", savedFiles.bhan_sm);
        appendFiles(formData, "chan_sm", savedFiles.chan_sm);
        appendFiles(formData, "dhan_sm", savedFiles.dhan_sm);
        appendFiles(formData, "ehan_sm", savedFiles.ehan_sm);

        $.ajax({
            url: "/file/upload.do",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false
        })
        .done(function(result) {
            alert("업로드 완료: " + result);
        })
        .fail(function(xhr) {
            alert("업로드 실패: " + xhr.status);
        });
    });
});

function appendFiles(formData, key, fileArr) {
    if (!fileArr || fileArr.length === 0) return;
    for (var i = 0; i < fileArr.length; i++) {
        formData.append(key, fileArr[i]);
    }
}

function fileNames(fileArr) {
    if (!fileArr || fileArr.length === 0) return "없음";
    var names = [];
    for (var i = 0; i < fileArr.length; i++) {
        names.push(fileArr[i].name);
    }
    return names.join(", ");
}
</script>

</body>
</html>
