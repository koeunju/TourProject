<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="/top" />

<script>
    function checkId(uid){
        let len = uid.length;
        $('#msgId').text('')
            .removeClass('text-danger')
            .removeClass('text-primary');
        if(len<4||len>18){
            $('#msgId').text('아이디는 4자 이상 18자 이내로 가능합니다')
                .addClass('text-danger');
            $('#id').select();
            return;
        }
        $.ajax({
            type:'GET',
            url:'idcheck?id='+uid,
            dataType:'json', //pom.xml에 json관련 라이브러리 등록해야 함
            cache:false,
        }).done(function(res){
            let n =parseInt(res.isUse);
            let cls=''
            if(n>0){
                cls='text-primary';
                $('#idstate').val(1);
            }else{
                cls='text-danger'
                $('#idstate').val("");
            }
            $('#msgId').text(res.idResult).addClass(cls)
        }).fail(function(err){
            alert('error: '+err.status);
        })

    }//checkId()------------------

    function checkEmail(uem){
        let len = uem.length;
        $('#msgEmail').text('')
            .removeClass('text-danger')
            .removeClass('text-primary');
        if(len<4||len>30){
            $('#msgEmail').text('올바르지 않은 이메일 양식입니다.')
                .addClass('text-danger');
            $('#email').select();
            return;
        }
        $.ajax({
            type:'GET',
            url:'emailcheck?email='+uem,
            dataType:'json',
            cache:false,
        }).done(function(res){
            let n =parseInt(res.isEma);
            let cls=''
            if(n>0){
                cls='text-primary';
                $('#emailstate').val(1);
            }else{
                cls='text-danger'
                $('#emailstate').val("");
            }
            $('#msgEmail').text(res.emailResult).addClass(cls)
        }).fail(function(err){
            alert('error: '+err.status);
        })

    }//checkEmail()------------------

    function checkNick(uni){
        let len = uni.length;
        $('#msgNick').text('')
            .removeClass('text-danger')
            .removeClass('text-primary');
        if(len<1||len>40){
            $('#msgNick').text('닉네임은 한 글자 이상 40자 이내로 가능합니다.')
                .addClass('text-danger');
            $('#nick').select();
            return;
        }
        $.ajax({
            type:'GET',
            url:'nickcheck?nick='+uni,
            dataType:'json',
            cache:false,
        }).done(function(res){
            let n =parseInt(res.isNic);
            let cls=''
            if(n>0){
                cls='text-primary';
                $('#nickstate').val(1);
            }else{
                cls='text-danger'
                $('#nickstate').val("");
            }
            $('#msgNick').text(res.nickResult).addClass(cls)
        }).fail(function(err){
            alert('error: '+err.status);
        })

    }//checkNick()------------------


    function checkTel(ute){
        let len = ute.length;
        $('#msgTel').text('')
            .removeClass('text-danger')
            .removeClass('text-primary');
        if(len<10||len>12){
            $('#msgTel').text('휴대폰 번호 11자리를 입력해주세요.')
                .addClass('text-danger');
            $('#tel').select();
            return;
        }
        $.ajax({
            type:'GET',
            url:'telcheck?tel='+ute,
            dataType:'json',
            cache:false,
        }).done(function(res){
            let n =parseInt(res.isTel);
            let cls=''
            if(n>0){
                cls='text-primary';
                $('#telstate').val(1);
            }else{
                cls='text-danger'
                $('#telstate').val("");
            }
            $('#msgTel').text(res.telResult).addClass(cls)
        }).fail(function(err){
            alert('error: '+err.status);
        })

    }//checkTel()------------------


    function check(){
        if(!joinF.idstate.value){
            alert('입력한 아이디는 사용 불가능합니다.');
            f.id.select();
            return;
        }
        if(!joinF.emailstate.value){
            alert('입력한 이메일은 사용 불가능합니다.');
            f.email.select();
            return;
        }
        if(!joinF.nickstate.value){
            alert('입력한 닉네임은 사용 불가능합니다.');
            f.email.select();
            return;
        }
        if(!joinF.telstate.value){
            alert('입력한 전화번호는 사용 불가능합니다.');
            f.email.select();
            return;
        }

        joinF.submit();
    }
</script>

<script src="../js/join.js"></script>


<div class="in" style="display: inline-block; margin: 0 auto;">
    <div class="card align-middle"
         style="width: 50rem; border-radius: 20px;">
        <div class="card-title" style="margin-top: 30px;">
            <h2 class="card-title text-center" style="color: #113366;">회원가입</h2>
        </div>
        <div class="card-body">
            <form name="joinF" id="joinF" action="join" method="POST">
                <h5 class="form-signin-heading">양식에 맞춰 입력해 주세요</h5>
                <label for="inputName" class="sr-only">*닉네임</label>
                <input type="hidden" name="nickstate" id="nickstate">
                <input type="text" name="nick" id="nick" placeholder="NickName" class="form-control" required autofocus onchange="checkNick(this.value)">
                <div id="msgNick"></div>
                <!--닉네임 사용 가능 여부-->
                <span class="text-danger">
				 	<form:errors path="nick"/>
				 </span>
                <br><label for="inputId" class="sr-only">*아이디</label>
                <input type="hidden" name="idstate" id="idstate">
                <input type="text" name="id" id="id"  placeholder="User ID" class="form-control" required autofocus onchange="checkId(this.value)">
                <div id="msgId"></div>
                <!--아이디 사용 가능 여부-->
                <span class="text-danger">
				 	<form:errors path="id"/>
				 </span>
                <br> <label for="inputPwd" class="sr-only">*비밀번호</label> <input
                    type="password" name="pwd" id="pwd" class="form-control"
                    placeholder="Password" required autofocus><br> <label
                    for="inputCheckPwd" class="sr-only">*비밀번호확인</label> <input
                    type="password" name="pwd2" id="pwd2" class="form-control"
                    placeholder="RE Password" required autofocus><br>
                <label for="inputTel" class="sr-only">*연락처</label>
                <input type="hidden" name="telstate" id="telstate">
                <input type="text" name="tel" id="tel" maxlength="11" class="form-control"
                       placeholder="Tel" onchange="checkTel(this.value)">
                <div id="msgTel"></div>
                <!--연락처 사용 가능 여부-->
                <span class="text-danger">
				 	<form:errors path="tel"/>
				 </span>
                <BR><label for="inputEmil" class="sr-only">*이메일</label>
                <input type="hidden" name="emailstate" id="emailstate">
                <input type="text" name="email" id="email" placeholder="Email" class="form-control" onchange="checkEmail(this.value)">
                <div id="msgEmail"></div>
                <!--이메일 사용 가능 여부-->
                <span class="text-danger">
				 	<form:errors path="email"/>
				 </span>

                <div>
                    <button type="button" class="btn btn-success" onclick="check()">회원가입</button>
                    <button type="reset" class="btn btn-warning">다시쓰기</button>
                </div>
            </form>

        </div>
    </div>
</div>



<c:import url="/foot" />
