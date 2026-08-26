# 업무일지 API 명세 (#112)

브랜치: `feature/#112-work-log` / Base URL: `/work-log`
권한: `APP_ADMIN`(관리자) · `EMPLOYEE`(직원). 모든 요청에 `Authorization: Bearer {accessToken}` 필요.

## 엔드포인트 요약

| 메서드 | 경로 | 기능 | 권한 |
|---|---|---|---|
| POST | `/work-log/template` | 양식 생성 | APP_ADMIN |
| GET | `/work-log/template` | 양식 목록 | APP_ADMIN, EMPLOYEE |
| GET | `/work-log/template/{workLogTemplateId}` | 양식 상세 | APP_ADMIN, EMPLOYEE |
| POST | `/work-log/employee/{workLogTemplateId}` | 일지 작성 | EMPLOYEE |
| PATCH | `/work-log/employee/{workLogId}` | 일지 수정 | EMPLOYEE (작성자 본인) |
| DELETE | `/work-log/employee/{workLogId}` | 일지 삭제 | EMPLOYEE (작성자 본인) |
| GET | `/work-log` | 전체 일지 목록 | APP_ADMIN |
| GET | `/work-log/employee` | 본인 일지 목록 | EMPLOYEE |
| GET | `/work-log/{workLogId}` | 일지 상세 | APP_ADMIN, EMPLOYEE |

## 개념

- **양식(Template)** — 관리자가 만든 빈 틀. 질문(가로축)과 구역(세로축)을 가진다. 생성 후 수정 불가.
- **일지(WorkLog)** — 직원이 제출한 1건. 엑셀 한 장에 해당하며 구역 x 질문 격자를 모두 포함한다.
- **답변(Answer)** — 격자의 칸 하나. `sectionId` + `questionId` 조합으로 위치가 정해진다.

### QuestionType

| 값 | 설명 | 보기 필요 |
|---|---|---|
| `SHORT_TEXT` | 주관식 | X |
| `LONG_TEXT` | 장문형 | X |
| `MULTIPLE_CHOICE` | 객관식 | O |
| `CHECK_BOX` | 체크박스 | O |
| `DROP_DOWN` | 드롭다운 | O |
| `FILE_UPLOAD` | 파일 업로드 | X |

---

## 1. 양식 생성

`POST /work-log/template` · APP_ADMIN · `201 Created`

### Request

```json
{
  "templateTitle": "4층 사육마감일지",
  "sections": ["A1", "A2", "B4", "기타"],
  "questions": [
    {
      "question": "온도",
      "questionType": "DROP_DOWN",
      "required": true,
      "options": [
        { "content": "20도", "etcOption": false },
        { "content": "기타", "etcOption": true }
      ]
    },
    {
      "question": "청소방법",
      "questionType": "SHORT_TEXT",
      "required": false,
      "options": []
    }
  ]
}
```

| 필드 | 타입 | 필수 | 제약 |
|---|---|---|---|
| `templateTitle` | String | O | 50자 이하, 중복 불가 |
| `sections` | String[] | O | 1개 이상, 각 20자 이하. 배열 순서가 표시 순서 |
| `questions` | Object[] | O | 1개 이상. 배열 순서가 표시 순서 |
| `questions[].question` | String | O | 80자 이하 |
| `questions[].questionType` | Enum | O | 위 표 참고 |
| `questions[].required` | boolean | O | 필수 응답 여부 |
| `questions[].options` | Object[] | X | 보기 필요 타입이면 1개 이상 |
| `questions[].options[].content` | String | O | 30자 이하 |
| `questions[].options[].etcOption` | boolean | O | 자유 입력 "기타" 칸 여부 |

### Response

```json
{ "message": "업무일지 양식 생성 성공" }
```

### Error

| 코드 | 상황 |
|---|---|
| 400 `WORK_LOG_OPTION_REQUIRED` | 객관식·체크박스·드롭다운인데 보기가 없음 |
| 409 `WORK_LOG_TEMPLATE_EXIST` | 양식명 중복 |

---

## 2. 양식 목록 조회

`GET /work-log/template` · APP_ADMIN, EMPLOYEE · `200 OK`

최신순(id 내림차순). 파라미터 없음.

```json
{
  "templates": [
    { "templateId": 2, "templateTitle": "5층 사육마감일지" },
    { "templateId": 1, "templateTitle": "4층 사육마감일지" }
  ]
}
```

---

## 3. 양식 상세 조회

`GET /work-log/template/{workLogTemplateId}` · APP_ADMIN, EMPLOYEE · `200 OK`

일지 작성 화면을 그리는 데 필요한 정보 전부. **답변은 포함되지 않는다.**

```json
{
  "templateId": 1,
  "templateTitle": "4층 사육마감일지",
  "sections": [
    { "sectionId": 1, "sectionName": "A1" },
    { "sectionId": 2, "sectionName": "A2" }
  ],
  "questions": [
    {
      "questionId": 10,
      "question": "온도",
      "questionType": "DROP_DOWN",
      "required": true,
      "options": [
        { "choiceId": 5, "number": 0, "content": "20도", "etcOption": false }
      ]
    }
  ]
}
```

| Error | 상황 |
|---|---|
| 404 `WORK_LOG_TEMPLATE_NOT_FOUND` | 없는 양식 |

---

## 4. 일지 작성

