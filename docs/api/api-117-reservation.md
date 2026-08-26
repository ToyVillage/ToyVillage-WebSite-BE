# 단체예약 API 명세 (#117)

브랜치: `faeture/#117-ReservationFix` / Base URL: `/reservation`
`/reservation/**` 는 `APP_ADMIN` 전용, `/reservation/employee**` 만 `EMPLOYEE`.
모든 요청에 `Authorization: Bearer {accessToken}` 필요.

## 이번 변경 요약

| 구분 | 메서드 | 경로 | 기능 |
|---|---|---|---|
| **신규** | POST | `/reservation` | 단체예약 생성 (+직원 배정) |
| **신규** | PATCH | `/reservation/{reservationId}` | 단체예약 수정 (+배정 교체) |
| **신규** | DELETE | `/reservation/{reservationId}` | 단체예약 삭제 |
| **신규** | GET | `/reservation/{reservationId}/employee` | 배정됨 / 배정가능 직원 목록 |
| **변경** | GET | `/reservation` | 상태 필터 · 단체명 검색 · 정렬 파라미터 추가 |
| 기존 | GET | `/reservation/{id}` | 상세 조회 |
| 기존 | POST/GET/DELETE | `/reservation/permission/...` | 개별 권한 부여·조회·해제 |
| 기존 | GET | `/reservation/employee`, `/reservation/employee/{id}` | 직원용 조회 |

## 상태(ReservationStatus)

날짜로 자동 계산되며 직접 지정할 수 없다. 목록·상세 조회 시점에 갱신된다.

| 값 | 표시 | 조건 |
|---|---|---|
| `BEFORE_SITE_VISIT` | 사전답사 전 | 사전답사일이 오늘 이후 |
| `SITE_VISIT_COMPLETED` | 사전답사 완료 | 사전답사일은 지났고 방문일은 남음 |
| `VISIT_COMPLETED` | 방문 완료 | 방문일이 지남 |

---

## 1. 단체예약 생성 (신규)

`POST /reservation` · APP_ADMIN · `201 Created`

### Request

```json
{
  "title": "대구유치원",
  "location": "대구광역시",
  "counselDate": "2026-08-16",
  "reservationName": "이승현",
  "leaderPhoneNumber": "010-7753-9698",
  "reservationCount": 12,
  "leaderCount": 3,
  "money": 48000,
  "visitDate": "2026-08-20",
  "visitTime": "10:00",
  "exitTime": "18:00",
  "visitSiteCount": 8,
  "visitSiteDate": "2026-08-16",
  "visitSiteTime": "10:00",
  "visitSiteExitTime": "15:00",
  "appAdminIds": [3, 7]
}
```

**상담일 관련**

| 필드 | 타입 | 필수 | 제약 |
|---|---|---|---|
| `title` | String | O | 단체명, 50자 이하 |
| `location` | String | O | 지역, 50자 이하 |
| `counselDate` | LocalDate | O | 상담일, `yyyy-MM-dd` |
| `reservationName` | String | O | 예약인 이름, 20자 이하 |
| `leaderPhoneNumber` | String | O | 대표자 연락처, 15자 이하 |

**방문일 관련**

| 필드 | 타입 | 필수 | 제약 |
|---|---|---|---|
| `reservationCount` | Integer | O | 총 인원, 1 이상 |
| `leaderCount` | Integer | O | 인솔자 인원, 0 이상 |
| `money` | Integer | O | 입장료, 0 이상 |
| `visitDate` | LocalDate | O | 방문일 |
| `visitTime` | LocalTime | O | 입장 시간, `HH:mm` |
| `exitTime` | LocalTime | O | 퇴장 시간, `HH:mm` |

**사전답사 관련**

| 필드 | 타입 | 필수 | 제약 |
|---|---|---|---|
| `visitSiteCount` | Integer | O | 사전답사 인원, 0 이상 |
| `visitSiteDate` | LocalDate | O | 사전답사일 |
| `visitSiteTime` | LocalTime | O | 사전답사 입장 시간, `HH:mm` |
| `visitSiteExitTime` | LocalTime | O | 사전답사 퇴장 시간, `HH:mm` |

**페이지 권한**

| 필드 | 타입 | 필수 | 제약 |
|---|---|---|---|
| `appAdminIds` | Long[] | X | 배정할 직원 id. 생략 시 배정 없음. 중복은 무시됨 |

> **예약일(`reservationDate`) · 예약시간(`reservationTime`)은 요청에 포함하지 않는다.**
> 레코드가 생성된 시각으로 서버가 자동 입력한다. 상태도 이때 함께 계산된다.

### Response

```json
{ "message": "단체예약 생성이 완료되었습니다." }
```

### Error

| 코드 | 상황 |
|---|---|
| 400 `RESERVATION_INVALID_TIME` | 퇴장 시간이 입장 시간보다 빠르거나 같음 (방문·사전답사 모두 검사) |
| 400 `RESERVATION_INVALID_DATE` | 사전답사일이 방문일보다 늦음 |
| 404 `APP_ADMIN_NOT_FOUND` | `appAdminIds` 에 없는 직원 id |

