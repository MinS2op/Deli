<%--
  Created by IntelliJ IDEA.
  User: Minseop
  Date: 2022-12-17
  Time: 오후 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>내 정보 수정</title>

    <!--jQuery-->
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous">
    </script>
    <!-- font-awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.css" rel="stylesheet">

    <link rel="stylesheet" href=" /resources/css/member/myPage/memberInfo.css" type="text/css">

</head>
<body>
<main id="memberInfo">

    <div class="container">

        <form id="modifyMemberInfo" action="/myPage/memberInfo/modify" method="post">

            <div class="topBox">
                <div class="topLeftBox">
                    <a href="/myPage"><i class="fa-solid fa-arrow-left"></i></a>
                </div>
                <div class="topRightBox">
                    <button type="button" id="saveButton">저장</button>
                </div>
            </div>

            <div class="titleBox">
                내 정보 수정
            </div>

            <div class="nameBox">
                <input type="text" id="nickName" name="mem_nick" value="${memberInfo.mem_nick}" placeholder="닉네임">
                <label id="nameRemoveLabel">X</label>
            </div>

            <div class="infoBox">
                <div class="infoTitleBox">
                    <span>이메일</span>
                </div>
                <div class="infoInputBox emailBox">
                    <input type="text" id="email" name="acc_email" value="${accountInfo.acc_email}" placeholder="email@deli.me" readonly>
                </div>
            </div>

            <div class="infoBox">
                <div class="infoTitleBox">
                    <span>비밀번호</span>
                </div>
                <div class="infoInputBox passWordBox">
                    <button type="button" id="passWordModifyBtn">변경</button>
                </div>
            </div>


            <div class="infoBox modifyPwBox">
                <div class="infoTitleBox">
                    <span class="pwSpan">현재 비밀번호</span>
                </div>
                <div class="infoInputBox passWordBox">
                    <input type="text" id="oldPassWord" name="oldPassWord">
                </div>
            </div>
            <div class="infoBox modifyPwBox">
                <div class="infoTitleBox">
                    <span class="pwSpan">새 비밀번호</span>
                </div>
                <div class="infoInputBox passWordBox">
                    <input type="text" id="newPassWord" name="newPassWord">
                </div>
            </div>
            <div class="infoBox modifyPwBox">
                <div class="infoTitleBox">
                    <span class="pwSpan">새 비밀번호 확인</span>
                </div>
                <div class="infoInputBox passWordBox">
                    <input type="text" id="confirmPassWord">
                    <button type="button" id="modifyPasswordConfirmBtn">확인</button><br>
                    <span id="pwCheckSpan"></span>
                </div>
            </div>


            <div class="infoBox">
                <div class="infoTitleBox">
                    <span>hidden</span><br>
                    <span>휴대폰번호</span>
                </div>
                <div class="infoInputBox phoneBox">
                    <input type="text" id="oldPhoneNumber" value="${memberInfo.mem_phone}">
                    <input type="text" id="phoneNumber" name="mem_phone" value="${memberInfo.mem_phone}" placeholder="01011112222" readonly>
                    <button type="button" id="reCertificationBtn">재인증</button>
                </div>
            </div>

            <div class="infoBox" id="certificationBox">
                <div class="infoTitleBox">
                    <span id="certificationNumberSpan">인증번호</span>
                </div>
                <div class="infoInputBox">
                    <input type="text" id="certificationNumber">
                    <button type="button" id="certificationConfirmBtn">확인</button>
                </div>
            </div>

            <div class="infoBox">
                <div class="infoTitleBox">
                    <span>회원가입일</span>
                </div>
                <div class="infoInputBox signUpDateBox">
                    <span>${accountInfo.acc_signupdate}</span>
                </div>
            </div>



            <div class="buttonBox">
                <a href="/account/logout">
                    <button type="button">로그아웃</button>
                </a>
                <br><br>
                <a href="/account/withdrawal">
                    <button type="button">회원탈퇴</button>
                </a>
            </div>

        </form>

    </div>









    <script src="/resources/js/member/myPage/memberInfo.js"></script>
</main>
</body>
</html>