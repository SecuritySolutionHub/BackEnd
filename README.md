# BackEnd



## Running locally

* maria DB
  * maria DB를 설치 후, 3306 포트로 연결합니다.
  * maria DB에 soltuionHub DB를 생성합니다.



## Docker

### Execution using dockerfile

#### make docker image

```bash
docker image build -t solution_hub:1.0 .
```



#### execute image

```bash
docker run solution_hub:1.0
```



## API document

API 문서를 확인하기 위해서 서버를 실행 후, 아래의 URL로 접속합니다.

`http://localhost:8080/swagger-ui.html`



## Admin 

[Admin Function]: https://github.com/SecuritySolutionHub/BackEnd/blob/main/docs/AdminFunction.md



## Entity Relation

![엔티티 다이어그램](https://user-images.githubusercontent.com/38449976/120375286-9850ca00-c355-11eb-89e6-0717f501ca9d.jpg)

