[tableDesc.xlsx](https://github.com/mbk1991/tableDescToSQL/files/13726714/tableDesc.xlsx)
								
![image](https://github.com/mbk1991/tableDescToSQL/assets/99261591/65c81fcc-74fb-4e74-802a-17d5d9e329be)

![image](https://github.com/mbk1991/tableDescToSQL/assets/99261591/6dff67ec-7452-4b90-b236-ffecef0c5bcd)


![image](https://github.com/mbk1991/tableDescToSQL/assets/99261591/10f0ff4d-b88e-4d6e-ba81-d86dc8174d50)



1.개요 <br>
위와 같은 포맷의 테이블 정의서를 오라클 DDL로 변경하여 콘솔 및 파일에 출력하는 java 프로그램이다.
테스트DB가 날아간 관계로 산출물 Excel에서 테이블을 뽑아내기 위해 작성하였다.


2.사용방법 <br>
 1)프로젝트 경로 내 csv 파일 위치 (엑셀 다른이름으로 CSV utf-8 파일 저장) <br>
 2)경로 및 확인<br>
 3)main메서드 실행<br>
 4)console 창에 출력되는 테이블 생성 DDL 확인 <br>

3.메모
- csv 경로 및 파일명을 명령행인자로 입력받아 콘솔에서 사용할 수 있는 프로그램으로 만들기<br> 
- 현재는 test버전으로, DDL 콘솔 출력 기능만 되고 파일 출력 등 보완 필요함<br>
- 코드 리펙토링 필요<br>