---

## 2. 단체예약 수정 (신규)

`PATCH /reservation/{reservationId}` · APP_ADMIN · `200 OK`

Request 형식은 생성과 동일. **모든 필드를 다시 보내야 한다** (부분 수정 아님).

- `appAdminIds` 기준으로 **배정이 통째로 교체**된다. 목록에서 빠진 직원은 배정 해제된다.
- 예약일·예약시간은 생성 시각 그대로 유지된다.
- 날짜가 바뀌면 상태가 다시 계산된다.

```json
{ "message": "단체예약 수정이 완료되었습니다." }
```

| Error | 상황 |
|---|---|
| 404 `RESERVATION_NOT_FOUND` | 없는 예약 |
| 그 외 | 생성과 동일 |

---

## 3. 단체예약 삭제 (신규)

`DELETE /reservation/{reservationId}` · APP_ADMIN · `200 OK`

해당 예약의 직원 배정도 함께 삭제된다.

```json
{ "message": "단체예약 삭제가 완료되었습니다." }
```

| Error | 상황 |
|---|---|
| 404 `RESERVATION_NOT_FOUND` | 없는 예약 |

---

## 4. 목록 조회 (파라미터 추가)

`GET /reservation` · APP_ADMIN · `200 OK`

### Query Parameters

| 파라미터 | 타입 | 필수 | 기본값 | 설명 |
|---|---|---|---|---|
| `status` | Enum | X | 전체 | 상태 카드 3개 버튼. `BEFORE_SITE_VISIT` / `SITE_VISIT_COMPLETED` / `VISIT_COMPLETED` |
| `title` | String | X | 전체 | 단체명 부분 일치, 대소문자 무시 |
| `sort` | Enum | X | `RESERVATION_DATE` | `COUNSEL_DATE`(상담일순) / `RESERVATION_DATE`(예약일순). 둘 다 내림차순 |
| `page` | int | X | 0 | |
| `size` | int | X | 20 | |

예시: `GET /reservation?status=BEFORE_SITE_VISIT&title=대구&sort=COUNSEL_DATE&page=0&size=10`

### Response

```json
{
  "beforeVisitSite": 3,
  "doneVisitSite": 3,
  "doneVisit": 3,
  "reservationAdminQueryListObjectResponse": {
    "content": [
      {
        "id": 42,
        "title": "대구어린이집",
        "counselDate": "2026-07-02",
        "reservationDate": "2026-07-13",
        "reservationTime": "13:01",
        "location": "대구광역시",
        "count": 18,
        "status": "사전답사 전"
      }
    ],
    "totalElements": 9,
    "totalPages": 1,
    "number": 0,
    "size": 10
  }
}
```

> **`beforeVisitSite` / `doneVisitSite` / `doneVisit` 세 카운트는 필터와 무관하게 항상 전체 기준이다.**
> 상태 버튼을 눌러도 카드 숫자는 그대로이고 목록만 걸러진다.

`status` 는 `@JsonValue` 로 한글 설명이 직렬화된다 (`"사전답사 전"`).

---

## 5. 직원 배정 목록 조회 (신규)

`GET /reservation/{reservationId}/employee?name=이승` · APP_ADMIN · `200 OK`

페이지 권한 화면의 "배정됨 / 배정가능" 두 그룹.

| 파라미터 | 타입 | 필수 | 설명 |
|---|---|---|---|
| `name` | String | X | 이름 부분 일치. 생략 시 전체 |

```json
{
  "assigned": [
    { "appAdminId": 3, "name": "이승현" }
  ],
  "assignable": [
    { "appAdminId": 7, "name": "김직원" }
  ]
}
```

- 대상은 `AppAdminRole.EMPLOYEE` 인 계정만. 관리자는 나오지 않는다.
- 이름 오름차순 정렬.

| Error | 상황 |
|---|---|
| 404 `RESERVATION_NOT_FOUND` | 없는 예약 |

---

## 기존 엔드포인트 (변경 없음)

| 메서드 | 경로 | 기능 | 권한 |
|---|---|---|---|
| GET | `/reservation/{id}` | 예약 상세 | APP_ADMIN |
| POST | `/reservation/permission/{reservationId}/{appAdminId}` | 권한 부여·해제 (body: `true`/`false`) | APP_ADMIN |
| GET | `/reservation/permission/{reservationId}` | 배정된 직원 목록 | APP_ADMIN |
| DELETE | `/reservation/permission/{reservationId}/{appAdminId}` | 권한 해제 | APP_ADMIN |
| GET | `/reservation/employee` | 본인에게 배정된 예약 목록 | EMPLOYEE |
| GET | `/reservation/employee/{id}` | 예약 상세 | EMPLOYEE |

---

## 알려진 제약

- `DELETE /reservation/permission/{reservationId}/{appAdminId}` 는 `@ResponseStatus(NO_CONTENT)` 인데 `MessageResponse` 를 반환한다. 204는 본문이 없어 메시지가 전달되지 않는다.
- 예약 상세 조회(`GET /reservation/{id}`)에 배정 직원 정보가 포함되지 않는다. 권한 목록은 별도 호출 필요.
