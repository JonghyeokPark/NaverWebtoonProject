본 프로젝트는 네이버 대학생 개발 프로젝트 입니다.
==============================================================================
참여 인원 
==============================================================================
성균관대학교 소프트웨어학과
김효진
류수형
박종혁
최남욱

==============================================================================
웹툰 받아오기 DB/SERVER
==============================================================================
서버 접속 : chmod 440 ntoon_key.pem
            ssh -i "ntoon_key.pem" ubuntu@52.88.69.12
node.js 키기 : NaverWebtoonProject/ntoonManager에 들어가서 node app.js치면 댐
DB구조 및 데이터 확인 : http://52.88.69.12/phpmyadmin

==============================================================================
FILE STRUCTURE 
==============================================================================
== PACKAGE ==

finishtoon : 완결 웹툰에 관한 java 파일들을 넣는 패키지 입니다.
mypage : 마이페이지 부분 java 파일을 넣는 패키지 압니다.
store : 스토어 부분 java 파일들을 넣는 패키지 입니다.
webtoon : 현재 연재되는 java 파일들을 넣는 패키지 입니다.


== JAVA FILE == 

FinishFragment : 탭중 finishwebtoon 해당하는 부분


MypageFragment : 탭중 mypage에 해당하는 부분


StoreFragment : 탭중 store에 해당하는 부분


WebtoonFragment : 탭중 webtoon에 해당하는 부분


MainActivity : 기본 틀입니다. 탭(tab)과 상단의 Ntoon부분(ntoonLayout), 그리고 탭으로 인해 변경되는 부분(container)를 가지고 있습니다.

DataBaseHelper : Mypage에 사용되는 SQLite 사용 관련 부분

MypageData : Mypage 리스트에 사용되는 데이터 DAO

MypageListAdapter : Mypage 리스트에 사용되는 리스트 어댑터 


==============================================================================
일일 개발
==============================================================================
15.12.31 - Hyojin
기본 베이직 틀을 짜서 올렸습니다.

15.01.05 - Jonghyeok
MyPAGE 초기버전 부분을 구현하여 올렸습니다.
수정한 파일 : MypageFragment.java, fragment_mypage.xml
새로 만든 파일 : DataBaseHelper.java , MypageData.java, MypageListAdapter.java, list_item_mypage.xml

15.01.05 - Namuk
바보
