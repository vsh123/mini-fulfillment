# MINI FULFILLMENT

KVP 스터디

요구 사항 : https://docs.google.com/presentation/d/1gyS9wuVJgh3RZRJF3oWwSSerfXaxFDAVagDFLqMmujc/edit#slide=id.g135d442150d_2_77

## 퀵 스타트


## 컨벤션을 위한 ktlint 적용 

```sh
./gradlew ktlintApplyToIdea
mkdir .git/hooks
./gradlew addKtlintCheckGitPreCommitHook
```


### 1주차 작업 내용

- [X] SKU 도메인 생성
  - [X] SKU는 고유의 코드를 가지고 있다.
  - [X] SKU는 이름을 가지고 있다. (최대 20자)
  - [X] SKU는 1개 이상의 바코드를 등록할 수 있다.
  - [X] SKU는 런칭 예정 / 판매중 / 판매 중지의 상태를 가지고 있다.
- [X] 바코드 도메인 생성
  - [X] 바코드는 고유하다
  - [X] 바코드는 최대 20자

- [ ] 센터 도메인 생성
  - [X] 센터는 오픈 준비 중 / 운영 중 / 폐점의 상태를 가지고 있다.
  - [ ] 센터는 고유한 코드를 가지고 있다.
  - [X] 센터는 이름을 가지고 있다. (최대 20자)