`POST /work-log/employee/{workLogTemplateId}` · EMPLOYEE · `201 Created`

한 요청에 엑셀 한 장 전체(구역 x 질문 전부)를 담는다.

### Request

```json
{
  "answers": [
    { "sectionId": 1, "questionId": 10, "answerText": "20도" },
    { "sectionId": 1, "questionId": 11, "answerText": "물청소" },
    { "sectionId": 2, "questionId": 10, "answerText": "21도" },
    { "sectionId": 2, "questionId": 12, "fileId": 77 }
  ]
}
```

| 필드 | 타입 | 필수 | 설명 |
|---|---|---|---|
| `answers` | Object[] | O | 1개 이상 |
| `answers[].sectionId` | Long | O | 해당 양식의 구역 id |
| `answers[].questionId` | Long | O | 해당 양식의 질문 id |
| `answers[].answerText` | String | X | 500자 이하. 주관식·장문·선택한 보기 값 |
| `answers[].fileId` | Long | X | `POST /file` 로 먼저 업로드하고 받은 id |

체크박스로 여러 개를 고르면 같은 `sectionId`+`questionId` 로 행을 여러 개 보낸다.

### Response

```json
{ "message": "업무일지 작성 성공" }
```

### Error

| 코드 | 상황 |
|---|---|
| 400 `WORK_LOG_ANSWER_REQUIRED` | 필수 질문이 어느 구역에서든 비어 있음 |
| 404 `WORK_LOG_TEMPLATE_NOT_FOUND` | 없는 양식 |
| 404 `WORK_LOG_SECTION_NOT_FOUND` | 이 양식 소속이 아닌 구역 |
| 404 `WORK_LOG_QUESTION_NOT_FOUND` | 이 양식 소속이 아닌 질문 |
| 404 `FILE_NOT_FOUND` | 없는 파일 |

---

## 5. 일지 수정

`PATCH /work-log/employee/{workLogId}` · EMPLOYEE (작성자 본인) · `200 OK`

Request 형식은 작성과 동일. **답변 전체를 교체**하므로 바뀌지 않은 답변도 모두 담아 보내야 한다.

```json
{ "message": "업무일지 수정 성공" }
```

| Error | 상황 |
|---|---|
| 403 `WORK_LOG_FORBIDDEN` | 본인이 작성한 일지가 아님 |
| 404 `WORK_LOG_NOT_FOUND` | 없는 일지 |
| 그 외 | 작성과 동일 |

---

## 6. 일지 삭제

`DELETE /work-log/employee/{workLogId}` · EMPLOYEE (작성자 본인) · `200 OK`

일지에 딸린 답변도 함께 삭제된다.

```json
{ "message": "업무일지 삭제 성공" }
```

| Error | 상황 |
|---|---|
| 403 `WORK_LOG_FORBIDDEN` | 본인이 작성한 일지가 아님 |
| 404 `WORK_LOG_NOT_FOUND` | 없는 일지 |

---

## 7. 일지 목록 조회 (관리자)

`GET /work-log?page=0&size=10` · APP_ADMIN · `200 OK`

모든 직원의 일지. **답변은 포함되지 않는다.**

```json
{
  "content": [
    {
      "workLogId": 7,
      "templateTitle": "4층 사육마감일지",
      "writerName": "김직원",
      "writeAt": "2026-08-24T09:31:00"
    }
  ],
  "totalElements": 31,
  "totalPages": 4,
  "number": 0,
  "size": 10
}
```

---

## 8. 일지 목록 조회 (직원)

`GET /work-log/employee?page=0&size=10` · EMPLOYEE · `200 OK`

토큰 기준 **본인이 작성한 일지만**. 응답 형식은 7번과 동일.

---

## 9. 일지 상세 조회

`GET /work-log/{workLogId}` · APP_ADMIN, EMPLOYEE · `200 OK`

답변을 구역별로 묶어서 반환한다. 답변이 없는 구역도 빈 배열로 포함된다.

```json
{
  "workLogId": 7,
  "templateId": 1,
  "templateTitle": "4층 사육마감일지",
  "writerName": "김직원",
  "writeAt": "2026-08-24T09:31:00",
  "sections": [
    {
      "sectionId": 1,
      "sectionName": "A1",
      "answers": [
        {
          "questionId": 10,
          "question": "온도",
          "questionType": "DROP_DOWN",
          "answerText": "20도",
          "file": null
        },
        {
          "questionId": 12,
          "question": "점검 사진",
          "questionType": "FILE_UPLOAD",
          "answerText": null,
          "file": { "fileName": "photo.jpg", "fileKey": "abc123" }
        }
      ]
    }
  ]
}
```

| Error | 상황 |
|---|---|
| 404 `WORK_LOG_NOT_FOUND` | 없는 일지 |

---

## 미구현 / 알려진 제약

- 양식 **수정·삭제 API 없음** (양식은 생성 후 변경 불가로 합의)
- 답변 값이 보기 목록에 있는 값인지 **서버가 검증하지 않음**
- 같은 `sectionId`+`questionId` 중복 답변을 **막지 않음** (체크박스 다중선택 때문)
- 하루에 같은 양식으로 **여러 번 제출 가능**
- 일지 상세 조회에 **소유자 확인 없음** — 직원이 다른 직원의 일지를 id로 조회 가능
- 엑셀 상단 공통 칸(근무자·결재란) 미구현
