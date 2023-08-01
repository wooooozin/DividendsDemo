# 프로젝트 설명
### **미국 주식 배당금 정보를 제공하는 API 서비스 시스템을 만드는 클론 코딩 및 추가 기능 구현 프로젝트 과제**
<br>
<br>


## 프로젝트 목표 및 기능 설명
- 웹 페이지를 분석하고 스크래핑 기법을 활용하여 필요한 데이터를 추출/저장합니다.
- 사용자별 데이터를 관리하고 예상 배당금 액수를 계산할 수 있습니다.
- 서비스에서 캐시의 필요성을 이해하고 캐시 서버를 구성합니다.

<br>
<br>

## 기술스텍
`Spring Boot` `Java` `JPA` `H2` `Redis` `Jsoup`
<br>

## 구현 API 리스트
- GET - finance/dividend/{companyName}
  - 회사 이름을 인풋으로 받아서 해당 회사의 메타 정보와 배당금 정보를 반환
  - 잘못된 회사명이 입력으로 들어온 경우 400 status 코드와 에러메시지 반환
    
    | <center>성공</center> | <center>실패</center> | 
    |----|-----|
    | <img width="400" alt="1" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/f9d9f74c-0083-43fb-8439-48f1b17f8842"> |<img width="400" alt="2" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/7fec6516-bd3c-44bb-a7fd-4062afec9cf8">|

- GET - company/autocomplete
  - 자동완성 기능을 위한 API
  - 검색하고자 하는 prefix 를 입력으로 받고, 해당 prefix 로 검색되는 회사명 리스트 중 10개 반환
  
    | <center>성공</center>                                                                                                             | <center>am 결과</center>                                                                                                          |
    |---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|
    | <img width="400" alt="3" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/fbd0b81d-f712-434f-807d-697946aa0e03"> | <img width="400" alt="4" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/7f978733-230a-4837-886c-305f8db5ea76"> |

- GET - company
  - 서비스에서 관리하고 있는 모든 회사 목록을 반환
  - 반환 결과는 Page 인터페이스 형태

    | <center>성공</center>                                                                                                             | <center>1 page 20</center>                                                                                                      |
    |---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|
    |<img width="400" alt="2" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/4e7e27f8-5c4b-4044-8bfd-ce85dcff1299"> | <img width="400" alt="1" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/04c61309-2323-4e12-a062-13199ab463bc"> |

- POST - company
  - 새로운 회사 정보 추가
  - 추가하고자 하는 회사의 ticker 를 입력으로 받아 해당 회사의 정보를 스크래핑하고 저장
  - 이미 보유하고 있는 회사의 정보일 경우 400 status 코드와 적절한 에러 메시지 반환
  - 존재하지 않는 회사 ticker 일 경우 400 status 코드와 적절한 에러 메시지 반환
  
    | <center>성공</center>                                                                                                             | <center> 실패 - 보유 회사 </center>                                                                                                   | <center> 실패 - 존재하지 않는 ticker </center>                                                                                          |
    |---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|
     | <img width="400" alt="1" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/b938e8be-cc03-4a01-9a2b-7ac9c0931221"> | <img width="400" alt="2" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/21dc1672-47ea-4909-91b4-85c378308213"> | <img width="300" alt="3" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/d41fbb4f-d1c8-45dc-97fb-19de01ea720f"> |

- DELETE - company/{ticker}
    - ticker 에 해당하는 회사 정보 삭제
    - 삭제시 회사의 배당금 정보와 캐시도 모두 삭제되어야 함

      | <center>성공</center>                                                                                                             | <center>캐시 삭제 확인</center>                                                                                                       |
          |---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|
        | <img width="400" alt="4" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/b83c8b85-fc14-499b-a71d-2baeb7d0baab"> | <img width="400" alt="5" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/8f3bee40-5873-47e2-8bb9-05ac14e915ef"> |

- POST - auth/signup
  - 회원가입 API
  - 중복 ID 는 허용하지 않음
  - 패스워드는 암호화된 형태로 저장되어야함
  
    | <center>성공 / 패스워트 암호화</center>                                                                                                 | <center> 중복 ID </center>                                                                                                        | 
        |--------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|
     |<img width="400" alt="패스워드" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/f3b1b26a-a734-4d52-8e8f-0d3cddec4fa2">|<img width="400" alt="중복아이디" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/95f9d1ab-eb5f-4bcf-a2a5-f00e62cb3b19">|

- POST - auth/signin
    - 로그인 API
    - 회원가입이 되어있고, 아이디/패스워드 정보가 옳은 경우 JWT 발급

  | <center>성공</center>                                                                                                             | <center> 아이디 없음 </center> | <center> 비밀번호 틀림 </center> |
  |---------------------------------------------------------------------------------------------------------------------------------|---------------------------|----------------------------|
  | <img width="350" alt="토큰" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/a690df7f-97d0-4ee2-b4df-259a990ab814">| <img width="350" alt="노아이디" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/ea2cfb6f-17b1-40ac-a20b-34205bbdc822"> |<img width="350" alt="비밀번호 틀림" src="https://github.com/wooooozin/DividendsDemo/assets/95316662/6348c0e4-b48a-4c60-a4c2-6e5e8dddfc77">|


- H2 DB

    | ![1](https://github.com/wooooozin/DividendsDemo/assets/95316662/18b04925-c66d-4f2a-acdd-795b9cd77343) | ![2](https://github.com/wooooozin/DividendsDemo/assets/95316662/3e524f47-f3dc-4f3a-9da5-fe4e885d6939) |
  | --- | --- |

# 🥸 후기
프로젝트를 진행하면서 Redis 서버 및 Cache 구현, JWT 인증, Docker 개념 등 <br>
처음 접하는 개념이 많아서 왜 이런 기능을 사용해야하고 기능 사용시 어떤 점을 유의해야하는 지 정리가 필요할 것 같다.<br>
