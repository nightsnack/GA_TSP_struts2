<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
    
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>遗传算法求解TSP</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@ include file="/common/header.jsp" %>
   
  </head>
  <!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
  <body class="hold-transition skin-yellow layout-top-nav">
    <div class="wrapper">

      <header class="main-header">
        <nav class="navbar navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <a href="#" class="navbar-brand"><b>Night</b>SNACK</a>
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                <i class="fa fa-bars"></i>
              </button>
            </div>

            
            <!-- Navbar Right Menu -->
          
          </div><!-- /.container-fluid -->
        </nav>
      </header>
      <!-- Full Width Column -->
      <div class="content-wrapper">
        <div class="container">
          <!-- Content Header (Page header) -->
          <section class="content-header">
            <h1>
              遗传算法求解TSP问题
              <small>请输入常量值</small>
            </h1>
            
          </section>

          <!-- Main content -->
          <section class="content">
           <div class="row">
            <!-- left column -->
            <div class="col-md-6">
              <!-- general form elements -->
             <div class="box">
                <div class="box-header with-border">
                  <h3 class="box-title">输入确认</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table class="table table-bordered">
                    <tr>
                      <th style="width: 10px">#</th>
                      <th>变量</th>
                      <th>值</th>
                    </tr>
                    <tr>
                      <td>1.</td>
                      <td>城市数量</td>
                      <td><s:property value="cityNum"/></td>
                    </tr>
                    <tr>
                      <td>2.</td>
                      <td>种群大小</td>
                      <td><s:property value="scale"/></td>
                    </tr>
                    <tr>
                      <td>3.</td>
                      <td>最大子代数量</td>
                      <td><s:property value="maxGen"/></td>
                    </tr>
                    <tr>
                      <td>4.</td>
                      <td>交叉概率</td>
                      <td><s:property value="Pc"/></td>
                    </tr>
                    <tr>
                      <td>5.</td>
                      <td>变异概率</td>
                      <td><s:property value="Pm"/></td>
                    </tr>
                    
                  </table>
                 <a class="btn btn-app" href="Constant_calculate.action?scale=${scale}&cityNum=${cityNum}&maxGen=${maxGen}&Pc=${Pc}&Pm=${Pm}">
                       <i class="fa fa-play"></i> Run
                 </a>
                </div><!-- /.box-body -->
               
              </div><!-- /.box -->
            </div>
           </div>
          </section><!-- /.content -->
        </div><!-- /.container -->
      </div><!-- /.content-wrapper -->
      <footer class="main-footer">
        <div class="container">
          <div class="pull-right hidden-xs">
            <b>Version</b> 0.0.1
          </div>
          <strong>Copyright &copy; 2014-2016 <a href="http://nuitsnack.lofter.com">Night·Snack</a>.</strong> All rights reserved.
        </div><!-- /.container -->
      </footer>
    </div><!-- ./wrapper -->
<%@include file="/common/footer.jsp" %>

  </body>
</html>