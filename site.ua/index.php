<?php include_once("bd.php"); ?>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Вход</title>
		<style type="text/css">
		INPUT {
		background: silver; /* Цвет фона */
		}
		</style>
	</head>
<body>

<?php
if(empty($login) and empty($password)){
print <<<HERE
<table>
Вход:
<br>
<br>
 
      <form action="login.php" method="POST">
      <tr>
      <td>Логин:</td>
      <td><input type="text" name="login" ></td>
      </tr>
	  
      <tr>
      <td>Пароль:</td>
      <td><input type="password" name="password" ></td>
      </tr>
	  
	  <tr>
      <td colspan="2"><input type="submit" value="OK" name="submit" ></td>
      </tr>
      </form>
      </table>
<a href="registration.php">Registration</a>
HERE;
}
else{
echo "Hi, <strong>".$login."</strong> | <a href='exit.php'>Выход</a><br>Content for unlogger user";
}
?>
</body>
</html>