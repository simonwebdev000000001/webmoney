<?php include_once("bd.php"); ?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>БЕСПЛАТНЫЕ уроки для веб програмиста</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">	
</head>
<body>
	
<?php
if (empty($_SESSION['login']) or empty($_SESSION['password'])) {
	exit ("Доступ на эту страницу разрешен только зарегистрированным пользователям. Если вы зарегистрированы, то войдите на сайт под своим логином и паролем<br><a href='index.php'>Главная страница</a>");
}

unset($_SESSION['password']);
unset($_SESSION['login']); 
unset($_SESSION['id']);// уничтожаем переменные в сессиях

exit("<meta http-equiv='Refresh' content='0; URL=index.php'>");
?>

</body>
</html>
