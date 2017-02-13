<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html class="bootstrap-admin-vertical-centered">
    <head>
        <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Bootstrap -->
        <link rel="stylesheet" media="screen" href="${ctx}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" media="screen" href="${ctx}/resources/css/bootstrap-theme.min.css">

        <!-- Bootstrap Admin Theme -->
        <link rel="stylesheet" media="screen" href="${ctx}/resources/css/bootstrap-admin-theme.css">

        <!-- Custom styles -->
        <style type="text/css">
            .alert{
                margin: 0 auto 20px;
            }
        </style>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
           <script type="text/javascript" src="js/html5shiv.js"></script>
           <script type="text/javascript" src="js/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="bootstrap-admin-without-padding">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="alert alert-info">
                        <a class="close" data-dismiss="alert" href="#">&times;</a>
                        Press enter key or click the Submit button
                    </div>
                    <form method="post" action="loginIn" class="bootstrap-admin-login-form" id="form">
                        <h1>Login</h1>
                        <div class="form-group">
                            <input class="form-control" type="text" name="userAccount" id="userAccount" placeholder="用户名">
                        </div>
                        <div class="form-group" >
                            <input class="form-control" type="password" name="bePassword" id="bePassword" placeholder="密码">
                        </div>
                        <div class="form-group" hidden="hidden">
                            <input class="form-control" type="password" name="password" id="password" >
                        </div>
                        <div class="form-group">
                            <label>
                                <input type="checkbox" name="remember_me">
                                Remember me
                            </label>
                        </div>
                        <a class="btn btn-lg btn-primary btn-block" onclick="login()">Sign in</a>
                    </form>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="${ctx}/resources/js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${ctx}/resources/js/md5.min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/bootstrap-jquery/jquery.bootstrap.min.js"></script>
		
        <script type="text/javascript">
            $(function() {
                // Setting focus
                $('input[name="userAccount"]').focus();

                // Setting width of the alert box
                var alert = $('.alert');
                var formWidth = $('.bootstrap-admin-login-form').innerWidth();
                var alertPadding = parseInt($('.alert').css('padding'));

                if (isNaN(alertPadding)) {
                    alertPadding = parseInt($(alert).css('padding-left'));
                }

                $('.alert').width(formWidth - 2 * alertPadding);
            });
            var message = "${message}"
            if ("no" == message) {
            	$.messager.alert("警告", "无此用户");
            }else if ("error" == message) {
            	$.messager.alert("警告", "密码错误");
            }
            function login() {
        		var password = hex_md5($('#bePassword').val());
        		$('#password').val(password);
        		$('#form').submit();
        	}
        </script>
    </body>
</html>
