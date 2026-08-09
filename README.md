# HTML → PDF 다운로드 (Spring Boot, 프론트 의존성 없음)

브라우저 JS(html2pdf 등) 없이 **서버에서 PDF 생성 후 다운로드**.

## 방식
1. 서버에서 HTML 문자열 생성 (화면과 같은 데이터)
2. OpenHTMLToPDF 로 PDF byte[] 변환
3. `Content-Disposition: attachment` 로 응답

```
화면 버튼 → GET /pdf/download.do → PdfService.htmlToPdf() → PDF 다운로드
```

## Maven
```xml
<dependency>
  <groupId>com.openhtmltopdf</groupId>
  <artifactId>openhtmltopdf-pdfbox</artifactId>
  <version>1.0.10</version>
</dependency>
```

## 주의
- “지금 보고 있는 브라우저 DOM을 그대로 캡처”는 프론트/헤드리스 브라우저 없이 불가
- 실무는 **같은 데이터로 서버 HTML을 다시 만들어** PDF화 하는 방식
- HTML은 XHTML에 가깝게 (`<br />`, 닫는 태그) 작성
- 한글 깨지면 한글 폰트 파일 등록 필요 (`builder.useFont(...)`)

예제: `PdfController`, `PdfService`

---

# JSP 레이어팝업 → 부모로 값 넘기기

내부망 JSP + jQuery 프로젝트에서 흔한 방식 정리.

## 많이 쓰는 방식

### 1) 레이어팝업 + 같은 화면 함수 호출 (지금 예제, 가장 흔함)

- 부모: `fileUpload('ahan_sm')` 로 레이어 오픈
- 모달 JSP를 ajax/`load` 로 레이어 div에 넣음 (iframe 아님)
- 모달 확인 시 **부모 함수를 직접 호출**

```javascript
// fileModal.jsp (모달)
setFileData(fileType, fileArr);

// parentForm.jsp (부모)
function setFileData(type, fileArr) {
    savedFiles[type] = fileArr;  // 배열로 보관
    closeFileModal();
}
```

포인트: 같은 document라 `parent` / `opener` 없이 함수 이름만 맞으면 됨.

### 2) window.open 새창 팝업

```javascript
// 모달(자식)
opener.setFileData(fileType, fileArr);
window.close();
```

### 3) iframe 모달

```javascript
// 모달(iframe 안)
parent.setFileData(fileType, fileArr);
```

프로젝트에 iframe이 없으면 1번만 보면 됨.

---

## 이 예제 흐름

1. `fileUpload('ahan_sm')` → `/file/fileModal.do?fileType=ahan_sm` 레이어 로드
2. 모달에서 파일 선택 후 확인
3. `setFileData(type, fileArr)` 로 부모에 전달
4. `savedFiles[type]` 배열에 저장
5. 나중에 `FormData` 로 서버 전송

```
부모 fileUpload(type)
   → 레이어에 fileModal.jsp
   → 확인 시 setFileData(type, fileArr)
   → savedFiles[type] = []
   → FormData append 후 /file/upload.do
```

---

## 실전 디버깅 (콘솔)

맞다. `fileArr` 는 콘솔로 찍어가며 확인하면 된다.

```javascript
function setFileData(type, fileArr) {
    console.log("type =", type);
    console.log("fileArr =", fileArr);
    console.log("개수 =", fileArr.length);
    if (fileArr.length > 0) {
        console.log("첫번째 파일명 =", fileArr[0].name);
        console.log("첫번째 크기 =", fileArr[0].size);
    }

    savedFiles[type] = fileArr;
    $("#preview_" + type).text(fileNames(fileArr));
    closeFileModal();
}
```

브라우저 F12 → Console 에서 확인.

추가로 보면 좋은 것:

```javascript
console.log(savedFiles);              // 유형별 배열 전체
console.log(savedFiles['ahan_sm']);   // 해당 유형만
```

서버 보내기 직전:

```javascript
// FormData 내용은 잘 안 보여서, append 전에 배열만 확인
console.log("전송 전 ahan_sm", savedFiles.ahan_sm);
```

---

## 헷갈릴 때 체크리스트

1. 모달 확인 버튼이 **어느 함수**를 호출하는지
2. 부모에 그 함수 이름이 **똑같이** 있는지 (`setFileData`)
3. `type` 문자열이 `ahan_sm` 처럼 **키와 동일한지**
4. `savedFiles[type]` 이 **배열(`[]`)** 인지
5. 서버 전송 시 `FormData.append(type, file)` 를 **파일 개수만큼** 돌리는지

막히면 콘솔에 `type`, `fileArr`, `savedFiles` 세 개만 찍어도 대부분 원인 나옴.
