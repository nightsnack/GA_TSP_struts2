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
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">Quick Example</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form role="form" action="Constant_list.action" method="post">
                  <div class="box-body">
                    <div class="form-group">
                      <label for="exampleInputEmail1">城市数量</label>
                      <input class="form-control" id="exampleInputEmail1" name="cityNum" placeholder="Enter the number of cities!">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">种群大小</label>
                      <input class="form-control" id="exampleInputPassword1" name="scale" placeholder="（大约城市数量的2倍）">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">最大子代数目</label>
                      <input class="form-control" id="exampleInputPassword1" name="maxGen">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">交叉概率</label>
                      <input class="form-control" id="exampleInputPassword1" name="Pc" placeholder="">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">变异概率</label>
                      <input class="form-control" id="exampleInputPassword1" name="Pm" >
                    </div>
                    
                    
                  </div><!-- /.box-body -->

                  <div class="box-footer">
                    <button type="submit" class="btn btn-primary">Submit</button>
                  </div>
                </form>
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