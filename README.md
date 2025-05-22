# introduce modules
### demo-app
내부에 존재하는 각각의 모듈들은 실행 가능한 모듈들입니다.
해당 모듈에는 "inbound adpater(interface 계층)와 usecase(application 계층)"이 존재합니다.
### demo-core
`demo-app`에서 공통적으로 사용하는 코드들이 존재하는 모듈입니다.
- `domain`. 도메인 객체들이 존재합니다.
- `port`. 자세한 기술 구현이 아닌, 인터페이스들이 존재합니다.
- `service`. demo-app 내부 각각의 모듈들이 전체적으로 공통으로 사용하는 비즈니스 로직이 존재합니다.
### demo-infra
자세한 기술 구현 로직들이 존재합니다.
### demo-lib
비즈니스 로직이 포함되어 있지 않은 로직들이 존재합니다.
모든 모듈에서 해당 모듈을 의존하여, 사용할 수 있습니다.

# Recommended Preferences
### Git Hook
```shell
git config core.hookspath .githooks
```
- This setting makes run lint on every commit.